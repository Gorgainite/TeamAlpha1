import java.util.Scanner;

public class Main {
	static Accounts accounts = new Accounts();
	static Account loggedIn = null;
	static Scanner userInput = new Scanner(System.in);
	static String optionSelected;

	public static void main(String[] args) {
		Boolean mainLoop = true;

		while (mainLoop == true) {
			System.out.print("Pick an option: ");
			optionSelected = userInput.next();

			if (optionSelected.toLowerCase().equals("register")) {
				System.out.print("Enter username: ");
				String username = userInput.next();

				System.out.print("Enter password: ");
				String password = userInput.next();

				accounts.registerAccount(username, password);
			}

			if (optionSelected.toLowerCase().equals("login")) {
				System.out.print("Enter username: ");
				String username = userInput.next();

				System.out.print("Enter password: ");
				String password = userInput.next();

				loggedIn = accounts.logIn(username, password);
			}

			if (optionSelected.toLowerCase().equals("logout")) {
				loggedIn = null;
				System.out.print("Successfully logged out.\n");
			}

			if (optionSelected.toLowerCase().equals("leaderboard")) {
				accounts.printLeaderboard();
			}

			if (optionSelected.toLowerCase().equals("play")) {
				if (loggedIn == null) {
					System.out.print("You must log in first.\n");
					continue;
				}

				Boolean win = playGame();

				if (win == true) {
					loggedIn.setWins(loggedIn.getWins() + 1);
				} else {
					loggedIn.setLosses(loggedIn.getLosses() + 1);
				}
			}

			if (optionSelected.toLowerCase().equals("quit")) {
				System.exit(0);
			}
		}
	}

	public static Boolean playGame() {
		Board board = new Board();
		Player player = new Player(0, 0);
		Monster monsters[] = new Monster[2];
		monsters[0] = new Monster(5, 6);
		monsters[1] = new Monster(6, 5);
		int maxTurns = 20;
		int turnCount = 0;
		Boolean gameLoop = true;

		board.initializePlayer(player);
		board.initializeMonsters(monsters);
		board.display();

		while (gameLoop == true) {
			if ((player.getPoisoned() & 1) == 0) {
				System.out.print("You are poisoned and can't move on this turn.\n");
			} else {
				Boolean turn;
				do {
					turn = false;
					
					System.out.print("Pick an option: ");
					optionSelected = userInput.next();

					if (optionSelected.toLowerCase().equals("north")) {
						String anything = board.movePlayer(player, "north");
						if (anything.equals("dead")) {
							return false;
						} else if (anything.equals("false")) {
							turn = true;
						}
					}

					if (optionSelected.toLowerCase().equals("east")) {
						String anything = board.movePlayer(player, "east");
						if (anything.equals("dead")) {
							return false;
						} else if (anything.equals("false")) {
							turn = true;
						}
					}

					if (optionSelected.toLowerCase().equals("south")) {
						String anything = board.movePlayer(player, "south");
						if (anything.equals("dead")) {
							return false;
						} else if (anything.equals("false")) {
							turn = true;
						}
					}

					if (optionSelected.toLowerCase().equals("west")) {
						String anything = board.movePlayer(player, "west");
						if (anything.equals("dead")) {
							return false;
						} else if (anything.equals("false")) {
							turn = true;
						}
					}

					if (optionSelected.toLowerCase().equals("food")) {
						board.placeFood(player);
					}
				} while (turn == true);
			}

			String anything = board.moveMonsters(monsters, player);
			if (anything.equals("dead")) {
				return false;
			}

			board.updateFood(player, monsters);
			player.checkPoison();
			for (Monster temp : monsters) {
				temp.checkPoison();
			}
			board.display();

			turnCount++;

			if (turnCount == maxTurns) {
				System.out.print("You win.\n");
				return true;
			}
		}

		return false;
	}
}