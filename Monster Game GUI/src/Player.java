import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {

	private static final long serialVersionUID = 1L;

	public boolean right, down, left, up;
	private int speed = 4;

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
	}

	private boolean canMove(int nextX, int nextY) {
		Rectangle bounds = new Rectangle(nextX, nextY, width, height);
		Level level = Main.level;

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
