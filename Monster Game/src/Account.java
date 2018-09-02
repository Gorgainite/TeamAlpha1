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
	
	public String getUsername() {
		return username;
	}
}
