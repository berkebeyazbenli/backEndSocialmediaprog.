package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Repository.UserRepo;
import com.example.demo.entities.User;

@Service
public class UserService {
    UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        super();
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User savePerson(User NewUser) {
        return userRepo.save(NewUser);
    }

    public User getPerson(Long userId) {
        return userRepo.findById(userId).orElse(null);

    }

    public User updatePerson(Long userId, User newUser) {
        Optional<User> user = userRepo.findById(userId);
        if (user != null) {
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setId(newUser.getId());
            userRepo.save(foundUser);
            return foundUser;

        } else
            return null;

    }

    public void deleteById(Long userId) {
        userRepo.deleteById(userId);
    }
}
