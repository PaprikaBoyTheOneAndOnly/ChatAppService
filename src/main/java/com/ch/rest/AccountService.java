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
}
