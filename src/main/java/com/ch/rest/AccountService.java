package com.ch.rest;

import com.ch.model.Account;
import com.ch.model.AccountManager;
import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/")
public class AccountService {
    private Gson gson;
    private AccountManager manager;

    public AccountService() {
        gson = new Gson();
        manager = AccountManager.getInstance();
    }

    @GET
    @Path("/hello")
    public Response hello() {
        return Response.status(200).entity(gson.toJson("Hello from Java")).build();
    }

    @POST
    @Path("/validateAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validateAccount(Account request) {
        Account foundAcc = this.manager.isValidLogin(request);

        if(foundAcc != null) {
            foundAcc.login();
            return Response.status(200).entity(gson.toJson(foundAcc)).build();
        }
        else
            return Response.status(204).build();
    }

    @POST
    @Path("/createNewAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewAccount(Account newAccount) {
        Account isValidNewAcc = this.manager.createNewAcc(newAccount);

        if(isValidNewAcc != null)
            return Response.status(200).entity(gson.toJson(isValidNewAcc)).build();
        else
            return Response.status(204).build();
    }

    @POST
    @Path("/createNewAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response accountReceivedMessage(Account account) {
        if(this.manager.accountReceivedMessage(account))
            return Response.status(200).entity(account).build();
        else
            return Response.status(200).entity(false).build();
    }

    @POST
    @Path("/getChatFromAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getChatFromAccount(Account account) {
        System.out.println("Im here");
        Chat chat = new Chat();
        List<Message> listSender = new ArrayList<>();
        List<Message> listReceiver = new ArrayList<>();

        listSender.add(new Message(LocalDateTime.now(), "Hello"));
        listReceiver.add(new Message(LocalDateTime.now(), "hi, how are you?"));
        listSender.add(new Message(LocalDateTime.now(), "Im fine, hbu?"));
        listReceiver.add(new Message(LocalDateTime.now(), "Me too"));

        chat.setReceiver(new Receiver(listReceiver));
        chat.setSender(new Sender(listSender));
        System.out.println(chat);

        return Response.status(200).entity(gson.toJson(chat)).build();
    }

    class Chat {
        private Receiver receiver;
        private Sender sender;
        Chat() {}

        public Chat(Receiver receiver, Sender sender) {
            this.receiver = receiver;
            this.sender = sender;
        }

        public Receiver getReceiver() {
            return receiver;
        }

        public void setReceiver(Receiver receiver) {
            this.receiver = receiver;
        }

        public Sender getSender() {
            return sender;
        }

        public void setSender(Sender sender) {
            this.sender = sender;
        }





    }
    class Receiver {
        private List<Message> messages;

        Receiver() {

        }
        Receiver(List<Message> messages) {
            this.messages = messages;
        }

        public List<Message> getMessages() {
            return messages;
        }

        public void setMessages(List<Message> messages) {
            this.messages = messages;
        }
    }
    class Sender {

        private List<Message> messages;

        Sender() {

        }
        Sender(List<Message> messages) {
            this.messages = messages;
        }

        public List<Message> getMessages() {
            return messages;
        }

        public void setMessages(List<Message> messages) {
            this.messages = messages;
        }
    }
    class Message {
        private LocalDateTime timestamp;
        private String message;

        Message() {

        }
        Message(LocalDateTime timestamp, String message) {
            this.timestamp = timestamp;
            this.message = message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
