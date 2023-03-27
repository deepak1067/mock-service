package com.knoldus.stocks.response;

import java.util.List;

public class ErrorResponse<T> {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public ErrorResponse() {
    }

    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    //Specific errors in API request processing
    private List<String> details;
}
