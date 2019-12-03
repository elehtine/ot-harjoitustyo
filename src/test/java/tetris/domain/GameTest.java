package tetris.domain;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {
    
	Game game;
    
	@Before
	public void setUp() {
		game = new Game();
		game.start();
	}

	@After
	public void tearDown() {
		game.terminate();
	}

	@Test
	public void testRunning() {
		assertTrue(game.getRunning());
	}

	@Test
	public void testTerminating() {
		assertTrue(game.getRunning());
		game.terminate();
		assertFalse(game.getRunning());
	}

	@Test
	public void testRotateChangesUi() {
		assertTrue(game.getRunning());
		game.rotate();
		assertTrue(game.getIsChanged());
	}

	@Test
	public void testDropChangesUi() {
		assertTrue(game.getRunning());
		game.drop();
		assertTrue(game.getIsChanged());
	}

	@Test
	public void testHardDropChangesUi() {
		assertTrue(game.getRunning());
		game.hardDrop();
		assertTrue(game.getIsChanged());
	}

	@Test
	public void testmoveChangesUi() {
		assertTrue(game.getRunning());
		game.move(1);
		assertTrue(game.getIsChanged());
	}

	@Test
	public void testIsChangedIsFalse() {
		assertTrue(game.getRunning());
		game.terminate();
		game.getIsChanged();
		assertFalse(game.getIsChanged());
	}

}

