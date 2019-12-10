package tetris.ui;

import javafx.scene.paint.*;
import javafx.scene.canvas.GraphicsContext;

import tetris.domain.Game;
import tetris.domain.UserHighScoreService;

/**
 * Handles painting of GraphicsContext
 */
public class Painter extends Thread {

	private static final long SLEEP_TIME = 20;
	private static final int PX_WIDTH = 20;
	private GraphicsContext gc;
	private Game game;
	private UserHighScoreService userHighScoreService;
	private boolean running;
	private static final Color TEXT_COLOR = Color.RED;

	public Painter(GraphicsContext gc, Game game, UserHighScoreService userHighScoreService) {
		this.gc = gc;
		this.game = game;
		this.running = false;
		this.userHighScoreService = userHighScoreService;
	}

	@Override
	public void run() {
		running = true;

		while (running) {
			if (game.getIsChanged()) {
				paint(game.getGrid(), game.getColors(), game.getScore());
			}

			try {
				Thread.sleep(SLEEP_TIME);
			} catch (Exception e) {
				running = false;
				e.printStackTrace();
			}
		}
	}

	public void terminate() {
		int score = game.getScore();
		running = false;
	}

	private void paint(int[][] grid, Color[] colors, int score) {
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j) {
				gc.setFill(colors[ grid[i][j] ]);
				gc.fillRect(i * PX_WIDTH, j * PX_WIDTH, PX_WIDTH, PX_WIDTH);
			}
		}
		gc.setFill(TEXT_COLOR);
		gc.fillText("Score :" + score, 100, 50);
	}

}

