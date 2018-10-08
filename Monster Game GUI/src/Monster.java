import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Monster extends Rectangle {

	private static final long serialVersionUID = 1L;

	public Monster(int x, int y) {
		setBounds(x, y, 32, 32);
	}

	public void tick() {

	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 32, 32);
	}

}
