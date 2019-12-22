package tetris.domain;

/**
 * Class for saving score
 */
public class HighScore implements Comparable<HighScore> {

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

	/**
	 * Equals method of highscore
	 *
	 * ignores name
	 *
	 * @return true if score is same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof HighScore)) {
			return false;
		}
		HighScore h = (HighScore) other;
		if (h.score == this.score) {
			return true;
		}
		return false;
	}

	/**
	 * Compares highscores to other
	 *
	 * ignores name
	 *
	 * @return difference in scores
	 */
	public int compareTo(HighScore h) {
		return h.score - this.score;
	}

}

