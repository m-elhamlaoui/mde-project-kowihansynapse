/**
 */
package APIMetamodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Authentication Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link APIMetamodel.AuthenticationConfig#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link APIMetamodel.AuthenticationConfig#getMethod <em>Method</em>}</li>
 *   <li>{@link APIMetamodel.AuthenticationConfig#getTokenExpiryMinutes <em>Token Expiry Minutes</em>}</li>
 * </ul>
 *
 * @see APIMetamodel.APIMetamodelPackage#getAuthenticationConfig()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL invariants='null'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ValidAuthMethod ValidTokenExpiry'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot ValidAuthMethod='Set{\'JWT\', \'SESSION\', \'OAUTH2\', \'BASIC\'}-&gt;includes(self.method)' ValidTokenExpiry='self.tokenExpiryMinutes &gt;= 5 and self.tokenExpiryMinutes &lt;= 10080'"
 * @generated
 */
public interface AuthenticationConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(boolean)
	 * @see APIMetamodel.APIMetamodelPackage#getAuthenticationConfig_Enabled()
	 * @model
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link APIMetamodel.AuthenticationConfig#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method</em>' attribute.
	 * @see #setMethod(String)
	 * @see APIMetamodel.APIMetamodelPackage#getAuthenticationConfig_Method()
	 * @model
	 * @generated
	 */
	String getMethod();

	/**
	 * Sets the value of the '{@link APIMetamodel.AuthenticationConfig#getMethod <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method</em>' attribute.
	 * @see #getMethod()
	 * @generated
	 */
	void setMethod(String value);

	/**
	 * Returns the value of the '<em><b>Token Expiry Minutes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Token Expiry Minutes</em>' attribute.
	 * @see #setTokenExpiryMinutes(int)
	 * @see APIMetamodel.APIMetamodelPackage#getAuthenticationConfig_TokenExpiryMinutes()
	 * @model
	 * @generated
	 */
	int getTokenExpiryMinutes();

	/**
	 * Sets the value of the '{@link APIMetamodel.AuthenticationConfig#getTokenExpiryMinutes <em>Token Expiry Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Token Expiry Minutes</em>' attribute.
	 * @see #getTokenExpiryMinutes()
	 * @generated
	 */
	void setTokenExpiryMinutes(int value);

} // AuthenticationConfig
