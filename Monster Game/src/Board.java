import java.util.ArrayList;

public class Board {
	ArrayList<Food> food = new ArrayList<Food>();

	int width;
	int height;
	int cells[][];

	public Board() {
		cells = new int[][] { { 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 1, 0, 0, 1 }, { 1, 0, 0, 1, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 1, 0, 0, 1 }, { 1, 0, 0, 1, 0, 0, 1 }, { 1, 1, 1, 1, 1, 1, 1 } };
		width = 7;
		height = 7;
	}

	public void display() {
		System.out.print("----------------------\n");
		for (int x = 0; x < width; x++) {
			System.out.print("|");
			for (int y = 0; y < height; y++) {
				switch (cells[x][y]) {
				case 0:
					if (cells[x][y + 1] != 0) {
						System.out.print("  |");
					} else {
						System.out.print("   ");
					}
					break;

				case 1:
					System.out.print("  |");
					break;

				case 2:
					System.out.print("FF|");
					break;

				case 3:
					System.out.print("##|");
					break;

				case 4:
					System.out.print("MM|");
					break;
				}
			}

			System.out.print("\n");
			System.out.print("-");

			for (int y = 0; y < 7; y++) {
				switch (cells[x][y]) {
				case 0:
					if (cells[x + 1][y] != 0) {
						System.out.print("---");
					} else {
						if (cells[x][y + 1] != 0) {
							System.out.print("  -");
						} else {
							System.out.print("   ");
						}
					}
					break;

				default:
					System.out.print("---");
					break;
				}
			}

			System.out.print("\n");
		}
	}

	public void initializeMonsters(Monster monster[]) {
		for (Monster temp : monster) {
			cells[temp.getY()][temp.getX()] = 4;
		}
	}

	public void initializePlayer(Player player) {
		cells[player.getY()][player.getX()] = 3;
	}

	public String moveMonsters(Monster monsters[], Player player) {
		for (Monster temp : monsters) {
			int tempX = temp.getX();
			int tempY = temp.getY();
			Boolean chooseDirection = false;

			if ((temp.getPoisoned() & 1) == 0) {
				// Do nothing
			} else {
				if (temp.getX() < player.getX()) {
					tempX += 1;

					if (tempX < 0 || tempX >= width || tempY < 0 || tempY >= height) {
						// Do nothing
					} else if (cells[tempY][tempX] == 0) {
						// Do nothing
					} else if (cells[tempY][tempX] == 3) {
						cells[player.getY()][player.getX()] = 1;
						System.out.print("You died.\n");
						return "dead";
					} else if (cells[tempY][tempX] == 4) {
						// Do nothing
					} else {
						chooseDirection = true;
					}
				}

				if (chooseDirection == true) {
					cells[temp.getY()][temp.getX()] = 1;
					temp.updatePosition(tempX, tempY);
					cells[temp.getY()][temp.getX()] = 4;
					continue;
				} else {
					tempX = temp.getX();
					tempY = temp.getY();
				}

				if (temp.getX() > player.getX()) {
					tempX -= 1;

					if (tempX < 0 || tempX >= width || tempY < 0 || tempY >= height) {
						// Do nothing
					} else if (cells[tempY][tempX] == 0) {
						// Do nothing
					} else if (cells[tempY][tempX] == 3) {
						cells[player.getY()][player.getX()] = 1;
						System.out.print("You died.\n");
						return "dead";
					} else if (cells[tempY][tempX] == 4) {
						// Do nothing
					} else {
						chooseDirection = true;
					}
				}

				if (chooseDirection == true) {
					cells[temp.getY()][temp.getX()] = 1;
					temp.updatePosition(tempX, tempY);
					cells[temp.getY()][temp.getX()] = 4;
					continue;
				} else {
					tempX = temp.getX();
					tempY = temp.getY();
				}

				if (temp.getY() < player.getY()) {
					tempY += 1;

					if (tempX < 0 || tempX >= width || tempY < 0 || tempY >= height) {
						// Do nothing
					} else if (cells[tempY][tempX] == 0) {
						// Do nothing
					} else if (cells[tempY][tempX] == 3) {
						cells[player.getY()][player.getX()] = 1;
						System.out.print("You died.\n");
						return "dead";
					} else if (cells[tempY][tempX] == 4) {
						// Do nothing
					} else {
						chooseDirection = true;
					}
				}

				if (chooseDirection == true) {
					cells[temp.getY()][temp.getX()] = 1;
					temp.updatePosition(tempX, tempY);
					cells[temp.getY()][temp.getX()] = 4;
					continue;
				} else {
					tempX = temp.getX();
					tempY = temp.getY();
				}

				if (temp.getY() > player.getY()) {
					tempY -= 1;

					if (tempX < 0 || tempX >= width || tempY < 0 || tempY >= height) {
						// Do nothing
					} else if (cells[tempY][tempX] == 0) {
						// Do nothing
					} else if (cells[tempY][tempX] == 3) {
						cells[player.getY()][player.getX()] = 1;
						System.out.print("You died.\n");
						return "dead";
					} else if (cells[tempY][tempX] == 4) {
						// Do nothing
					} else {
						chooseDirection = true;
					}
				}

				if (chooseDirection == true) {
					cells[temp.getY()][temp.getX()] = 1;
					temp.updatePosition(tempX, tempY);
					cells[temp.getY()][temp.getX()] = 4;
					continue;
				} else {
					tempX = temp.getX();
					tempY = temp.getY();
				}
			}
		}

		return "nothing";
	}

