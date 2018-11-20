package com.example.j2eeapp.services;

import com.example.j2eeapp.domain.UserEntity;

/**
 * 
 * Service providing service methods to work with user data and entity.
 * 
 * @author Diogo
 *
 */
public interface UserService {

	/**
	 * 
	 * Create user - persist to database
	 * 
	 * @param userEntity
	 * @return
	 */
	boolean createUser(UserEntity userEntity);
}
