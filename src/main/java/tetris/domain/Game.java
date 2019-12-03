package tetris.domain;

import javafx.scene.paint.*;

public class Game extends Thread {

	private Grid grid;
	private boolean isChanged;
	private boolean running;
	private static final long SLEEP_TIME = 1000;

	private int width = 10;
	private int height = 20;

	public Game() {
		initGame();
	}

	private void initGame() {
		grid = new Grid(width, height);
	}

	@Override
	public void run() {
		System.out.println("RUN GAME");
		running = true;
		while (running) {
			drop();

			try {
				Thread.sleep(SLEEP_TIME);
			} catch (Exception e) {
				e.printStackTrace();
				running = false;
			}
		}
	}

	public void rotate() {
		if (grid.rotateBlock()) {
			isChanged = true;
		}
	}

	/*
	 * right: dx = 1
	 * left: dx = -1
	 */
	public void move(int dx) {
		if (dx != 1 && dx != -1) {
			return;
		}
		if (grid.move(dx)) {
			isChanged = true;
		}
	}

	public boolean drop() {
		isChanged = true;
		if (grid.dropBlock()) {
			return true;
		}
		grid.nextBlock();
		return false;
	}

	public void hardDrop() {
		while (grid.dropBlock()) {
			;
			// Later count points from dropping distance
		}
		grid.nextBlock();
		isChanged = true;
	}

	// TODO: Refactor these
	public int[][] getGrid() {
		return grid.getGrid();
	}

	public Color[] getColors() {
		return grid.getColors();
	}

	public boolean getIsChanged() {
		boolean result = isChanged;
		isChanged = false;
		return result;
	}

}

