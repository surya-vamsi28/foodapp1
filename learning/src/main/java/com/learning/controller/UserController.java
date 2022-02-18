package com.learning.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.User;
import com.learning.exception.AlreadyExistsException;
import com.learning.repository.RoleRepository;
import com.learning.repository.UserRepository;
import com.learning.service.UserService;
import com.learning.dto.EROLE;
import com.learning.dto.Role;
import com.learning.payload.request.SignupRequest;
import com.learning.payload.response.MessageResponse;
import com.learning.payload.request.LoginRequest;
import com.learning.payload.response.JwtResponse;
import com.learning.security.jwt.JwtUtils;
import com.learning.security.services.UserDetailsImpl;



@RestController
@RequestMapping("/api/auth")
public class UserController {
	@Autowired
	UserService userservice;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordencoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateToken(authentication);
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		
		List<String> roles = userDetailsImpl.getAuthorities().stream().map(i->i.getAuthority()).collect(Collectors.toList());
		
		
		
		return ResponseEntity.ok(new JwtResponse(jwt, userDetailsImpl.getId(), userDetailsImpl.getUsername(), userDetailsImpl.getEmail(), roles));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest ){
		
		
		
		
		  if (userRepository.existsByEmail(signupRequest.getEmail())) {
		      return ResponseEntity
		          .badRequest()
		          .body(new MessageResponse("Error: Email is already in use!"));
		    }
		  
		  
		  // user's account
		  
		  User user  = new User(signupRequest.getName(), 
				  signupRequest.getEmail(), 
				  passwordencoder.encode(signupRequest.getPassword()),
				  signupRequest.getAddress() 
				  );
		  // retrieving the roles details
		  
		  Set<String> strRoles= signupRequest.getRole();
		  
		  Set<Role> roles = new HashSet<>();
		  
		  if(strRoles ==null) {
			  Role userRole = roleRepository.findByRoleName(EROLE.ROLE_USER)
					  .orElseThrow(
							  ()->new RuntimeException("Error:role not found")
							  );
			  roles.add(userRole);
		  }
		  
		  else {
			  strRoles.forEach(e->{
				  switch (e) {
				case "admin":
					Role roleAdmin	= roleRepository.findByRoleName(EROLE.ROLE_ADMIN)
							  .orElseThrow(
									  ()->new RuntimeException("Error:role not found")
									  );
					roles.add(roleAdmin);
					break;

				default:
					 Role userRole = roleRepository.findByRoleName(EROLE.ROLE_USER)
					  .orElseThrow(
							  ()->new RuntimeException("Error:role not found")
							  );
					 roles.add(userRole);
				}
			  });
			  
			  
			  
		  }
		  user.setRoles(roles);
		  userRepository.save(user);
		return ResponseEntity.status(201).body(new MessageResponse("user created successfully"));
		  
		  
		  
		
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/register")
	public ResponseEntity<?> addUser(@Valid @RequestBody User register) throws AlreadyExistsException {
		User result = userservice.addregister(register);
			System.out.println(result);
			return ResponseEntity.status(201).body(result);
}
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody User register){
		String res = userservice.authenticate(register.getEmail(), register.getPassword());
		System.out.println(res);
		Map<String,String> map = new HashMap<String, String>();
		map.put("message",res);
		return ResponseEntity.status(201).body(map);
	}
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) throws com.learning.exception.IdNotFoundException{
		User result = userservice.getregisterById(id);
		return ResponseEntity.status(201).body(result);
	}
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/users")
	public ResponseEntity<?> getAllUserDetails(){
		Optional<List<User>> optional = userservice.getAllUserDetails();
		if(optional.isEmpty()) {
			Map<String,String> map = new HashMap<String, String>();
			map.put("message","no record found");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.of(optional);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteuserbyid(@PathVariable("id") Integer id){
		String res = userservice.deleteUserById(id);
		System.out.println(res);
		Map<String,String> map = new HashMap<String, String>();
		map.put("message",res);
		return ResponseEntity.status(201).body(map);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateuserbyid(@PathVariable("id") Integer id,@RequestBody User register){
		String res = userservice.updateregister(id, register);
		Map<String,String> map = new HashMap<String, String>();
		map.put("message",res);
		return ResponseEntity.status(201).body(map);
	}
	
	
}
