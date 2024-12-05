package com.backend.book.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.book.Model.Entity.User;
import com.backend.book.Repository.UserRepository;
import com.backend.book.Service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(String id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUserName(updatedUser.getUserName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setDob(updatedUser.getDob());
            user.setReadingHistory(updatedUser.getReadingHistory());
            return userRepository.save(user);
        }).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }

    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "Delete successfully";
    }
}
