package tetris.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.*;

import tetris.domain.User;
import tetris.domain.HighScore;

/**
 * Class for saving highscores to file
 */
public class FileHighScoreDao implements HighScoreDao {

	private List<HighScore> highScores;
	private String file;

	/**
	 * Initialize class
	 *
	 * @param file name of file used as database
	 */
	public FileHighScoreDao(String file) throws Exception {
        this.highScores = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                HighScore score = new HighScore(parts[0], Integer.parseInt(parts[1]));
                highScores.add(score);
            }
        } catch (Exception e) {
			e.printStackTrace();
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

	/**
	 * Return all highscore data
	 *
	 * @return list of highscores
	 */
	@Override
	public List<HighScore> findAll() {
		return highScores;
	}

	/**
	 * Add new highscore to data
	 *
	 * @param h highscore to add
	 */
	@Override
	public void create(HighScore h) {
		highScores.add(h);
	}

	/**
	 * Write all data to file
	 */
	@Override
	public void save() {
		FileWriter writer = null;
		try {
			writer = new FileWriter(new File(file));
            for (HighScore score : highScores) {
                writer.write(score.getUsername() + ";" + score.getScore() + "\n");
            }
        } catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

