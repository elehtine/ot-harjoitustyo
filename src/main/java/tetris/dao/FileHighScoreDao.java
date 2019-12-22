package tetris.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.*;

import tetris.domain.User;
import tetris.domain.HighScore;

public class FileHighScoreDao implements HighScoreDao {

	private List<HighScore> highScores;
	private String file;

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

	@Override
	public List<HighScore> findAll() {
		return highScores;
	}

	@Override
	public void create(HighScore h) {
		highScores.add(h);
	}

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
			} catch (Exception e) {}
		}
	}

}

