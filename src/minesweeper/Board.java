package minesweeper;

import java.util.Random;

abstract class Board {
	int[][] board = new int[10][10];
	
	public abstract void setup();
}
