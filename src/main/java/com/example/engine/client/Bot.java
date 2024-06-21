package com.example.engine.client;


import com.example.engine.client.proxy.Connector;
import com.example.engine.enums.Colour;

import java.util.concurrent.CompletableFuture;

/**
 * Bot is a client which plays programmatically
 * HexEngine uses this Bot as a mediator to communicate the game progress to the actual Bot through the connector
* */
public final class Bot extends Client {

    private final Connector connector;

    public Bot(Connector connector, Colour colour) {
        super(colour);
        this.connector = connector;
    }

    public void notifyMove(int i, int j) {
        CompletableFuture.runAsync(() -> connector.notifyMove(i,j));
    }

    public void notifySwap() {
        CompletableFuture.runAsync(connector::notifySwap);
    }

    public void notifyStart(String firstColour){
        CompletableFuture.runAsync(() -> connector.notifyStart(firstColour));
    }

    public void notifyEnd(int i, int j, String winColour){
        CompletableFuture.runAsync(() -> connector.notifyEnd(i,j,winColour));
    }
}
