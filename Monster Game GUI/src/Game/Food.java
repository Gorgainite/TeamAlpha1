package Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Food extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	public int placeTimer = 0;
	public int placeTimerTarget =  60*1;

	public Food(int x, int y) {
		setBounds(x, y, 32, 32);
	}
	
	public void tick() {
		placeTimer++;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}
	
}
