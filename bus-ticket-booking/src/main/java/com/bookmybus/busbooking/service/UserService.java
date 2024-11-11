package com.bookmybus.busbooking.service;

import java.util.List;
import com.bookmybus.busbooking.entity.User;

public interface UserService {

	User createUser(User user);
    User getUserById(Long userId);
    List<User> getAllUsers();
    User updateUser(Long userId, User user);
    void deleteUser(Long userId);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User findByUsername(String username);
    void save(User user);
	String verify(User user);
}
