import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainTest {
	Board board;
	Player player;
	Monster monsters[];
	
	@BeforeEach
	void setUp() {
		board = new Board();
		monsters = new Monster[1];
		monsters[0] = new Monster(0, 0);
		board.initializeMonsters(monsters);
	}
	
	@Test
	void testMoveMonsters() {
		player = new Player(2, 0);
		board.initializePlayer(player);
		board.display();
		board.moveMonsters(monsters, player);
		board.display();
	}
}
