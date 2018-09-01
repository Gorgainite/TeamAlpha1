import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountTest {

	@before
	public void setUp() {
		1 = new Username("Matthieu Etienne");
		1 = new Password("12345");
	}
	@Test
	
	public void testRepeatedPlayerID() {	
		fail("Not yet implemented");
	}
	
	@Test
	public void testPlayerLogin() {
		assertTrue(Username.equals("Matthieu Etienne"));
		assertTrue(Password.equals("12345"));
	}
	
	@Test
	public void testPlayerLoginVerification() {
		fail("Not yet implemented");
	}

}
