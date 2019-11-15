package com.me.ch.service;

import com.me.ch.model.Addressable;
import com.me.ch.model.Chat;
import com.me.ch.model.File;
import com.me.ch.model.Message;
import com.me.ch.repository.FileRepository;
import com.me.ch.repository.MessageEntity;
import com.me.ch.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@ApplicationScope
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private FileRepository fileRepository;

    public void addMessage(Message message) throws NullPointerException {
        this.messageRepository.save(new MessageEntity(
                message.getFrom(),
                message.getTo(),
                message.getText(),
                message.getTimeSent()));
    }

    public ArrayList<Chat> getChatsFromAccount(String username) {
        ArrayList<Chat> chats = new ArrayList<>();

        List<Addressable> addressableList = new ArrayList<>();

        this.messageRepository.getAllMessages(username).forEach(messageEntity ->
            addressableList.add(new Message(
                    messageEntity.getFrom_user(),
                    messageEntity.getTo_user(),
                    messageEntity.getMessage(),
                    messageEntity.getSent_time())));

        this.fileRepository.getAllFiles(username).forEach(fileEntity -> {
            addressableList.add(new File(
                    fileEntity.getFrom_user(),
                    fileEntity.getTo_user(),
                    fileEntity.getFilename(),
                    fileEntity.getOriginal_filename(),
                    fileEntity.getMedia_type(),
                    fileEntity.getSent_time()));
        });
        addressableList.sort(Comparator.comparing(Addressable::getTimeSent));

        addressableList.forEach(addressable -> {
            String chatWith = addressable.getFrom().equals(username) ? addressable.getTo() : addressable.getFrom();
            if (containsChatWith(chats, chatWith)) {
                chats.forEach(chat -> {
                    if (chat.getChatWith().equals(chatWith)) {
                        chat.addAddressable(addressable);
                    }
                });
            } else {
                Chat chat = new Chat(chatWith);
                chat.addAddressable(addressable);
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
