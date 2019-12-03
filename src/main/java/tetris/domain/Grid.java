package tetris.domain;

import javafx.scene.paint.*;
import java.util.*;

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
	
	private static final int COLOR_NB = 2;
	private static final Color[] COLORS = new Color[] { Color.VIOLET, Color.AQUA, Color.BLACK };

	private static final boolean T = true;
	private static final boolean F = false;

	private static final int[] startX = { 5, 5 };
	private static final int[] startY = { 1, 2 };

	private static final int BLOCK_WIDTH = 5;
	private static final int BLOCK_ADD = -2;

	private Random random;

	/**
	 * Empty block for testing
	 */
	private final boolean[][] EMPTY_BLOCK = new boolean[][] {
		{ F, F, F, F, F },
		{ F, F, F, F, F },
		{ F, F, T, F, F },
		{ F, F, F, F, F },
		{ F, F, F, F, F } 
	};

	private final boolean[][] T_BLOCK = new boolean[][] {
		{ F, F, F, F, F },
		{ F, F, F, F, F },
		{ F, T, T, T, F },
		{ F, F, T, F, F },
		{ F, F, F, F, F }
	};

	private final boolean[][] I_BLOCK = new boolean[][] {
		{ F, F, F, F, F },
		{ F, F, F, F, F },
		{ T, T, T, T, F },
		{ F, F, F, F, F },
		{ F, F, F, F, F }
	};

	private boolean[][][] blocks;

	// For testing
	public Grid(int width, int height, int index) {
		this.random = new Random(System.nanoTime());
		blocks = new boolean[][][] { T_BLOCK, I_BLOCK };
		this.width = width;
		this.height = height;
		initGrid(index);
	}

	public Grid(int width, int height) {
		this.random = new Random(System.nanoTime());
		this.blocks = new boolean[][][] { T_BLOCK, I_BLOCK };
		this.width = width;
		this.height = height;
		initGrid(COLOR_NB);
	}

	public void nextBlock() {
		System.out.println("something");
		for (int i = 0; i < BLOCK_WIDTH; ++i) {
			for (int j = 0; j < BLOCK_WIDTH; ++j) {
				if (block[i][j]) {
					grid[i + x + BLOCK_ADD][j + y + BLOCK_ADD] = blockColor;
				}
			}
		}
		newBlock(COLOR_NB);
	}

	public void newBlock(int index) {
		if (index == COLOR_NB) {
			blockColor = random.nextInt(COLOR_NB);
		} else {
			blockColor = index;
		}
		block = blocks[blockColor];
		x = startX[blockColor];
		y = startY[blockColor];
	}

	// rotate block clockwise
	public boolean rotateBlock() {
		boolean[][] temp = new boolean[BLOCK_WIDTH][BLOCK_WIDTH];
		for (int i = 0; i < BLOCK_WIDTH; ++i) {
			for (int j = 0; j < BLOCK_WIDTH; ++j) {
				temp[i][j] = block[BLOCK_WIDTH - 1 - j][i];
			}
		}
		if (canMove(x, y, temp)) {
			block = temp;
			return true;
		}
		return false;
	}

	// Fix collision
	public boolean dropBlock() {
		if (canMove(x, y + 1, block)) {
			++y;
			return true;
		}
		return false;
	}

	public boolean hardDrop() {
		boolean result = false;
		while (dropBlock()) {
			result = true;
		}
		return result;
	}

	public boolean move(int dx) {
		if (dx != 1 && dx != -1) {
			return false;
		}
		if (canMove(x + dx, y, block)) {
			x += dx;
			return true;
		}
		return false;
	}
		

	public boolean canMove(int x, int y, boolean[][] block) {
		for (int i = 0; i < block.length; ++i) {
			for (int j = 0; j < block[0].length; ++j) {
				int newX = x + i + BLOCK_ADD;
				int newY = y + j + BLOCK_ADD;
				if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
					if (block[i][j]) {
						System.out.println("over");
						return false;
					}
					continue;
				}
				if (block[i][j] && grid[newX][newY] != COLOR_NB) {
					System.out.println("hit");
					return false;
				}
			}
		}
		return true;
	}

	public int[][] getGrid() {
		int[][] result = copy(grid);
		if (block == null) {
			return result;
		}
		for (int i = 0; i < BLOCK_WIDTH; ++i) {
			for (int j = 0; j < BLOCK_WIDTH; ++j) {
				if (i + x + BLOCK_ADD < 0 || i + x + BLOCK_ADD >= width) {
					continue;
				}
				if (j + y + BLOCK_ADD < 0 || j + y + BLOCK_ADD >= height) {
					continue;
				}
				if (block[i][j]) { 
					result[i + x + BLOCK_ADD][j + y + BLOCK_ADD] = blockColor;
				}
			}
		}
		return result;
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void initGrid(int index) {
		this.grid = new int[width][height];
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				grid[i][j] = COLOR_NB;
			}
		}
		newBlock(index);
	}

	private static int[][] copy(int[][] array) {
		int[][] result = new int[ array.length ][ array[0].length ];
		for (int i = 0; i < array.length; ++i) {
			for (int j = 0; j < array[0].length; ++j) {
				result[i][j] = array[i][j];
			}
		}
		return result;
	}

}

