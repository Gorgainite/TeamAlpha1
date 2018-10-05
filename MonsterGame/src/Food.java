public class Food {
	int timer;
	int x;
	int y;
	
	public Food(int x, int y) {
		this.x = x;
		this.y = y;
		timer = 0;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
