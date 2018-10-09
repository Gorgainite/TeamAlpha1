package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {

	private static final long serialVersionUID = 1L;

	public boolean right, down, left, up;
	private int speed = 4;

	public boolean poisoned = false;
	public int poisonTimer = 0;
	private int poisonTimerTarget = 60 * 20;

	public Player(int x, int y) {
		setBounds(x, y, 32, 32);
	}

	public void tick() {
		if (right && canMove(x + speed, y))
			x += speed;
		if (down && canMove(x, y + speed))
			y += speed;
		if (left && canMove(x - speed, y))
			x -= speed;
		if (up && canMove(x, y - speed))
			y -= speed;

		for (int i = 0; i < Main.board.food.size(); i++) {
			Food en = Main.board.food.get(i);
			if (en.intersects(this)) {
				if (en.placeTimer >= en.placeTimerTarget) {
					Main.board.food.remove(i);
					poisoned = true;
					poisonTimer = 0;
				}
			}
		}

		for (int i = 0; i < Main.board.monsters.size(); i++) {
			Monster en = Main.board.monsters.get(i);
			if (en.intersects(this)) {
				Main.end(false);
			}
		}

		if (poisoned == true) {
			speed = 2;
			poisonTimer++;
		}

		if (poisonTimer == poisonTimerTarget) {
			poisoned = false;
			speed = 4;
			poisonTimer = 0;
		}
	}

	private boolean canMove(int nextX, int nextY) {
		Rectangle bounds = new Rectangle(nextX, nextY, width, height);
		Board level = Main.board;

		for (int xx = 0; xx < level.tiles.length; xx++) {
			for (int yy = 0; yy < level.tiles[0].length; yy++) {
				if (level.tiles[xx][yy] != null) {
					if (bounds.intersects(level.tiles[xx][yy])) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, width, height);
	}

}
