package tetris.domain;

import java.util.*;
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
		grid = new Grid(10, 20, 1);
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

	@Test
	public void testDrop() {
		int y = grid.getY();
		grid.dropBlock();
		assertEquals(grid.getY(), y+1);
	}

	@Test
	public void testRotateAnddrop() {
		int y = grid.getY();
		grid.dropBlock();
		grid.rotateBlock();
		assertEquals(grid.getY(), y+1);
	}

	@Test
	public void testHardDrop() {
		grid.hardDrop();
		assertEquals(grid.getY(), grid.getHeight()-2);
	}

	@Test
	public void gridCanBeChangedWithoutInfluence() {
		int[][] first = grid.getGrid();
		for (int i = 0; i < grid.getWidth(); ++i) {
			for (int j = 0; j < grid.getHeight(); ++j) {
				first[i][j] = i * j;
			}
		}
		int[][] second = grid.getGrid();
		assertFalse("Changing grid outside Grid object changes gamegrid", Arrays.equals(first, second));
	}

}

