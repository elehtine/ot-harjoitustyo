package tetris.domain;

import javafx.scene.paint.*;

/**
 * Thread that updates game grid and drops block
 */
public class Game extends Thread {

	private static final long SLEEP_TIME = 1000;

	private Grid grid;
	private boolean isChanged;
	private int score;
	private boolean running;
	private boolean isStarted; // For testing
	private boolean stop;

	private int width = 10;
	private int height = 20;

	/**
	 * Constructor initializes game grid, block and score
	 */
	public Game() {
		grid = new Grid(width, height);
		score = 0;
		running = true;
		stop = false;
	}

	/**
	 * Start game
	 */
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

	/**
	 * Stops game
	 */
	public void terminate() {
		running = false;
		stop = true;
	}

	/**
	 * Rotates block
	 */
	public void rotate() {
		if (!running) {
			return;
		}
		if (grid.rotateClockwise()) {
			isChanged = true;
		}
	}

	/**
	 * Move block rigth and left one pixel
	 *
	 * right: dx = 1
	 * left: dx = -1
	 *
	 * @param dx direction to move
	 */
	public void move(int dx) {
		if (!running) {
			return;
		}
		if (dx != 1 && dx != -1) {
			return;
		}
		if (grid.move(dx)) {
			isChanged = true;
		}
	}

	/**
	 * Drop block one pixel down
	 */
	public void drop() {
		if (!running) {
			return;
		}
		if (grid.dropBlock()) {
			isChanged = true;
			++score;
			return;
		}
		newBlock();
	}

	/**
	 * Drop block to the bottom
	 */
	public void hardDrop() {
		if (!running) {
			return;
		}
		while (grid.dropBlock()) {
			++score;
		}
		newBlock();
	}

	/**
	 * Create new block
	 */
	private void newBlock() {
		score += grid.blockToGrid();
		if (grid.nextBlock()) {
			isChanged = true;
			return;
		}
		running = false;
		isChanged = true;
	}

	/**
	 * Return copy of game grid
	 *
	 * @return game grid
	 */
	public int[][] getGrid() {
		return grid.getGrid();
	}

	/**
	 * Return colors used in game
	 * 
	 * @return colors
	 */
	public Color[] getColors() {
		return grid.getColors();
	}

	/**
	 * Return is grid state updated since last check
	 */
	public boolean getIsChanged() {
		boolean result = isChanged;
		isChanged = false;
		return result;
	}

	/**
	 * Return is thread running
	 *
	 * @return true if thread is running
	 */
	public boolean getIsRunning() {
		return running;
	}

	/**
	 * Return is thread running
	 *
	 * Only for testing!!
	 *
	 * @return true if thread is running
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

