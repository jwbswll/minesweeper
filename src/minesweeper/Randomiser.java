package minesweeper;

import java.util.Random;

public class Randomiser {
	int max = 9;
	public Randomiser() {
		
	}
	public int[] getRandomCoords() {
		Random random = new Random();
		int row = random.nextInt(max);
		int col = random.nextInt(max);
		int[] coords = {row, col};
		
		return coords;
	}
}
