package tetris.dao;

import java.util.*;
import tetris.domain.HighScore;

public interface HighScoreDao {

	public List<HighScore> findAll();

	public void save() throws Exception;

}

