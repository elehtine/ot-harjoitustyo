package tetris.dao;

import java.util.*;
import tetris.domain.User;

public interface UserDao {

	public List<User> findAll();

	public void save() throws Exception;

	public User findByUsername(String username);
}

