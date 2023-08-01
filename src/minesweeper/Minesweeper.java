package minesweeper;
import java.util.Arrays;
import java.util.Scanner;

public class Minesweeper {
	
	boolean endGame = false;
	int[] move;
	int numMines = 10;
	
	// 0 = no mines in 3x3 grid
	// 10 = no mines in 3x3 grid and has been checked by player
	// n < 9 = there is n mines in 3x3 grid 
	// >100 = mine
	
	
	public void start() {
		System.out.println("\n           Welcome to Minesweeper");
		line();
		System.out.println();
		Scanner x = new Scanner(System.in);
		numMines = setDifficulty(x);
		setupGame();
		MineField mineField = new MineField();
		mineField.setup(numMines);
		VisibleField visibleField = new VisibleField();
		visibleField.addMines(mineField);
		while(!endGame) { 
			printLegend(mineField);
			move = playerMove(x);
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
						visibleField.revealAdjacentEmptySquares(move, 0, mineField);
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
			x.close();
		}
		 
	
	public int setDifficulty(Scanner s) {
		int diff;
		int numMines = 10;
		boolean valid = false;
		while(!valid) {
			System.out.println("                  DIFFICULTY\n");
			System.out.println("                --------------");
			System.out.println("                | Easy   [1] |\n                | Medium [2] |\n                | Hard   [3] |");
			System.out.println("                --------------\n");
			System.out.print("Select a difficulty level: ");
			
			while(!s.hasNextInt()) {
				System.out.println("Invalid input, please try again\n");
				s.next();
			}
			diff = s.nextInt();
			if (diff == 1) {
				System.out.println("\nDifficulty: Easy\n\n");
				numMines = 10;
				valid = true;
			} else if (diff == 2) {
				System.out.println("\nDifficulty: Medium\n\n");
				numMines = 20;
				valid = true;
			} else if (diff == 3) {
				System.out.println("\nDifficulty: Hard\n\n");
				numMines = 30;
				valid = true;
			} else {
				System.out.println("Invalid input, please try again\n");
			}
		}
		return numMines;
	}
	
	
	public void setupGame() {
		
		line();
		for (int k = 0; k < 10; k++) {
			if(k == 0) {
				// top number row
				System.out.print("     " + k + "   ");
			}else if (k < 9) {
			System.out.print(k + "   ");
			} else if (k == 9) {
				System.out.print(k + "\n\n");
			}
		}
		for (int i = 0; i < 10; i++) {
			if (i < 10) {
				// left number row
				System.out.print(i + "  ");
			}
			for (int j = 0; j < 10; j++) {
				if (j < 9) {
					System.out.print("| # ");
				} else if (j == 9) {
					System.out.println("| # |\n");
				}
			}
		}
		line();
	}
	
	public void printLegend(MineField mineField) { 
		Minesweeper.line();
		for (int k = 0; k < 10; k++) {
			if(k == 0) {
				// top number row
				System.out.print("     " + k + "   ");
			}else if (k < 9) {
			System.out.print(k + "   ");
			} else if (k == 9) {
				System.out.print(k + "\n\n");
			}
		}
		for (int i = 0; i < 10; i++) {
			if (i < 10) {
				// left number row
				System.out.print(i + "  ");
			}
			for (int j = 0; j < 10; j++) {
				if (j < 9) {
					if (mineField.board[i][j] < 10) {
						System.out.printf("| %s ", mineField.board[i][j]);
					} else if (mineField.board[i][j] == 10) {
						System.out.print("|   ");
					} else if (mineField.board[i][j] >= 100) {
						System.out.print("| * ");
					}
				} else if (j == 9) {
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
	
	
	
	
	public int[] playerMove(Scanner s) {
		boolean validMove = false;
		int row = 0;
		int col = 0;
		System.out.println("Time to tempt fate!");
		System.out.println();
		while(!validMove) {
			System.out.print("\nSelect a row number if you dare: ");
			while(!s.hasNextInt()) {
				System.out.print("\nIncorrect value, please enter a number from 0 - 9: ");
				s.next();
			}
			row = s.nextInt();
			
			System.out.print("\nSelect a column number if you dare: ");
			while(!s.hasNextInt()) {
				System.out.print("\nIncorrect value, please enter a number from 0 - 9: ");
				s.next();
			}
			col = s.nextInt();
			if (row > 9 || col > 9 || row < 0 || col < 0) {
				System.out.println("\nIncorrect values, please only enter numbers from 0 - 9");
			} else {
				validMove = true;
			}
		}
		int[] move = {row, col};
		return move;
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
