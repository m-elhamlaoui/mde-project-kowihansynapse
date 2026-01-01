/**
 */
package APIMetamodel.impl;

import APIMetamodel.APIMetamodelPackage;
import APIMetamodel.Message;
import APIMetamodel.Participant;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Message</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link APIMetamodel.impl.MessageImpl#getSequenceNumber <em>Sequence Number</em>}</li>
 *   <li>{@link APIMetamodel.impl.MessageImpl#getOperation <em>Operation</em>}</li>
 *   <li>{@link APIMetamodel.impl.MessageImpl#getMessageType <em>Message Type</em>}</li>
 *   <li>{@link APIMetamodel.impl.MessageImpl#getFromParticipant <em>From Participant</em>}</li>
 *   <li>{@link APIMetamodel.impl.MessageImpl#getToParticipant <em>To Participant</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MessageImpl extends MinimalEObjectImpl.Container implements Message {
	/**
	 * The default value of the '{@link #getSequenceNumber() <em>Sequence Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSequenceNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int SEQUENCE_NUMBER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSequenceNumber() <em>Sequence Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSequenceNumber()
	 * @generated
	 * @ordered
	 */
	protected int sequenceNumber = SEQUENCE_NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getOperation() <em>Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperation()
	 * @generated
	 * @ordered
	 */
	protected static final String OPERATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOperation() <em>Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperation()
	 * @generated
	 * @ordered
	 */
	protected String operation = OPERATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMessageType() <em>Message Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageType()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessageType() <em>Message Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageType()
	 * @generated
	 * @ordered
	 */
	protected String messageType = MESSAGE_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFromParticipant() <em>From Participant</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFromParticipant()
	 * @generated
	 * @ordered
	 */
	protected Participant fromParticipant;

	/**
	 * The cached value of the '{@link #getToParticipant() <em>To Participant</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToParticipant()
	 * @generated
	 * @ordered
	 */
	protected Participant toParticipant;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MessageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return APIMetamodelPackage.Literals.MESSAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSequenceNumber(int newSequenceNumber) {
		int oldSequenceNumber = sequenceNumber;
		sequenceNumber = newSequenceNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.MESSAGE__SEQUENCE_NUMBER, oldSequenceNumber, sequenceNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOperation() {
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOperation(String newOperation) {
		String oldOperation = operation;
		operation = newOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.MESSAGE__OPERATION, oldOperation, operation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMessageType() {
		return messageType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMessageType(String newMessageType) {
		String oldMessageType = messageType;
		messageType = newMessageType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.MESSAGE__MESSAGE_TYPE, oldMessageType, messageType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Participant getFromParticipant() {
		if (fromParticipant != null && fromParticipant.eIsProxy()) {
			InternalEObject oldFromParticipant = (InternalEObject)fromParticipant;
			fromParticipant = (Participant)eResolveProxy(oldFromParticipant);
			if (fromParticipant != oldFromParticipant) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, APIMetamodelPackage.MESSAGE__FROM_PARTICIPANT, oldFromParticipant, fromParticipant));
			}
		}
		return fromParticipant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Participant basicGetFromParticipant() {
		return fromParticipant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFromParticipant(Participant newFromParticipant) {
		Participant oldFromParticipant = fromParticipant;
		fromParticipant = newFromParticipant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.MESSAGE__FROM_PARTICIPANT, oldFromParticipant, fromParticipant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Participant getToParticipant() {
		if (toParticipant != null && toParticipant.eIsProxy()) {
			InternalEObject oldToParticipant = (InternalEObject)toParticipant;
			toParticipant = (Participant)eResolveProxy(oldToParticipant);
			if (toParticipant != oldToParticipant) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, APIMetamodelPackage.MESSAGE__TO_PARTICIPANT, oldToParticipant, toParticipant));
			}
		}
		return toParticipant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Participant basicGetToParticipant() {
		return toParticipant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setToParticipant(Participant newToParticipant) {
		Participant oldToParticipant = toParticipant;
		toParticipant = newToParticipant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.MESSAGE__TO_PARTICIPANT, oldToParticipant, toParticipant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case APIMetamodelPackage.MESSAGE__SEQUENCE_NUMBER:
				return getSequenceNumber();
			case APIMetamodelPackage.MESSAGE__OPERATION:
				return getOperation();
			case APIMetamodelPackage.MESSAGE__MESSAGE_TYPE:
				return getMessageType();
			case APIMetamodelPackage.MESSAGE__FROM_PARTICIPANT:
				if (resolve) return getFromParticipant();
				return basicGetFromParticipant();
			case APIMetamodelPackage.MESSAGE__TO_PARTICIPANT:
				if (resolve) return getToParticipant();
				return basicGetToParticipant();
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
			case APIMetamodelPackage.MESSAGE__SEQUENCE_NUMBER:
				setSequenceNumber((Integer)newValue);
				return;
			case APIMetamodelPackage.MESSAGE__OPERATION:
				setOperation((String)newValue);
				return;
			case APIMetamodelPackage.MESSAGE__MESSAGE_TYPE:
				setMessageType((String)newValue);
				return;
			case APIMetamodelPackage.MESSAGE__FROM_PARTICIPANT:
				setFromParticipant((Participant)newValue);
				return;
			case APIMetamodelPackage.MESSAGE__TO_PARTICIPANT:
				setToParticipant((Participant)newValue);
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
			case APIMetamodelPackage.MESSAGE__SEQUENCE_NUMBER:
				setSequenceNumber(SEQUENCE_NUMBER_EDEFAULT);
				return;
			case APIMetamodelPackage.MESSAGE__OPERATION:
				setOperation(OPERATION_EDEFAULT);
				return;
			case APIMetamodelPackage.MESSAGE__MESSAGE_TYPE:
				setMessageType(MESSAGE_TYPE_EDEFAULT);
				return;
			case APIMetamodelPackage.MESSAGE__FROM_PARTICIPANT:
				setFromParticipant((Participant)null);
				return;
			case APIMetamodelPackage.MESSAGE__TO_PARTICIPANT:
				setToParticipant((Participant)null);
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
			case APIMetamodelPackage.MESSAGE__SEQUENCE_NUMBER:
				return sequenceNumber != SEQUENCE_NUMBER_EDEFAULT;
			case APIMetamodelPackage.MESSAGE__OPERATION:
				return OPERATION_EDEFAULT == null ? operation != null : !OPERATION_EDEFAULT.equals(operation);
			case APIMetamodelPackage.MESSAGE__MESSAGE_TYPE:
				return MESSAGE_TYPE_EDEFAULT == null ? messageType != null : !MESSAGE_TYPE_EDEFAULT.equals(messageType);
			case APIMetamodelPackage.MESSAGE__FROM_PARTICIPANT:
				return fromParticipant != null;
			case APIMetamodelPackage.MESSAGE__TO_PARTICIPANT:
				return toParticipant != null;
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
		result.append(" (sequenceNumber: ");
		result.append(sequenceNumber);
		result.append(", operation: ");
		result.append(operation);
		result.append(", messageType: ");
		result.append(messageType);
		result.append(')');
		return result.toString();
	}

} //MessageImpl
