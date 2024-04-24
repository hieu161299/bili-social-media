package com.bilibili.controller;

import com.bilibili.models.User;
import com.bilibili.repository.UserRepository;
import com.bilibili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return savedUser;
    }

    @PutMapping("/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable Integer userId) throws Exception {
        User updateUser = userService.updateUser(user, userId);
        return updateUser;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new Exception("user not exist with userId " + userId);
        }
        userRepository.delete(user.get());
        return "user deleted successfully with id: " + userId;
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        return user;
    }

    @PutMapping("/follow/{userId1}/{userId2}")
    public User followUserHandle(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
        User user = userService.followUser(userId1, userId2);
        return user;
    }
@GetMapping("/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        List<User> users = userService.searchUser(query);
        return users;
    }
}
