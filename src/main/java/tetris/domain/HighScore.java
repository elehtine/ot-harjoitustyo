package tetris.domain;

/**
 * Class for saving scores
 */
public class HighScore {

	private final int score;
	private final String username;

	public HighScore(String username, int score) {
		this.score = score;
		this.username = username;
	}

	/**
	 * Return score
	 *
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Return user that got score
	 *
	 * @return user that made score
	 */
	public String getUsername() {
		return username;
	}

}

