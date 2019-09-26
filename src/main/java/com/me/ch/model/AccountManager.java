package com.me.ch.model;

import com.me.ch.repository.AccountRepository;
import com.me.ch.repository.DbAccount;
import com.me.ch.repository.DbMessage;
import com.me.ch.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScope
@Service("accountManager")
public class AccountManager {
    @Autowired
    private AccountRepository accountRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageRepository messageRepository;

    public AccountManager() {
    }

    public Account isValidLogin(Account account) {
        DbAccount foundAccount = accountRepository.findValidAccount(account.getUsername(), account.getPassword());

        return foundAccount == null ? null : new Account(foundAccount.getUsername(), foundAccount.getPassword());
    }

    public Account createAccount(Account account) {
        if (!this.isExistingAccount(account.getUsername())) {
            try {
                accountRepository.addAccount(account.getUsername(), account.getPassword());
                return account;
            } catch (SQLException e) {
                logger.error("Duplicated Account \"" + account.getUsername() + "\" tried to be added to Database!");
            }
        }
        return null;
    }

    public void addMessage(Message message) throws NullPointerException {
        this.messageRepository.save(new DbMessage(message.getFrom(), message.getTo(), message.getText()));
    }

    public ArrayList<Chat> getChatsFromAccount(String username) {
        ArrayList<Chat> chats = new ArrayList<>();

        this.messageRepository.getAllMessages(username).forEach(dbMessage -> {
            String chatWith = dbMessage.getFromUser().equals(username) ? dbMessage.getToUser() : dbMessage.getFromUser();

            if (containsChatWith(chats, chatWith)) {
                chats.forEach(chat -> {
                    if (chat.getChatWith().equals(chatWith)) {
                        chat.addMessage(new Message(username, chatWith, dbMessage.getMessage()));
                    }
                });
            } else {
                Chat chat = new Chat(chatWith);
                chat.addMessage(new Message(username, chatWith, dbMessage.getMessage()));
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

    public boolean isExistingAccount(String username) {
        return this.accountRepository.existsById(username);
    }

}
