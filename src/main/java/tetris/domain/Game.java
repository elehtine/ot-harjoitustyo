package tetris.domain;

import javafx.scene.paint.*;

public class Game {

	private Grid grid;

	private final int WIDTH = 10;
	private final int HEIGHT = 20;

	public Game() {
		initGame();
	}

	private void initGame() {
		grid = new Grid(WIDTH, HEIGHT);
	}

	// TODO: Refactor these
	public int[][] getGrid() {
		return grid.getGrid();
	}

	public Color[] getColors() {
		return grid.getColors();
	}

}

