/**
 */
package APIMetamodel.tests;

import APIMetamodel.APIMetamodelFactory;
import APIMetamodel.AuthenticationConfig;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Authentication Config</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class AuthenticationConfigTest extends TestCase {

	/**
	 * The fixture for this Authentication Config test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AuthenticationConfig fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(AuthenticationConfigTest.class);
	}

	/**
	 * Constructs a new Authentication Config test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AuthenticationConfigTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Authentication Config test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(AuthenticationConfig fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Authentication Config test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AuthenticationConfig getFixture() {
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
		setFixture(APIMetamodelFactory.eINSTANCE.createAuthenticationConfig());
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

} //AuthenticationConfigTest
