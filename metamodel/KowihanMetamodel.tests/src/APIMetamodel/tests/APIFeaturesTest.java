/**
 */
package APIMetamodel.tests;

import APIMetamodel.APIFeatures;
import APIMetamodel.APIMetamodelFactory;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>API Features</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class APIFeaturesTest extends TestCase {

	/**
	 * The fixture for this API Features test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected APIFeatures fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(APIFeaturesTest.class);
	}

	/**
	 * Constructs a new API Features test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public APIFeaturesTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this API Features test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(APIFeatures fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this API Features test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected APIFeatures getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(APIMetamodelFactory.eINSTANCE.createAPIFeatures());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //APIFeaturesTest
