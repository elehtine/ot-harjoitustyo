package tetris.domain;

import java.util.*;

import tetris.dao.*;

/**
 * Service for finding and saving database
 */
public class UserHighScoreService {

	private UserDao userDao;
	private HighScoreDao highScoreDao;
	private PasswordHandler passwordHandler;

	/**
	 * Initializes daos
	 */
	public UserHighScoreService(UserDao userDao, HighScoreDao highScoreDao) {
		this.userDao = userDao;
		this.highScoreDao = highScoreDao;
		this.passwordHandler = PasswordHandler.getPasswordHandler();
	}

	/**
	 * Validates new usernames
	 *
	 * @return true if unique name and contains just characters
	 */
	public boolean isOkUsername(String username) {
		if (userDao.findByUsername(username) != null) {
			return false;
		}
		return username.matches("[a-zA-Z]+");
	}

	/**
	 * Confirms login
	 *
	 * @return true if correct username and password
	 */
	public boolean isCorrectLogin(String username, String password) {
		User u = userDao.findByUsername(username);
		if (u == null) {
			return false;
		}
		return passwordHandler.checkPassword(password, u.getPasswordHash());
	}


	/**
	 * Add new user to database
	 *
	 * @param username name of new user
	 * @param password password of new user
	 */
	public void addUser(String username, String password) {
		userDao.create(new User(username, password));
	}

	/**
	 * Returns all highscores as sorted
	 *
	 * @return sorted highscores
	 */
	public List<HighScore> getHighScore() {
		List<HighScore> result = highScoreDao.findAll();
		Collections.sort(result);
		return result;
	}

	/**
	 * Add new highscore to database
	 *
	 * @param h new highscore object
	 */
	public void addHighScore(HighScore h) {
		highScoreDao.create(h);
	}

	/**
	 * Saves all data to database
	 */
	public void save() {
		userDao.save();
		highScoreDao.save();
	}

}

