package minesweeper;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Minesweeper {
	
	boolean endGame = false;
	int[] move;
	int size;
	
	// 0 = no mines in 3x3 grid
	// 10 = no mines in 3x3 grid and has been checked by player
	// n < 9 = there is n mines in 3x3 grid 
	// >100 = mine
	
	
	public void start() {
		System.out.println("\n           Welcome to Minesweeper");
		size = setDifficulty();
		setupGame(size);
		MineField mineField = new MineField();
		mineField.setSize(size);
		mineField.setup();
		VisibleField visibleField = new VisibleField();
		visibleField.setSize(size);
		visibleField.addMines(mineField);
		Scanner s = new Scanner(System.in);
		while(!endGame) { 
			printLegend(mineField);
			move = playerMove(s);
			int revealedSpace = mineField.checkMove(move);
			if (revealedSpace >= 100) {
				System.out.println();
				System.out.println("\n        OH NO! You blew yourself up!");
				visibleField.printGameOver(mineField);
				System.out.println("\n                 Game Over!");
				gameOver();
			} else {
				if (revealedSpace == 10) {
					visibleField.board[move[0]][move[1]] = 10;
					if (Arrays.deepEquals(mineField.board, visibleField.board)) {
						System.out.println("\n       You revealed all empty squares");
						visibleField.printGameOver(mineField);
						System.out.println("                  You Win!");
						gameOver();
					} else {
						System.out.println("\n\n\t\s\s\sNo mines around here!");
						int count = 0;
//						visibleField.revealAdjacentEmptySquares(move, count, mineField);
						visibleField.printField();
					}
				} else {
					visibleField.board[move[0]][move[1]] = revealedSpace;
					if (Arrays.deepEquals(mineField.board, visibleField.board)) {
						System.out.println("\n       You revealed all empty squares");
						visibleField.printGameOver(mineField);
						System.out.println("                  You Win!");
						gameOver();
					} else {
						System.out.println("\n\n\t\s\s\sPhew! That was close!");
						visibleField.printField();
					}
				}
				
				}
			}	
			s.close();
		}
		 
	
	public int setDifficulty() {
		Scanner s = new Scanner(System.in);
		boolean valid = false;
		final Pattern EASY_PAT = Pattern.compile("easy", Pattern.CASE_INSENSITIVE);
		final String MED_PAT = "^(?i)medium$";
		final String HARD_PAT = "^(?i)hard$";
		String diff;
		while(!valid) {
			System.out.println("How hard do you want your game to be?");
			System.out.println("Type: Easy, Medium or Hard");
			if(s.hasNext()) {
				
			diff = s.next();
			System.out.println(diff);
			if (diff.toLowerCase().trim().equals("easy")) {
				size = 10;
				valid = true;
			} else if(diff.toLowerCase().trim().equals("medium")) {
				size = 20;
				valid = true;
			} else if (diff.toLowerCase().trim().equals("hard")) {
				size = 30;
				valid = true;
			} else {
				System.out.println("Invalid input, please try again hello");
				s.next();
			}
			} else {
				System.out.println("Invalid input, please try again");
				s.next();
			}
		}
		s.close();
		return size;
	}
	
	public int[] playerMove(Scanner s) {
		boolean validMove = false;
		int row = 0;
		int col = 0;
		System.out.println("Time to tempt fate!");
		System.out.println();
		while(!validMove) {
			System.out.print("\nSelect a row number if you dare: ");
			while(!s.hasNextInt()) {
				System.out.printf("\nIncorrect value, please enter a number from 0 - %s: ", size-1);
				s.next();
			}
			row = s.nextInt();
			
			System.out.print("\nSelect a column number if you dare: ");
			while(!s.hasNextInt()) {
				System.out.printf("\nIncorrect value, please enter a number from 0 - %s: ", size-1);
				s.next();
			}
			col = s.nextInt();
			if (row > size-1 || col > size-1 || row < 0 || col < 0) {
				System.out.printf("\nIncorrect values, please only enter numbers from 0 - %s", size-1);
			} else {
				validMove = true;
			}
		}
		int[] move = {row, col};
		return move;
	}
	
	
	public void setupGame(int size) {
		
		line();
		for (int k = 0; k < size; k++) {
			if(k == 0) {
				// top number row
				System.out.print("     " + k + "   ");
			}else if (k < 9) {
				System.out.print(k + "   ");
			}else if (size == 10 && k == 9) {
				System.out.print(k + "   ");	
			}else if (size > 10 && k == 9) {
				System.out.print(k + "  ");	
			}else if (k > 9 && k < size-1) {
				System.out.print(k + "  ");
			} else if (k == size-1) {
				System.out.print(k + "\n\n");
			}
		}
		for (int i = 0; i < size; i++) {
			if (i < 10) {
				// left number row
				System.out.print(i + "  ");
			} else if (i > 9) {
				System.out.print(i + " ");
			}
			for (int j = 0; j < size; j++) {
				if (j < size-1) {
					System.out.print("| # ");
				} else if (j == size-1) {
					System.out.println("| # |\n");
				}
			}
		}
		line();
	}
	
	public void printLegend(MineField mineField) { 
		Minesweeper.line();
		for (int k = 0; k < size; k++) {
			if(k == 0) {
				// top number row
				System.out.print("     " + k + "   ");
			}else if (k < size-1) {
			System.out.print(k + "   ");
			} else if (k == size-1) {
				System.out.print(k + "\n\n");
			}
		}
		for (int i = 0; i < size; i++) {
			if (i < size) {
				// left number row
				System.out.print(i + "  ");
			}
			for (int j = 0; j < size; j++) {
				if (j < size-1) {
					if (mineField.board[i][j] < 10) {
						System.out.printf("| %s ", mineField.board[i][j]);
					} else if (mineField.board[i][j] == 10) {
						System.out.print("|   ");
					} else if (mineField.board[i][j] >= 100) {
						System.out.print("| * ");
					}
				} else if (j == size-1) {
					if (mineField.board[i][j] < 10) {
						System.out.printf("| %s |\n\n", mineField.board[i][j]);
					} else if (mineField.board[i][j] == 10) {
						System.out.print("|   |\n\n");
					} else if (mineField.board[i][j] >= 100) {
						System.out.print("| * |\n\n");
					}
					
				}
			}
		}
		Minesweeper.line();
	}
	
	
	
	
	
	
	public void gameOver() {
		endGame = true;
	}
	
	
	public static void line() {
		for (int i = 0; i < 45; i++) {
			if (i < 44 ) {
				if (i % 2 == 0) {
					System.out.print("=");
				} else {
					System.out.print("x");
				}
			} else {
				System.out.print("=\n");
			}
		}
		System.out.println();
	}
	
}
