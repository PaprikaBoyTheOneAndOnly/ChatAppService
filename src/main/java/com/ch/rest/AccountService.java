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
    public Response validateAccount(User user) {
        Account foundAcc = this.manager.isValidName(user.getUsername());

        if(foundAcc != null)
            return Response.status(200).entity(gson.toJson(foundAcc)).build();
        else
            return Response.status(204).build();
    }
}
class User {
    private String username;
    private String password;

    public User() {
        username = "";
        password =" ";
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

