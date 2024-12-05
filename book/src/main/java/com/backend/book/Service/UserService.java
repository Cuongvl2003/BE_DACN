package com.backend.book.Service;

import java.util.List;
import java.util.Optional;

import com.backend.book.Model.Entity.User;

public interface UserService {
    public List<User> getAllUsers();
    public Optional<User> getUserById(String Id);
    public User saveUser(User user);
    public User updateUser(String id, User user);
    public String deleteUser(String id);
    
}