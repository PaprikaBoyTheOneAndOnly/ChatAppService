package com.me.ch.service;

import com.me.ch.model.Chat;
import com.me.ch.model.Message;
import com.me.ch.repository.MessageEntity;
import com.me.ch.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.ArrayList;
import java.util.List;

@ApplicationScope
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void addMessage(Message message) throws NullPointerException {
        this.messageRepository.save(new MessageEntity(message.getFrom(), message.getTo(), message.getText()));
    }

    public ArrayList<Chat> getChatsFromAccount(String username) {
        ArrayList<Chat> chats = new ArrayList<>();

        this.messageRepository.getAllMessages(username).forEach(messageEntity -> {
            String chatWith = messageEntity.getFromUser().equals(username) ? messageEntity.getToUser() : messageEntity.getFromUser();
            if (containsChatWith(chats, chatWith)) {
                chats.forEach(chat -> {
                    if (chat.getChatWith().equals(chatWith)) {
                        chat.addMessage(new Message(messageEntity.getFromUser(), messageEntity.getToUser(), messageEntity.getMessage()));
                    }
                });
            } else {
                Chat chat = new Chat(chatWith);
                chat.addMessage(new Message(messageEntity.getFromUser(), messageEntity.getToUser(), messageEntity.getMessage()));
                chats.add(chat);
            }
        });

        return chats;
    }

    private boolean containsChatWith(final List<Chat> chats, final String name) {
        return chats.stream()
                .map(Chat::getChatWith)
                .anyMatch(name::equals);
    }

}
