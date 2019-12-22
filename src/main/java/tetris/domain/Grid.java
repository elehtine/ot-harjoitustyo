package tetris.domain;

import javafx.scene.paint.*;
import java.util.*;

/**
 * Class contains game grid and currently
 * dropping block
 */
public class Grid {

	private int width;
	private int height;

	private int[][] grid;
	private boolean[][] block;
	private int blockColor;
	private int x;
	private int y;
	
	private static final int COLOR_NB = 7;
	private static final Color[] COLORS = new Color[] { 
		Color.VIOLET, Color.AQUA, Color.GREEN,
		Color.RED, Color.BLUE, Color.ORANGE,
		Color.YELLOW, Color.BLACK 
	};

	private static final boolean T = true;
	private static final boolean F = false;

	private static final int[] START_X = { 5, 5, 5, 5, 5, 5, 5 };
	private static final int[] START_Y = { 1, 2, 1, 1, 1, 1, 1 };

	private static final int BLOCK_WIDTH = 5;
	private static final int BLOCK_ADD = -2;

	private Random random;

	/*
	 * Empty block for testing
	 */
	private static final boolean[][] EMPTY_BLOCK = new boolean[][] {
		{ F, F, F, F, F },
		{ F, F, F, F, F },
		{ F, F, T, F, F },
		{ F, F, F, F, F },
		{ F, F, F, F, F } 
	};

	private static final boolean[][] T_BLOCK = new boolean[][] {
		{ F, F, F, F, F },
		{ F, F, F, F, F },
		{ F, T, T, T, F },
		{ F, F, T, F, F },
		{ F, F, F, F, F }
	};

	private static final boolean[][] I_BLOCK = new boolean[][] {
		{ F, F, F, F, F },
		{ F, F, F, F, F },
		{ T, T, T, T, F },
		{ F, F, F, F, F },
		{ F, F, F, F, F }
	};

	private static final boolean[][] S_BLOCK = new boolean[][] {
		{ F, F, F, F, F },
		{ F, F, T, T, F },
		{ F, T, T, F, F },
		{ F, F, F, F, F },
		{ F, F, F, F, F }
	};

	private static final boolean[][] Z_BLOCK = new boolean[][] {
		{ F, F, F, F, F },
		{ F, T, T, F, F },
		{ F, F, T, T, F },
		{ F, F, F, F, F },
		{ F, F, F, F, F } 
	};

	private static final boolean[][] J_BLOCK = new boolean[][] {
		{ F, F, F, F, F },
		{ F, F, T, T, F },
		{ F, F, T, F, F },
		{ F, F, T, F, F },
		{ F, F, F, F, F } 
	};

	private static final boolean[][] L_BLOCK = new boolean[][] {
		{ F, F, F, F, F },
		{ F, T, T, F, F },
		{ F, F, T, F, F },
		{ F, F, T, F, F },
		{ F, F, F, F, F } 
	};

	private static final boolean[][] O_BLOCK = new boolean[][] {
		{ F, F, F, F, F },
		{ F, F, T, T, F },
		{ F, F, T, T, F },
		{ F, F, F, F, F },
		{ F, F, F, F, F } 
	};

	private boolean[][][] blocks;

	/**
	 * Alternative constructor for testing
	 */
	public Grid(int width, int height, int index) {
		this.random = new Random(System.nanoTime());
		this.blocks = new boolean[][][] {
			T_BLOCK, I_BLOCK, S_BLOCK, 
			Z_BLOCK, J_BLOCK, L_BLOCK,
			O_BLOCK
		};
		this.width = width;
		this.height = height;
		initGrid(index);
	}

	/**
	 * Constructor that game use
	 */
	public Grid(int width, int height) {
		this.random = new Random(System.nanoTime());
		this.blocks = new boolean[][][] {
			T_BLOCK, I_BLOCK, S_BLOCK, 
			Z_BLOCK, J_BLOCK, L_BLOCK,
			O_BLOCK
		};
		this.width = width;
		this.height = height;
		initGrid(COLOR_NB);
	}

	/**
	 * Adds block to grid and call new block method
	 * 
	 * @see tetris.domain.Grid#newBlock(int)
	 *
	 * @return can game be continued or is it over
	 */
	public boolean nextBlock() {
		for (int i = 0; i < BLOCK_WIDTH; ++i) {
			for (int j = 0; j < BLOCK_WIDTH; ++j) {
				if (block[i][j]) {
					grid[i + x + BLOCK_ADD][j + y + BLOCK_ADD] = blockColor;
				}
			}
		}
		return newBlock(COLOR_NB);
	}

	/**
	 * Adds block to grid
	 *
	 * @return points from removed lines
	 */
	public int blockToGrid() {
		for (int i = 0; i < BLOCK_WIDTH; ++i) {
			for (int j = 0; j < BLOCK_WIDTH; ++j) {
				if (block[i][j]) {
					grid[i + x + BLOCK_ADD][j + y + BLOCK_ADD] = blockColor;
				}
			}
		}
		return removeLines();
	}

	/**
	 * Summons new block
	 * 
	 * @return can game be continued or is it over
	 */
	public boolean newBlock(int index) {
		if (index == COLOR_NB) {
			blockColor = random.nextInt(COLOR_NB);
		} else {
			blockColor = index;
		}
		block = blocks[blockColor];
		x = START_X[blockColor];
		y = START_Y[blockColor];
		if (!canMove(x, y, block)) {
			return false;
		}
		return true;
	}

	/**
	 * Rotates block counter clockwise
	 *
	 * @return is rotation legal
	 */
	public boolean rotateCounterClockwise() {
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
		for (int i = 1; i <= 2; ++i) {
			if (canMove(x + i, y, temp)) {
				x += i;
				block = temp;
				return true;
			}
			if (canMove(x - i, y, temp)) {
				x -= i;
				block = temp;
				return true;
			}
		}
		return false;
	}

	/**
	 * Rotates block clockwise
	 *
	 * @return is rotation legal
	 */
	public boolean rotateClockwise() {
		boolean[][] temp = new boolean[BLOCK_WIDTH][BLOCK_WIDTH];
		for (int i = 0; i < BLOCK_WIDTH; ++i) {
			for (int j = 0; j < BLOCK_WIDTH; ++j) {
				temp[i][j] = block[j][BLOCK_WIDTH - 1 - i];
			}
		}
		if (canMove(x, y, temp)) {
			block = temp;
			return true;
		}
		for (int i = 1; i <= 2; ++i) {
			if (canMove(x + i, y, temp)) {
				x += i;
				block = temp;
				return true;
			}
			if (canMove(x - i, y, temp)) {
				x -= i;
				block = temp;
				return true;
			}
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
						return false;
					}
					continue;
				}
				if (block[i][j] && grid[newX][newY] != COLOR_NB) {
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

	/**
	 * Removes full lines
	 *
	 * @return Points frome removed lines
	 */
	private int removeLines() {
		int add = 0;
		for (int j = height-1; j >= 0; --j) {
			boolean remove = true;
			for (int i = 0; i < width; ++i) {
				if (grid[i][j] == COLOR_NB) {
					remove = false;
				}
				grid[i][j + add] = grid[i][j];
			}
			if (remove) {
				++add;
			}
		}
		return 2*add*add;
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

