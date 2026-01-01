/**
 */
package APIMetamodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link APIMetamodel.Relationship#getName <em>Name</em>}</li>
 *   <li>{@link APIMetamodel.Relationship#getType <em>Type</em>}</li>
 *   <li>{@link APIMetamodel.Relationship#getTargetEntity <em>Target Entity</em>}</li>
 *   <li>{@link APIMetamodel.Relationship#getRelatedName <em>Related Name</em>}</li>
 *   <li>{@link APIMetamodel.Relationship#getOnDelete <em>On Delete</em>}</li>
 * </ul>
 *
 * @see APIMetamodel.APIMetamodelPackage#getRelationship()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL invariants='null'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ValidRelationshipName ValidTarget ValidCascadeBehavior ValidRelationType'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot ValidRelationshipName='self.name.matches(\'[a-z][a-zA-Z0-9_]*\')' ValidTarget='not self.targetEntity.oclIsUndefined() and self.targetEntity.size() &gt; 0' ValidCascadeBehavior='Set{\'CASCADE\', \'SET_NULL\', \'PROTECT\'}-&gt;includes(self.onDelete)' ValidRelationType='Set{\'ONE_TO_ONE\', \'ONE_TO_MANY\', \'MANY_TO_ONE\', \'MANY_TO_MANY\'}-&gt;includes(self.type)'"
 * @generated
 */
public interface Relationship extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see APIMetamodel.APIMetamodelPackage#getRelationship_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link APIMetamodel.Relationship#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see APIMetamodel.APIMetamodelPackage#getRelationship_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link APIMetamodel.Relationship#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Target Entity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Entity</em>' attribute.
	 * @see #setTargetEntity(String)
	 * @see APIMetamodel.APIMetamodelPackage#getRelationship_TargetEntity()
	 * @model
	 * @generated
	 */
	String getTargetEntity();

	/**
	 * Sets the value of the '{@link APIMetamodel.Relationship#getTargetEntity <em>Target Entity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Entity</em>' attribute.
	 * @see #getTargetEntity()
	 * @generated
	 */
	void setTargetEntity(String value);

	/**
	 * Returns the value of the '<em><b>Related Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Related Name</em>' attribute.
	 * @see #setRelatedName(String)
	 * @see APIMetamodel.APIMetamodelPackage#getRelationship_RelatedName()
	 * @model
	 * @generated
	 */
	String getRelatedName();

	/**
	 * Sets the value of the '{@link APIMetamodel.Relationship#getRelatedName <em>Related Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Related Name</em>' attribute.
	 * @see #getRelatedName()
	 * @generated
	 */
	void setRelatedName(String value);

	/**
	 * Returns the value of the '<em><b>On Delete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Delete</em>' attribute.
	 * @see #setOnDelete(String)
	 * @see APIMetamodel.APIMetamodelPackage#getRelationship_OnDelete()
	 * @model
	 * @generated
	 */
	String getOnDelete();

	/**
	 * Sets the value of the '{@link APIMetamodel.Relationship#getOnDelete <em>On Delete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Delete</em>' attribute.
	 * @see #getOnDelete()
	 * @generated
	 */
	void setOnDelete(String value);

} // Relationship
