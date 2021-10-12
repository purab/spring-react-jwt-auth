package com.pkharat.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pkharat.app.entities.User;
import com.pkharat.app.repository.UserDetailsRepository;

@Service
public class CustomUserService implements UserDetailsService {
	
	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDetailsRepository.findByUserName(username);
		if(null==username) {
			throw new UsernameNotFoundException("user not found with username:"+username);
		}
		return user;
	}

}
