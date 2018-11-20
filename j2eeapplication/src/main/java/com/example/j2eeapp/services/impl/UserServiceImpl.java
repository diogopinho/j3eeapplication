package com.example.j2eeapp.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.j2eeapp.dao.UserDao;
import com.example.j2eeapp.domain.UserEntity;
import com.example.j2eeapp.services.UserService;

/**
 * 
 * Service providing service methods to work with user data and entity.
 * 
 * @author Diogo
 *
 */
public class UserServiceImpl implements UserService,UserDetailsService {

	private UserDao userDao;
	
	/**
	 * 
	 * Create user - persist to database
	 * 
	 * @param userEntity
	 * @return
	 */
	public boolean createUser(UserEntity userEntity) {
		userDao.save(userEntity);
		return true;
	}
	

	/**
	 * Construct UserDetails instance required by Spring security.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		return null;
	}



	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
