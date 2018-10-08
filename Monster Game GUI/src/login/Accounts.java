package login;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Accounts {
	ArrayList<Account> accounts;

	public Accounts() {
		accounts = new ArrayList<Account>();
		accounts.add(new Account("administrator", "password", AccountType.ADMIN));
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public Boolean registerAccount(String username, String password) {
		for (Account temp : accounts) {
			if (username.equals(temp.getUsername())) {
				JOptionPane.showMessageDialog(null, "Username already in use.");
				return false;
			}
		}

		accounts.add(new Account(username, password, AccountType.STANDARD));
		JOptionPane.showMessageDialog(null, "Sucessfully registered.");

		return true;
	}

	public Account logIn(String username, String password) {
		for (Account temp : accounts) {
			if (username.equals(temp.getUsername())) {
				if (password.equals(temp.getPassword())) {
					JOptionPane.showMessageDialog(null, "Succesfully logged in.");
					return temp;
				}
			}
		}

		JOptionPane.showMessageDialog(null, "Your username or password is incorrect.");
		return null;
	}

	public Boolean removeAccount(String username) {
		for (Account temp : accounts) {
			if (username.equals(temp.getUsername())) {
				accounts.remove(temp);
				JOptionPane.showMessageDialog(null, "Successfully removed account.");
				return true;
			}
		}

		JOptionPane.showMessageDialog(null, "Username not found.");
		return false;
	}

	public void printLeaderboard(JFrame frame) {
		int i = 0;
		String[] data = new String[accounts.size()];

		for (Account temp : accounts) {
			data[i] = temp.getUsername() + " " + temp.getWins() + " " + temp.getLosses();
			i++;
		}

		JList list = new JList(data);
		list.setSelectionModel(new NoSelectionModel());
		frame.getContentPane().add(list, "4, 6");
	}
}
