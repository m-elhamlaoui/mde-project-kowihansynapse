/**
 */
package APIMetamodel.impl;

import APIMetamodel.APIFeatures;
import APIMetamodel.APIMetamodelFactory;
import APIMetamodel.APIMetamodelPackage;
import APIMetamodel.ApplicationModel;
import APIMetamodel.Attribute;
import APIMetamodel.AuthenticationConfig;
import APIMetamodel.DatabaseConfig;
import APIMetamodel.Entity;
import APIMetamodel.Index;
import APIMetamodel.Interaction;
import APIMetamodel.Message;
import APIMetamodel.Operation;
import APIMetamodel.Parameter;
import APIMetamodel.Participant;
import APIMetamodel.Relationship;

import APIMetamodel.util.APIMetamodelValidator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class APIMetamodelPackageImpl extends EPackageImpl implements APIMetamodelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass applicationModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relationshipEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass indexEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interactionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass participantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass messageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass databaseConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass authenticationConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass apiFeaturesEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see APIMetamodel.APIMetamodelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private APIMetamodelPackageImpl() {
		super(eNS_URI, APIMetamodelFactory.eINSTANCE);
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link APIMetamodelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static APIMetamodelPackage init() {
		if (isInited) return (APIMetamodelPackage)EPackage.Registry.INSTANCE.getEPackage(APIMetamodelPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredAPIMetamodelPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		APIMetamodelPackageImpl theAPIMetamodelPackage = registeredAPIMetamodelPackage instanceof APIMetamodelPackageImpl ? (APIMetamodelPackageImpl)registeredAPIMetamodelPackage : new APIMetamodelPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theAPIMetamodelPackage.createPackageContents();

		// Initialize created meta-data
		theAPIMetamodelPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theAPIMetamodelPackage,
			 new EValidator.Descriptor() {
				 @Override
				 public EValidator getEValidator() {
					 return APIMetamodelValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theAPIMetamodelPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(APIMetamodelPackage.eNS_URI, theAPIMetamodelPackage);
		return theAPIMetamodelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getApplicationModel() {
		return applicationModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getApplicationModel_ProjectName() {
		return (EAttribute)applicationModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getApplicationModel_Framework() {
		return (EAttribute)applicationModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getApplicationModel_PythonVersion() {
		return (EAttribute)applicationModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getApplicationModel_Description() {
		return (EAttribute)applicationModelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getApplicationModel_Entities() {
		return (EReference)applicationModelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getApplicationModel_Interactions() {
		return (EReference)applicationModelEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getApplicationModel_Database() {
		return (EReference)applicationModelEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getApplicationModel_Authentication() {
		return (EReference)applicationModelEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getApplicationModel_ApiFeatures() {
		return (EReference)applicationModelEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEntity() {
		return entityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEntity_Name() {
		return (EAttribute)entityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEntity_Description() {
		return (EAttribute)entityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEntity_IsAbstract() {
		return (EAttribute)entityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEntity_TableName() {
		return (EAttribute)entityEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEntity_Attributes() {
		return (EReference)entityEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEntity_Relationships() {
		return (EReference)entityEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEntity_Operations() {
		return (EReference)entityEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEntity_Indexes() {
		return (EReference)entityEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEntity_ParentEntity() {
		return (EReference)entityEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAttribute() {
		return attributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttribute_Name() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttribute_Type() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttribute_IsPrimaryKey() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttribute_IsNullable() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttribute_IsUnique() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttribute_DefaultValue() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttribute_MaxLength() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttribute_MinValue() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttribute_MaxValue() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAttribute_HelpText() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRelationship() {
		return relationshipEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRelationship_Name() {
		return (EAttribute)relationshipEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRelationship_Type() {
		return (EAttribute)relationshipEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRelationship_TargetEntity() {
		return (EAttribute)relationshipEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRelationship_RelatedName() {
		return (EAttribute)relationshipEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRelationship_OnDelete() {
		return (EAttribute)relationshipEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOperation() {
		return operationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOperation_Name() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOperation_ReturnType() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOperation_Visibility() {
		return (EAttribute)operationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperation_Parameters() {
		return (EReference)operationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getParameter() {
		return parameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameter_Name() {
		return (EAttribute)parameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameter_Type() {
		return (EAttribute)parameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIndex() {
		return indexEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIndex_Name() {
		return (EAttribute)indexEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIndex_Fields() {
		return (EAttribute)indexEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIndex_IsUnique() {
		return (EAttribute)indexEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInteraction() {
		return interactionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInteraction_Name() {
		return (EAttribute)interactionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInteraction_UseCaseDescription() {
		return (EAttribute)interactionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInteraction_HttpMethod() {
		return (EAttribute)interactionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInteraction_Endpoint() {
		return (EAttribute)interactionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInteraction_Participants() {
		return (EReference)interactionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInteraction_Messages() {
		return (EReference)interactionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getParticipant() {
		return participantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParticipant_Name() {
		return (EAttribute)participantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParticipant_ActorType() {
		return (EAttribute)participantEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParticipant_EntityName() {
		return (EAttribute)participantEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMessage() {
		return messageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMessage_SequenceNumber() {
		return (EAttribute)messageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMessage_Operation() {
		return (EAttribute)messageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMessage_MessageType() {
		return (EAttribute)messageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMessage_FromParticipant() {
		return (EReference)messageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMessage_ToParticipant() {
		return (EReference)messageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDatabaseConfig() {
		return databaseConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDatabaseConfig_Type() {
		return (EAttribute)databaseConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDatabaseConfig_Host() {
		return (EAttribute)databaseConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDatabaseConfig_Port() {
		return (EAttribute)databaseConfigEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDatabaseConfig_Name() {
		return (EAttribute)databaseConfigEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAuthenticationConfig() {
		return authenticationConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAuthenticationConfig_Enabled() {
		return (EAttribute)authenticationConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAuthenticationConfig_Method() {
		return (EAttribute)authenticationConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAuthenticationConfig_TokenExpiryMinutes() {
		return (EAttribute)authenticationConfigEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAPIFeatures() {
		return apiFeaturesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAPIFeatures_Pagination() {
		return (EAttribute)apiFeaturesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAPIFeatures_Filtering() {
		return (EAttribute)apiFeaturesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAPIFeatures_Swagger() {
		return (EAttribute)apiFeaturesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAPIFeatures_CorsEnabled() {
		return (EAttribute)apiFeaturesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public APIMetamodelFactory getAPIMetamodelFactory() {
		return (APIMetamodelFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		applicationModelEClass = createEClass(APPLICATION_MODEL);
		createEAttribute(applicationModelEClass, APPLICATION_MODEL__PROJECT_NAME);
		createEAttribute(applicationModelEClass, APPLICATION_MODEL__FRAMEWORK);
		createEAttribute(applicationModelEClass, APPLICATION_MODEL__PYTHON_VERSION);
		createEAttribute(applicationModelEClass, APPLICATION_MODEL__DESCRIPTION);
		createEReference(applicationModelEClass, APPLICATION_MODEL__ENTITIES);
		createEReference(applicationModelEClass, APPLICATION_MODEL__INTERACTIONS);
		createEReference(applicationModelEClass, APPLICATION_MODEL__DATABASE);
		createEReference(applicationModelEClass, APPLICATION_MODEL__AUTHENTICATION);
		createEReference(applicationModelEClass, APPLICATION_MODEL__API_FEATURES);

		entityEClass = createEClass(ENTITY);
		createEAttribute(entityEClass, ENTITY__NAME);
		createEAttribute(entityEClass, ENTITY__DESCRIPTION);
		createEAttribute(entityEClass, ENTITY__IS_ABSTRACT);
		createEAttribute(entityEClass, ENTITY__TABLE_NAME);
		createEReference(entityEClass, ENTITY__ATTRIBUTES);
		createEReference(entityEClass, ENTITY__RELATIONSHIPS);
		createEReference(entityEClass, ENTITY__OPERATIONS);
		createEReference(entityEClass, ENTITY__INDEXES);
		createEReference(entityEClass, ENTITY__PARENT_ENTITY);

		attributeEClass = createEClass(ATTRIBUTE);
		createEAttribute(attributeEClass, ATTRIBUTE__NAME);
		createEAttribute(attributeEClass, ATTRIBUTE__TYPE);
		createEAttribute(attributeEClass, ATTRIBUTE__IS_PRIMARY_KEY);
		createEAttribute(attributeEClass, ATTRIBUTE__IS_NULLABLE);
		createEAttribute(attributeEClass, ATTRIBUTE__IS_UNIQUE);
		createEAttribute(attributeEClass, ATTRIBUTE__DEFAULT_VALUE);
		createEAttribute(attributeEClass, ATTRIBUTE__MAX_LENGTH);
		createEAttribute(attributeEClass, ATTRIBUTE__MIN_VALUE);
		createEAttribute(attributeEClass, ATTRIBUTE__MAX_VALUE);
		createEAttribute(attributeEClass, ATTRIBUTE__HELP_TEXT);

		relationshipEClass = createEClass(RELATIONSHIP);
		createEAttribute(relationshipEClass, RELATIONSHIP__NAME);
		createEAttribute(relationshipEClass, RELATIONSHIP__TYPE);
		createEAttribute(relationshipEClass, RELATIONSHIP__TARGET_ENTITY);
		createEAttribute(relationshipEClass, RELATIONSHIP__RELATED_NAME);
		createEAttribute(relationshipEClass, RELATIONSHIP__ON_DELETE);

		operationEClass = createEClass(OPERATION);
		createEAttribute(operationEClass, OPERATION__NAME);
		createEAttribute(operationEClass, OPERATION__RETURN_TYPE);
		createEAttribute(operationEClass, OPERATION__VISIBILITY);
		createEReference(operationEClass, OPERATION__PARAMETERS);

		parameterEClass = createEClass(PARAMETER);
		createEAttribute(parameterEClass, PARAMETER__NAME);
		createEAttribute(parameterEClass, PARAMETER__TYPE);

		indexEClass = createEClass(INDEX);
		createEAttribute(indexEClass, INDEX__NAME);
		createEAttribute(indexEClass, INDEX__FIELDS);
		createEAttribute(indexEClass, INDEX__IS_UNIQUE);

		interactionEClass = createEClass(INTERACTION);
		createEAttribute(interactionEClass, INTERACTION__NAME);
		createEAttribute(interactionEClass, INTERACTION__USE_CASE_DESCRIPTION);
		createEAttribute(interactionEClass, INTERACTION__HTTP_METHOD);
		createEAttribute(interactionEClass, INTERACTION__ENDPOINT);
		createEReference(interactionEClass, INTERACTION__PARTICIPANTS);
		createEReference(interactionEClass, INTERACTION__MESSAGES);

		participantEClass = createEClass(PARTICIPANT);
		createEAttribute(participantEClass, PARTICIPANT__NAME);
		createEAttribute(participantEClass, PARTICIPANT__ACTOR_TYPE);
		createEAttribute(participantEClass, PARTICIPANT__ENTITY_NAME);

		messageEClass = createEClass(MESSAGE);
		createEAttribute(messageEClass, MESSAGE__SEQUENCE_NUMBER);
		createEAttribute(messageEClass, MESSAGE__OPERATION);
		createEAttribute(messageEClass, MESSAGE__MESSAGE_TYPE);
		createEReference(messageEClass, MESSAGE__FROM_PARTICIPANT);
		createEReference(messageEClass, MESSAGE__TO_PARTICIPANT);

		databaseConfigEClass = createEClass(DATABASE_CONFIG);
		createEAttribute(databaseConfigEClass, DATABASE_CONFIG__TYPE);
		createEAttribute(databaseConfigEClass, DATABASE_CONFIG__HOST);
		createEAttribute(databaseConfigEClass, DATABASE_CONFIG__PORT);
		createEAttribute(databaseConfigEClass, DATABASE_CONFIG__NAME);

		authenticationConfigEClass = createEClass(AUTHENTICATION_CONFIG);
		createEAttribute(authenticationConfigEClass, AUTHENTICATION_CONFIG__ENABLED);
		createEAttribute(authenticationConfigEClass, AUTHENTICATION_CONFIG__METHOD);
		createEAttribute(authenticationConfigEClass, AUTHENTICATION_CONFIG__TOKEN_EXPIRY_MINUTES);

		apiFeaturesEClass = createEClass(API_FEATURES);
		createEAttribute(apiFeaturesEClass, API_FEATURES__PAGINATION);
		createEAttribute(apiFeaturesEClass, API_FEATURES__FILTERING);
		createEAttribute(apiFeaturesEClass, API_FEATURES__SWAGGER);
		createEAttribute(apiFeaturesEClass, API_FEATURES__CORS_ENABLED);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(applicationModelEClass, ApplicationModel.class, "ApplicationModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getApplicationModel_ProjectName(), ecorePackage.getEString(), "projectName", null, 0, 1, ApplicationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getApplicationModel_Framework(), ecorePackage.getEString(), "framework", null, 0, 1, ApplicationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getApplicationModel_PythonVersion(), ecorePackage.getEString(), "pythonVersion", null, 0, 1, ApplicationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getApplicationModel_Description(), ecorePackage.getEString(), "description", null, 0, 1, ApplicationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationModel_Entities(), this.getEntity(), null, "entities", null, 0, -1, ApplicationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationModel_Interactions(), this.getInteraction(), null, "interactions", null, 0, -1, ApplicationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationModel_Database(), this.getDatabaseConfig(), null, "database", null, 0, 1, ApplicationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationModel_Authentication(), this.getAuthenticationConfig(), null, "authentication", null, 0, 1, ApplicationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getApplicationModel_ApiFeatures(), this.getAPIFeatures(), null, "apiFeatures", null, 0, 1, ApplicationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(entityEClass, Entity.class, "Entity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEntity_Name(), ecorePackage.getEString(), "name", null, 0, 1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntity_Description(), ecorePackage.getEString(), "description", null, 0, 1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntity_IsAbstract(), ecorePackage.getEBoolean(), "isAbstract", null, 0, 1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEntity_TableName(), ecorePackage.getEString(), "tableName", null, 0, 1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntity_Attributes(), this.getAttribute(), null, "attributes", null, 0, -1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntity_Relationships(), this.getRelationship(), null, "relationships", null, 0, -1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntity_Operations(), this.getOperation(), null, "operations", null, 0, -1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntity_Indexes(), this.getIndex(), null, "indexes", null, 0, -1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEntity_ParentEntity(), this.getEntity(), null, "parentEntity", null, 0, 1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeEClass, Attribute.class, "Attribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttribute_Name(), ecorePackage.getEString(), "name", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Type(), ecorePackage.getEString(), "type", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_IsPrimaryKey(), ecorePackage.getEBoolean(), "isPrimaryKey", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_IsNullable(), ecorePackage.getEBoolean(), "isNullable", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_IsUnique(), ecorePackage.getEBoolean(), "isUnique", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_DefaultValue(), ecorePackage.getEString(), "defaultValue", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_MaxLength(), ecorePackage.getEInt(), "maxLength", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_MinValue(), ecorePackage.getEInt(), "minValue", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_MaxValue(), ecorePackage.getEInt(), "maxValue", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_HelpText(), ecorePackage.getEString(), "helpText", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(relationshipEClass, Relationship.class, "Relationship", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRelationship_Name(), ecorePackage.getEString(), "name", null, 0, 1, Relationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelationship_Type(), ecorePackage.getEString(), "type", null, 0, 1, Relationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelationship_TargetEntity(), ecorePackage.getEString(), "targetEntity", null, 0, 1, Relationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelationship_RelatedName(), ecorePackage.getEString(), "relatedName", null, 0, 1, Relationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRelationship_OnDelete(), ecorePackage.getEString(), "onDelete", null, 0, 1, Relationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationEClass, Operation.class, "Operation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOperation_Name(), ecorePackage.getEString(), "name", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_ReturnType(), ecorePackage.getEString(), "returnType", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperation_Visibility(), ecorePackage.getEString(), "visibility", null, 0, 1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperation_Parameters(), this.getParameter(), null, "parameters", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParameter_Name(), ecorePackage.getEString(), "name", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameter_Type(), ecorePackage.getEString(), "type", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(indexEClass, Index.class, "Index", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIndex_Name(), ecorePackage.getEString(), "name", null, 0, 1, Index.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIndex_Fields(), ecorePackage.getEString(), "fields", null, 0, -1, Index.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIndex_IsUnique(), ecorePackage.getEBoolean(), "isUnique", null, 0, 1, Index.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(interactionEClass, Interaction.class, "Interaction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInteraction_Name(), ecorePackage.getEString(), "name", null, 0, 1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInteraction_UseCaseDescription(), ecorePackage.getEString(), "useCaseDescription", null, 0, 1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInteraction_HttpMethod(), ecorePackage.getEString(), "httpMethod", null, 0, 1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInteraction_Endpoint(), ecorePackage.getEString(), "endpoint", null, 0, 1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInteraction_Participants(), this.getParticipant(), null, "participants", null, 0, -1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInteraction_Messages(), this.getMessage(), null, "messages", null, 0, -1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(participantEClass, Participant.class, "Participant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParticipant_Name(), ecorePackage.getEString(), "name", null, 0, 1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParticipant_ActorType(), ecorePackage.getEString(), "actorType", null, 0, 1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParticipant_EntityName(), ecorePackage.getEString(), "entityName", null, 0, 1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(messageEClass, Message.class, "Message", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMessage_SequenceNumber(), ecorePackage.getEInt(), "sequenceNumber", null, 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMessage_Operation(), ecorePackage.getEString(), "operation", null, 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMessage_MessageType(), ecorePackage.getEString(), "messageType", null, 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMessage_FromParticipant(), this.getParticipant(), null, "fromParticipant", null, 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMessage_ToParticipant(), this.getParticipant(), null, "toParticipant", null, 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(databaseConfigEClass, DatabaseConfig.class, "DatabaseConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDatabaseConfig_Type(), ecorePackage.getEString(), "type", null, 0, 1, DatabaseConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDatabaseConfig_Host(), ecorePackage.getEString(), "host", null, 0, 1, DatabaseConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDatabaseConfig_Port(), ecorePackage.getEInt(), "port", null, 0, 1, DatabaseConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDatabaseConfig_Name(), ecorePackage.getEString(), "name", null, 0, 1, DatabaseConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(authenticationConfigEClass, AuthenticationConfig.class, "AuthenticationConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAuthenticationConfig_Enabled(), ecorePackage.getEBoolean(), "enabled", null, 0, 1, AuthenticationConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAuthenticationConfig_Method(), ecorePackage.getEString(), "method", null, 0, 1, AuthenticationConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAuthenticationConfig_TokenExpiryMinutes(), ecorePackage.getEInt(), "tokenExpiryMinutes", null, 0, 1, AuthenticationConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(apiFeaturesEClass, APIFeatures.class, "APIFeatures", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAPIFeatures_Pagination(), ecorePackage.getEBoolean(), "pagination", null, 0, 1, APIFeatures.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAPIFeatures_Filtering(), ecorePackage.getEBoolean(), "filtering", null, 0, 1, APIFeatures.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAPIFeatures_Swagger(), ecorePackage.getEBoolean(), "swagger", null, 0, 1, APIFeatures.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAPIFeatures_CorsEnabled(), ecorePackage.getEBoolean(), "corsEnabled", null, 0, 1, APIFeatures.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore/OCL
		createOCLAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot
		createPivotAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore/OCL</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createOCLAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore/OCL";
		addAnnotation
		  (applicationModelEClass,
		   source,
		   new String[] {
			   "invariants", null
		   });
		addAnnotation
		  (entityEClass,
		   source,
		   new String[] {
			   "invariants", null
		   });
		addAnnotation
		  (attributeEClass,
		   source,
		   new String[] {
			   "invariants", null
		   });
		addAnnotation
		  (relationshipEClass,
		   source,
		   new String[] {
			   "invariants", null
		   });
		addAnnotation
		  (operationEClass,
		   source,
		   new String[] {
			   "invariants", null
		   });
		addAnnotation
		  (parameterEClass,
		   source,
		   new String[] {
			   "invariants", null
		   });
		addAnnotation
		  (indexEClass,
		   source,
		   new String[] {
			   "invariants", null
		   });
		addAnnotation
		  (interactionEClass,
		   source,
		   new String[] {
			   "invariants", null
		   });
		addAnnotation
		  (participantEClass,
		   source,
		   new String[] {
			   "invariants", null
		   });
		addAnnotation
		  (messageEClass,
		   source,
		   new String[] {
			   "invariants", null
		   });
		addAnnotation
		  (databaseConfigEClass,
		   source,
		   new String[] {
			   "invariants", null
		   });
		addAnnotation
		  (authenticationConfigEClass,
		   source,
		   new String[] {
			   "invariants", null
		   });
		addAnnotation
		  (apiFeaturesEClass,
		   source,
		   new String[] {
			   "invariants", null
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";
		addAnnotation
		  (applicationModelEClass,
		   source,
		   new String[] {
			   "constraints", "ValidPythonVersion ValidProjectName HasEntities ValidFramework"
		   });
		addAnnotation
		  (entityEClass,
		   source,
		   new String[] {
			   "constraints", "ValidEntityName HasAttributes NoDuplicateAttributes ValidTableName"
		   });
		addAnnotation
		  (attributeEClass,
		   source,
		   new String[] {
			   "constraints", "ValidAttributeName PrimaryKeyNotNullable ValidTypeConstraints ValidDataType"
		   });
		addAnnotation
		  (relationshipEClass,
		   source,
		   new String[] {
			   "constraints", "ValidRelationshipName ValidTarget ValidCascadeBehavior ValidRelationType"
		   });
		addAnnotation
		  (operationEClass,
		   source,
		   new String[] {
			   "constraints", "ValidOperationName ValidVisibility ValidReturnType"
		   });
		addAnnotation
		  (parameterEClass,
		   source,
		   new String[] {
			   "constraints", "ValidParameterName ValidParameterType"
		   });
		addAnnotation
		  (indexEClass,
		   source,
		   new String[] {
			   "constraints", "ValidIndexName HasFields ValidIndexConfiguration"
		   });
		addAnnotation
		  (interactionEClass,
		   source,
		   new String[] {
			   "constraints", "ValidInteractionName ValidHTTPMethod HasParticipants ValidEndpoint"
		   });
		addAnnotation
		  (participantEClass,
		   source,
		   new String[] {
			   "constraints", "ValidParticipantName ValidActorType ValidEntityReference"
		   });
		addAnnotation
		  (messageEClass,
		   source,
		   new String[] {
			   "constraints", "ValidSequenceNumber ValidMessageType ValidParticipants ValidOperation"
		   });
		addAnnotation
		  (databaseConfigEClass,
		   source,
		   new String[] {
			   "constraints", "ValidDatabaseType ValidPort ValidDatabaseName"
		   });
		addAnnotation
		  (authenticationConfigEClass,
		   source,
		   new String[] {
			   "constraints", "ValidAuthMethod ValidTokenExpiry"
		   });
		addAnnotation
		  (apiFeaturesEClass,
		   source,
		   new String[] {
			   "constraints", "ValidFeatureCombination"
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createPivotAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot";
		addAnnotation
		  (applicationModelEClass,
		   source,
		   new String[] {
			   "ValidPythonVersion", "self.pythonVersion.matches(\'3\\.[7-9]|3\\.1[0-2]\')",
			   "ValidProjectName", "not self.projectName.oclIsUndefined() and self.projectName.size() >= 3 and self.projectName.matches(\'[a-zA-Z_][a-zA-Z0-9_]*\')",
			   "HasEntities", "self.entities->size() >= 1",
			   "ValidFramework", "Set{\'DJANGO\', \'FLASK\', \'FASTAPI\'}->includes(self.framework)"
		   });
		addAnnotation
		  (entityEClass,
		   source,
		   new String[] {
			   "ValidEntityName", "self.name.matches(\'[A-Z][a-zA-Z0-9_]*\')",
			   "HasAttributes", "self.attributes->size() >= 1",
			   "NoDuplicateAttributes", "self.attributes->forAll(a1, a2 | a1 <> a2 implies a1.name <> a2.name)",
			   "ValidTableName", "self.tableName.matches(\'[a-z][a-z0-9_]*\')"
		   });
		addAnnotation
		  (attributeEClass,
		   source,
		   new String[] {
			   "ValidAttributeName", "self.name.matches(\'[a-z][a-zA-Z0-9_]*\')",
			   "PrimaryKeyNotNullable", "self.isPrimaryKey implies not self.isNullable",
			   "ValidTypeConstraints", "(self.type = \'STRING\' and self.maxLength > 0) implies self.maxLength <= 5000",
			   "ValidDataType", "Set{\'STRING\', \'TEXT\', \'INTEGER\', \'FLOAT\', \'DECIMAL\', \'BOOLEAN\', \'DATE\', \'DATETIME\', \'TIME\', \'UUID\', \'EMAIL\', \'URL\', \'JSON\'}->includes(self.type)"
		   });
		addAnnotation
		  (relationshipEClass,
		   source,
		   new String[] {
			   "ValidRelationshipName", "self.name.matches(\'[a-z][a-zA-Z0-9_]*\')",
			   "ValidTarget", "not self.targetEntity.oclIsUndefined() and self.targetEntity.size() > 0",
			   "ValidCascadeBehavior", "Set{\'CASCADE\', \'SET_NULL\', \'PROTECT\'}->includes(self.onDelete)",
			   "ValidRelationType", "Set{\'ONE_TO_ONE\', \'ONE_TO_MANY\', \'MANY_TO_ONE\', \'MANY_TO_MANY\'}->includes(self.type)"
		   });
		addAnnotation
		  (operationEClass,
		   source,
		   new String[] {
			   "ValidOperationName", "self.name.matches(\'[a-z][a-zA-Z0-9_]*\')",
			   "ValidVisibility", "Set{\'PUBLIC\', \'PRIVATE\', \'PROTECTED\'}->includes(self.visibility)",
			   "ValidReturnType", "not self.returnType.oclIsUndefined() and self.returnType.size() > 0"
		   });
		addAnnotation
		  (parameterEClass,
		   source,
		   new String[] {
			   "ValidParameterName", "self.name.matches(\'[a-z][a-zA-Z0-9_]*\')",
			   "ValidParameterType", "not self.type.oclIsUndefined() and self.type.size() > 0"
		   });
		addAnnotation
		  (indexEClass,
		   source,
		   new String[] {
			   "ValidIndexName", "self.name.matches(\'[a-z][a-z0-9_]*\')",
			   "HasFields", "self.fields->size() >= 1",
			   "ValidIndexConfiguration", "self.fields->forAll(f | not f.oclIsUndefined() and f.size() > 0)"
		   });
		addAnnotation
		  (interactionEClass,
		   source,
		   new String[] {
			   "ValidInteractionName", "self.name.matches(\'[A-Z][a-zA-Z0-9_]*\')",
			   "ValidHTTPMethod", "Set{\'GET\', \'POST\', \'PUT\', \'PATCH\', \'DELETE\'}->includes(self.httpMethod)",
			   "HasParticipants", "self.participants->size() >= 2",
			   "ValidEndpoint", "self.endpoint.startsWith(\'/\') and self.endpoint.matches(\'/[a-zA-Z0-9_/-]*\')"
		   });
		addAnnotation
		  (participantEClass,
		   source,
		   new String[] {
			   "ValidParticipantName", "self.name.matches(\'[A-Z][a-zA-Z0-9_]*\')",
			   "ValidActorType", "Set{\'ACTOR\', \'ENTITY\', \'SYSTEM\', \'EXTERNAL\'}->includes(self.actorType)",
			   "ValidEntityReference", "self.actorType = \'ENTITY\' implies (not self.entityName.oclIsUndefined() and self.entityName.size() > 0)"
		   });
		addAnnotation
		  (messageEClass,
		   source,
		   new String[] {
			   "ValidSequenceNumber", "self.sequenceNumber > 0",
			   "ValidMessageType", "Set{\'SYNCHRONOUS\', \'ASYNCHRONOUS\', \'REPLY\', \'CREATE\'}->includes(self.messageType)",
			   "ValidParticipants", "self.fromParticipant <> self.toParticipant",
			   "ValidOperation", "not self.operation.oclIsUndefined() and self.operation.size() > 0"
		   });
		addAnnotation
		  (databaseConfigEClass,
		   source,
		   new String[] {
			   "ValidDatabaseType", "Set{\'POSTGRESQL\', \'MYSQL\', \'SQLITE\', \'MONGODB\'}->includes(self.type)",
			   "ValidPort", "self.port > 0 and self.port <= 65535",
			   "ValidDatabaseName", "not self.name.oclIsUndefined() and self.name.size() > 0 and self.name.matches(\'[a-zA-Z_][a-zA-Z0-9_]*\')"
		   });
		addAnnotation
		  (authenticationConfigEClass,
		   source,
		   new String[] {
			   "ValidAuthMethod", "Set{\'JWT\', \'SESSION\', \'OAUTH2\', \'BASIC\'}->includes(self.method)",
			   "ValidTokenExpiry", "self.tokenExpiryMinutes >= 5 and self.tokenExpiryMinutes <= 10080"
		   });
		addAnnotation
		  (apiFeaturesEClass,
		   source,
		   new String[] {
			   "ValidFeatureCombination", "self.swagger implies self.corsEnabled"
		   });
	}

} //APIMetamodelPackageImpl
