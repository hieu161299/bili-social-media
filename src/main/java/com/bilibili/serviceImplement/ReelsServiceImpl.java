package com.bilibili.serviceImplement;

import com.bilibili.models.Reels;
import com.bilibili.models.User;
import com.bilibili.repository.ReelsRepository;
import com.bilibili.service.ReelsService;
import com.bilibili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImpl implements ReelsService {
    @Autowired
    private ReelsRepository reelsRepository;
    @Autowired
    UserService userService;
    @Override
    public Reels createReel(Reels reel, User user) {

        Reels createdReel = new Reels();

        createdReel.setTitle(reel.getTitle());
        createdReel.setUser(user);
        createdReel.setVideo(reel.getVideo());

        return reelsRepository.save(createdReel);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReel(Integer userId) throws Exception{
        userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);
    }
}
