package tetris.ui;

import javafx.scene.paint.*;
import javafx.scene.canvas.GraphicsContext;
/**
 * Handles painting of GraphicsContext
 */
public class Painter {

	public GraphicsContext gc;

	public Painter(GraphicsContext gc) {
		this.gc = gc;
	}

	public void paint(int[][] grid, Color[] colors, int pxWidth) {
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j) {
				gc.setFill(colors[ grid[i][j] ]);
				gc.fillRect(i*pxWidth, j*pxWidth, pxWidth, pxWidth);
			}
		}
	}

}

