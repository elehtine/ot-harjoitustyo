package tetris.dao;

import java.util.*;
import tetris.domain.HighScore;

/**
 * Dao interface for saving highscores
 */
public interface HighScoreDao {

	public List<HighScore> findAll();

	public void create(HighScore h);

	public void save();

}

