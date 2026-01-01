/**
 */
package APIMetamodel.impl;

import APIMetamodel.APIFeatures;
import APIMetamodel.APIMetamodelPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>API Features</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link APIMetamodel.impl.APIFeaturesImpl#isPagination <em>Pagination</em>}</li>
 *   <li>{@link APIMetamodel.impl.APIFeaturesImpl#isFiltering <em>Filtering</em>}</li>
 *   <li>{@link APIMetamodel.impl.APIFeaturesImpl#isSwagger <em>Swagger</em>}</li>
 *   <li>{@link APIMetamodel.impl.APIFeaturesImpl#isCorsEnabled <em>Cors Enabled</em>}</li>
 * </ul>
 *
 * @generated
 */
public class APIFeaturesImpl extends MinimalEObjectImpl.Container implements APIFeatures {
	/**
	 * The default value of the '{@link #isPagination() <em>Pagination</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPagination()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PAGINATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPagination() <em>Pagination</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPagination()
	 * @generated
	 * @ordered
	 */
	protected boolean pagination = PAGINATION_EDEFAULT;

	/**
	 * The default value of the '{@link #isFiltering() <em>Filtering</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFiltering()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FILTERING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFiltering() <em>Filtering</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFiltering()
	 * @generated
	 * @ordered
	 */
	protected boolean filtering = FILTERING_EDEFAULT;

	/**
	 * The default value of the '{@link #isSwagger() <em>Swagger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSwagger()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SWAGGER_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSwagger() <em>Swagger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSwagger()
	 * @generated
	 * @ordered
	 */
	protected boolean swagger = SWAGGER_EDEFAULT;

	/**
	 * The default value of the '{@link #isCorsEnabled() <em>Cors Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCorsEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CORS_ENABLED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCorsEnabled() <em>Cors Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCorsEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean corsEnabled = CORS_ENABLED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected APIFeaturesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return APIMetamodelPackage.Literals.API_FEATURES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isPagination() {
		return pagination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPagination(boolean newPagination) {
		boolean oldPagination = pagination;
		pagination = newPagination;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.API_FEATURES__PAGINATION, oldPagination, pagination));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFiltering() {
		return filtering;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFiltering(boolean newFiltering) {
		boolean oldFiltering = filtering;
		filtering = newFiltering;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.API_FEATURES__FILTERING, oldFiltering, filtering));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSwagger() {
		return swagger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSwagger(boolean newSwagger) {
		boolean oldSwagger = swagger;
		swagger = newSwagger;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.API_FEATURES__SWAGGER, oldSwagger, swagger));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isCorsEnabled() {
		return corsEnabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCorsEnabled(boolean newCorsEnabled) {
		boolean oldCorsEnabled = corsEnabled;
		corsEnabled = newCorsEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.API_FEATURES__CORS_ENABLED, oldCorsEnabled, corsEnabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case APIMetamodelPackage.API_FEATURES__PAGINATION:
				return isPagination();
			case APIMetamodelPackage.API_FEATURES__FILTERING:
				return isFiltering();
			case APIMetamodelPackage.API_FEATURES__SWAGGER:
				return isSwagger();
			case APIMetamodelPackage.API_FEATURES__CORS_ENABLED:
				return isCorsEnabled();
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
			case APIMetamodelPackage.API_FEATURES__PAGINATION:
				setPagination((Boolean)newValue);
				return;
			case APIMetamodelPackage.API_FEATURES__FILTERING:
				setFiltering((Boolean)newValue);
				return;
			case APIMetamodelPackage.API_FEATURES__SWAGGER:
				setSwagger((Boolean)newValue);
				return;
			case APIMetamodelPackage.API_FEATURES__CORS_ENABLED:
				setCorsEnabled((Boolean)newValue);
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
			case APIMetamodelPackage.API_FEATURES__PAGINATION:
				setPagination(PAGINATION_EDEFAULT);
				return;
			case APIMetamodelPackage.API_FEATURES__FILTERING:
				setFiltering(FILTERING_EDEFAULT);
				return;
			case APIMetamodelPackage.API_FEATURES__SWAGGER:
				setSwagger(SWAGGER_EDEFAULT);
				return;
			case APIMetamodelPackage.API_FEATURES__CORS_ENABLED:
				setCorsEnabled(CORS_ENABLED_EDEFAULT);
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
			case APIMetamodelPackage.API_FEATURES__PAGINATION:
				return pagination != PAGINATION_EDEFAULT;
			case APIMetamodelPackage.API_FEATURES__FILTERING:
				return filtering != FILTERING_EDEFAULT;
			case APIMetamodelPackage.API_FEATURES__SWAGGER:
				return swagger != SWAGGER_EDEFAULT;
			case APIMetamodelPackage.API_FEATURES__CORS_ENABLED:
				return corsEnabled != CORS_ENABLED_EDEFAULT;
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
		result.append(" (pagination: ");
		result.append(pagination);
		result.append(", filtering: ");
		result.append(filtering);
		result.append(", swagger: ");
		result.append(swagger);
		result.append(", corsEnabled: ");
		result.append(corsEnabled);
		result.append(')');
		return result.toString();
	}

} //APIFeaturesImpl
