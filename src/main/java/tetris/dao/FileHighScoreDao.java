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
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

	@Override
	public List<HighScore> findAll() {
		return highScores;
	}

	@Override
	public void save() throws Exception {
		try (FileWriter writer = new FileWriter(new File(file))) {
            for (HighScore score : highScores) {
                writer.write(score.getUsername() + ";" + score.getScore());
            }
        }
	}

}

