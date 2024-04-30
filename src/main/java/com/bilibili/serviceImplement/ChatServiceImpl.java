package com.bilibili.serviceImplement;

import com.bilibili.models.Chat;
import com.bilibili.models.User;
import com.bilibili.repository.ChatRepository;
import com.bilibili.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(User reqUser, User user2) {

        Chat isExist = chatRepository.findChatByUsersId(user2 , reqUser);
        if(isExist != null) {
            return isExist;
        };

        Chat newChat = new Chat();
        newChat.getUsers().add(user2);
        newChat.getUsers().add(reqUser);
        newChat.setTimestamp(LocalDateTime.now());

        return  chatRepository.save(newChat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {
        Optional<Chat> opt = chatRepository.findById(chatId);
        if(opt.isEmpty()){
            throw new Exception("chat not found with id - " + chatId);
        }
        return opt.get();
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {
        return chatRepository.findByUsersId(userId);
    }
}
