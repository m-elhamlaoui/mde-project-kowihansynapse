package KowihanGenerator;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class ManualGenerator {
    public static void main(String[] args) {
        try {
            System.out.println("Starting Kowihan Generator...");
            
            
            registerMetamodelGlobally();
            

            String modelPath = null;
            String outputPath = null;
            
            for (int i = 0; i < args.length; i++) {
                if ("--model".equals(args[i]) && i + 1 < args.length) {
                    modelPath = args[i + 1];
                    i++;
                } else if ("--output".equals(args[i]) && i + 1 < args.length) {
                    outputPath = args[i + 1];
                    i++;
                }
                // Ignore other arguments (--template, --metamodel) as they're not needed
            }
            
            // Use provided arguments or defaults
            if (modelPath == null) {
                modelPath = "test-models/test-model.xmi";
                System.out.println("  No --model argument provided, using default: " + modelPath);
            }
            if (outputPath == null) {
                outputPath = "output";
                System.out.println("  No --output argument provided, using default: " + outputPath);
            }
            
            URI modelURI = URI.createFileURI(new File(modelPath).getAbsolutePath());
            File targetFolder = new File(outputPath);
            List<String> arguments = new ArrayList<String>();
            
            System.out.println(" Model: " + modelURI);
            System.out.println(" Target: " + targetFolder.getAbsolutePath());
            
            
            File modelFile = new File(modelPath);
            if (!modelFile.exists()) {
                throw new RuntimeException(" Model file not found: " + modelFile.getAbsolutePath());
            }
            
            
            targetFolder.mkdirs();
            
            
            Main generator = new Main(modelURI, targetFolder, arguments);
            
          
            generator.doGenerate(new BasicMonitor());
            
            System.out.println(" SUCCESS: Generation completed!");
            System.out.println(" Output directory: " + targetFolder.getAbsolutePath());
            
        } catch (Exception e) {
            System.err.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private static void registerMetamodelGlobally() {
        try {
            System.out.println(" Loading metamodel from .ecore file...");
            
            // Register resource factories GLOBALLY first
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
            
            ResourceSet resourceSet = new ResourceSetImpl();
            
            // Chemin vers le .ecore - ajustez si nécessaire
            String[] possiblePaths = {
                "../KowihanMetamodel/APIMetamodel.ecore",
                "platform:/resource/KowihanMetamodel/APIMetamodel.ecore",
                "/home/wissalelalouan/eclipse-workspace/KowihanMetamodel/APIMetamodel.ecore"
            };
            
            Resource ecoreResource = null;
            for (String path : possiblePaths) {
                try {
                    URI ecoreURI = path.startsWith("platform:") ? 
                        URI.createURI(path) : URI.createFileURI(path);
                    ecoreResource = resourceSet.getResource(ecoreURI, true);
                    System.out.println(" Found .ecore at: " + path);
                    break;
                } catch (Exception e) {
                    System.out.println(" Not found: " + path);
                }
            }
            
            if (ecoreResource == null || ecoreResource.getContents().isEmpty()) {
                throw new RuntimeException(" Cannot load APIMetamodel.ecore from any path");
            }
            
            EPackage metamodelPackage = (EPackage) ecoreResource.getContents().get(0);
            
            //  CRITICAL: Register in the GLOBAL registry, not just local ResourceSet
            EPackage.Registry.INSTANCE.put(metamodelPackage.getNsURI(), metamodelPackage);
            
            // Also register with the common URI
            EPackage.Registry.INSTANCE.put("http://www.kowihan.com/APIMetamodel", metamodelPackage);
            
            System.out.println(" Metamodel registered GLOBALLY: " + metamodelPackage.getName());
            System.out.println("   URI: " + metamodelPackage.getNsURI());
            
        } catch (Exception e) {
            System.err.println(" Failed to register metamodel: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Cannot continue without metamodel", e);
        }
    }
}
