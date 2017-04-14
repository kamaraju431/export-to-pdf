package com.aizant.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aizant.DAO.UserDAO;
import com.aizant.model.User;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private UserDAO userDao;
    
    @Autowired
    PasswordEncoder passwordEncoder;
     
    @Transactional
    public User registerNewUserAccount(User user)  {
    	System.out.println("USER BEING ADDED" + user.getUsername());
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	System.out.println("USER PASSWORD" + user.getPassword());
        userDao.saveOrUpdate(user);      
        return user;
    }
}