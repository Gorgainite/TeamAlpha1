import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Monster extends Rectangle {

	private static final long serialVersionUID = 1L;

	private int random = 0, smart = 1, findPath = 2;
	private int state = random;
	private int right = 0, down = 1, left = 2, up = 3;
	private int dir = -1;
	private Random randomGen;
	private int time = 0;
	private int targetTime = 60 * 4;
	private int speed = 3;
	private int lastDir = -1;

	public boolean poisoned = false;
	public int poisonTimer = 0;
	private int poisonTimerTarget = 60 * 20;

	public Monster(int x, int y) {
		randomGen = new Random();
		setBounds(x, y, 32, 32);
		dir = randomGen.nextInt(4);
	}

	public void tick() {
		if (state == random) {
			if (dir == right) {
				if (canMove(x + speed, y)) {
					x += speed;
				} else {
					dir = randomGen.nextInt(4);
				}
			} else if (dir == down) {
				if (canMove(x, y + speed)) {
					y += speed;
				} else {
					dir = randomGen.nextInt(4);
				}
			} else if (dir == left) {
				if (canMove(x - speed, y)) {
					x -= speed;
				} else {
					dir = randomGen.nextInt(4);
				}
			} else if (dir == up) {
				if (canMove(x, y - speed)) {
					y -= speed;
				} else {
					dir = randomGen.nextInt(4);
				}
			}

			time++;

			if (time == targetTime) {
				state = smart;
				time = 0;
			}
		} else if (state == smart) {
			boolean move = false;

			if (x < Main.player.x) {
				if (canMove(x + speed, y)) {
					x += speed;
					move = true;
					lastDir = right;
				}
			}

			if (y < Main.player.y) {
				if (canMove(x, y + speed)) {
					y += speed;
					move = true;
					lastDir = down;
				}
			}

			if (x > Main.player.x) {
				if (canMove(x - speed, y)) {
					x -= speed;
					move = true;
					lastDir = left;
				}
			}

			if (y > Main.player.y) {
				if (canMove(x, y - speed)) {
					y -= speed;
					move = true;
					lastDir = up;
				}
			}

			if (x == Main.player.x && y == Main.player.y)
				move = true;

			if (!move) {
				state = findPath;
			}

			time++;

			if (time == targetTime) {
				state = random;
				time = 0;
			}
		} else if (state == findPath) {
			if (lastDir == right) {
				if (y < Main.player.y) {
					if (canMove(x, y + speed)) {
						y += speed;
						state = smart;
					}
				} else {
					if (canMove(x, y - speed)) {
						y -= speed;
						state = smart;
					}
				}

				if (canMove(x + speed, y)) {
					x += speed;
				}
			} else if (lastDir == down) {
				if (x < Main.player.x) {
					if (canMove(x + speed, y)) {
						x += speed;
						state = smart;
					}
				} else {
					if (canMove(x - speed, y)) {
						x -= speed;
						state = smart;
					}
				}

				if (canMove(x, y + speed)) {
					y += speed;
				}
			} else if (lastDir == left) {
				if (y < Main.player.y) {
					if (canMove(x, y + speed)) {
						y += speed;
						state = smart;
					}
				} else {
					if (canMove(x, y - speed)) {
						y -= speed;
						state = smart;
					}
				}

				if (canMove(x + speed, y)) {
					x -= speed;
				}
			} else if (lastDir == up) {
				if (x < Main.player.x) {
					if (canMove(x + speed, y)) {
						x += speed;
						state = smart;
					}
				} else {
					if (canMove(x - speed, y)) {
						x -= speed;
						state = smart;
					}
				}

				if (canMove(x, y + speed)) {
					y -= speed;
				}
			}

			time++;

			if (time == targetTime) {
				state = random;
				time = 0;
			}
		}

		for (int i = 0; i < Main.level.food.size(); i++) {
			Food en = Main.level.food.get(i);
			if (en.intersects(this)) {
				if (en.placeTimer >= en.placeTimerTarget) {
					Main.level.food.remove(i);
					poisoned = true;
					poisonTimer = 0;
				}
			}
		}

		if (poisoned == true) {
			speed = 1;
			poisonTimer++;
		}

		if (poisonTimer == poisonTimerTarget) {
			poisoned = false;
			speed = 3;
			poisonTimer = 0;
		}
	}

	private boolean canMove(int nextX, int nextY) {
		Rectangle bounds = new Rectangle(nextX, nextY, width, height);
		Board level = Main.level;

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
		g.setColor(Color.red);
		g.fillRect(x, y, 32, 32);
	}

}
