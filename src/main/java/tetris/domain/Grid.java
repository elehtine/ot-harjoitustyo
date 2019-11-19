package tetris.domain;

import javafx.scene.paint.*;

/**
 * Game area
 * Handles falled and falling blocks
 *
 */
public class Grid {

	private int width;
	private int height;

	private int[][] grid;
	private boolean[][] block;
	private int blockColor;
	private int x;
	private int y;
	
	private final int COLOR_NB = 2;
	private final Color[] COLORS = new Color[] { Color.WHITE, Color.AQUA, Color.BLACK };

	private final boolean T = true;
	private final boolean F = false;

	private final int BLOCK_WIDTH = 5;
	private final int BLOCK_ADD = -2;

	/**
	 * Empty block for testing
	 */
	private final boolean[][] EMPTY_BLOCK = new boolean[][] {
		{ F, F ,F, F, F },
		{ F, F ,F, F, F },
		{ F, F ,T, F, F },
		{ F, F ,F, F, F },
		{ F, F ,F, F, F }
	};

	private final boolean[][] I_BLOCK = new boolean[][] {
		{ F, F ,F, F, F },
		{ F, F ,F, F, F },
		{ T, T ,T, T, F },
		{ F, F ,F, F, F },
		{ F, F ,F, F, F }
	};

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		initGrid();
	}

	public int[][] getGrid() {
		int[][] result = new int[width][height];
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				result[i][j] = grid[i][j];
			}
		}
		if (block == null) {
			return result;
		}
		for (int i = 0; i < BLOCK_WIDTH; ++i) {
			for (int j = 0; j < BLOCK_WIDTH; ++j) {
				if (i+x + BLOCK_ADD < 0 || i+x + BLOCK_ADD >= width) {
					continue;
				}
				if (j+y < 0 || j+y >= height) {
					continue;
				}
				result[i+x + BLOCK_ADD][j+y] = block[i][j] ? blockColor : COLOR_NB;
			}
		}
		return result;
	}

	// TODO: Fix to final variables
	public void newBlock() {
		block = I_BLOCK;
		blockColor = 1;
		x = 5;
		y = 0;
	}

	// rotate block clockwise
	public void rotateBlock() {
		boolean[][] temp = new boolean[BLOCK_WIDTH][BLOCK_WIDTH];
		for (int i = 0; i < BLOCK_WIDTH; ++i) {
			for (int j = 0; j < BLOCK_WIDTH; ++j) {
				temp[i][j] = block[BLOCK_WIDTH-1 -j][i];
			}
		}
		block = temp;
	}

	// Fix collision
	public void moveBlock() {
		++y;
	}

	public int getBlockHash() {
		int hash = 0;
		for (int i = 0; i < BLOCK_WIDTH; ++i) {
			for (int j = 0; j < BLOCK_WIDTH; ++j) {
				if (block[i][j]) {
					++hash;
				}
				hash *= 2;
			}
		}
		return hash;
	}

	public Color[] getColors() {
		return COLORS;
	}

	private void initGrid() {
		this.grid = new int[width][height];
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				grid[i][j] = COLOR_NB;
			}
		}
		newBlock();
	}

}

