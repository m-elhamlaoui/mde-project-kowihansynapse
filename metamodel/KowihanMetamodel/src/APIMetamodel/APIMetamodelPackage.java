/**
 */
package APIMetamodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see APIMetamodel.APIMetamodelFactory
 * @model kind="package"
 * @generated
 */
public interface APIMetamodelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "APIMetamodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.kowihan.com/APIMetamodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "api";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	APIMetamodelPackage eINSTANCE = APIMetamodel.impl.APIMetamodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link APIMetamodel.impl.ApplicationModelImpl <em>Application Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see APIMetamodel.impl.ApplicationModelImpl
	 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getApplicationModel()
	 * @generated
	 */
	int APPLICATION_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_MODEL__PROJECT_NAME = 0;

	/**
	 * The feature id for the '<em><b>Framework</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_MODEL__FRAMEWORK = 1;

	/**
	 * The feature id for the '<em><b>Python Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_MODEL__PYTHON_VERSION = 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_MODEL__DESCRIPTION = 3;

	/**
	 * The feature id for the '<em><b>Entities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_MODEL__ENTITIES = 4;

	/**
	 * The feature id for the '<em><b>Interactions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_MODEL__INTERACTIONS = 5;

	/**
	 * The feature id for the '<em><b>Database</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_MODEL__DATABASE = 6;

	/**
	 * The feature id for the '<em><b>Authentication</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_MODEL__AUTHENTICATION = 7;

	/**
	 * The feature id for the '<em><b>Api Features</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_MODEL__API_FEATURES = 8;

	/**
	 * The number of structural features of the '<em>Application Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_MODEL_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>Application Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link APIMetamodel.impl.EntityImpl <em>Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see APIMetamodel.impl.EntityImpl
	 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getEntity()
	 * @generated
	 */
	int ENTITY = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__IS_ABSTRACT = 2;

	/**
	 * The feature id for the '<em><b>Table Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__TABLE_NAME = 3;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__ATTRIBUTES = 4;

	/**
	 * The feature id for the '<em><b>Relationships</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__RELATIONSHIPS = 5;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__OPERATIONS = 6;

	/**
	 * The feature id for the '<em><b>Indexes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__INDEXES = 7;

	/**
	 * The feature id for the '<em><b>Parent Entity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__PARENT_ENTITY = 8;

	/**
	 * The number of structural features of the '<em>Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link APIMetamodel.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see APIMetamodel.impl.AttributeImpl
	 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Is Primary Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__IS_PRIMARY_KEY = 2;

	/**
	 * The feature id for the '<em><b>Is Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__IS_NULLABLE = 3;

	/**
	 * The feature id for the '<em><b>Is Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__IS_UNIQUE = 4;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__DEFAULT_VALUE = 5;

	/**
	 * The feature id for the '<em><b>Max Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__MAX_LENGTH = 6;

	/**
	 * The feature id for the '<em><b>Min Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__MIN_VALUE = 7;

	/**
	 * The feature id for the '<em><b>Max Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__MAX_VALUE = 8;

	/**
	 * The feature id for the '<em><b>Help Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__HELP_TEXT = 9;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = 10;

	/**
	 * The number of operations of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link APIMetamodel.impl.RelationshipImpl <em>Relationship</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see APIMetamodel.impl.RelationshipImpl
	 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getRelationship()
	 * @generated
	 */
	int RELATIONSHIP = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__NAME = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Target Entity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__TARGET_ENTITY = 2;

	/**
	 * The feature id for the '<em><b>Related Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__RELATED_NAME = 3;

	/**
	 * The feature id for the '<em><b>On Delete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__ON_DELETE = 4;

	/**
	 * The number of structural features of the '<em>Relationship</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Relationship</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link APIMetamodel.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see APIMetamodel.impl.OperationImpl
	 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Return Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__RETURN_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__VISIBILITY = 2;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__PARAMETERS = 3;

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link APIMetamodel.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see APIMetamodel.impl.ParameterImpl
	 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__TYPE = 1;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link APIMetamodel.impl.IndexImpl <em>Index</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see APIMetamodel.impl.IndexImpl
	 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getIndex()
	 * @generated
	 */
	int INDEX = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__NAME = 0;

	/**
	 * The feature id for the '<em><b>Fields</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__FIELDS = 1;

	/**
	 * The feature id for the '<em><b>Is Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__IS_UNIQUE = 2;

	/**
	 * The number of structural features of the '<em>Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link APIMetamodel.impl.InteractionImpl <em>Interaction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see APIMetamodel.impl.InteractionImpl
	 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getInteraction()
	 * @generated
	 */
	int INTERACTION = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Use Case Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__USE_CASE_DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Http Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__HTTP_METHOD = 2;

	/**
	 * The feature id for the '<em><b>Endpoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__ENDPOINT = 3;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__PARTICIPANTS = 4;

	/**
	 * The feature id for the '<em><b>Messages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__MESSAGES = 5;

	/**
	 * The number of structural features of the '<em>Interaction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Interaction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link APIMetamodel.impl.ParticipantImpl <em>Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see APIMetamodel.impl.ParticipantImpl
	 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getParticipant()
	 * @generated
	 */
	int PARTICIPANT = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Actor Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__ACTOR_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__ENTITY_NAME = 2;

	/**
	 * The number of structural features of the '<em>Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link APIMetamodel.impl.MessageImpl <em>Message</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see APIMetamodel.impl.MessageImpl
	 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getMessage()
	 * @generated
	 */
	int MESSAGE = 9;

	/**
	 * The feature id for the '<em><b>Sequence Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__SEQUENCE_NUMBER = 0;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__OPERATION = 1;

	/**
	 * The feature id for the '<em><b>Message Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__MESSAGE_TYPE = 2;

	/**
	 * The feature id for the '<em><b>From Participant</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__FROM_PARTICIPANT = 3;

	/**
	 * The feature id for the '<em><b>To Participant</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__TO_PARTICIPANT = 4;

	/**
	 * The number of structural features of the '<em>Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link APIMetamodel.impl.DatabaseConfigImpl <em>Database Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see APIMetamodel.impl.DatabaseConfigImpl
	 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getDatabaseConfig()
	 * @generated
	 */
	int DATABASE_CONFIG = 10;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_CONFIG__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_CONFIG__HOST = 1;

	/**
	 * The feature id for the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_CONFIG__PORT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_CONFIG__NAME = 3;

	/**
	 * The number of structural features of the '<em>Database Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_CONFIG_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Database Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_CONFIG_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link APIMetamodel.impl.AuthenticationConfigImpl <em>Authentication Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see APIMetamodel.impl.AuthenticationConfigImpl
	 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getAuthenticationConfig()
	 * @generated
	 */
	int AUTHENTICATION_CONFIG = 11;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHENTICATION_CONFIG__ENABLED = 0;

	/**
	 * The feature id for the '<em><b>Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHENTICATION_CONFIG__METHOD = 1;

	/**
	 * The feature id for the '<em><b>Token Expiry Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHENTICATION_CONFIG__TOKEN_EXPIRY_MINUTES = 2;

	/**
	 * The number of structural features of the '<em>Authentication Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHENTICATION_CONFIG_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Authentication Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHENTICATION_CONFIG_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link APIMetamodel.impl.APIFeaturesImpl <em>API Features</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see APIMetamodel.impl.APIFeaturesImpl
	 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getAPIFeatures()
	 * @generated
	 */
	int API_FEATURES = 12;

	/**
	 * The feature id for the '<em><b>Pagination</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_FEATURES__PAGINATION = 0;

	/**
	 * The feature id for the '<em><b>Filtering</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_FEATURES__FILTERING = 1;

	/**
	 * The feature id for the '<em><b>Swagger</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_FEATURES__SWAGGER = 2;

	/**
	 * The feature id for the '<em><b>Cors Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_FEATURES__CORS_ENABLED = 3;

	/**
	 * The number of structural features of the '<em>API Features</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_FEATURES_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>API Features</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int API_FEATURES_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link APIMetamodel.ApplicationModel <em>Application Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application Model</em>'.
	 * @see APIMetamodel.ApplicationModel
	 * @generated
	 */
	EClass getApplicationModel();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.ApplicationModel#getProjectName <em>Project Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Name</em>'.
	 * @see APIMetamodel.ApplicationModel#getProjectName()
	 * @see #getApplicationModel()
	 * @generated
	 */
	EAttribute getApplicationModel_ProjectName();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.ApplicationModel#getFramework <em>Framework</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Framework</em>'.
	 * @see APIMetamodel.ApplicationModel#getFramework()
	 * @see #getApplicationModel()
	 * @generated
	 */
	EAttribute getApplicationModel_Framework();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.ApplicationModel#getPythonVersion <em>Python Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Python Version</em>'.
	 * @see APIMetamodel.ApplicationModel#getPythonVersion()
	 * @see #getApplicationModel()
	 * @generated
	 */
	EAttribute getApplicationModel_PythonVersion();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.ApplicationModel#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see APIMetamodel.ApplicationModel#getDescription()
	 * @see #getApplicationModel()
	 * @generated
	 */
	EAttribute getApplicationModel_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link APIMetamodel.ApplicationModel#getEntities <em>Entities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entities</em>'.
	 * @see APIMetamodel.ApplicationModel#getEntities()
	 * @see #getApplicationModel()
	 * @generated
	 */
	EReference getApplicationModel_Entities();

	/**
	 * Returns the meta object for the containment reference list '{@link APIMetamodel.ApplicationModel#getInteractions <em>Interactions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Interactions</em>'.
	 * @see APIMetamodel.ApplicationModel#getInteractions()
	 * @see #getApplicationModel()
	 * @generated
	 */
	EReference getApplicationModel_Interactions();

	/**
	 * Returns the meta object for the containment reference '{@link APIMetamodel.ApplicationModel#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Database</em>'.
	 * @see APIMetamodel.ApplicationModel#getDatabase()
	 * @see #getApplicationModel()
	 * @generated
	 */
	EReference getApplicationModel_Database();

	/**
	 * Returns the meta object for the containment reference '{@link APIMetamodel.ApplicationModel#getAuthentication <em>Authentication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Authentication</em>'.
	 * @see APIMetamodel.ApplicationModel#getAuthentication()
	 * @see #getApplicationModel()
	 * @generated
	 */
	EReference getApplicationModel_Authentication();

	/**
	 * Returns the meta object for the containment reference '{@link APIMetamodel.ApplicationModel#getApiFeatures <em>Api Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Api Features</em>'.
	 * @see APIMetamodel.ApplicationModel#getApiFeatures()
	 * @see #getApplicationModel()
	 * @generated
	 */
	EReference getApplicationModel_ApiFeatures();

	/**
	 * Returns the meta object for class '{@link APIMetamodel.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity</em>'.
	 * @see APIMetamodel.Entity
	 * @generated
	 */
	EClass getEntity();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Entity#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see APIMetamodel.Entity#getName()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Name();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Entity#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see APIMetamodel.Entity#getDescription()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Description();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Entity#isIsAbstract <em>Is Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Abstract</em>'.
	 * @see APIMetamodel.Entity#isIsAbstract()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_IsAbstract();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Entity#getTableName <em>Table Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Table Name</em>'.
	 * @see APIMetamodel.Entity#getTableName()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_TableName();

	/**
	 * Returns the meta object for the containment reference list '{@link APIMetamodel.Entity#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see APIMetamodel.Entity#getAttributes()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_Attributes();

	/**
	 * Returns the meta object for the containment reference list '{@link APIMetamodel.Entity#getRelationships <em>Relationships</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relationships</em>'.
	 * @see APIMetamodel.Entity#getRelationships()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_Relationships();

	/**
	 * Returns the meta object for the containment reference list '{@link APIMetamodel.Entity#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see APIMetamodel.Entity#getOperations()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_Operations();

	/**
	 * Returns the meta object for the containment reference list '{@link APIMetamodel.Entity#getIndexes <em>Indexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Indexes</em>'.
	 * @see APIMetamodel.Entity#getIndexes()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_Indexes();

	/**
	 * Returns the meta object for the reference '{@link APIMetamodel.Entity#getParentEntity <em>Parent Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent Entity</em>'.
	 * @see APIMetamodel.Entity#getParentEntity()
	 * @see #getEntity()
	 * @generated
	 */
	EReference getEntity_ParentEntity();

	/**
	 * Returns the meta object for class '{@link APIMetamodel.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see APIMetamodel.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Attribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see APIMetamodel.Attribute#getName()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Name();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Attribute#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see APIMetamodel.Attribute#getType()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Type();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Attribute#isIsPrimaryKey <em>Is Primary Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Primary Key</em>'.
	 * @see APIMetamodel.Attribute#isIsPrimaryKey()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_IsPrimaryKey();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Attribute#isIsNullable <em>Is Nullable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Nullable</em>'.
	 * @see APIMetamodel.Attribute#isIsNullable()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_IsNullable();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Attribute#isIsUnique <em>Is Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Unique</em>'.
	 * @see APIMetamodel.Attribute#isIsUnique()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_IsUnique();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Attribute#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value</em>'.
	 * @see APIMetamodel.Attribute#getDefaultValue()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_DefaultValue();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Attribute#getMaxLength <em>Max Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Length</em>'.
	 * @see APIMetamodel.Attribute#getMaxLength()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_MaxLength();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Attribute#getMinValue <em>Min Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Value</em>'.
	 * @see APIMetamodel.Attribute#getMinValue()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_MinValue();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Attribute#getMaxValue <em>Max Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Value</em>'.
	 * @see APIMetamodel.Attribute#getMaxValue()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_MaxValue();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Attribute#getHelpText <em>Help Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Help Text</em>'.
	 * @see APIMetamodel.Attribute#getHelpText()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_HelpText();

	/**
	 * Returns the meta object for class '{@link APIMetamodel.Relationship <em>Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relationship</em>'.
	 * @see APIMetamodel.Relationship
	 * @generated
	 */
	EClass getRelationship();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Relationship#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see APIMetamodel.Relationship#getName()
	 * @see #getRelationship()
	 * @generated
	 */
	EAttribute getRelationship_Name();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Relationship#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see APIMetamodel.Relationship#getType()
	 * @see #getRelationship()
	 * @generated
	 */
	EAttribute getRelationship_Type();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Relationship#getTargetEntity <em>Target Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Entity</em>'.
	 * @see APIMetamodel.Relationship#getTargetEntity()
	 * @see #getRelationship()
	 * @generated
	 */
	EAttribute getRelationship_TargetEntity();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Relationship#getRelatedName <em>Related Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Related Name</em>'.
	 * @see APIMetamodel.Relationship#getRelatedName()
	 * @see #getRelationship()
	 * @generated
	 */
	EAttribute getRelationship_RelatedName();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Relationship#getOnDelete <em>On Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Delete</em>'.
	 * @see APIMetamodel.Relationship#getOnDelete()
	 * @see #getRelationship()
	 * @generated
	 */
	EAttribute getRelationship_OnDelete();

	/**
	 * Returns the meta object for class '{@link APIMetamodel.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see APIMetamodel.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Operation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see APIMetamodel.Operation#getName()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_Name();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Operation#getReturnType <em>Return Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Return Type</em>'.
	 * @see APIMetamodel.Operation#getReturnType()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_ReturnType();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Operation#getVisibility <em>Visibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Visibility</em>'.
	 * @see APIMetamodel.Operation#getVisibility()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_Visibility();

	/**
	 * Returns the meta object for the containment reference list '{@link APIMetamodel.Operation#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see APIMetamodel.Operation#getParameters()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Parameters();

	/**
	 * Returns the meta object for class '{@link APIMetamodel.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see APIMetamodel.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Parameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see APIMetamodel.Parameter#getName()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Name();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Parameter#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see APIMetamodel.Parameter#getType()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Type();

	/**
	 * Returns the meta object for class '{@link APIMetamodel.Index <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Index</em>'.
	 * @see APIMetamodel.Index
	 * @generated
	 */
	EClass getIndex();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Index#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see APIMetamodel.Index#getName()
	 * @see #getIndex()
	 * @generated
	 */
	EAttribute getIndex_Name();

	/**
	 * Returns the meta object for the attribute list '{@link APIMetamodel.Index#getFields <em>Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Fields</em>'.
	 * @see APIMetamodel.Index#getFields()
	 * @see #getIndex()
	 * @generated
	 */
	EAttribute getIndex_Fields();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Index#isIsUnique <em>Is Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Unique</em>'.
	 * @see APIMetamodel.Index#isIsUnique()
	 * @see #getIndex()
	 * @generated
	 */
	EAttribute getIndex_IsUnique();

	/**
	 * Returns the meta object for class '{@link APIMetamodel.Interaction <em>Interaction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interaction</em>'.
	 * @see APIMetamodel.Interaction
	 * @generated
	 */
	EClass getInteraction();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Interaction#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see APIMetamodel.Interaction#getName()
	 * @see #getInteraction()
	 * @generated
	 */
	EAttribute getInteraction_Name();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Interaction#getUseCaseDescription <em>Use Case Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Use Case Description</em>'.
	 * @see APIMetamodel.Interaction#getUseCaseDescription()
	 * @see #getInteraction()
	 * @generated
	 */
	EAttribute getInteraction_UseCaseDescription();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Interaction#getHttpMethod <em>Http Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Http Method</em>'.
	 * @see APIMetamodel.Interaction#getHttpMethod()
	 * @see #getInteraction()
	 * @generated
	 */
	EAttribute getInteraction_HttpMethod();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Interaction#getEndpoint <em>Endpoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Endpoint</em>'.
	 * @see APIMetamodel.Interaction#getEndpoint()
	 * @see #getInteraction()
	 * @generated
	 */
	EAttribute getInteraction_Endpoint();

	/**
	 * Returns the meta object for the containment reference list '{@link APIMetamodel.Interaction#getParticipants <em>Participants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Participants</em>'.
	 * @see APIMetamodel.Interaction#getParticipants()
	 * @see #getInteraction()
	 * @generated
	 */
	EReference getInteraction_Participants();

	/**
	 * Returns the meta object for the containment reference list '{@link APIMetamodel.Interaction#getMessages <em>Messages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Messages</em>'.
	 * @see APIMetamodel.Interaction#getMessages()
	 * @see #getInteraction()
	 * @generated
	 */
	EReference getInteraction_Messages();

	/**
	 * Returns the meta object for class '{@link APIMetamodel.Participant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Participant</em>'.
	 * @see APIMetamodel.Participant
	 * @generated
	 */
	EClass getParticipant();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Participant#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see APIMetamodel.Participant#getName()
	 * @see #getParticipant()
	 * @generated
	 */
	EAttribute getParticipant_Name();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Participant#getActorType <em>Actor Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actor Type</em>'.
	 * @see APIMetamodel.Participant#getActorType()
	 * @see #getParticipant()
	 * @generated
	 */
	EAttribute getParticipant_ActorType();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Participant#getEntityName <em>Entity Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Entity Name</em>'.
	 * @see APIMetamodel.Participant#getEntityName()
	 * @see #getParticipant()
	 * @generated
	 */
	EAttribute getParticipant_EntityName();

	/**
	 * Returns the meta object for class '{@link APIMetamodel.Message <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message</em>'.
	 * @see APIMetamodel.Message
	 * @generated
	 */
	EClass getMessage();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Message#getSequenceNumber <em>Sequence Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sequence Number</em>'.
	 * @see APIMetamodel.Message#getSequenceNumber()
	 * @see #getMessage()
	 * @generated
	 */
	EAttribute getMessage_SequenceNumber();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Message#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation</em>'.
	 * @see APIMetamodel.Message#getOperation()
	 * @see #getMessage()
	 * @generated
	 */
	EAttribute getMessage_Operation();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.Message#getMessageType <em>Message Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message Type</em>'.
	 * @see APIMetamodel.Message#getMessageType()
	 * @see #getMessage()
	 * @generated
	 */
	EAttribute getMessage_MessageType();

	/**
	 * Returns the meta object for the reference '{@link APIMetamodel.Message#getFromParticipant <em>From Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From Participant</em>'.
	 * @see APIMetamodel.Message#getFromParticipant()
	 * @see #getMessage()
	 * @generated
	 */
	EReference getMessage_FromParticipant();

	/**
	 * Returns the meta object for the reference '{@link APIMetamodel.Message#getToParticipant <em>To Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To Participant</em>'.
	 * @see APIMetamodel.Message#getToParticipant()
	 * @see #getMessage()
	 * @generated
	 */
	EReference getMessage_ToParticipant();

	/**
	 * Returns the meta object for class '{@link APIMetamodel.DatabaseConfig <em>Database Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Database Config</em>'.
	 * @see APIMetamodel.DatabaseConfig
	 * @generated
	 */
	EClass getDatabaseConfig();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.DatabaseConfig#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see APIMetamodel.DatabaseConfig#getType()
	 * @see #getDatabaseConfig()
	 * @generated
	 */
	EAttribute getDatabaseConfig_Type();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.DatabaseConfig#getHost <em>Host</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Host</em>'.
	 * @see APIMetamodel.DatabaseConfig#getHost()
	 * @see #getDatabaseConfig()
	 * @generated
	 */
	EAttribute getDatabaseConfig_Host();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.DatabaseConfig#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port</em>'.
	 * @see APIMetamodel.DatabaseConfig#getPort()
	 * @see #getDatabaseConfig()
	 * @generated
	 */
	EAttribute getDatabaseConfig_Port();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.DatabaseConfig#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see APIMetamodel.DatabaseConfig#getName()
	 * @see #getDatabaseConfig()
	 * @generated
	 */
	EAttribute getDatabaseConfig_Name();

	/**
	 * Returns the meta object for class '{@link APIMetamodel.AuthenticationConfig <em>Authentication Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Authentication Config</em>'.
	 * @see APIMetamodel.AuthenticationConfig
	 * @generated
	 */
	EClass getAuthenticationConfig();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.AuthenticationConfig#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see APIMetamodel.AuthenticationConfig#isEnabled()
	 * @see #getAuthenticationConfig()
	 * @generated
	 */
	EAttribute getAuthenticationConfig_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.AuthenticationConfig#getMethod <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method</em>'.
	 * @see APIMetamodel.AuthenticationConfig#getMethod()
	 * @see #getAuthenticationConfig()
	 * @generated
	 */
	EAttribute getAuthenticationConfig_Method();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.AuthenticationConfig#getTokenExpiryMinutes <em>Token Expiry Minutes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Token Expiry Minutes</em>'.
	 * @see APIMetamodel.AuthenticationConfig#getTokenExpiryMinutes()
	 * @see #getAuthenticationConfig()
	 * @generated
	 */
	EAttribute getAuthenticationConfig_TokenExpiryMinutes();

	/**
	 * Returns the meta object for class '{@link APIMetamodel.APIFeatures <em>API Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>API Features</em>'.
	 * @see APIMetamodel.APIFeatures
	 * @generated
	 */
	EClass getAPIFeatures();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.APIFeatures#isPagination <em>Pagination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pagination</em>'.
	 * @see APIMetamodel.APIFeatures#isPagination()
	 * @see #getAPIFeatures()
	 * @generated
	 */
	EAttribute getAPIFeatures_Pagination();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.APIFeatures#isFiltering <em>Filtering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filtering</em>'.
	 * @see APIMetamodel.APIFeatures#isFiltering()
	 * @see #getAPIFeatures()
	 * @generated
	 */
	EAttribute getAPIFeatures_Filtering();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.APIFeatures#isSwagger <em>Swagger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Swagger</em>'.
	 * @see APIMetamodel.APIFeatures#isSwagger()
	 * @see #getAPIFeatures()
	 * @generated
	 */
	EAttribute getAPIFeatures_Swagger();

	/**
	 * Returns the meta object for the attribute '{@link APIMetamodel.APIFeatures#isCorsEnabled <em>Cors Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cors Enabled</em>'.
	 * @see APIMetamodel.APIFeatures#isCorsEnabled()
	 * @see #getAPIFeatures()
	 * @generated
	 */
	EAttribute getAPIFeatures_CorsEnabled();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	APIMetamodelFactory getAPIMetamodelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link APIMetamodel.impl.ApplicationModelImpl <em>Application Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see APIMetamodel.impl.ApplicationModelImpl
		 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getApplicationModel()
		 * @generated
		 */
		EClass APPLICATION_MODEL = eINSTANCE.getApplicationModel();

		/**
		 * The meta object literal for the '<em><b>Project Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION_MODEL__PROJECT_NAME = eINSTANCE.getApplicationModel_ProjectName();

		/**
		 * The meta object literal for the '<em><b>Framework</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION_MODEL__FRAMEWORK = eINSTANCE.getApplicationModel_Framework();

		/**
		 * The meta object literal for the '<em><b>Python Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION_MODEL__PYTHON_VERSION = eINSTANCE.getApplicationModel_PythonVersion();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION_MODEL__DESCRIPTION = eINSTANCE.getApplicationModel_Description();

		/**
		 * The meta object literal for the '<em><b>Entities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_MODEL__ENTITIES = eINSTANCE.getApplicationModel_Entities();

		/**
		 * The meta object literal for the '<em><b>Interactions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_MODEL__INTERACTIONS = eINSTANCE.getApplicationModel_Interactions();

		/**
		 * The meta object literal for the '<em><b>Database</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_MODEL__DATABASE = eINSTANCE.getApplicationModel_Database();

		/**
		 * The meta object literal for the '<em><b>Authentication</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_MODEL__AUTHENTICATION = eINSTANCE.getApplicationModel_Authentication();

		/**
		 * The meta object literal for the '<em><b>Api Features</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION_MODEL__API_FEATURES = eINSTANCE.getApplicationModel_ApiFeatures();

		/**
		 * The meta object literal for the '{@link APIMetamodel.impl.EntityImpl <em>Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see APIMetamodel.impl.EntityImpl
		 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getEntity()
		 * @generated
		 */
		EClass ENTITY = eINSTANCE.getEntity();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__NAME = eINSTANCE.getEntity_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__DESCRIPTION = eINSTANCE.getEntity_Description();

		/**
		 * The meta object literal for the '<em><b>Is Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__IS_ABSTRACT = eINSTANCE.getEntity_IsAbstract();

		/**
		 * The meta object literal for the '<em><b>Table Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__TABLE_NAME = eINSTANCE.getEntity_TableName();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__ATTRIBUTES = eINSTANCE.getEntity_Attributes();

		/**
		 * The meta object literal for the '<em><b>Relationships</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__RELATIONSHIPS = eINSTANCE.getEntity_Relationships();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__OPERATIONS = eINSTANCE.getEntity_Operations();

		/**
		 * The meta object literal for the '<em><b>Indexes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__INDEXES = eINSTANCE.getEntity_Indexes();

		/**
		 * The meta object literal for the '<em><b>Parent Entity</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY__PARENT_ENTITY = eINSTANCE.getEntity_ParentEntity();

		/**
		 * The meta object literal for the '{@link APIMetamodel.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see APIMetamodel.impl.AttributeImpl
		 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__NAME = eINSTANCE.getAttribute_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__TYPE = eINSTANCE.getAttribute_Type();

		/**
		 * The meta object literal for the '<em><b>Is Primary Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__IS_PRIMARY_KEY = eINSTANCE.getAttribute_IsPrimaryKey();

		/**
		 * The meta object literal for the '<em><b>Is Nullable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__IS_NULLABLE = eINSTANCE.getAttribute_IsNullable();

		/**
		 * The meta object literal for the '<em><b>Is Unique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__IS_UNIQUE = eINSTANCE.getAttribute_IsUnique();

		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__DEFAULT_VALUE = eINSTANCE.getAttribute_DefaultValue();

		/**
		 * The meta object literal for the '<em><b>Max Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__MAX_LENGTH = eINSTANCE.getAttribute_MaxLength();

		/**
		 * The meta object literal for the '<em><b>Min Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__MIN_VALUE = eINSTANCE.getAttribute_MinValue();

		/**
		 * The meta object literal for the '<em><b>Max Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__MAX_VALUE = eINSTANCE.getAttribute_MaxValue();

		/**
		 * The meta object literal for the '<em><b>Help Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__HELP_TEXT = eINSTANCE.getAttribute_HelpText();

		/**
		 * The meta object literal for the '{@link APIMetamodel.impl.RelationshipImpl <em>Relationship</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see APIMetamodel.impl.RelationshipImpl
		 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getRelationship()
		 * @generated
		 */
		EClass RELATIONSHIP = eINSTANCE.getRelationship();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATIONSHIP__NAME = eINSTANCE.getRelationship_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATIONSHIP__TYPE = eINSTANCE.getRelationship_Type();

		/**
		 * The meta object literal for the '<em><b>Target Entity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATIONSHIP__TARGET_ENTITY = eINSTANCE.getRelationship_TargetEntity();

		/**
		 * The meta object literal for the '<em><b>Related Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATIONSHIP__RELATED_NAME = eINSTANCE.getRelationship_RelatedName();

		/**
		 * The meta object literal for the '<em><b>On Delete</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATIONSHIP__ON_DELETE = eINSTANCE.getRelationship_OnDelete();

		/**
		 * The meta object literal for the '{@link APIMetamodel.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see APIMetamodel.impl.OperationImpl
		 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__NAME = eINSTANCE.getOperation_Name();

		/**
		 * The meta object literal for the '<em><b>Return Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__RETURN_TYPE = eINSTANCE.getOperation_ReturnType();

		/**
		 * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__VISIBILITY = eINSTANCE.getOperation_Visibility();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__PARAMETERS = eINSTANCE.getOperation_Parameters();

		/**
		 * The meta object literal for the '{@link APIMetamodel.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see APIMetamodel.impl.ParameterImpl
		 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__NAME = eINSTANCE.getParameter_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__TYPE = eINSTANCE.getParameter_Type();

		/**
		 * The meta object literal for the '{@link APIMetamodel.impl.IndexImpl <em>Index</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see APIMetamodel.impl.IndexImpl
		 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getIndex()
		 * @generated
		 */
		EClass INDEX = eINSTANCE.getIndex();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEX__NAME = eINSTANCE.getIndex_Name();

		/**
		 * The meta object literal for the '<em><b>Fields</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEX__FIELDS = eINSTANCE.getIndex_Fields();

		/**
		 * The meta object literal for the '<em><b>Is Unique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEX__IS_UNIQUE = eINSTANCE.getIndex_IsUnique();

		/**
		 * The meta object literal for the '{@link APIMetamodel.impl.InteractionImpl <em>Interaction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see APIMetamodel.impl.InteractionImpl
		 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getInteraction()
		 * @generated
		 */
		EClass INTERACTION = eINSTANCE.getInteraction();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERACTION__NAME = eINSTANCE.getInteraction_Name();

		/**
		 * The meta object literal for the '<em><b>Use Case Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERACTION__USE_CASE_DESCRIPTION = eINSTANCE.getInteraction_UseCaseDescription();

		/**
		 * The meta object literal for the '<em><b>Http Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERACTION__HTTP_METHOD = eINSTANCE.getInteraction_HttpMethod();

		/**
		 * The meta object literal for the '<em><b>Endpoint</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERACTION__ENDPOINT = eINSTANCE.getInteraction_Endpoint();

		/**
		 * The meta object literal for the '<em><b>Participants</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERACTION__PARTICIPANTS = eINSTANCE.getInteraction_Participants();

		/**
		 * The meta object literal for the '<em><b>Messages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERACTION__MESSAGES = eINSTANCE.getInteraction_Messages();

		/**
		 * The meta object literal for the '{@link APIMetamodel.impl.ParticipantImpl <em>Participant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see APIMetamodel.impl.ParticipantImpl
		 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getParticipant()
		 * @generated
		 */
		EClass PARTICIPANT = eINSTANCE.getParticipant();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT__NAME = eINSTANCE.getParticipant_Name();

		/**
		 * The meta object literal for the '<em><b>Actor Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT__ACTOR_TYPE = eINSTANCE.getParticipant_ActorType();

		/**
		 * The meta object literal for the '<em><b>Entity Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT__ENTITY_NAME = eINSTANCE.getParticipant_EntityName();

		/**
		 * The meta object literal for the '{@link APIMetamodel.impl.MessageImpl <em>Message</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see APIMetamodel.impl.MessageImpl
		 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getMessage()
		 * @generated
		 */
		EClass MESSAGE = eINSTANCE.getMessage();

		/**
		 * The meta object literal for the '<em><b>Sequence Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE__SEQUENCE_NUMBER = eINSTANCE.getMessage_SequenceNumber();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE__OPERATION = eINSTANCE.getMessage_Operation();

		/**
		 * The meta object literal for the '<em><b>Message Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE__MESSAGE_TYPE = eINSTANCE.getMessage_MessageType();

		/**
		 * The meta object literal for the '<em><b>From Participant</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE__FROM_PARTICIPANT = eINSTANCE.getMessage_FromParticipant();

		/**
		 * The meta object literal for the '<em><b>To Participant</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE__TO_PARTICIPANT = eINSTANCE.getMessage_ToParticipant();

		/**
		 * The meta object literal for the '{@link APIMetamodel.impl.DatabaseConfigImpl <em>Database Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see APIMetamodel.impl.DatabaseConfigImpl
		 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getDatabaseConfig()
		 * @generated
		 */
		EClass DATABASE_CONFIG = eINSTANCE.getDatabaseConfig();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_CONFIG__TYPE = eINSTANCE.getDatabaseConfig_Type();

		/**
		 * The meta object literal for the '<em><b>Host</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_CONFIG__HOST = eINSTANCE.getDatabaseConfig_Host();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_CONFIG__PORT = eINSTANCE.getDatabaseConfig_Port();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_CONFIG__NAME = eINSTANCE.getDatabaseConfig_Name();

		/**
		 * The meta object literal for the '{@link APIMetamodel.impl.AuthenticationConfigImpl <em>Authentication Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see APIMetamodel.impl.AuthenticationConfigImpl
		 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getAuthenticationConfig()
		 * @generated
		 */
		EClass AUTHENTICATION_CONFIG = eINSTANCE.getAuthenticationConfig();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUTHENTICATION_CONFIG__ENABLED = eINSTANCE.getAuthenticationConfig_Enabled();

		/**
		 * The meta object literal for the '<em><b>Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUTHENTICATION_CONFIG__METHOD = eINSTANCE.getAuthenticationConfig_Method();

		/**
		 * The meta object literal for the '<em><b>Token Expiry Minutes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AUTHENTICATION_CONFIG__TOKEN_EXPIRY_MINUTES = eINSTANCE.getAuthenticationConfig_TokenExpiryMinutes();

		/**
		 * The meta object literal for the '{@link APIMetamodel.impl.APIFeaturesImpl <em>API Features</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see APIMetamodel.impl.APIFeaturesImpl
		 * @see APIMetamodel.impl.APIMetamodelPackageImpl#getAPIFeatures()
		 * @generated
		 */
		EClass API_FEATURES = eINSTANCE.getAPIFeatures();

		/**
		 * The meta object literal for the '<em><b>Pagination</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute API_FEATURES__PAGINATION = eINSTANCE.getAPIFeatures_Pagination();

		/**
		 * The meta object literal for the '<em><b>Filtering</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute API_FEATURES__FILTERING = eINSTANCE.getAPIFeatures_Filtering();

		/**
		 * The meta object literal for the '<em><b>Swagger</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute API_FEATURES__SWAGGER = eINSTANCE.getAPIFeatures_Swagger();

		/**
		 * The meta object literal for the '<em><b>Cors Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute API_FEATURES__CORS_ENABLED = eINSTANCE.getAPIFeatures_CorsEnabled();

	}

} //APIMetamodelPackage
