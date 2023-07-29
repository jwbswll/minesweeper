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