	public String movePlayer(Player player, String direction) {
		int tempX;
		int tempY;

		switch (direction) {
		case "north":
			tempX = player.getX();
			tempY = player.getY() - 1;
			break;

		case "east":
			tempX = player.getX() + 1;
			tempY = player.getY();
			break;

		case "south":
			tempX = player.getX();
			tempY = player.getY() + 1;
			break;

		case "west":
			tempX = player.getX() - 1;
			tempY = player.getY();
			break;

		default:
			tempX = player.getX();
			tempY = player.getY();
			break;
		}

		if (tempX < 0 || tempX >= width || tempY < 0 || tempY >= height) {
			System.out.print("Can't move in that direction, you would be out of bounds.\n");
			return "false";
		} else if (cells[tempY][tempX] == 0) {
			System.out.print("Can't move in that direction, it is an empty space.\n");
			return "false";
		} else if (cells[tempY][tempX] == 4) {
			cells[player.getY()][player.getX()] = 1;
			System.out.print("You died.\n");
			return "dead";
		} else {
			cells[player.getY()][player.getX()] = 1;
			player.updatePosition(tempX, tempY);
			cells[player.getY()][player.getX()] = 3;
			return "nothing";
		}
	}

	public void placeFood(Player player) {
		food.add(new Food(player.getX(), player.getY()));
	}

	public void updateFood(Player player, Monster monsters[]) {
		for (Food temp : food) {
			if (cells[temp.getY()][temp.getX()] == 1) {
				cells[temp.getY()][temp.getX()] = 2;
			}
		}

		for (int i = food.size() - 1; i >= 0; i--) {
			if (cells[player.getY()][player.getX()] == cells[food.get(i).getY()][food.get(i).getX()]
					&& food.get(i).getTimer() > 0) {
				cells[food.get(i).getY()][food.get(i).getX()] = 3;
				food.remove(i);
				System.out.print("You're poisoned.\n");
				player.setPoisoned();
			}
		}
		
		for (int i = food.size() - 1; i >= 0; i--) {
			for (Monster temp : monsters) {
				if (cells[temp.getY()][temp.getX()] == cells[food.get(i).getY()][food.get(i).getX()]) {
					cells[food.get(i).getY()][food.get(i).getX()] = 4;
					food.remove(i);
					temp.setPoisoned();
					break;
				}
			}
		}
		
		for (int i = food.size() - 1; i >= 0; i--) {
			food.get(i).setTimer(food.get(i).getTimer() + 1);
			if (food.get(i).getTimer() == 5) {
				cells[food.get(i).getY()][food.get(i).getX()] = 1;
				food.remove(i);
			}
		}
	}
}
