package com.bilibili.controller;

import com.bilibili.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public List<User> getUser(){
        List<User> users = new ArrayList<>();
        User user = new User(1,"Eden" , "Hazard" , "eden@gmail.com" , "123456");
        users.add(user);
        return users;
    }
    @PostMapping
    public User createUser(@RequestBody User user){
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        return  newUser;
    }
    @PutMapping
    public User updateUser(@RequestBody User user){
        User user1 = new User(1,"Eden" , "Hazard" , "eden@gmail.com" , "123456");
        if(user.getFirstName() != null) {
            user1.setFirstName(user.getFirstName());
        }
        if(user.getLastName() != null) {
            user1.setLastName(user.getLastName());
        }
        if(user.getEmail() != null) {
            user1.setEmail(user.getEmail());
        }
        return  user1;
    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Integer userId){
      return "user deleted successfully with id: " + userId;
    }

}
