package com.example.jerseyasyncrest.resources;

import com.example.jerseyasyncrest.dto.Data;
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
        return new ResourceConfig(AsyncResource.class);
    }

    @Test
    void pingRequestShouldReturnSuccessResponseWith200Status() {
        var response = target("/api/ping").request().get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        Data data = response.readEntity(Data.class);
        assertEquals("pong", data.message());
    }

    @Test
    void asyncRequestShouldWaitAndReturnSuccessResponseWith200Status() {
        var response = target("/api").request().get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        Data data = response.readEntity(Data.class);
        assertEquals("Response from async operation !!!", data.message());
    }
}
