import javafx.scene.control.TextField;

public class Account {
	private TextField username;
	private TextField password;
	private int wins;
	private int losses;

	public Account(TextField username2, TextField password2) {
		this.username = username2;
		this.password = password2;
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

	public TextField getUsername() {
		return username;
	}

	public TextField getPassword() {
		return password;
	}
}
