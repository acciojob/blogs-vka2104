package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    public User createUser(String username, String password){

        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setFirstName("test");
        user.setLastName("test");

        return userRepository3.save(user);
    }

    public void deleteUser(int userId){
        userRepository3.deleteById(userId);
    }

    public User updateUser(Integer id, String password){
        Optional<User> userOpt = userRepository3.findById(id);
        if(!userOpt.isPresent()) return null;
        User user = userOpt.get();
        user.setPassword(password);
        return userRepository3.save(user);
    }
}
