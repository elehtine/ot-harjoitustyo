package tetris.ui;

import javafx.scene.paint.*;
import javafx.scene.canvas.GraphicsContext;

import tetris.domain.Game;
import tetris.domain.HighScore;
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
	private String username;
	private boolean running;
	private static final Color TEXT_COLOR = Color.RED;
	
	public Painter(GraphicsContext gc, UserHighScoreService userHighScoreService, String username) {
		this.gc = gc;
		this.running = false;
		this.userHighScoreService = userHighScoreService;
		this.username = username;
	}

	/**
	 * Start game thread and update ui
	 */
	@Override
	public void run() {
		running = true;
		game = new Game();
		game.start();
		while (running) {
			if (game.getIsChanged()) {
				paint(game.getGrid(), game.getColors(), game.getScore());
			}
			if (!game.getIsRunning()) {
				break;
			}

			try {
				Thread.sleep(SLEEP_TIME);
			} catch (Exception e) {
				running = false;
				e.printStackTrace();
			}
		}
		HighScore highscore = new HighScore(username, game.getScore());
		userHighScoreService.addHighScore(highscore);
		game.terminate();
	}

	/**
	 * Method for terminating game
	 */
	public void terminate() {
		running = false;
	}

	/**
	 * Rotate game block clockwise
	 */
	public void rotate() {
		if (running && game != null) {
			game.rotate();
		}
	}

	/**
	 * Drop game block one line down
	 */
	public void drop() {
		if (running && game != null) {
			game.drop();
		}
	}

	/**
	 * Drop game block to bottom
	 */
	public void hardDrop() {
		if (running && game != null) {
			game.hardDrop();
		}
	}

	/**
	 * Moves block in game
	 */
	public void move(int x) {
		if (running && game != null) {
			game.move(x);
		}
	}

	/**
	 * Updates username that will be saved in highscore database
	 */
	public void setUsername(String name) {
		this.username = name;
	}

	/**
	 * Paints new game grid to ui
	 */
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

