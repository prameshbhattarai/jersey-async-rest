package com.example.jerseyasyncrest.resources;

import com.example.jerseyasyncrest.dto.AsyncResponse;
import com.example.jerseyasyncrest.service.AsyncService;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AsyncResourceTest extends JerseyTest {

    @BeforeAll
    public static void fixJerseyTestForJUnit5Setup() {
        System.setProperty("jersey.config.test.container.port", "0");
    }

    @Override
    protected Application configure() {
        return new ResourceConfig()
                .registerInstances(
                        new AsyncResource(
                                new AsyncService()
                        )
                );
    }

    @Test
    void pingRequestShouldReturnSuccessResponseWith200Status() {
        var response = target("/api/ping").request().get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        AsyncResponse asyncResponse = response.readEntity(AsyncResponse.class);
        assertEquals("pong", asyncResponse.message());
    }

    @Test
    void asyncRequestShouldWaitAndReturnSuccessResponseWith200Status() {
        var response = target("/api/async-ping").request().get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        AsyncResponse asyncResponse = response.readEntity(AsyncResponse.class);
        assertEquals("Response from async-ping operation !!!", asyncResponse.message());
    }

    @Test
    void streamRequestShouldReturnSuccessResponseWith200Status() {
        var response = target("/api/stream").request().get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("text/csv", response.getHeaderString(HttpHeaders.CONTENT_TYPE));
    }
}
