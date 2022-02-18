package com.learning.security.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.learning.dto.User;
import com.learning.exception.IdNotValidException;
import com.learning.repository.UserRepository;
@Component
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("user not found with username " + username));
		
		return UserDetailsImpl.build(user);
	}

}
