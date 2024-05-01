package com.bilibili.controller;

import com.bilibili.models.Message;
import com.bilibili.models.User;
import com.bilibili.service.MessageService;
import com.bilibili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class CreateMessage {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @PostMapping("/chat/{chatId}")
    public Message createMessage(@RequestBody Message req,
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer chatId) throws Exception {

        User user = userService.findUserByJwt(jwt);
        Message message = messageService.createMessage(user , chatId , req);

        return message;
    }

    @GetMapping("/chat/{chatId}")
    public List<Message> findChatsMessage(
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer chatId) throws Exception {

        User user = userService.findUserByJwt(jwt);
        List<Message> messageList = messageService.findChatsMessages(chatId);

        return messageList;
    }
}
