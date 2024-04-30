package com.bilibili.controller;

import com.bilibili.models.Chat;
import com.bilibili.models.User;
import com.bilibili.request.CreateChatRequest;
import com.bilibili.service.ChatService;
import com.bilibili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;

    @PostMapping
    public Chat createChat(@RequestHeader("Authorization") String jwt,
                           @RequestBody CreateChatRequest req) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);
        User receivedUser = userService.findUserById(req.getUserId());

        Chat chat = chatService.createChat(reqUser, receivedUser);
        return chat;
    }

    @GetMapping
    public List<Chat> findUsersChat (@RequestHeader("Authorization") String jwt) {
            User user = userService.findUserByJwt(jwt);
        List<Chat> chatList = chatService.findUsersChat(user.getId());
        return chatList;
    }
}
