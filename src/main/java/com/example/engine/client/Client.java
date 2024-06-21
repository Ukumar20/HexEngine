package com.example.engine.client;

import com.example.engine.enums.Colour;

/**
* A Client is who can play the game by interacting with the HexEngine
* API Clients can also be supported
* */
public abstract class Client {

    private final Colour colour;

    public Client(Colour colour) {
        this.colour = colour;
    }

    public Colour getColour() {
        return colour;
    }
}
