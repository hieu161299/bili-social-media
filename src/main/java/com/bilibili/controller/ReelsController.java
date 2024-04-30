package com.bilibili.controller;

import com.bilibili.models.Reels;
import com.bilibili.models.User;
import com.bilibili.service.ReelsService;
import com.bilibili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reels")
public class ReelsController {
    @Autowired
    private ReelsService reelsService;
    @Autowired
    private UserService userService;

    @PostMapping
    public Reels createReels(@RequestBody Reels reel,
                             @RequestHeader("Authorization") String jwt) {

        User reqUser = userService.findUserByJwt(jwt);
        Reels createdReel = reelsService.createReel(reel, reqUser);

        return createdReel;
    }
    @GetMapping()
    public List<Reels> findAllReels() {
        List<Reels> reelsList = reelsService.findAllReels();
        return reelsList;
    }

    @GetMapping("/user/{userId}")
    public List<Reels> findUsersReels(@PathVariable Integer userId) throws Exception{
        List<Reels> reelsList = reelsService.findUsersReel(userId);
        return  reelsList;
    }
}
