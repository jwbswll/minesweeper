package minesweeper;

abstract class Board {
	
	int size;
	int[][] board =  new int[size][size];
	
	public void setSize(int size) {
		this.size = size;
	}
}
