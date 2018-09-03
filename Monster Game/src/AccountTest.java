import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountTest {
	Accounts accounts;
	
	@BeforeEach
	void setUp() {
		accounts = new Accounts();
	}
	
	@Test
	void testRegisterAccount() {	
		accounts.registerAccount("Reilley", "1234");
		accounts.registerAccount("Reilley", "1234");
	}
	
	@Test
	void testLoginTrue() {
		accounts.registerAccount("Reilley", "1234");
		assertEquals(accounts.getAccounts().get(0), accounts.logIn("Reilley", "1234"));
	}
	
	@Test
	void testLoginFalse() {
		assertEquals(null, accounts.logIn("Matt", "1234"));
	}
}
