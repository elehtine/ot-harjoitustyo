package tetris.domain;

import java.util.*;

import tetris.dao.*;

public class UserHighScoreService {

	private UserDao userDao;
	private HighScoreDao highScoreDao;
	private PasswordHandler passwordHandler;

	public UserHighScoreService(UserDao userDao, HighScoreDao highScoreDao) {
		this.userDao = userDao;
		this.highScoreDao = highScoreDao;
		this.passwordHandler = PasswordHandler.getPasswordHandler();
	}

	public boolean isOkUsername(String username) {
		if (userDao.findByUsername(username) != null) {
			return false;
		}
		return username.matches("[a-zA-Z]+");
	}

	public boolean isCorrectLogin(String username, String password) {
		User u = userDao.findByUsername(username);
		if (u == null) {
			return false;
		}
		return passwordHandler.checkPassword(password, u.getPasswordHash());
	}

	public void addUser(String username, String password) {
		userDao.create(new User(username, password));
	}

	public List<HighScore> getHighScore() {
		List<HighScore> result = highScoreDao.findAll();
		Collections.sort(result);
		return result;
	}

	public void addHighScore(HighScore h) {
		highScoreDao.create(h);
	}

	public void save() {
		userDao.save();
		highScoreDao.save();
	}

}

