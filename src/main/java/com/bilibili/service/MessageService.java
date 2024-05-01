package com.bilibili.service;

import com.bilibili.models.Chat;
import com.bilibili.models.Message;
import com.bilibili.models.User;

import java.util.List;

public interface MessageService {
    public Message createMessage(User user , Integer chatId , Message message) throws Exception;
    public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
