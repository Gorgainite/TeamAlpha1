import java.util.ArrayList;

public class Main {
	static ArrayList<Account> accounts = new ArrayList<Account>();
	
	public static void main(String[] args) {
		
	}

	public static void registerAccount(String username, String password) {
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
	
	public static String login(String username, String password) {
		for (Account temp : accounts) {
			if (username.equals(temp.getUsername())) {
				System.out.print("Sucessfully logged in.\n");
				return username;
			}
		}
		
		System.out.print("Your username or password is incorrect.\n");
		return null;
	}
}
