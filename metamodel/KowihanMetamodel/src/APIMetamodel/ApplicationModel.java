/**
 */
package APIMetamodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Application Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link APIMetamodel.ApplicationModel#getProjectName <em>Project Name</em>}</li>
 *   <li>{@link APIMetamodel.ApplicationModel#getFramework <em>Framework</em>}</li>
 *   <li>{@link APIMetamodel.ApplicationModel#getPythonVersion <em>Python Version</em>}</li>
 *   <li>{@link APIMetamodel.ApplicationModel#getDescription <em>Description</em>}</li>
 *   <li>{@link APIMetamodel.ApplicationModel#getEntities <em>Entities</em>}</li>
 *   <li>{@link APIMetamodel.ApplicationModel#getInteractions <em>Interactions</em>}</li>
 *   <li>{@link APIMetamodel.ApplicationModel#getDatabase <em>Database</em>}</li>
 *   <li>{@link APIMetamodel.ApplicationModel#getAuthentication <em>Authentication</em>}</li>
 *   <li>{@link APIMetamodel.ApplicationModel#getApiFeatures <em>Api Features</em>}</li>
 * </ul>
 *
 * @see APIMetamodel.APIMetamodelPackage#getApplicationModel()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL invariants='null'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ValidPythonVersion ValidProjectName HasEntities ValidFramework'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot ValidPythonVersion='self.pythonVersion.matches(\'3\\.[7-9]|3\\.1[0-2]\')' ValidProjectName='not self.projectName.oclIsUndefined() and self.projectName.size() &gt;= 3 and self.projectName.matches(\'[a-zA-Z_][a-zA-Z0-9_]*\')' HasEntities='self.entities-&gt;size() &gt;= 1' ValidFramework='Set{\'DJANGO\', \'FLASK\', \'FASTAPI\'}-&gt;includes(self.framework)'"
 * @generated
 */
public interface ApplicationModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project Name</em>' attribute.
	 * @see #setProjectName(String)
	 * @see APIMetamodel.APIMetamodelPackage#getApplicationModel_ProjectName()
	 * @model
	 * @generated
	 */
	String getProjectName();

	/**
	 * Sets the value of the '{@link APIMetamodel.ApplicationModel#getProjectName <em>Project Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project Name</em>' attribute.
	 * @see #getProjectName()
	 * @generated
	 */
	void setProjectName(String value);

	/**
	 * Returns the value of the '<em><b>Framework</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Framework</em>' attribute.
	 * @see #setFramework(String)
	 * @see APIMetamodel.APIMetamodelPackage#getApplicationModel_Framework()
	 * @model
	 * @generated
	 */
	String getFramework();

	/**
	 * Sets the value of the '{@link APIMetamodel.ApplicationModel#getFramework <em>Framework</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Framework</em>' attribute.
	 * @see #getFramework()
	 * @generated
	 */
	void setFramework(String value);

	/**
	 * Returns the value of the '<em><b>Python Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Python Version</em>' attribute.
	 * @see #setPythonVersion(String)
	 * @see APIMetamodel.APIMetamodelPackage#getApplicationModel_PythonVersion()
	 * @model
	 * @generated
	 */
	String getPythonVersion();

	/**
	 * Sets the value of the '{@link APIMetamodel.ApplicationModel#getPythonVersion <em>Python Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Python Version</em>' attribute.
	 * @see #getPythonVersion()
	 * @generated
	 */
	void setPythonVersion(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see APIMetamodel.APIMetamodelPackage#getApplicationModel_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link APIMetamodel.ApplicationModel#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Entities</b></em>' containment reference list.
	 * The list contents are of type {@link APIMetamodel.Entity}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entities</em>' containment reference list.
	 * @see APIMetamodel.APIMetamodelPackage#getApplicationModel_Entities()
	 * @model containment="true"
	 * @generated
	 */
	EList<Entity> getEntities();

	/**
	 * Returns the value of the '<em><b>Interactions</b></em>' containment reference list.
	 * The list contents are of type {@link APIMetamodel.Interaction}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interactions</em>' containment reference list.
	 * @see APIMetamodel.APIMetamodelPackage#getApplicationModel_Interactions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Interaction> getInteractions();

	/**
	 * Returns the value of the '<em><b>Database</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Database</em>' containment reference.
	 * @see #setDatabase(DatabaseConfig)
	 * @see APIMetamodel.APIMetamodelPackage#getApplicationModel_Database()
	 * @model containment="true"
	 * @generated
	 */
	DatabaseConfig getDatabase();

	/**
	 * Sets the value of the '{@link APIMetamodel.ApplicationModel#getDatabase <em>Database</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Database</em>' containment reference.
	 * @see #getDatabase()
	 * @generated
	 */
	void setDatabase(DatabaseConfig value);

	/**
	 * Returns the value of the '<em><b>Authentication</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Authentication</em>' containment reference.
	 * @see #setAuthentication(AuthenticationConfig)
	 * @see APIMetamodel.APIMetamodelPackage#getApplicationModel_Authentication()
	 * @model containment="true"
	 * @generated
	 */
	AuthenticationConfig getAuthentication();

	/**
	 * Sets the value of the '{@link APIMetamodel.ApplicationModel#getAuthentication <em>Authentication</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Authentication</em>' containment reference.
	 * @see #getAuthentication()
	 * @generated
	 */
	void setAuthentication(AuthenticationConfig value);

	/**
	 * Returns the value of the '<em><b>Api Features</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Api Features</em>' containment reference.
	 * @see #setApiFeatures(APIFeatures)
	 * @see APIMetamodel.APIMetamodelPackage#getApplicationModel_ApiFeatures()
	 * @model containment="true"
	 * @generated
	 */
	APIFeatures getApiFeatures();

	/**
	 * Sets the value of the '{@link APIMetamodel.ApplicationModel#getApiFeatures <em>Api Features</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Api Features</em>' containment reference.
	 * @see #getApiFeatures()
	 * @generated
	 */
	void setApiFeatures(APIFeatures value);

} // ApplicationModel
