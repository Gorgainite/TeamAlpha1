package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Board {

	public int width;
	public int height;

	public Tile[][] tiles;
	public List<Monster> monsters;
	public List<Food> food;

	public Board(String path) {
		food = new ArrayList<>();
		monsters = new ArrayList<>();

		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			this.width = map.getWidth();
			this.height = map.getHeight();
			int[] pixels = new int[width * height];
			tiles = new Tile[width][height];
			map.getRGB(0, 0, width, height, pixels, 0, width);

			for (int xx = 0; xx < width; xx++) {
				for (int yy = 0; yy < height; yy++) {
					int val = pixels[xx + (yy * width)];

					if (val == 0xff000000) { // Wall
						tiles[xx][yy] = new Tile(xx * 32, yy * 32);
					} else if (val == 0xff0026ff) { // Player
						Main.player.x = xx * 32;
						Main.player.y = yy * 32;
					} else if (val == 0xffff0000) { // Enemy
						monsters.add(new Monster(xx * 32, yy * 32));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tick() {
		for (Food food : food) {
			food.tick();
		}

		for (Monster monster : monsters) {
			monster.tick();
		}
	}

	public void addFood() {
		food.add(new Food(Main.player.x, Main.player.y));
	}

	public void render(Graphics g) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (tiles[x][y] != null)
					tiles[x][y].render(g);
			}
		}

		for (Food food : food) {
			food.render(g);
		}

		for (Monster monster : monsters) {
			monster.render(g);
		}
	}
}
