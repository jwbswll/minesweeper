package minesweeper;

public class VisibleField extends Board {
	
	public void printField() {
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
					if (this.board[i][j] > 0 && this.board[i][j] < 10) {
						System.out.printf("| %s ", this.board[i][j]);
					} else if (this.board[i][j] == 10) {
						System.out.print("|   ");
					} else {
						System.out.print("| # ");
					}
				} else if (j == 9) {
					if (this.board[i][j] > 0 && this.board[i][j] < 10) {
						System.out.printf("| %s |\n\n", this.board[i][j]);
					} else if (this.board[i][j] == 10) {
						System.out.print("|   |\n\n");
					} else {
						System.out.print("| # |\n\n");
					}
				}
			}
		}
		Minesweeper.line();
	}
	
	public void addMines(MineField mineField) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (mineField.board[i][j] >= 100)
				this.board[i][j] = mineField.board[i][j];
			}
		}
	}
	
	public boolean isValid(int[] nextMove, MineField mineField) {
		int x = nextMove[0];
		int y = nextMove[1];
		int [][] dirs = {{1, 1}, {-1, -1}, {-1, 1}, {1, -1},{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		if (x >= 0 && x <= 9 && y >= 0 && y <= 9) {
			if (mineField.board[x][y] == 10) {
				for (int[] dir : dirs) {
					if(x+dir[0] < 10 && y+dir[1] < 10 && x+dir[0] >= 0 && y+dir[1] >= 0) {
					if (mineField.board[x+dir[0]][y+dir[1]] < 10) {
						this.board[x+dir[0]][y+dir[1]] = mineField.board[x+dir[0]][y+dir[1]];
					}
					}
				}
				return true;
			} else if (mineField.board[x][y] < 10) {
				this.board[x][y] = mineField.board[x][y];
				return false;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void revealAdjacentEmptySquares(int[] move, int count, MineField mineField) {
		int x = move[0];
		int y = move[1];
		int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		if((mineField.board[x][y] == 10)) {
			board[x][y] = 10;
			while (count <= 7 && count >= 0) {
				int[] nextMove = {x+dirs[count][0], y+dirs[count][1]};
				if (isValid(nextMove, mineField) == true) {
					revealAdjacentEmptySquares(nextMove, count++, mineField);		 
				} else {
					count ++;
				}
			} 
		} else if (mineField.board[x][y] < 10) {
			this.board[x][y] = mineField.board[x][y];
			while (count <= 7 && count >= 0) {
				int[] nextMove = {x-dirs[count][0], y-dirs[count][1]};
				if (isValid(nextMove, mineField) == true) {
					revealAdjacentEmptySquares(nextMove, count-2, mineField);		 
				} else {
					count ++;
				}
			}
		}
	}

	
	public void printGameOver(MineField mineField) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				this.board[i][j] = mineField.board[i][j];
			}
		}
		
		
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
					if (this.board[i][j] < 10) {
						System.out.printf("| %s ", this.board[i][j]);
					} else if (this.board[i][j] == 10) {
						System.out.print("|   ");
					} else if (this.board[i][j] >= 100) {
						System.out.print("| * ");
					}
				} else if (j == 9) {
					if (this.board[i][j] < 10) {
						System.out.printf("| %s |\n\n", this.board[i][j]);
					} else if (this.board[i][j] == 10) {
						System.out.print("|   |\n\n");
					} else if (this.board[i][j] >= 100) {
						System.out.print("| * |\n\n");
					}
				}
			}
		}
		Minesweeper.line();
		
		
	}
}
