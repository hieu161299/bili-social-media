package com.bilibili.service;

import com.bilibili.models.User;

import java.util.List;

public interface UserService {
    public User registerUser(User user);

    public User findUserById(Integer userId) throws Exception;

    public User findUserByEmail(String email);

    public User followUser(Integer userId1, Integer userId2) throws Exception;  //  follow

    public User updateUser(User user , Integer userId) throws Exception;

    public List<User> searchUser(String query);
    public User findUserByJwt(String jwt);
}
