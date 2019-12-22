package tetris.dao;

import java.util.*;
import tetris.domain.User;

public interface UserDao {

	public List<User> findAll();

	public void create(User user);

	public void save();

	public User findByUsername(String username);
}

