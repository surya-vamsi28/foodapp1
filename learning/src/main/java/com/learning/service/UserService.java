package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.learning.dto.User;
import com.learning.exception.AlreadyExistsException;
import com.learning.exception.IdNotFoundException;
@Service
public interface UserService {
	public User addregister(User register) throws AlreadyExistsException;
	public String updateregister(Integer id, User register);
	public User getregisterById(Integer id) throws IdNotFoundException;
	public Optional<List<User>> getAllUserDetails() ;
	public String deleteUserById(Integer id);
	public String authenticate(String email,String password);
	
}
