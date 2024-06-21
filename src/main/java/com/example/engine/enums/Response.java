package com.example.engine.enums;

/**
* Response stores all possible outcomes of a move in the Game
* It is used by the Engine to communicate back the response of a move through the Controller to the client
* */
public enum Response {

    WON(true, "WON"),
    OK(true, "OK"),
    INVALID(false, "INVALID"),
    NOT_YOUR_TURN(false,"NOT YOUR TURN"),
    NOT_EMPTY(false,"NOT EMPTY"),
    NOT_APPLICABLE(false,"NOT APPLICABLE"),
    NOT_AUTHENTIC(false,"NOT AUTHENTIC");

    private final boolean successful;

    private final String message;

    Response(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getMessage() {
        return message;
    }
}
