package com.kowihan.acceleo.service;

import com.kowihan.acceleo.model.metamodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class ModelGenerationService {

    private final ResourceLoader resourceLoader;
    private final FileStorageService fileStorageService;

    @Value("${acceleo.metamodel.path:metamodels/APIMetamodel.ecore}")
    private String metamodelPath;

    public ModelGenerationService(ResourceLoader resourceLoader, FileStorageService fileStorageService) {
        this.resourceLoader = resourceLoader;
        this.fileStorageService = fileStorageService;
    }

    public Path generateXMIFromSpecification(ApplicationModel model) throws Exception {
        
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        // Root element: xmi:XMI
        Element xmiRoot = doc.createElementNS("http://www.omg.org/XMI", "xmi:XMI");
        xmiRoot.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xmi", "http://www.omg.org/XMI");
        xmiRoot.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        xmiRoot.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:api", "http://www.kowihan.com/APIMetamodel");
        xmiRoot.setAttribute("xmi:version", "2.0");
        doc.appendChild(xmiRoot);

        // ApplicationModel element using api namespace
        Element appModel = doc.createElementNS("http://www.kowihan.com/APIMetamodel", "api:ApplicationModel");
        appModel.setAttribute("xmi:id", "_" + UUID.randomUUID().toString().replace("-", ""));
        appModel.setAttribute("projectName", model.getProjectName());
        appModel.setAttribute("framework", model.getFramework());
        appModel.setAttribute("pythonVersion", model.getPythonVersion() != null ? model.getPythonVersion() : "3.9");
        if (model.getDescription() != null) {
            appModel.setAttribute("description", model.getDescription());
        }
        xmiRoot.appendChild(appModel);

        // Database Config - element name is "database" (feature name), type is DatabaseConfig
        if (model.getDatabase() != null) {
            Element dbConfig = createDatabaseConfig(doc, model.getDatabase());
            appModel.appendChild(dbConfig);
        }

        // Authentication Config - element name is "authentication"
        if (model.getAuthentication() != null) {
            Element authConfig = createAuthenticationConfig(doc, model.getAuthentication());
            appModel.appendChild(authConfig);
        }

        // API Features - element name is "apiFeatures"
        if (model.getApiFeatures() != null) {
            Element apiFeatures = createAPIFeatures(doc, model.getApiFeatures());
            appModel.appendChild(apiFeatures);
        }

        // Entities - element name is "entities" (feature name)
        for (Entity entity : model.getEntities()) {
            Element entityElem = createEntity(doc, entity);
            appModel.appendChild(entityElem);
        }

        // Write to file
        Path xmiFile = fileStorageService.createTempFile("model_", ".xmi");
        try (FileOutputStream fos = new FileOutputStream(xmiFile.toFile())) {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(fos);
            transformer.transform(source, result);
        }

        return xmiFile;
    }

    private Element createDatabaseConfig(Document doc, DatabaseConfig db) {
        // Element name = feature name "database", xsi:type = api:DatabaseConfig
        Element dbConfig = doc.createElement("database");
        dbConfig.setAttribute("xsi:type", "api:DatabaseConfig");
        dbConfig.setAttribute("xmi:id", "_" + UUID.randomUUID().toString().replace("-", ""));
        dbConfig.setAttribute("type", db.getType());
        if (db.getHost() != null) dbConfig.setAttribute("host", db.getHost());
        if (db.getPort() != null) dbConfig.setAttribute("port", String.valueOf(db.getPort()));
        if (db.getName() != null) dbConfig.setAttribute("name", db.getName());
        return dbConfig;
    }

    private Element createAuthenticationConfig(Document doc, AuthenticationConfig auth) {
        // Element name = feature name "authentication"
        Element authConfig = doc.createElement("authentication");
        authConfig.setAttribute("xsi:type", "api:AuthenticationConfig");
        authConfig.setAttribute("xmi:id", "_" + UUID.randomUUID().toString().replace("-", ""));
        authConfig.setAttribute("enabled", String.valueOf(auth.getEnabled()));
        if (auth.getMethod() != null) authConfig.setAttribute("method", auth.getMethod());
        if (auth.getTokenExpiryMinutes() != null) {
            authConfig.setAttribute("tokenExpiryMinutes", String.valueOf(auth.getTokenExpiryMinutes()));
        }
        return authConfig;
    }

    private Element createAPIFeatures(Document doc, APIFeatures features) {
        // Element name = feature name "apiFeatures"
        Element apiFeatures = doc.createElement("apiFeatures");
        apiFeatures.setAttribute("xsi:type", "api:APIFeatures");
        apiFeatures.setAttribute("xmi:id", "_" + UUID.randomUUID().toString().replace("-", ""));
        apiFeatures.setAttribute("pagination", String.valueOf(features.getPagination()));
        apiFeatures.setAttribute("filtering", String.valueOf(features.getFiltering()));
        apiFeatures.setAttribute("swagger", String.valueOf(features.getSwagger()));
        apiFeatures.setAttribute("corsEnabled", String.valueOf(features.getCorsEnabled()));
        return apiFeatures;
    }

    private Element createEntity(Document doc, Entity entity) {
        // Element name = feature name "entities"
        Element entityElem = doc.createElement("entities");
        entityElem.setAttribute("xsi:type", "api:Entity");
        entityElem.setAttribute("xmi:id", "_" + UUID.randomUUID().toString().replace("-", ""));
        entityElem.setAttribute("name", entity.getName());
        if (entity.getTableName() != null) entityElem.setAttribute("tableName", entity.getTableName());
        if (entity.getDescription() != null) entityElem.setAttribute("description", entity.getDescription());
        if (entity.getIsAbstract() != null) entityElem.setAttribute("isAbstract", String.valueOf(entity.getIsAbstract()));

        // Attributes - element name = feature name "attributes"
        for (Attribute attr : entity.getAttributes()) {
            Element attrElem = createAttribute(doc, attr);
            entityElem.appendChild(attrElem);
        }

        // Relationships - element name = feature name "relationships"
        for (Relationship rel : entity.getRelationships()) {
            Element relElem = createRelationship(doc, rel);
            entityElem.appendChild(relElem);
        }

        return entityElem;
    }

    private Element createAttribute(Document doc, Attribute attr) {
        // Element name = feature name "attributes"
        Element attrElem = doc.createElement("attributes");
        attrElem.setAttribute("xsi:type", "api:Attribute");
        attrElem.setAttribute("xmi:id", "_" + UUID.randomUUID().toString().replace("-", ""));
        attrElem.setAttribute("name", attr.getName());
        attrElem.setAttribute("type", attr.getType());
        attrElem.setAttribute("isPrimaryKey", String.valueOf(attr.getIsPrimaryKey()));
        attrElem.setAttribute("isNullable", String.valueOf(attr.getIsNullable()));
        attrElem.setAttribute("isUnique", String.valueOf(attr.getIsUnique()));
        if (attr.getMaxLength() != null) attrElem.setAttribute("maxLength", String.valueOf(attr.getMaxLength()));
        if (attr.getDefaultValue() != null) attrElem.setAttribute("defaultValue", attr.getDefaultValue());
        return attrElem;
    }

    private Element createRelationship(Document doc, Relationship rel) {
        // Element name = feature name "relationships"
        Element relElem = doc.createElement("relationships");
        relElem.setAttribute("xsi:type", "api:Relationship");
        relElem.setAttribute("xmi:id", "_" + UUID.randomUUID().toString().replace("-", ""));
        relElem.setAttribute("name", rel.getName());
        relElem.setAttribute("type", rel.getType());
        
        // FIXED: targetEntity should be the entity NAME, not a UML ID
        String targetEntityValue = rel.getTargetEntity();
        if (targetEntityValue != null && !targetEntityValue.isEmpty()) {
            // If it looks like a UML ID, use the relationship name as the target
            if (targetEntityValue.startsWith("_") || targetEntityValue.length() > 50) {
                String entityName = rel.getName();
                if (entityName != null && !entityName.isEmpty()) {
                    targetEntityValue = entityName.substring(0, 1).toUpperCase() + entityName.substring(1);
                    System.out.println("⚠️  Converted UML ID to entity name: " + rel.getTargetEntity() + " -> " + targetEntityValue);
                }
            }
            relElem.setAttribute("targetEntity", targetEntityValue);
        }
        
        if (rel.getRelatedName() != null) relElem.setAttribute("relatedName", rel.getRelatedName());
        if (rel.getOnDelete() != null) relElem.setAttribute("onDelete", rel.getOnDelete());
        return relElem;
    }
}