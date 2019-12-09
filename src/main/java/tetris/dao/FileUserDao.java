package tetris.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.*;

import tetris.domain.User;

public class FileUserDao implements UserDao {

	private List<User> users;
	private String file;

	public FileUserDao(String file) throws Exception {
        this.users = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                User u = new User(parts[0], parts[1]);
                users.add(u);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

	@Override
	public List<User> findAll() {
		return users;
	}

	@Override
	public void save() throws Exception {
		try (FileWriter writer = new FileWriter(new File(file))) {
            for (User user : users) {
                writer.write(user.getUsername() + ";" + user.getPasswordHash() + "\n");
            }
        }
	}

	@Override
	public User findByUsername(String username) {
		for (User u: users) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}

}

