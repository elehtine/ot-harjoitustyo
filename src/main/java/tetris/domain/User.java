package tetris.domain;

public class User {

	private String userName;
	private int passwordHash;

	public User(String userName, String password) {
		PasswordHandler passwordHandler = PasswordHandler.getPasswordHandler();
		this.userName = userName;
		this.passwordHash = passwordHandler.getPasswordHash(password);
	}

	public String getUsername() {
		return userName;
	}

	public int getPasswordHash() {
		return passwordHash;
	}

}

