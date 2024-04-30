package com.bilibili.service;

import com.bilibili.models.Chat;
import com.bilibili.models.User;

import java.util.List;

public interface ChatService {
    public Chat createChat(User sendedUser , User receivedUser);
    public Chat findChatById(Integer chatId) throws Exception;
    public List<Chat> findUsersChat(Integer userId);
}
