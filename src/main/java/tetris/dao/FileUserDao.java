package tetris.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.*;

import tetris.domain.User;

/**
 * Class for saving users to file
 */
public class FileUserDao implements UserDao {

	private List<User> users;
	private String file;

	/**
	 * Initializes class
	 *
	 * @param file name of file that will contain data
	 */
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

	/**
	 * Returns all user data
	 *
	 * @return all users
	 */
	@Override
	public List<User> findAll() {
		return users;
	}

	/**
	 * Add new user to data
	 *
	 * @param user user that want to add
	 */
	@Override
	public void create(User user) {
		users.add(user);
	}

	/**
	 * Save all user data to database file
	 */
	@Override
	public void save() {
		FileWriter writer = null;
		try {
			writer = new FileWriter(new File(file));
            for (User user : users) {
                writer.write(user.getUsername() + ";" + user.getPasswordHash() + "\n");
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

	/**
	 * Find users from data by username
	 *
	 * @param username name of user
	 * @return user if exists
	 */
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

