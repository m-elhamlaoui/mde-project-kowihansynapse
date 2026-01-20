/**
 */
package APIMetamodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>API Features</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link APIMetamodel.APIFeatures#isPagination <em>Pagination</em>}</li>
 *   <li>{@link APIMetamodel.APIFeatures#isFiltering <em>Filtering</em>}</li>
 *   <li>{@link APIMetamodel.APIFeatures#isSwagger <em>Swagger</em>}</li>
 *   <li>{@link APIMetamodel.APIFeatures#isCorsEnabled <em>Cors Enabled</em>}</li>
 * </ul>
 *
 * @see APIMetamodel.APIMetamodelPackage#getAPIFeatures()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL invariants='null'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ValidFeatureCombination'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot ValidFeatureCombination='self.swagger implies self.corsEnabled'"
 * @generated
 */
public interface APIFeatures extends EObject {
	/**
	 * Returns the value of the '<em><b>Pagination</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pagination</em>' attribute.
	 * @see #setPagination(boolean)
	 * @see APIMetamodel.APIMetamodelPackage#getAPIFeatures_Pagination()
	 * @model
	 * @generated
	 */
	boolean isPagination();

	/**
	 * Sets the value of the '{@link APIMetamodel.APIFeatures#isPagination <em>Pagination</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pagination</em>' attribute.
	 * @see #isPagination()
	 * @generated
	 */
	void setPagination(boolean value);

	/**
	 * Returns the value of the '<em><b>Filtering</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filtering</em>' attribute.
	 * @see #setFiltering(boolean)
	 * @see APIMetamodel.APIMetamodelPackage#getAPIFeatures_Filtering()
	 * @model
	 * @generated
	 */
	boolean isFiltering();

	/**
	 * Sets the value of the '{@link APIMetamodel.APIFeatures#isFiltering <em>Filtering</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filtering</em>' attribute.
	 * @see #isFiltering()
	 * @generated
	 */
	void setFiltering(boolean value);

	/**
	 * Returns the value of the '<em><b>Swagger</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Swagger</em>' attribute.
	 * @see #setSwagger(boolean)
	 * @see APIMetamodel.APIMetamodelPackage#getAPIFeatures_Swagger()
	 * @model
	 * @generated
	 */
	boolean isSwagger();

	/**
	 * Sets the value of the '{@link APIMetamodel.APIFeatures#isSwagger <em>Swagger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Swagger</em>' attribute.
	 * @see #isSwagger()
	 * @generated
	 */
	void setSwagger(boolean value);

	/**
	 * Returns the value of the '<em><b>Cors Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cors Enabled</em>' attribute.
	 * @see #setCorsEnabled(boolean)
	 * @see APIMetamodel.APIMetamodelPackage#getAPIFeatures_CorsEnabled()
	 * @model
	 * @generated
	 */
	boolean isCorsEnabled();

	/**
	 * Sets the value of the '{@link APIMetamodel.APIFeatures#isCorsEnabled <em>Cors Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cors Enabled</em>' attribute.
	 * @see #isCorsEnabled()
	 * @generated
	 */
	void setCorsEnabled(boolean value);

} // APIFeatures
