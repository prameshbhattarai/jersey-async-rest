package com.example.jerseyasyncrest.resources;

import com.example.jerseyasyncrest.dto.Data;
import com.example.jerseyasyncrest.handler.AsyncCompletionHandler;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.ContainerRequest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Path("/api")
public class AsyncResource {

    public static final Logger logger = LogManager.getLogger(AsyncCompletionHandler.class);
    private static final int SLEEP = 4 * 1000;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public void async(@Suspended AsyncResponse asyncResponse,
                      @Context ContainerRequest request) {
        logger.info("Async request received");
        AsyncCompletionHandler.handle(
                request,
                asyncResponse,
                createFutureResponse()
        );
    }

    private CompletableFuture<Response> createFutureResponse() {
        var completableFuture = new CompletableFuture<Response>();
        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(SLEEP);
            logger.info("Async operation completed");
            completableFuture.complete(
                    Response.ok()
                            .entity(new Data("Response from async operation !!!"))
                            .build()
            );
            return null;
        });
        return completableFuture;
    }
}
