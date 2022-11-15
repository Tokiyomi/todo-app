package carolina.garma.todoapp;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class TodoErrorDetails {
    private final Date timestamp;
    private final String message;
    private final String details;

    private final HttpStatus status;

    public TodoErrorDetails(Date timestamp, String message, String details, HttpStatus status) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
    public HttpStatus getStatus() {
        return status;
    }
}
