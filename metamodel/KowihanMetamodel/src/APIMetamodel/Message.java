/**
 */
package APIMetamodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link APIMetamodel.Message#getSequenceNumber <em>Sequence Number</em>}</li>
 *   <li>{@link APIMetamodel.Message#getOperation <em>Operation</em>}</li>
 *   <li>{@link APIMetamodel.Message#getMessageType <em>Message Type</em>}</li>
 *   <li>{@link APIMetamodel.Message#getFromParticipant <em>From Participant</em>}</li>
 *   <li>{@link APIMetamodel.Message#getToParticipant <em>To Participant</em>}</li>
 * </ul>
 *
 * @see APIMetamodel.APIMetamodelPackage#getMessage()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL invariants='null'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ValidSequenceNumber ValidMessageType ValidParticipants ValidOperation'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot ValidSequenceNumber='self.sequenceNumber &gt; 0' ValidMessageType='Set{\'SYNCHRONOUS\', \'ASYNCHRONOUS\', \'REPLY\', \'CREATE\'}-&gt;includes(self.messageType)' ValidParticipants='self.fromParticipant &lt;&gt; self.toParticipant' ValidOperation='not self.operation.oclIsUndefined() and self.operation.size() &gt; 0'"
 * @generated
 */
public interface Message extends EObject {
	/**
	 * Returns the value of the '<em><b>Sequence Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sequence Number</em>' attribute.
	 * @see #setSequenceNumber(int)
	 * @see APIMetamodel.APIMetamodelPackage#getMessage_SequenceNumber()
	 * @model
	 * @generated
	 */
	int getSequenceNumber();

	/**
	 * Sets the value of the '{@link APIMetamodel.Message#getSequenceNumber <em>Sequence Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sequence Number</em>' attribute.
	 * @see #getSequenceNumber()
	 * @generated
	 */
	void setSequenceNumber(int value);

	/**
	 * Returns the value of the '<em><b>Operation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' attribute.
	 * @see #setOperation(String)
	 * @see APIMetamodel.APIMetamodelPackage#getMessage_Operation()
	 * @model
	 * @generated
	 */
	String getOperation();

	/**
	 * Sets the value of the '{@link APIMetamodel.Message#getOperation <em>Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' attribute.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(String value);

	/**
	 * Returns the value of the '<em><b>Message Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Type</em>' attribute.
	 * @see #setMessageType(String)
	 * @see APIMetamodel.APIMetamodelPackage#getMessage_MessageType()
	 * @model
	 * @generated
	 */
	String getMessageType();

	/**
	 * Sets the value of the '{@link APIMetamodel.Message#getMessageType <em>Message Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Type</em>' attribute.
	 * @see #getMessageType()
	 * @generated
	 */
	void setMessageType(String value);

	/**
	 * Returns the value of the '<em><b>From Participant</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From Participant</em>' reference.
	 * @see #setFromParticipant(Participant)
	 * @see APIMetamodel.APIMetamodelPackage#getMessage_FromParticipant()
	 * @model
	 * @generated
	 */
	Participant getFromParticipant();

	/**
	 * Sets the value of the '{@link APIMetamodel.Message#getFromParticipant <em>From Participant</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From Participant</em>' reference.
	 * @see #getFromParticipant()
	 * @generated
	 */
	void setFromParticipant(Participant value);

	/**
	 * Returns the value of the '<em><b>To Participant</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To Participant</em>' reference.
	 * @see #setToParticipant(Participant)
	 * @see APIMetamodel.APIMetamodelPackage#getMessage_ToParticipant()
	 * @model
	 * @generated
	 */
	Participant getToParticipant();

	/**
	 * Sets the value of the '{@link APIMetamodel.Message#getToParticipant <em>To Participant</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Participant</em>' reference.
	 * @see #getToParticipant()
	 * @generated
	 */
	void setToParticipant(Participant value);

} // Message
