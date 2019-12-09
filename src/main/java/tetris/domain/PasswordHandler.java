package tetris.domain;

/**
 * Singleton class
 *
 * Generates password hash and compare password to hash
 */
public class PasswordHandler {

	private static PasswordHandler passwordHandler = null;
	private static final int MAX_VALUE = 15485863;
	private static final int FACTOR = 281;

	private PasswordHandler() {
		//	Private constructor
	}

	public static PasswordHandler getPasswordHandler() {
		if (passwordHandler == null) {
			passwordHandler = new PasswordHandler();
		}
		return passwordHandler;
	}

	/**
	 * @return true if password produces same hash
	 */ 
	public boolean comparePassword(String password, int passwordHash) {
		return passwordHash == getPasswordHash(password);
	}

	/**
	 * @return password as a hashed long
	 */
	public int getPasswordHash(String password) {
		int hash = 1;
		for (int i = 0; i < password.length(); ++i) {
			hash += (int) password.charAt(i);
			hash = hash * FACTOR % MAX_VALUE;
		}
		return hash;
	}

}

