package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Main extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	public static JFrame frame;

	private static boolean isRunning = false;

	public static final int WIDTH = 640, HEIGHT = 480;
	public static final String TITLE = "Monster Game";

	private static Thread thread;

	public static Player player;
	public static Board board;

	private static int gameTimer;
	public static int gameTimerTarget = 60 * 120;

	public Main() {
		Dimension dimension = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);

		gameTimer = 0;

		addKeyListener(this);

		player = new Player(WIDTH / 2, HEIGHT / 2);
		board = new Board("/map/map.png");
	}

	public synchronized void start() {
		if (isRunning)
			return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized static void stop() {
		if (!isRunning)
			return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void tick() {
		player.tick();
		board.tick();

		gameTimer++;
		if (gameTimer == gameTimerTarget) {
			end(true);
		}
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		player.render(g);
		board.render(g);

		g.dispose();
		bs.show();
	}

	@Override
	public void run() {

		requestFocus();

		int fps = 0;
		double timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double targetTick = 60.0;
		double delta = 0;
		double ns = 1000000000 / targetTick;

		while (isRunning) {
			long now = System.nanoTime();

			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				tick();
				render();
				fps++;
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + fps);
				fps = 0;
				timer += 1000;
			}
		}

		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			player.right = true;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			player.down = true;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			player.left = true;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			player.up = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			player.right = false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			player.down = false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			player.left = false;
		if (e.getKeyCode() == KeyEvent.VK_UP)
			player.up = false;
		if (e.getKeyCode() == KeyEvent.VK_F)
			board.addFood();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static void main(String[] args) {
		Main game = new Main();
		frame = new JFrame(TITLE);
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}
	
	public static void end(Boolean win) {
		if (win) {
			Login.Main.loggedIn.setWins(Login.Main.loggedIn.getWins() + 1);
			JOptionPane.showMessageDialog(null, "You win!");
		} else {
			Login.Main.loggedIn.setLosses(Login.Main.loggedIn.getLosses() + 1);
			JOptionPane.showMessageDialog(null, "You lose!");
		}
		
		frame.dispose();
		stop();
	}
}
