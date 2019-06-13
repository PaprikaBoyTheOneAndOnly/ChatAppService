package com.ch.rest;

import com.ch.model.Account;
import com.ch.model.AccountManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/")
public class AccountService {
    private Gson gson;

    private AccountManager manager;

    public AccountService() {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(CHAT_STATUS.class, new ChatStatusTypeDeserializer());
        gb.registerTypeAdapter(CHAT_STATUS.class, new ChatStatusTypeSerializer());
        gson = gb.create();
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
        System.out.println(request);
        Account foundAcc = this.manager.isValidLogin(request);

        if (foundAcc != null) {
            foundAcc.login();
            return Response.status(200).entity(gson.toJson(foundAcc)).build();
        } else
            return Response.status(204).build();
    }

    @POST
    @Path("/createNewAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewAccount(Account newAccount) {
        System.out.println(newAccount);
        Account isValidNewAcc = this.manager.createNewAcc(newAccount);

        if (isValidNewAcc != null)
            return Response.status(200).entity(gson.toJson(isValidNewAcc)).build();
        else
            return Response.status(204).build();
    }

    @POST
    @Path("/createNewAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response accountReceivedMessage(Account account) {
        if (this.manager.accountReceivedMessage(account))
            return Response.status(200).entity(account).build();
        else
            return Response.status(200).entity(false).build();
    }

    @POST
    @Path("/getChatFromAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getChatFromAccount(Account account) {
        Chat chat = new Chat();

        chat.setMessages(Arrays.asList(
                new Message("Hi", CHAT_STATUS.SENT),
                new Message("Hi, how are you?", CHAT_STATUS.RECEIVED),
                new Message("Im fine, hbu?", CHAT_STATUS.SENT),
                new Message("me 2", CHAT_STATUS.RECEIVED),
                new Message("stfu", CHAT_STATUS.RECEIVED)
        ));

        return Response.status(200).entity(gson.toJson(Arrays.asList(chat))).build();
    }

    private class Chat {
        private List<Message> messages;

        Chat() {
            this.messages = new ArrayList<>();
        }

        public List<Message> getMessages() {
            return messages;
        }

        void setMessages(List<Message> messages) {
            this.messages = messages;
        }
    }

    private class Message {
        private String text;
        private CHAT_STATUS status;

        public Message() {
        }

        public Message(String text, CHAT_STATUS status) {
            this.text = text;
            this.status = status;
        }
    }

    private enum CHAT_STATUS {
        RECEIVED(1), SENT(0);

        private int status;

        CHAT_STATUS(int status) {
            this.status = status;
        }

        public static CHAT_STATUS getEnumType(int typeInt) {
            switch (typeInt) {
                case 1:
                    return RECEIVED;
                case 0:
                    return SENT;
                default:
                    return null;
            }
        }

        public int getValue() {
            return this.status;
        }
    }

    private static class ChatStatusTypeDeserializer implements JsonDeserializer<CHAT_STATUS> {
        @Override
        public CHAT_STATUS deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx) throws JsonParseException {
            int typeInt = json.getAsInt();
            return CHAT_STATUS.getEnumType(typeInt);
        }
    }

    private static class ChatStatusTypeSerializer implements JsonSerializer<CHAT_STATUS> {

        @Override
        public JsonElement serialize(CHAT_STATUS chat_status, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(chat_status.getValue());
        }
    }
}
