package com.bilibili.service;

import com.bilibili.models.Reels;
import com.bilibili.models.User;

import java.util.List;
public interface ReelsService {
    public Reels createReel(Reels reel , User user);
    public List<Reels> findAllReels();
    public List<Reels> findUsersReel(Integer userId) throws Exception;

}
