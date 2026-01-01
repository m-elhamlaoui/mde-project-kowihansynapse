/**
 */
package APIMetamodel.impl;

import APIMetamodel.APIFeatures;
import APIMetamodel.APIMetamodelPackage;
import APIMetamodel.ApplicationModel;
import APIMetamodel.AuthenticationConfig;
import APIMetamodel.DatabaseConfig;
import APIMetamodel.Entity;
import APIMetamodel.Interaction;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Application Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link APIMetamodel.impl.ApplicationModelImpl#getProjectName <em>Project Name</em>}</li>
 *   <li>{@link APIMetamodel.impl.ApplicationModelImpl#getFramework <em>Framework</em>}</li>
 *   <li>{@link APIMetamodel.impl.ApplicationModelImpl#getPythonVersion <em>Python Version</em>}</li>
 *   <li>{@link APIMetamodel.impl.ApplicationModelImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link APIMetamodel.impl.ApplicationModelImpl#getEntities <em>Entities</em>}</li>
 *   <li>{@link APIMetamodel.impl.ApplicationModelImpl#getInteractions <em>Interactions</em>}</li>
 *   <li>{@link APIMetamodel.impl.ApplicationModelImpl#getDatabase <em>Database</em>}</li>
 *   <li>{@link APIMetamodel.impl.ApplicationModelImpl#getAuthentication <em>Authentication</em>}</li>
 *   <li>{@link APIMetamodel.impl.ApplicationModelImpl#getApiFeatures <em>Api Features</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ApplicationModelImpl extends MinimalEObjectImpl.Container implements ApplicationModel {
	/**
	 * The default value of the '{@link #getProjectName() <em>Project Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProjectName() <em>Project Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected String projectName = PROJECT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getFramework() <em>Framework</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFramework()
	 * @generated
	 * @ordered
	 */
	protected static final String FRAMEWORK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFramework() <em>Framework</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFramework()
	 * @generated
	 * @ordered
	 */
	protected String framework = FRAMEWORK_EDEFAULT;

	/**
	 * The default value of the '{@link #getPythonVersion() <em>Python Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPythonVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String PYTHON_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPythonVersion() <em>Python Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPythonVersion()
	 * @generated
	 * @ordered
	 */
	protected String pythonVersion = PYTHON_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEntities() <em>Entities</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntities()
	 * @generated
	 * @ordered
	 */
	protected EList<Entity> entities;

	/**
	 * The cached value of the '{@link #getInteractions() <em>Interactions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInteractions()
	 * @generated
	 * @ordered
	 */
	protected EList<Interaction> interactions;

	/**
	 * The cached value of the '{@link #getDatabase() <em>Database</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabase()
	 * @generated
	 * @ordered
	 */
	protected DatabaseConfig database;

	/**
	 * The cached value of the '{@link #getAuthentication() <em>Authentication</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthentication()
	 * @generated
	 * @ordered
	 */
	protected AuthenticationConfig authentication;

	/**
	 * The cached value of the '{@link #getApiFeatures() <em>Api Features</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApiFeatures()
	 * @generated
	 * @ordered
	 */
	protected APIFeatures apiFeatures;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ApplicationModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return APIMetamodelPackage.Literals.APPLICATION_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getProjectName() {
		return projectName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProjectName(String newProjectName) {
		String oldProjectName = projectName;
		projectName = newProjectName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.APPLICATION_MODEL__PROJECT_NAME, oldProjectName, projectName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFramework() {
		return framework;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFramework(String newFramework) {
		String oldFramework = framework;
		framework = newFramework;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.APPLICATION_MODEL__FRAMEWORK, oldFramework, framework));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPythonVersion() {
		return pythonVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPythonVersion(String newPythonVersion) {
		String oldPythonVersion = pythonVersion;
		pythonVersion = newPythonVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.APPLICATION_MODEL__PYTHON_VERSION, oldPythonVersion, pythonVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.APPLICATION_MODEL__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Entity> getEntities() {
		if (entities == null) {
			entities = new EObjectContainmentEList<Entity>(Entity.class, this, APIMetamodelPackage.APPLICATION_MODEL__ENTITIES);
		}
		return entities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Interaction> getInteractions() {
		if (interactions == null) {
			interactions = new EObjectContainmentEList<Interaction>(Interaction.class, this, APIMetamodelPackage.APPLICATION_MODEL__INTERACTIONS);
		}
		return interactions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DatabaseConfig getDatabase() {
		return database;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDatabase(DatabaseConfig newDatabase, NotificationChain msgs) {
		DatabaseConfig oldDatabase = database;
		database = newDatabase;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.APPLICATION_MODEL__DATABASE, oldDatabase, newDatabase);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDatabase(DatabaseConfig newDatabase) {
		if (newDatabase != database) {
			NotificationChain msgs = null;
			if (database != null)
				msgs = ((InternalEObject)database).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - APIMetamodelPackage.APPLICATION_MODEL__DATABASE, null, msgs);
			if (newDatabase != null)
				msgs = ((InternalEObject)newDatabase).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - APIMetamodelPackage.APPLICATION_MODEL__DATABASE, null, msgs);
			msgs = basicSetDatabase(newDatabase, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.APPLICATION_MODEL__DATABASE, newDatabase, newDatabase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AuthenticationConfig getAuthentication() {
		return authentication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAuthentication(AuthenticationConfig newAuthentication, NotificationChain msgs) {
		AuthenticationConfig oldAuthentication = authentication;
		authentication = newAuthentication;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.APPLICATION_MODEL__AUTHENTICATION, oldAuthentication, newAuthentication);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAuthentication(AuthenticationConfig newAuthentication) {
		if (newAuthentication != authentication) {
			NotificationChain msgs = null;
			if (authentication != null)
				msgs = ((InternalEObject)authentication).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - APIMetamodelPackage.APPLICATION_MODEL__AUTHENTICATION, null, msgs);
			if (newAuthentication != null)
				msgs = ((InternalEObject)newAuthentication).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - APIMetamodelPackage.APPLICATION_MODEL__AUTHENTICATION, null, msgs);
			msgs = basicSetAuthentication(newAuthentication, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.APPLICATION_MODEL__AUTHENTICATION, newAuthentication, newAuthentication));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public APIFeatures getApiFeatures() {
		return apiFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetApiFeatures(APIFeatures newApiFeatures, NotificationChain msgs) {
		APIFeatures oldApiFeatures = apiFeatures;
		apiFeatures = newApiFeatures;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.APPLICATION_MODEL__API_FEATURES, oldApiFeatures, newApiFeatures);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setApiFeatures(APIFeatures newApiFeatures) {
		if (newApiFeatures != apiFeatures) {
			NotificationChain msgs = null;
			if (apiFeatures != null)
				msgs = ((InternalEObject)apiFeatures).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - APIMetamodelPackage.APPLICATION_MODEL__API_FEATURES, null, msgs);
			if (newApiFeatures != null)
				msgs = ((InternalEObject)newApiFeatures).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - APIMetamodelPackage.APPLICATION_MODEL__API_FEATURES, null, msgs);
			msgs = basicSetApiFeatures(newApiFeatures, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.APPLICATION_MODEL__API_FEATURES, newApiFeatures, newApiFeatures));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case APIMetamodelPackage.APPLICATION_MODEL__ENTITIES:
				return ((InternalEList<?>)getEntities()).basicRemove(otherEnd, msgs);
			case APIMetamodelPackage.APPLICATION_MODEL__INTERACTIONS:
				return ((InternalEList<?>)getInteractions()).basicRemove(otherEnd, msgs);
			case APIMetamodelPackage.APPLICATION_MODEL__DATABASE:
				return basicSetDatabase(null, msgs);
			case APIMetamodelPackage.APPLICATION_MODEL__AUTHENTICATION:
				return basicSetAuthentication(null, msgs);
			case APIMetamodelPackage.APPLICATION_MODEL__API_FEATURES:
				return basicSetApiFeatures(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case APIMetamodelPackage.APPLICATION_MODEL__PROJECT_NAME:
				return getProjectName();
			case APIMetamodelPackage.APPLICATION_MODEL__FRAMEWORK:
				return getFramework();
			case APIMetamodelPackage.APPLICATION_MODEL__PYTHON_VERSION:
				return getPythonVersion();
			case APIMetamodelPackage.APPLICATION_MODEL__DESCRIPTION:
				return getDescription();
			case APIMetamodelPackage.APPLICATION_MODEL__ENTITIES:
				return getEntities();
			case APIMetamodelPackage.APPLICATION_MODEL__INTERACTIONS:
				return getInteractions();
			case APIMetamodelPackage.APPLICATION_MODEL__DATABASE:
				return getDatabase();
			case APIMetamodelPackage.APPLICATION_MODEL__AUTHENTICATION:
				return getAuthentication();
			case APIMetamodelPackage.APPLICATION_MODEL__API_FEATURES:
				return getApiFeatures();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case APIMetamodelPackage.APPLICATION_MODEL__PROJECT_NAME:
				setProjectName((String)newValue);
				return;
			case APIMetamodelPackage.APPLICATION_MODEL__FRAMEWORK:
				setFramework((String)newValue);
				return;
			case APIMetamodelPackage.APPLICATION_MODEL__PYTHON_VERSION:
				setPythonVersion((String)newValue);
				return;
			case APIMetamodelPackage.APPLICATION_MODEL__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case APIMetamodelPackage.APPLICATION_MODEL__ENTITIES:
				getEntities().clear();
				getEntities().addAll((Collection<? extends Entity>)newValue);
				return;
			case APIMetamodelPackage.APPLICATION_MODEL__INTERACTIONS:
				getInteractions().clear();
				getInteractions().addAll((Collection<? extends Interaction>)newValue);
				return;
			case APIMetamodelPackage.APPLICATION_MODEL__DATABASE:
				setDatabase((DatabaseConfig)newValue);
				return;
			case APIMetamodelPackage.APPLICATION_MODEL__AUTHENTICATION:
				setAuthentication((AuthenticationConfig)newValue);
				return;
			case APIMetamodelPackage.APPLICATION_MODEL__API_FEATURES:
				setApiFeatures((APIFeatures)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case APIMetamodelPackage.APPLICATION_MODEL__PROJECT_NAME:
				setProjectName(PROJECT_NAME_EDEFAULT);
				return;
			case APIMetamodelPackage.APPLICATION_MODEL__FRAMEWORK:
				setFramework(FRAMEWORK_EDEFAULT);
				return;
			case APIMetamodelPackage.APPLICATION_MODEL__PYTHON_VERSION:
				setPythonVersion(PYTHON_VERSION_EDEFAULT);
				return;
			case APIMetamodelPackage.APPLICATION_MODEL__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case APIMetamodelPackage.APPLICATION_MODEL__ENTITIES:
				getEntities().clear();
				return;
			case APIMetamodelPackage.APPLICATION_MODEL__INTERACTIONS:
				getInteractions().clear();
				return;
			case APIMetamodelPackage.APPLICATION_MODEL__DATABASE:
				setDatabase((DatabaseConfig)null);
				return;
			case APIMetamodelPackage.APPLICATION_MODEL__AUTHENTICATION:
				setAuthentication((AuthenticationConfig)null);
				return;
			case APIMetamodelPackage.APPLICATION_MODEL__API_FEATURES:
				setApiFeatures((APIFeatures)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case APIMetamodelPackage.APPLICATION_MODEL__PROJECT_NAME:
				return PROJECT_NAME_EDEFAULT == null ? projectName != null : !PROJECT_NAME_EDEFAULT.equals(projectName);
			case APIMetamodelPackage.APPLICATION_MODEL__FRAMEWORK:
				return FRAMEWORK_EDEFAULT == null ? framework != null : !FRAMEWORK_EDEFAULT.equals(framework);
			case APIMetamodelPackage.APPLICATION_MODEL__PYTHON_VERSION:
				return PYTHON_VERSION_EDEFAULT == null ? pythonVersion != null : !PYTHON_VERSION_EDEFAULT.equals(pythonVersion);
			case APIMetamodelPackage.APPLICATION_MODEL__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case APIMetamodelPackage.APPLICATION_MODEL__ENTITIES:
				return entities != null && !entities.isEmpty();
			case APIMetamodelPackage.APPLICATION_MODEL__INTERACTIONS:
				return interactions != null && !interactions.isEmpty();
			case APIMetamodelPackage.APPLICATION_MODEL__DATABASE:
				return database != null;
			case APIMetamodelPackage.APPLICATION_MODEL__AUTHENTICATION:
				return authentication != null;
			case APIMetamodelPackage.APPLICATION_MODEL__API_FEATURES:
				return apiFeatures != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (projectName: ");
		result.append(projectName);
		result.append(", framework: ");
		result.append(framework);
		result.append(", pythonVersion: ");
		result.append(pythonVersion);
		result.append(", description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //ApplicationModelImpl
