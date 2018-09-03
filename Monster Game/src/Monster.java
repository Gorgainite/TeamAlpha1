public class Monster {
	int x;
	int y;
	int poisoned;
	
	public Monster(int x, int y) {
		this.x = x;
		this.y = y;
		poisoned = -1;
	}

	public void updatePosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setPoisoned() {
		this.poisoned = 0;
	}
	
	public void checkPoison() {
		if (poisoned > -1) {
			poisoned++;
		}
		
		if (poisoned == 5) {
			poisoned = -1;
		}
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getPoisoned() {
		return poisoned;
	}
}