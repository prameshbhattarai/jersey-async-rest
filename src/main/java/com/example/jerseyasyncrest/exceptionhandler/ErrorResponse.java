package com.example.jerseyasyncrest.exceptionhandler;

public record ErrorResponse(int status, String reason, String description) {
}
