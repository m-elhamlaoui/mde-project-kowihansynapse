/**
 */
package APIMetamodel.impl;

import APIMetamodel.APIMetamodelPackage;
import APIMetamodel.Interaction;
import APIMetamodel.Message;
import APIMetamodel.Participant;

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
 * An implementation of the model object '<em><b>Interaction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link APIMetamodel.impl.InteractionImpl#getName <em>Name</em>}</li>
 *   <li>{@link APIMetamodel.impl.InteractionImpl#getUseCaseDescription <em>Use Case Description</em>}</li>
 *   <li>{@link APIMetamodel.impl.InteractionImpl#getHttpMethod <em>Http Method</em>}</li>
 *   <li>{@link APIMetamodel.impl.InteractionImpl#getEndpoint <em>Endpoint</em>}</li>
 *   <li>{@link APIMetamodel.impl.InteractionImpl#getParticipants <em>Participants</em>}</li>
 *   <li>{@link APIMetamodel.impl.InteractionImpl#getMessages <em>Messages</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InteractionImpl extends MinimalEObjectImpl.Container implements Interaction {
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
	 * The default value of the '{@link #getUseCaseDescription() <em>Use Case Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUseCaseDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String USE_CASE_DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUseCaseDescription() <em>Use Case Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUseCaseDescription()
	 * @generated
	 * @ordered
	 */
	protected String useCaseDescription = USE_CASE_DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getHttpMethod() <em>Http Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpMethod()
	 * @generated
	 * @ordered
	 */
	protected static final String HTTP_METHOD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHttpMethod() <em>Http Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHttpMethod()
	 * @generated
	 * @ordered
	 */
	protected String httpMethod = HTTP_METHOD_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndpoint() <em>Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndpoint()
	 * @generated
	 * @ordered
	 */
	protected static final String ENDPOINT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEndpoint() <em>Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndpoint()
	 * @generated
	 * @ordered
	 */
	protected String endpoint = ENDPOINT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParticipants() <em>Participants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipants()
	 * @generated
	 * @ordered
	 */
	protected EList<Participant> participants;

	/**
	 * The cached value of the '{@link #getMessages() <em>Messages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessages()
	 * @generated
	 * @ordered
	 */
	protected EList<Message> messages;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InteractionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return APIMetamodelPackage.Literals.INTERACTION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.INTERACTION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getUseCaseDescription() {
		return useCaseDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUseCaseDescription(String newUseCaseDescription) {
		String oldUseCaseDescription = useCaseDescription;
		useCaseDescription = newUseCaseDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.INTERACTION__USE_CASE_DESCRIPTION, oldUseCaseDescription, useCaseDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getHttpMethod() {
		return httpMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHttpMethod(String newHttpMethod) {
		String oldHttpMethod = httpMethod;
		httpMethod = newHttpMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.INTERACTION__HTTP_METHOD, oldHttpMethod, httpMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEndpoint(String newEndpoint) {
		String oldEndpoint = endpoint;
		endpoint = newEndpoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, APIMetamodelPackage.INTERACTION__ENDPOINT, oldEndpoint, endpoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Participant> getParticipants() {
		if (participants == null) {
			participants = new EObjectContainmentEList<Participant>(Participant.class, this, APIMetamodelPackage.INTERACTION__PARTICIPANTS);
		}
		return participants;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Message> getMessages() {
		if (messages == null) {
			messages = new EObjectContainmentEList<Message>(Message.class, this, APIMetamodelPackage.INTERACTION__MESSAGES);
		}
		return messages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case APIMetamodelPackage.INTERACTION__PARTICIPANTS:
				return ((InternalEList<?>)getParticipants()).basicRemove(otherEnd, msgs);
			case APIMetamodelPackage.INTERACTION__MESSAGES:
				return ((InternalEList<?>)getMessages()).basicRemove(otherEnd, msgs);
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
			case APIMetamodelPackage.INTERACTION__NAME:
				return getName();
			case APIMetamodelPackage.INTERACTION__USE_CASE_DESCRIPTION:
				return getUseCaseDescription();
			case APIMetamodelPackage.INTERACTION__HTTP_METHOD:
				return getHttpMethod();
			case APIMetamodelPackage.INTERACTION__ENDPOINT:
				return getEndpoint();
			case APIMetamodelPackage.INTERACTION__PARTICIPANTS:
				return getParticipants();
			case APIMetamodelPackage.INTERACTION__MESSAGES:
				return getMessages();
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
			case APIMetamodelPackage.INTERACTION__NAME:
				setName((String)newValue);
				return;
			case APIMetamodelPackage.INTERACTION__USE_CASE_DESCRIPTION:
				setUseCaseDescription((String)newValue);
				return;
			case APIMetamodelPackage.INTERACTION__HTTP_METHOD:
				setHttpMethod((String)newValue);
				return;
			case APIMetamodelPackage.INTERACTION__ENDPOINT:
				setEndpoint((String)newValue);
				return;
			case APIMetamodelPackage.INTERACTION__PARTICIPANTS:
				getParticipants().clear();
				getParticipants().addAll((Collection<? extends Participant>)newValue);
				return;
			case APIMetamodelPackage.INTERACTION__MESSAGES:
				getMessages().clear();
				getMessages().addAll((Collection<? extends Message>)newValue);
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
			case APIMetamodelPackage.INTERACTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case APIMetamodelPackage.INTERACTION__USE_CASE_DESCRIPTION:
				setUseCaseDescription(USE_CASE_DESCRIPTION_EDEFAULT);
				return;
			case APIMetamodelPackage.INTERACTION__HTTP_METHOD:
				setHttpMethod(HTTP_METHOD_EDEFAULT);
				return;
			case APIMetamodelPackage.INTERACTION__ENDPOINT:
				setEndpoint(ENDPOINT_EDEFAULT);
				return;
			case APIMetamodelPackage.INTERACTION__PARTICIPANTS:
				getParticipants().clear();
				return;
			case APIMetamodelPackage.INTERACTION__MESSAGES:
				getMessages().clear();
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
			case APIMetamodelPackage.INTERACTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case APIMetamodelPackage.INTERACTION__USE_CASE_DESCRIPTION:
				return USE_CASE_DESCRIPTION_EDEFAULT == null ? useCaseDescription != null : !USE_CASE_DESCRIPTION_EDEFAULT.equals(useCaseDescription);
			case APIMetamodelPackage.INTERACTION__HTTP_METHOD:
				return HTTP_METHOD_EDEFAULT == null ? httpMethod != null : !HTTP_METHOD_EDEFAULT.equals(httpMethod);
			case APIMetamodelPackage.INTERACTION__ENDPOINT:
				return ENDPOINT_EDEFAULT == null ? endpoint != null : !ENDPOINT_EDEFAULT.equals(endpoint);
			case APIMetamodelPackage.INTERACTION__PARTICIPANTS:
				return participants != null && !participants.isEmpty();
			case APIMetamodelPackage.INTERACTION__MESSAGES:
				return messages != null && !messages.isEmpty();
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
		result.append(", useCaseDescription: ");
		result.append(useCaseDescription);
		result.append(", httpMethod: ");
		result.append(httpMethod);
		result.append(", endpoint: ");
		result.append(endpoint);
		result.append(')');
		return result.toString();
	}

} //InteractionImpl
