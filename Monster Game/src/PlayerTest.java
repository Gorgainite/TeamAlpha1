import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
	Board board;
	Player player;
	
	@BeforeEach
	void setUp() {
		board = new Board();
		player = new Player(0, 0);
		board.initializePlayer(player);
	}
	
	@Test
	void testMoveDirection() {
		board.display();
		assertEquals("nothing", board.movePlayer(player, "south"));
		board.display();
		assertEquals("nothing", board.movePlayer(player, "north"));
		board.display();
		assertEquals("nothing", board.movePlayer(player, "east"));
		board.display();
		assertEquals("nothing", board.movePlayer(player, "west"));
		board.display();
	}
	
	@Test
	void testEmptyTile() {
		board.movePlayer(player, "east");
		assertEquals("false", board.movePlayer(player, "south"));
	}

	@Test
	void testOutOfBounds() {
		assertEquals("false", board.movePlayer(player, "north"));
	}
}
