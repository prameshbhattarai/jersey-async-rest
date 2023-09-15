package com.example.jerseyasyncrest.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
public class AsyncResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response ping() {
        return Response.ok().entity(new Data("Service health check")).build();
    }

    record Data(String name) {}
}
