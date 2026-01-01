/**
 */
package APIMetamodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Database Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link APIMetamodel.DatabaseConfig#getType <em>Type</em>}</li>
 *   <li>{@link APIMetamodel.DatabaseConfig#getHost <em>Host</em>}</li>
 *   <li>{@link APIMetamodel.DatabaseConfig#getPort <em>Port</em>}</li>
 *   <li>{@link APIMetamodel.DatabaseConfig#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see APIMetamodel.APIMetamodelPackage#getDatabaseConfig()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL invariants='null'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ValidDatabaseType ValidPort ValidDatabaseName'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot ValidDatabaseType='Set{\'POSTGRESQL\', \'MYSQL\', \'SQLITE\', \'MONGODB\'}-&gt;includes(self.type)' ValidPort='self.port &gt; 0 and self.port &lt;= 65535' ValidDatabaseName='not self.name.oclIsUndefined() and self.name.size() &gt; 0 and self.name.matches(\'[a-zA-Z_][a-zA-Z0-9_]*\')'"
 * @generated
 */
public interface DatabaseConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see APIMetamodel.APIMetamodelPackage#getDatabaseConfig_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link APIMetamodel.DatabaseConfig#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Host</em>' attribute.
	 * @see #setHost(String)
	 * @see APIMetamodel.APIMetamodelPackage#getDatabaseConfig_Host()
	 * @model
	 * @generated
	 */
	String getHost();

	/**
	 * Sets the value of the '{@link APIMetamodel.DatabaseConfig#getHost <em>Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Host</em>' attribute.
	 * @see #getHost()
	 * @generated
	 */
	void setHost(String value);

	/**
	 * Returns the value of the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port</em>' attribute.
	 * @see #setPort(int)
	 * @see APIMetamodel.APIMetamodelPackage#getDatabaseConfig_Port()
	 * @model
	 * @generated
	 */
	int getPort();

	/**
	 * Sets the value of the '{@link APIMetamodel.DatabaseConfig#getPort <em>Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port</em>' attribute.
	 * @see #getPort()
	 * @generated
	 */
	void setPort(int value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see APIMetamodel.APIMetamodelPackage#getDatabaseConfig_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link APIMetamodel.DatabaseConfig#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // DatabaseConfig
