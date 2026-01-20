/**
 */
package APIMetamodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Index</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link APIMetamodel.Index#getName <em>Name</em>}</li>
 *   <li>{@link APIMetamodel.Index#getFields <em>Fields</em>}</li>
 *   <li>{@link APIMetamodel.Index#isIsUnique <em>Is Unique</em>}</li>
 * </ul>
 *
 * @see APIMetamodel.APIMetamodelPackage#getIndex()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL invariants='null'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ValidIndexName HasFields ValidIndexConfiguration'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot ValidIndexName='self.name.matches(\'[a-z][a-z0-9_]*\')' HasFields='self.fields-&gt;size() &gt;= 1' ValidIndexConfiguration='self.fields-&gt;forAll(f | not f.oclIsUndefined() and f.size() &gt; 0)'"
 * @generated
 */
public interface Index extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see APIMetamodel.APIMetamodelPackage#getIndex_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link APIMetamodel.Index#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Fields</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fields</em>' attribute list.
	 * @see APIMetamodel.APIMetamodelPackage#getIndex_Fields()
	 * @model
	 * @generated
	 */
	EList<String> getFields();

	/**
	 * Returns the value of the '<em><b>Is Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Unique</em>' attribute.
	 * @see #setIsUnique(boolean)
	 * @see APIMetamodel.APIMetamodelPackage#getIndex_IsUnique()
	 * @model
	 * @generated
	 */
	boolean isIsUnique();

	/**
	 * Sets the value of the '{@link APIMetamodel.Index#isIsUnique <em>Is Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Unique</em>' attribute.
	 * @see #isIsUnique()
	 * @generated
	 */
	void setIsUnique(boolean value);

} // Index
