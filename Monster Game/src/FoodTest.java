import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FoodTest {
	Board board;
	Player player;
	Monster monsters[];
	
	@BeforeEach
	void setUp() {
		board = new Board();
		player = new Player(0, 0);
		monsters = new Monster[1];
		monsters[0] = new Monster(5, 6);
		board.initializePlayer(player);
	}

	@Test
	void testFoodTime() {
		int i = 0;
		
		board.placeFood(player);
		board.movePlayer(player, "east");
		board.updateFood(player, monsters);
		board.display();
		while (i < 4) {
			board.updateFood(player, monsters);
			i++;
		}
		board.display();
		board.movePlayer(player, "west");
		board.updateFood(player, monsters);
		assertEquals(-1, player.getPoisoned());
	}
	
	@Test
	void testPoisonedTime() {
		int i = 0;
		
		board.placeFood(player);
		board.updateFood(player, monsters);
		board.updateFood(player, monsters);
		while (i < 5) {
			player.checkPoison();
			i++;
		}
		assertEquals(-1, player.getPoisoned());
	}
}
