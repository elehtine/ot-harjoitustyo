package tetris.domain;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PasswordHandlerTest {

	PasswordHandler passwordHandler;

	@Before
	public void setUp() {
		passwordHandler = PasswordHandler.getPasswordHandler();
	}

	@Test
	public void modifiesHashValue() {
		assertFalse(passwordHandler.comparePassword("password", 1));
	}

	@Test
	public void identifyCorrectPassword() {
		String password = "password";
		int passwordHash = passwordHandler.getPasswordHash(password);
		assertTrue(passwordHandler.comparePassword(password, passwordHash));
	}

	@Test
	public void identifyWrongPassword() {
		int correctPasswordHash = passwordHandler.getPasswordHash("password");
		String wrongPassword = "apssword";
		assertFalse(passwordHandler.comparePassword(wrongPassword, correctPasswordHash));
	}

}

