package com.bookmybus.busbooking.service;

import java.util.List;
import java.util.Optional;
import org.springframework.security.core.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookmybus.busbooking.entity.User;
import com.bookmybus.busbooking.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	private BCryptPasswordEncoder bEncoder = new BCryptPasswordEncoder(12);

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long userId) {
		// TODO Auto-generated method stub
		Optional<User> userOptional = userRepository.findById(userId);
	    
	    if (userOptional.isPresent()) {
	        return userOptional.get();
	    } else {
	        throw new RuntimeException("User not found with id " + userId);
	    }
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User updateUser(Long userId, User userDetails) {
		// TODO Auto-generated method stub
		User existingUser = getUserById(userId);
        existingUser.setUsername(userDetails.getUsername());
        existingUser.setPassword(userDetails.getPassword());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPhone(userDetails.getPhone());
        existingUser.setRole(userDetails.getRole());
        return userRepository.save(existingUser);
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);	
	}

	@Override
	public boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.existsByEmail(email);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public void save(User user) {
		user.setPassword(bEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	

	@Override
	public String verify(User user) {
	    try {
	        Authentication authentication = authenticationManager
	            .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
	        if (authentication.isAuthenticated()) {
	            return jwtService.generateToken(user.getUsername());
	        }
	    } catch (AuthenticationException e) {
	        return "Invalid credentials!";
	    }
	    return "Invalid credentials!";
	}
}
