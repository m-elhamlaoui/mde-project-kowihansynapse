/**
 */
package APIMetamodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Participant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link APIMetamodel.Participant#getName <em>Name</em>}</li>
 *   <li>{@link APIMetamodel.Participant#getActorType <em>Actor Type</em>}</li>
 *   <li>{@link APIMetamodel.Participant#getEntityName <em>Entity Name</em>}</li>
 * </ul>
 *
 * @see APIMetamodel.APIMetamodelPackage#getParticipant()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL invariants='null'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ValidParticipantName ValidActorType ValidEntityReference'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot ValidParticipantName='self.name.matches(\'[A-Z][a-zA-Z0-9_]*\')' ValidActorType='Set{\'ACTOR\', \'ENTITY\', \'SYSTEM\', \'EXTERNAL\'}-&gt;includes(self.actorType)' ValidEntityReference='self.actorType = \'ENTITY\' implies (not self.entityName.oclIsUndefined() and self.entityName.size() &gt; 0)'"
 * @generated
 */
public interface Participant extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see APIMetamodel.APIMetamodelPackage#getParticipant_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link APIMetamodel.Participant#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Actor Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actor Type</em>' attribute.
	 * @see #setActorType(String)
	 * @see APIMetamodel.APIMetamodelPackage#getParticipant_ActorType()
	 * @model
	 * @generated
	 */
	String getActorType();

	/**
	 * Sets the value of the '{@link APIMetamodel.Participant#getActorType <em>Actor Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actor Type</em>' attribute.
	 * @see #getActorType()
	 * @generated
	 */
	void setActorType(String value);

	/**
	 * Returns the value of the '<em><b>Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entity Name</em>' attribute.
	 * @see #setEntityName(String)
	 * @see APIMetamodel.APIMetamodelPackage#getParticipant_EntityName()
	 * @model
	 * @generated
	 */
	String getEntityName();

	/**
	 * Sets the value of the '{@link APIMetamodel.Participant#getEntityName <em>Entity Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entity Name</em>' attribute.
	 * @see #getEntityName()
	 * @generated
	 */
	void setEntityName(String value);

} // Participant
