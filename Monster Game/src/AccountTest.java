import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class AccountTest {
	
	@Test
	void testRegisterAccount() {
		Main.registerAccount("Reilley", "1234");
		Main.registerAccount("Reilley", "1234");
	}
	
	@Test
	void testLoginTrue() {
		assertEquals("Reilley", Main.login("Reilley", "1234"));
	}
	
	@Test
	void testLoginFalse() {
		assertEquals(null, Main.login("Matt", "1234"));
	}
}
