package com.example.jerseyasyncrest.exceptionhandler;

public class ErrorResponse {
    private int status;
    private String reason;
    private String description;

    public ErrorResponse() {
    }

    public ErrorResponse(int status, String reason, String description) {
        this.status = status;
        this.reason = reason;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public String getDescription() {
        return description;
    }
}
