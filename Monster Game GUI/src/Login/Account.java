package Login;


public class Account {
	private String username;
	private String password;
	private AccountType type;
	private int wins;
	private int losses;

	public Account(String username, String password, AccountType type) {
		this.username = username;
		this.password = password;
		this.type = type;
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

	public AccountType getType() {
		return type;
	}
}
