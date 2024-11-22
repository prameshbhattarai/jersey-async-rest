package com.example.jerseyasyncrest.service;

import com.example.jerseyasyncrest.dto.AsyncResponse;
import com.example.jerseyasyncrest.resources.AsyncResource;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class AsyncService {
    private static final Logger logger = LogManager.getLogger(AsyncService.class);
    private static final int SLEEP = 4 * 1000;

    public CompletableFuture<Response> createFutureStreamResponse() {
        CompletableFuture<Response> completableFuture = new CompletableFuture<>();
        var filename = "large-file.csv";
        var inputStream = getClass().getClassLoader().getResourceAsStream(filename);

        if (inputStream != null) {
            StreamingOutput streamingOutput = outputStream -> {
                // close the input stream after reading the file
                try (InputStream input = inputStream) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                } catch (Exception e) {
                    logger.error("Error while streaming the file ", e);
                }
            };
            completableFuture.completeAsync(() ->
                    Response.ok(streamingOutput)
                            .type("text/csv")
                            .build());
        } else {
            completableFuture.complete(
                    Response.status(Response.Status.NOT_FOUND)
                            .entity("Unable to stream file !!!")
                            .build()
            );
        }
        return completableFuture;
    }

    public CompletableFuture<Response> createFutureResponse() {
        var completableFuture = new CompletableFuture<Response>();
        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(SLEEP);
            logger.info("Async operation completed");
            completableFuture.complete(
                    Response.ok()
                            .entity(new AsyncResponse("Response from async-ping operation !!!"))
                            .build()
            );
            return null;
        });
        return completableFuture;
    }
}
