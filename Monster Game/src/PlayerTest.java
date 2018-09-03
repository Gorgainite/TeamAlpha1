import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class PlayerTest {

	@Before
	void setUp() {
		initialisePlayer("1","1"); 
	}
	
	@Test
	void testPlayerInsideGridHollow() {
		main.playerGetNextPosition("S");
		main.playerGetNextPosition("E");
		assertEquals(main.boardUpdatePlayer);
	}
	
	@Test
	void testPlayerOutsideGrid() {
		playerGetNextPosition = "W";
		assertEquals(main.boardUpdatePlayer);
	}
	
	@Test
	void testPlayerDirectionNorth() {
		PlayerGetNextPosition = "N";
		assertEquals(Main.boardUpdatePlayer);
	}
	
}
