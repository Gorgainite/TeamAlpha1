import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class FoodTest {
	
	@Before 
	void setUp() {
		initialiseFood(playerPosition));
		initialisePlayer("1","8");
	}
	
	@Test
	void testFoodDuration() {
		assertEquals(Main.FoodDuration);
	}
	
	@Test
	void testFoodEffect() {
		assertEquals(Main.PlayerSpeed);
	}
}
