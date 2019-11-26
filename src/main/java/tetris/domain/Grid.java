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
	
	private int colorNb	= 2;
	private final Color[] COLORS = new Color[] { Color.VIOLET, Color.AQUA, Color.BLACK };

	private final boolean t = true;
	private final boolean f = false;

	private final int[] startX = { 5, 5 };
	private final int[] startY = { 1, 2 };

	private final int blockWidth = 5;
	private final int blockAdd = -2;

	private Random random;

	/**
	 * Empty block for testing
	 */
	private final boolean[][] EMPTY_BLOCK = new boolean[][] {
		{ f, f, f, f, f },
		{ f, f, f, f, f },
		{ f, f, t, f, f },
		{ f, f, f, f, f },
		{ f, f, f, f, f } 
	};

	private final boolean[][] T_BLOCK = new boolean[][] {
		{ f, f, f, f, f },
		{ f, f, f, f, f },
		{ f, t, t, t, f },
		{ f, f, t, f, f },
		{ f, f, f, f, f }
	};

	private final boolean[][] I_BLOCK = new boolean[][] {
		{ f, f, f, f, f },
		{ f, f, f, f, f },
		{ t, t, t, t, f },
		{ f, f, f, f, f },
		{ f, f, f, f, f }
	};

	private boolean[][][] blocks;

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
		initGrid(colorNb);
	}

	public void newBlock(int index) {
		if (index == colorNb) {
			blockColor = random.nextInt(colorNb);
		} else {
			blockColor = index;
		}
		block = blocks[blockColor];
		x = startX[blockColor];
		y = startY[blockColor];
	}

	// rotate block clockwise
	public boolean rotateBlock() {
		boolean[][] temp = new boolean[blockWidth][blockWidth];
		for (int i = 0; i < blockWidth; ++i) {
			for (int j = 0; j < blockWidth; ++j) {
				temp[i][j] = block[blockWidth - 1 - j][i];
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
		if (canMove(x, y+1, block)) {
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
				int newX = x + i + blockAdd;
				int newY = y + j + blockAdd;
				if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
					if (block[i][j]) {
						System.out.println("over");
						return false;
					}
					continue;
				}
				if (block[i][j] && grid[newX][newY] != colorNb) {
					for (int u = 0; u < width; ++u) {
						for (int v = 0; v < height; ++v) {
							System.out.print(grid[u][v]);
						}
						System.out.println();
					}

					System.out.println(newX+":"+newY);
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
		for (int i = 0; i < blockWidth; ++i) {
			for (int j = 0; j < blockWidth; ++j) {
				if (i + x + blockAdd < 0 || i + x + blockAdd >= width) {
					continue;
				}
				if (j + y + blockAdd < 0 || j + y + blockAdd >= height) {
					continue;
				}
				result[i + x + blockAdd][j + y + blockAdd] = block[i][j] ? blockColor : colorNb;
			}
		}
		return result;
	}

	public int getBlockHash() {
		int hash = 0;
		for (int i = 0; i < blockWidth; ++i) {
			for (int j = 0; j < blockWidth; ++j) {
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
				grid[i][j] = colorNb;
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

