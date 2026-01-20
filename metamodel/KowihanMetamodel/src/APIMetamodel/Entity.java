/**
 */
package APIMetamodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link APIMetamodel.Entity#getName <em>Name</em>}</li>
 *   <li>{@link APIMetamodel.Entity#getDescription <em>Description</em>}</li>
 *   <li>{@link APIMetamodel.Entity#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link APIMetamodel.Entity#getTableName <em>Table Name</em>}</li>
 *   <li>{@link APIMetamodel.Entity#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link APIMetamodel.Entity#getRelationships <em>Relationships</em>}</li>
 *   <li>{@link APIMetamodel.Entity#getOperations <em>Operations</em>}</li>
 *   <li>{@link APIMetamodel.Entity#getIndexes <em>Indexes</em>}</li>
 *   <li>{@link APIMetamodel.Entity#getParentEntity <em>Parent Entity</em>}</li>
 * </ul>
 *
 * @see APIMetamodel.APIMetamodelPackage#getEntity()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL invariants='null'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ValidEntityName HasAttributes NoDuplicateAttributes ValidTableName'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot ValidEntityName='self.name.matches(\'[A-Z][a-zA-Z0-9_]*\')' HasAttributes='self.attributes-&gt;size() &gt;= 1' NoDuplicateAttributes='self.attributes-&gt;forAll(a1, a2 | a1 &lt;&gt; a2 implies a1.name &lt;&gt; a2.name)' ValidTableName='self.tableName.matches(\'[a-z][a-z0-9_]*\')'"
 * @generated
 */
public interface Entity extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see APIMetamodel.APIMetamodelPackage#getEntity_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link APIMetamodel.Entity#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see APIMetamodel.APIMetamodelPackage#getEntity_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link APIMetamodel.Entity#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Abstract</em>' attribute.
	 * @see #setIsAbstract(boolean)
	 * @see APIMetamodel.APIMetamodelPackage#getEntity_IsAbstract()
	 * @model
	 * @generated
	 */
	boolean isIsAbstract();

	/**
	 * Sets the value of the '{@link APIMetamodel.Entity#isIsAbstract <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Abstract</em>' attribute.
	 * @see #isIsAbstract()
	 * @generated
	 */
	void setIsAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Table Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table Name</em>' attribute.
	 * @see #setTableName(String)
	 * @see APIMetamodel.APIMetamodelPackage#getEntity_TableName()
	 * @model
	 * @generated
	 */
	String getTableName();

	/**
	 * Sets the value of the '{@link APIMetamodel.Entity#getTableName <em>Table Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table Name</em>' attribute.
	 * @see #getTableName()
	 * @generated
	 */
	void setTableName(String value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link APIMetamodel.Attribute}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see APIMetamodel.APIMetamodelPackage#getEntity_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Attribute> getAttributes();

	/**
	 * Returns the value of the '<em><b>Relationships</b></em>' containment reference list.
	 * The list contents are of type {@link APIMetamodel.Relationship}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relationships</em>' containment reference list.
	 * @see APIMetamodel.APIMetamodelPackage#getEntity_Relationships()
	 * @model containment="true"
	 * @generated
	 */
	EList<Relationship> getRelationships();

	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
	 * The list contents are of type {@link APIMetamodel.Operation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference list.
	 * @see APIMetamodel.APIMetamodelPackage#getEntity_Operations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Operation> getOperations();

	/**
	 * Returns the value of the '<em><b>Indexes</b></em>' containment reference list.
	 * The list contents are of type {@link APIMetamodel.Index}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexes</em>' containment reference list.
	 * @see APIMetamodel.APIMetamodelPackage#getEntity_Indexes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Index> getIndexes();

	/**
	 * Returns the value of the '<em><b>Parent Entity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Entity</em>' reference.
	 * @see #setParentEntity(Entity)
	 * @see APIMetamodel.APIMetamodelPackage#getEntity_ParentEntity()
	 * @model
	 * @generated
	 */
	Entity getParentEntity();

	/**
	 * Sets the value of the '{@link APIMetamodel.Entity#getParentEntity <em>Parent Entity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Entity</em>' reference.
	 * @see #getParentEntity()
	 * @generated
	 */
	void setParentEntity(Entity value);

} // Entity
