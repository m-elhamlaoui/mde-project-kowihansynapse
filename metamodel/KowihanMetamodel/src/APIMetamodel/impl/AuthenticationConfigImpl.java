/**
 */
package APIMetamodel.impl;

import APIMetamodel.APIMetamodelPackage;
import APIMetamodel.AuthenticationConfig;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Authentication Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link APIMetamodel.impl.AuthenticationConfigImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link APIMetamodel.impl.AuthenticationConfigImpl#getMethod <em>Method</em>}</li>
 *   <li>{@link APIMetamodel.impl.AuthenticationConfigImpl#getTokenExpiryMinutes <em>Token Expiry Minutes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AuthenticationConfigImpl extends MinimalEObjectImpl.Container implements AuthenticationConfig {
	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean enabled = ENABLED_EDEFAULT;

	/**
	 * The default value of the '{@link #getMethod() <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethod()
	 * @generated
	 * @ordered
	 */
	protected static final String METHOD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMethod() <em>Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethod()
	 * @generated
	 * @ordered
	 */
	protected String method = METHOD_EDEFAULT;

	/**
	 * The default value of the '{@link #getTokenExpiryMinutes() <em>Token Expiry Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTokenExpiryMinutes()
	 * @generated
	 * @ordered
	 */
	protected static final int TOKEN_EXPIRY_MINUTES_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTokenExpiryMinutes() <em>Token Expiry Minutes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTokenExpiryMinutes()
	 * @generated
	 * @ordered
	 */
	protected int tokenExpiryMinutes = TOKEN_EXPIRY_MINUTES_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AuthenticationConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return APIMetamodelPackage.Literals.AUTHENTICATION_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnabled(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.AUTHENTICATION_CONFIG__ENABLED, oldEnabled, enabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMethod() {
		return method;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMethod(String newMethod) {
		String oldMethod = method;
		method = newMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.AUTHENTICATION_CONFIG__METHOD, oldMethod, method));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getTokenExpiryMinutes() {
		return tokenExpiryMinutes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTokenExpiryMinutes(int newTokenExpiryMinutes) {
		int oldTokenExpiryMinutes = tokenExpiryMinutes;
		tokenExpiryMinutes = newTokenExpiryMinutes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.AUTHENTICATION_CONFIG__TOKEN_EXPIRY_MINUTES, oldTokenExpiryMinutes, tokenExpiryMinutes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case APIMetamodelPackage.AUTHENTICATION_CONFIG__ENABLED:
				return isEnabled();
			case APIMetamodelPackage.AUTHENTICATION_CONFIG__METHOD:
				return getMethod();
			case APIMetamodelPackage.AUTHENTICATION_CONFIG__TOKEN_EXPIRY_MINUTES:
				return getTokenExpiryMinutes();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case APIMetamodelPackage.AUTHENTICATION_CONFIG__ENABLED:
				setEnabled((Boolean)newValue);
				return;
			case APIMetamodelPackage.AUTHENTICATION_CONFIG__METHOD:
				setMethod((String)newValue);
				return;
			case APIMetamodelPackage.AUTHENTICATION_CONFIG__TOKEN_EXPIRY_MINUTES:
				setTokenExpiryMinutes((Integer)newValue);
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
			case APIMetamodelPackage.AUTHENTICATION_CONFIG__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
				return;
			case APIMetamodelPackage.AUTHENTICATION_CONFIG__METHOD:
				setMethod(METHOD_EDEFAULT);
				return;
			case APIMetamodelPackage.AUTHENTICATION_CONFIG__TOKEN_EXPIRY_MINUTES:
				setTokenExpiryMinutes(TOKEN_EXPIRY_MINUTES_EDEFAULT);
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
			case APIMetamodelPackage.AUTHENTICATION_CONFIG__ENABLED:
				return enabled != ENABLED_EDEFAULT;
			case APIMetamodelPackage.AUTHENTICATION_CONFIG__METHOD:
				return METHOD_EDEFAULT == null ? method != null : !METHOD_EDEFAULT.equals(method);
			case APIMetamodelPackage.AUTHENTICATION_CONFIG__TOKEN_EXPIRY_MINUTES:
				return tokenExpiryMinutes != TOKEN_EXPIRY_MINUTES_EDEFAULT;
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
		result.append(" (enabled: ");
		result.append(enabled);
		result.append(", method: ");
		result.append(method);
		result.append(", tokenExpiryMinutes: ");
		result.append(tokenExpiryMinutes);
		result.append(')');
		return result.toString();
	}

} //AuthenticationConfigImpl
