package minesweeper;

import java.util.Arrays;
import java.util.Scanner;

public class Minesweeper {
	int[][] gameBoard = new int[10][10];
	int[][] legendBoard = new int[10][10];
	boolean hasWon = false;
	boolean hasLost = false;
	int[] move;
	
	// next steps: 
	// create Board interface to make gameBoard and legendBoard
	// create populateLegend method to add bombs to legendBoard, and to add numbers
	// when player inputs a move, check legendBoard and if their move locates a bomb, 
	// game over, display new board with bombs and numbers
	// if they locate a number, display their current board with the number in place of #
	// if they locate an empty spot display their current board with a 0 in the spot they chose
	
	
	public void start() {
		System.out.println("\n           Welcome to Minesweeper");
		line();
		setupGame();
		System.out.println();
		line();
		MineField mineField = new MineField();
//		while(hasLost == false && hasWon == false) { 
		move = playerMove();
		System.out.println(Arrays.toString(move));
		mineField.setup(); 
			
//		}
		 
	}
	
	
	
	
	public void setupGame() {

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
	}
	
	
	
	
	public int[] playerMove() {
		boolean validMove = false;
		int row = 0;
		int col = 0;
		System.out.println("Time to tempt fate!");
		Scanner s = new Scanner(System.in);
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
	
	
	public void newLegendBoard(int[] move) {
		int row = move[0];
		int col = move[1];
		System.out.println("row: " + row + ", col: " + col);
	}
	
	
	
	
	public void line() {
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
