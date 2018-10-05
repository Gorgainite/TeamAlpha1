import java.util.ArrayList;

import javafx.scene.control.TextField;

public class Accounts {
	ArrayList<Account> accounts;

	public Accounts() {
		accounts = new ArrayList<Account>();
	}
	
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	
	public void registerAccount(TextField username, TextField password) {
		Boolean usernameCheck = true;
		
		for (Account temp : accounts) {
			if (username.equals(temp.getUsername())) {
				System.out.print("Username is already in use.\n");
				usernameCheck = false;
			}
		}
		
		if (usernameCheck == true) {
			accounts.add(new Account(username, password));
			System.out.print("Successfully registered.\n");
		}
	}
	
	public Account logIn(TextField username, TextField password) {
		for (Account temp : accounts) {
			if (username.equals(temp.getUsername())) {
				if (password.equals(temp.getPassword())) {
					System.out.print("Sucessfully logged in.\n");
					return temp;
				}
			}
		}
		
		System.out.print("Your username or password is incorrect.\n");
		return null;
	}
	
	public void printLeaderboard() {
		for (Account temp : accounts) {
			System.out.print(temp.getUsername() + " " + temp.getWins() + " " + temp.getLosses() + "\n");
		}
	}
}
