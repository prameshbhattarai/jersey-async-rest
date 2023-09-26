package com.example.jerseyasyncrest;

import com.example.jerseyasyncrest.handler.CORSFilter;
import jakarta.ws.rs.core.UriBuilder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.io.IOException;

public class JerseyAsyncApplication {

    public static final String URL = "http://localhost:8080";

    public static void main(String... args) throws IOException, InterruptedException {

        var app = new ApplicationConfig()
                .name("Jersey Async Application")
                .enableJackson()
                .registerDefaultMappers()
                .build();

        // enable CORS request
        app.register(CORSFilter.class);

        // scan resources packages
        app.packages("com.example.jerseyasyncrest.resources");

        var server = GrizzlyHttpServerFactory.createHttpServer(UriBuilder.fromUri(URL).build(), app, false);
        server.getHttpHandler().setAllowEncodedSlash(true);
        server.start();

        Runtime.getRuntime().addShutdownHook(
                new Thread(
                        server::shutdownNow,
                        "serverShutdown"
                )
        );

        Thread.currentThread().join();
    }

}