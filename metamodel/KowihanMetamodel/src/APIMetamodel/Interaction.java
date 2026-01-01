/**
 */
package APIMetamodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interaction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link APIMetamodel.Interaction#getName <em>Name</em>}</li>
 *   <li>{@link APIMetamodel.Interaction#getUseCaseDescription <em>Use Case Description</em>}</li>
 *   <li>{@link APIMetamodel.Interaction#getHttpMethod <em>Http Method</em>}</li>
 *   <li>{@link APIMetamodel.Interaction#getEndpoint <em>Endpoint</em>}</li>
 *   <li>{@link APIMetamodel.Interaction#getParticipants <em>Participants</em>}</li>
 *   <li>{@link APIMetamodel.Interaction#getMessages <em>Messages</em>}</li>
 * </ul>
 *
 * @see APIMetamodel.APIMetamodelPackage#getInteraction()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL invariants='null'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ValidInteractionName ValidHTTPMethod HasParticipants ValidEndpoint'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot ValidInteractionName='self.name.matches(\'[A-Z][a-zA-Z0-9_]*\')' ValidHTTPMethod='Set{\'GET\', \'POST\', \'PUT\', \'PATCH\', \'DELETE\'}-&gt;includes(self.httpMethod)' HasParticipants='self.participants-&gt;size() &gt;= 2' ValidEndpoint='self.endpoint.startsWith(\'/\') and self.endpoint.matches(\'/[a-zA-Z0-9_/-]*\')'"
 * @generated
 */
public interface Interaction extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see APIMetamodel.APIMetamodelPackage#getInteraction_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link APIMetamodel.Interaction#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Use Case Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Use Case Description</em>' attribute.
	 * @see #setUseCaseDescription(String)
	 * @see APIMetamodel.APIMetamodelPackage#getInteraction_UseCaseDescription()
	 * @model
	 * @generated
	 */
	String getUseCaseDescription();

	/**
	 * Sets the value of the '{@link APIMetamodel.Interaction#getUseCaseDescription <em>Use Case Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Use Case Description</em>' attribute.
	 * @see #getUseCaseDescription()
	 * @generated
	 */
	void setUseCaseDescription(String value);

	/**
	 * Returns the value of the '<em><b>Http Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Http Method</em>' attribute.
	 * @see #setHttpMethod(String)
	 * @see APIMetamodel.APIMetamodelPackage#getInteraction_HttpMethod()
	 * @model
	 * @generated
	 */
	String getHttpMethod();

	/**
	 * Sets the value of the '{@link APIMetamodel.Interaction#getHttpMethod <em>Http Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Http Method</em>' attribute.
	 * @see #getHttpMethod()
	 * @generated
	 */
	void setHttpMethod(String value);

	/**
	 * Returns the value of the '<em><b>Endpoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Endpoint</em>' attribute.
	 * @see #setEndpoint(String)
	 * @see APIMetamodel.APIMetamodelPackage#getInteraction_Endpoint()
	 * @model
	 * @generated
	 */
	String getEndpoint();

	/**
	 * Sets the value of the '{@link APIMetamodel.Interaction#getEndpoint <em>Endpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Endpoint</em>' attribute.
	 * @see #getEndpoint()
	 * @generated
	 */
	void setEndpoint(String value);

	/**
	 * Returns the value of the '<em><b>Participants</b></em>' containment reference list.
	 * The list contents are of type {@link APIMetamodel.Participant}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participants</em>' containment reference list.
	 * @see APIMetamodel.APIMetamodelPackage#getInteraction_Participants()
	 * @model containment="true"
	 * @generated
	 */
	EList<Participant> getParticipants();

	/**
	 * Returns the value of the '<em><b>Messages</b></em>' containment reference list.
	 * The list contents are of type {@link APIMetamodel.Message}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Messages</em>' containment reference list.
	 * @see APIMetamodel.APIMetamodelPackage#getInteraction_Messages()
	 * @model containment="true"
	 * @generated
	 */
	EList<Message> getMessages();

} // Interaction
