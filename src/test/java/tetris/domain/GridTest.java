package tetris.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GridTest {
    
	Grid grid;
    
	@Before
	public void setUp() {
		grid = new Grid(10, 20);
	}

	@Test 
	public void testBlock() {
		assertEquals(grid.getBlockHash(), 61440);
	}

    @Test
    public void testRotate() {
		grid.rotateBlock();
		assertEquals(grid.getBlockHash(), 8659200);
	}

}

