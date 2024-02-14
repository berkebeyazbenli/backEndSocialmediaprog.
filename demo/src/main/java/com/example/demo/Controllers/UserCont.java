package com.example.demo.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.UserRepo;
import com.example.demo.Services.UserService;
import com.example.demo.entities.User;

@RestController
@RequestMapping("/users")
public class UserCont {
    private UserService userService;

    public UserCont(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User NewUser) {
        return userService.savePerson(NewUser);
    }

    // append//biri çağırdığın tek user döndüreceğiz
    @GetMapping("/{UserId}")
    public User getPerson(@PathVariable Long UserId) {
        // Custom exception
        return userService.getPerson(UserId);

    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
       return userService.updatePerson(userId, newUser);

    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long UserId) {

        userService.deleteById(UserId);
    }
}
