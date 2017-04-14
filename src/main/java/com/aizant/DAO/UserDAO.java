package com.aizant.DAO;

import java.util.List;

import com.aizant.model.User;

public interface UserDAO {
	public List<User> list();

	public User get(String id);
	public User getByUsername(String username);

	public void saveOrUpdate(User user);

	public void delete(String id);

	public List getAllUsers();

	public String deleteUser(String id);

	public List<User> getUserByPage(int pageid, int total);

	public int getPageCount();




}
