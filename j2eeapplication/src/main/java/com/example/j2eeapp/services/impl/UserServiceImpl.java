package com.example.j2eeapp.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
		
		if(!userDao.checkAvailable(userEntity.getUserName())) {
			FacesMessage message = constructErrorMessage(String.format("User Name '%s' is not available", userEntity.getUserName()),null);
			getFacesContext().addMessage(null, message);
			return false;
		}
		try {
			userDao.save(userEntity);
		} catch(Exception e) {
			FacesMessage message = constructFatalMessage(e.getMessage(), null);
			getFacesContext().addMessage(null, message);
			return false;
		}
		
		return true;
	}
	

	/**
	 * Construct UserDetails instance required by Spring security.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity user = userDao.loadUserByUserName(username);
		
		if(user==null) {
			throw new UsernameNotFoundException(String.format("No such user with name provided '%s'", username));
		}
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		User userDetails = new User(user.getUserName(), user.getPassword(), authorities);
		return userDetails;
	}


	protected FacesMessage constructErrorMessage(String message, String detail) {
		return new FacesMessage(FacesMessage.SEVERITY_ERROR,message,detail);
	}
	
	protected FacesMessage constructInfoMessage(String message, String detail) {
		return new FacesMessage(FacesMessage.SEVERITY_INFO, message, detail);
	}
	
	protected FacesMessage constructFatalMessage(String message, String detail) {
		return new FacesMessage(FacesMessage.SEVERITY_FATAL, message, detail);
	}
	
	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
