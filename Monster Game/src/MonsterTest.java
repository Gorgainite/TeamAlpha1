import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class MonsterTest {
	
	@Before
	void setUp() {
		initialisePlayer("1","1");
		initialiseMonster("1","5");
	}
	
	@Test
	void testMonsterDirection() {
		main.playerGetNextPosition("E");
		assertEquals(Main.boardUpdatePlayer);
		assertEquals(Main.boardUpdateMonster);
	}
	
	@Test
	void testMonsterGrid() {
		assertEquals(Main.MonsterPosition);
	}
}
