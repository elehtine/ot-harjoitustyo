package tetris.domain;

import javafx.scene.paint.*;

public class Game {

	private Grid grid;

	private int width = 10;
	private int height = 20;

	public Game() {
		initGame();
	}

	private void initGame() {
		grid = new Grid(width, height);
	}

	public void rotate() {
		grid.rotateBlock();
	}

	/*
	 * right: dx = 1
	 * left: dx = -1
	 */
	public void move(int dx) {
		if (dx != 1 && dx != -1) {
			return;
		}
		grid.move(dx);
	}

	public void drop() {
		System.out.println(grid.dropBlock());
	}

	public void hardDrop() {
	}

	// TODO: Refactor these
	public int[][] getGrid() {
		return grid.getGrid();
	}

	public Color[] getColors() {
		return grid.getColors();
	}

}

