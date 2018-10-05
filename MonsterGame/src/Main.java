import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {

	static Accounts accounts = new Accounts();
	static Account loggedIn = null;
	static String optionSelected;
	static Scanner userInput = new Scanner(System.in);
	boolean mainLoop = true;

	
		
	
	
	@Override
	public void start(Stage primaryStage) {
		Button btnLogin = new Button();
		btnLogin.setText("Login");
		btnLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Label loginLabel = new Label("Enter Username");
				TextField username = new TextField();

				username.setPrefHeight(35);

				Label passwordLabel = new Label("Enter Password");
				TextField password = new TextField();
				password.setPrefHeight(35);

				Button submitButton = new Button("Submit");
				submitButton.setPrefHeight(35);
				submitButton.setDefaultButton(true);
				submitButton.setPrefWidth(100);

				GridPane root = new GridPane();
				Scene secondScene = new Scene(root, 400, 400);
				root.add(username, 1, 2);
				root.add(loginLabel, 0, 2);
				root.add(password, 1, 3);
				root.add(passwordLabel, 0, 3);
				root.add(submitButton, 1, 4);

				Stage secondStage = new Stage();
				secondStage.setTitle("Second Stage");
				secondStage.setScene(secondScene);

				// THIS ISNT HOW YOU DO THIS
				submitButton.setOnAction(actionEvent -> secondStage.close());

				// Set position of second window, related to primary window.
				secondStage.setX(primaryStage.getX() + 0);
				secondStage.setY(primaryStage.getY() + 0);

				loggedIn = accounts.logIn(username, password);

				secondStage.show();

			}
		});

		Button btnRegister = new Button();
		btnRegister.setText("Register");

		btnRegister.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				

				Label registerLabel = new Label("Register a Username");
				TextField username = new TextField();
				username.setPrefHeight(35);

				Label rPasswordLabel = new Label("Register a Password");
				TextField password = new TextField();
				password.setPrefHeight(35);

				Button submitButton = new Button("Submit");
				submitButton.setPrefHeight(35);
				submitButton.setDefaultButton(true);
				submitButton.setPrefWidth(100);
				accounts.registerAccount(username, password);

				GridPane root = new GridPane();
				Scene secondScene = new Scene(root, 400, 400);
				root.add(username, 1, 2);
				root.add(registerLabel, 0, 2);
				root.add(password, 1, 3);
				root.add(rPasswordLabel, 0, 3);
				root.add(submitButton, 1, 4);

				Stage secondStage = new Stage();
				secondStage.setTitle("Second Stage");
				secondStage.setScene(secondScene);

				// THIS ISNT HOW YOU DO THIS THIS JUST CLOSES THE CURRENT PAGE
				submitButton.setOnAction(actionEvent -> secondStage.close());

				// Set position of second window, related to primary window.
				secondStage.setX(primaryStage.getX() + 0);
				secondStage.setY(primaryStage.getY() + 0);

				secondStage.show();

			}
		});

		Button btnPlay = new Button();
		btnPlay.setText("Play");
		btnPlay.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				

				if (loggedIn == null) {

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Woah There CowBoy");
					alert.setHeaderText("WARNING:");
					alert.setContentText("You must login to play!");

					alert.showAndWait();
				} 

			}

		});

		Button btnLeader = new Button();
		btnLeader.setText("LeaderBoard");
		btnLeader.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				accounts.printLeaderboard();
			}

		});

		Button btnQuit = new Button();
		btnQuit.setText("Quit");
		btnQuit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.print("Quit");
				primaryStage.close();
			}

		});

		Button btnLogout = new Button();
		btnLogout.setText("Log Out");
		btnLogout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				loggedIn = null;

				if (loggedIn == null) {

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("You have Logged Out");
					alert.setHeaderText("WARNING:");
					alert.setContentText("Thank you for being logged in and now not so much");

					alert.showAndWait();
				}
			}

		});

		GridPane root = new GridPane();
		root.setHgap(50);
		root.setVgap(50);
		root.add(btnLogin, 2, 1);
		root.add(btnRegister, 2, 2);
		root.add(btnPlay, 2, 3);
		root.add(btnLeader, 2, 4);
		root.add(btnLogout, 2, 5);
		root.add(btnQuit, 3, 5);
		// root.setGridLinesVisible(true);

		Scene scene = new Scene(root, 400, 400);

		primaryStage.setTitle("Alpha #1 Monster Game");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
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
