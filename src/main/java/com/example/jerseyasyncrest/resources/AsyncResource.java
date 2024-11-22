package com.example.jerseyasyncrest.resources;

import com.example.jerseyasyncrest.dto.AsyncResponse;
import com.example.jerseyasyncrest.handler.AsyncCompletionHandler;
import com.example.jerseyasyncrest.service.AsyncService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.ContainerRequest;

@Path("/api")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class AsyncResource {
    private static final Logger logger = LogManager.getLogger(AsyncResource.class);
    private final AsyncService asyncService;

    public AsyncResource(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GET()
    @Path("/ping")
    public Response ping() {
        logger.info("ping request received");
        return Response
                .ok(new AsyncResponse("pong"))
                .build();
    }

    @GET
    @Path("/async-ping")
    public void async(@Suspended jakarta.ws.rs.container.AsyncResponse asyncResponse,
                      @Context ContainerRequest request) {
        logger.info("Async request received");
        AsyncCompletionHandler.handle(
                request,
                asyncResponse,
                asyncService.createFutureResponse()
        );
    }

    @GET
    @Path("/stream")
    public void stream(@Suspended jakarta.ws.rs.container.AsyncResponse asyncResponse,
                       @Context ContainerRequest request) {
        logger.info("Stream request received");
        AsyncCompletionHandler.handle(
                request,
                asyncResponse,
                asyncService.createFutureStreamResponse()
        );
    }
}
