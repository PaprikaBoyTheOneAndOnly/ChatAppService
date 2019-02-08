package com.ch.rest;


import com.ch.model.AccountManager;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
}

