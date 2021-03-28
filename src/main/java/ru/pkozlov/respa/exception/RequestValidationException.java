package ru.pkozlov.respa.exception;

import org.springframework.web.server.ServerWebInputException;

public class RequestValidationException extends ServerWebInputException {
    private String[] details;

    public RequestValidationException(String message) {
        super(message);
    }

    public RequestValidationException(String message, String[] details) {
        super(message);
        this.details = details;
    }

    public String[] getDetails() {
        return details;
    }
}
