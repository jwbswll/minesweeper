package minesweeper;

import java.util.ArrayList;
import java.util.Arrays;

public class MineField extends Board {
	public void setup() {
		Randomiser rand = new Randomiser();
		ArrayList<int[]> mineCoords = new ArrayList<int[]>();
		for (int i = 0; i < 10; i++) {
				int[] randomMines = rand.getRandomCoords();
				Boolean isUnique = false;
				while(!isUnique) {
					if (!isInList(mineCoords, randomMines)) {
						mineCoords.add(randomMines);
						isUnique = true;
					} else {
						randomMines = rand.getRandomCoords();
					}
				}
				
		}
		// place 100 wherever there is a mine
		for (int[] x : mineCoords) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					this.board[x[0]][x[1]] = 100; 
				}
			}
		}
		
		// increase count of neighbouring mines
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (this.board[i][j] >= 100) {
					// all mines that arent on the edges
					if(i > 0 && j > 0 && i < 9 && j < 9) {
						//top left
						this.board[i-1][j-1]++;
						// top middle
						this.board[i-1][j]++;
						// top right
						this.board[i-1][j+1]++;
						// middle left
						this.board[i][j-1]++;
						// middle right
						this.board[i][j+1]++;
						// bottom left
						this.board[i+1][j-1]++;
						// bottom middle
						this.board[i+1][j]++;
						// bottom right
						this.board[i+1][j+1]++;
					// top row
					} else if (i == 0 && j > 0 && j < 9) {
						// middle left
						this.board[i][j-1]++;
						// middle right
						this.board[i][j+1]++;
						// bottom left
						this.board[i+1][j-1]++;
						// bottom middle
						this.board[i+1][j]++;
						// bottom right
						this.board[i+1][j+1]++;
					// bottom row	
					} else if (i == 9 && j > 0 && j < 9) {
						//top left
						this.board[i-1][j-1]++;
						// top middle
						this.board[i-1][j]++;
						// top right
						this.board[i-1][j+1]++;
						// middle left
						this.board[i][j-1]++;
						// middle right
						this.board[i][j+1]++;
					// left column
					} else if (j == 0 && i > 0 && i < 9) {
						// top middle
						this.board[i-1][j]++;
						// top right
						this.board[i-1][j+1]++;
						// middle right
						this.board[i][j+1]++;
						// bottom right
						this.board[i+1][j+1]++;
						// bottom middle
						this.board[i+1][j]++;
					// right column
					} else if (j == 9 && i > 0 && i < 9) {
						//top left
						this.board[i-1][j-1]++;
						// top middle
						this.board[i-1][j]++;
						// middle left
						this.board[i][j-1]++;
						// bottom left
						this.board[i+1][j-1]++;
						// bottom middle
						this.board[i+1][j]++;
					}
				}
			}
		}
		
		
		for(int[] row : this.board) {
			System.out.println();
			for (int i : row) {
	            System.out.print(i);
	            System.out.print("\t");
	        }
	        System.out.println();
        }
	}
	public boolean isInList(final ArrayList<int[]> coords, final int[] randMines ) {
		return coords.stream().anyMatch(x -> Arrays.equals(x, randMines));
	}
}
