public class Account {
	private String username;
	private String password;
	private int wins;
	private int losses;

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
		this.wins = 0;
		this.losses = 0;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
