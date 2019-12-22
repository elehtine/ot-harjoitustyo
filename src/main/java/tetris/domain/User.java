package tetris.domain;

/**
 * Class for saving users
 */
public class User {

	private String userName;
	private int passwordHash;

	public User(String userName, String password) {
		PasswordHandler passwordHandler = PasswordHandler.getPasswordHandler();
		this.userName = userName;
		this.passwordHash = passwordHandler.getPasswordHash(password);
	}

	/**
	 * Return username
	 *
	 * @return username
	 */
	public String getUsername() {
		return userName;
	}

	/**
	 * Return hashed password
	 *
	 * @return password hash
	 */
	public int getPasswordHash() {
		return passwordHash;
	}

}

