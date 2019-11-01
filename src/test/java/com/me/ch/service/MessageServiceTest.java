package com.me.ch.service;

import com.me.ch.configuration.MyTestConfiguration;
import com.me.ch.model.Chat;
import com.me.ch.model.Message;
import com.me.ch.repository.MessageEntity;
import com.me.ch.repository.MessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Import(MyTestConfiguration.class)
@RunWith(SpringRunner.class)
public class MessageServiceTest {
    @MockBean
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService;

    @Test
    public void addMessage() {
        this.messageService.addMessage(new Message("Admin", "User", "Hello There!"));
        verify(messageRepository, times(1)).save(new MessageEntity("Admin", "User", "Hello There!"));
    }

    @Test
    public void getChatsFromAccount() {
        ArrayList<MessageEntity> messageEntities = new ArrayList<>();
        MessageEntity dbMessage1 = new MessageEntity("Admin", "User 2", "Hello There");
        messageEntities.add(dbMessage1);
        messageEntities.add(new MessageEntity("User 2", "Admin", "General Kenobi"));
        messageEntities.add(new MessageEntity("User 3", "Admin", "Hello gamer"));
        when(messageRepository.getAllMessages(anyString())).thenReturn(messageEntities);

        List<Chat> adminsChats = this.messageService.getChatsFromAccount("Admin");
        assertThat(adminsChats, is(not(empty())));
        assertThat(adminsChats.size(), is(2));
        assertThat(adminsChats.get(0).getChatWith(), is(dbMessage1.getToUser()));

        assertThat(adminsChats.get(0).getMessages().get(0).getTo(), is(dbMessage1.getToUser()));
        assertThat(adminsChats.get(0).getMessages().get(0).getFrom(), is(dbMessage1.getFromUser()));

        assertThat(adminsChats.get(0).getMessages().get(1).getFrom(), is(dbMessage1.getToUser()));
        assertThat(adminsChats.get(0).getMessages().get(1).getTo(), is(dbMessage1.getFromUser()));
    }

}
