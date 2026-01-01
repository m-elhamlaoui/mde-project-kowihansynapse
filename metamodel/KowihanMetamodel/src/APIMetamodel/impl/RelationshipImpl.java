/**
 */
package APIMetamodel.impl;

import APIMetamodel.APIMetamodelPackage;
import APIMetamodel.Relationship;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relationship</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link APIMetamodel.impl.RelationshipImpl#getName <em>Name</em>}</li>
 *   <li>{@link APIMetamodel.impl.RelationshipImpl#getType <em>Type</em>}</li>
 *   <li>{@link APIMetamodel.impl.RelationshipImpl#getTargetEntity <em>Target Entity</em>}</li>
 *   <li>{@link APIMetamodel.impl.RelationshipImpl#getRelatedName <em>Related Name</em>}</li>
 *   <li>{@link APIMetamodel.impl.RelationshipImpl#getOnDelete <em>On Delete</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelationshipImpl extends MinimalEObjectImpl.Container implements Relationship {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetEntity() <em>Target Entity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetEntity()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_ENTITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetEntity() <em>Target Entity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetEntity()
	 * @generated
	 * @ordered
	 */
	protected String targetEntity = TARGET_ENTITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getRelatedName() <em>Related Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelatedName()
	 * @generated
	 * @ordered
	 */
	protected static final String RELATED_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRelatedName() <em>Related Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelatedName()
	 * @generated
	 * @ordered
	 */
	protected String relatedName = RELATED_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnDelete() <em>On Delete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnDelete()
	 * @generated
	 * @ordered
	 */
	protected static final String ON_DELETE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnDelete() <em>On Delete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnDelete()
	 * @generated
	 * @ordered
	 */
	protected String onDelete = ON_DELETE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelationshipImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return APIMetamodelPackage.Literals.RELATIONSHIP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.RELATIONSHIP__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.RELATIONSHIP__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTargetEntity() {
		return targetEntity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTargetEntity(String newTargetEntity) {
		String oldTargetEntity = targetEntity;
		targetEntity = newTargetEntity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.RELATIONSHIP__TARGET_ENTITY, oldTargetEntity, targetEntity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRelatedName() {
		return relatedName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRelatedName(String newRelatedName) {
		String oldRelatedName = relatedName;
		relatedName = newRelatedName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.RELATIONSHIP__RELATED_NAME, oldRelatedName, relatedName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOnDelete() {
		return onDelete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOnDelete(String newOnDelete) {
		String oldOnDelete = onDelete;
		onDelete = newOnDelete;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.RELATIONSHIP__ON_DELETE, oldOnDelete, onDelete));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case APIMetamodelPackage.RELATIONSHIP__NAME:
				return getName();
			case APIMetamodelPackage.RELATIONSHIP__TYPE:
				return getType();
			case APIMetamodelPackage.RELATIONSHIP__TARGET_ENTITY:
				return getTargetEntity();
			case APIMetamodelPackage.RELATIONSHIP__RELATED_NAME:
				return getRelatedName();
			case APIMetamodelPackage.RELATIONSHIP__ON_DELETE:
				return getOnDelete();
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
			case APIMetamodelPackage.RELATIONSHIP__NAME:
				setName((String)newValue);
				return;
			case APIMetamodelPackage.RELATIONSHIP__TYPE:
				setType((String)newValue);
				return;
			case APIMetamodelPackage.RELATIONSHIP__TARGET_ENTITY:
				setTargetEntity((String)newValue);
				return;
			case APIMetamodelPackage.RELATIONSHIP__RELATED_NAME:
				setRelatedName((String)newValue);
				return;
			case APIMetamodelPackage.RELATIONSHIP__ON_DELETE:
				setOnDelete((String)newValue);
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
			case APIMetamodelPackage.RELATIONSHIP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case APIMetamodelPackage.RELATIONSHIP__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case APIMetamodelPackage.RELATIONSHIP__TARGET_ENTITY:
				setTargetEntity(TARGET_ENTITY_EDEFAULT);
				return;
			case APIMetamodelPackage.RELATIONSHIP__RELATED_NAME:
				setRelatedName(RELATED_NAME_EDEFAULT);
				return;
			case APIMetamodelPackage.RELATIONSHIP__ON_DELETE:
				setOnDelete(ON_DELETE_EDEFAULT);
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
			case APIMetamodelPackage.RELATIONSHIP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case APIMetamodelPackage.RELATIONSHIP__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case APIMetamodelPackage.RELATIONSHIP__TARGET_ENTITY:
				return TARGET_ENTITY_EDEFAULT == null ? targetEntity != null : !TARGET_ENTITY_EDEFAULT.equals(targetEntity);
			case APIMetamodelPackage.RELATIONSHIP__RELATED_NAME:
				return RELATED_NAME_EDEFAULT == null ? relatedName != null : !RELATED_NAME_EDEFAULT.equals(relatedName);
			case APIMetamodelPackage.RELATIONSHIP__ON_DELETE:
				return ON_DELETE_EDEFAULT == null ? onDelete != null : !ON_DELETE_EDEFAULT.equals(onDelete);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", type: ");
		result.append(type);
		result.append(", targetEntity: ");
		result.append(targetEntity);
		result.append(", relatedName: ");
		result.append(relatedName);
		result.append(", onDelete: ");
		result.append(onDelete);
		result.append(')');
		return result.toString();
	}

} //RelationshipImpl
