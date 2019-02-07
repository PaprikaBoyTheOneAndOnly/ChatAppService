package com.ch.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class HelloRestService {
    @GET
    @Path("/hello")
    public Response hello() {
        return Response.status(200).entity("Hello from Java").build();
    }
}
