package com.aizant.DAO;

import java.util.List;

import com.aizant.model.Login;

public interface LoginDAO {
	public List<Login> list();

	public Login get(int id);

	public void save(Login login);

	public void Update(Login login);

	public void delete(int id);
	
}
