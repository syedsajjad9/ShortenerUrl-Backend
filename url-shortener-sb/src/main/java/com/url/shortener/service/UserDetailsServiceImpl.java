package com.url.shortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.url.shortener.models.User;
import com.url.shortener.repository.UserRepository;





@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
	
		User user=userRepository.findByUsername(username)
				                .orElseThrow(() -> new UsernameNotFoundException("User not found with username" + username));
		return UserDetailsImpl.build(user);
	}

}
