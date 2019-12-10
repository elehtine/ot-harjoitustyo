package tetris.domain;

import javafx.scene.paint.*;

public class Game extends Thread {

	private static final long SLEEP_TIME = 1000;

	private Grid grid;
	private boolean isChanged;
	private int score;
	private boolean running;
	private boolean isStarted; // For testing

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
		running = true;
		isStarted = true;
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

	public void terminate() {
		running = false;
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

	public void drop() {
		if (grid.dropBlock()) {
			isChanged = true;
			++score;
			return;
		}
		newBlock();
	}

	public void hardDrop() {
		while (grid.dropBlock()) {
			++score;
			;
			// Later count points from dropping distance
		}
		newBlock();
	}

	private void newBlock() {
		score += grid.blockToGrid();
		if (grid.nextBlock()) {
			isChanged = true;
			return;
		}
		grid = new Grid(width, height);
		isChanged = true;
	}

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

	/**
	 * Only for testing
	 */
	public boolean getRunning() {
		for (int i = 0; i < 10; ++i) {
			if (isStarted) {
				break;
			}
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return running;
	}

	/**
	 * Returns current score
	 *
	 * @return Current score of game
	 */
	public int getScore() {
		return score;
	}

}

