package com.example.engine.client.proxy;

import com.example.engine.Controller;
import com.example.engine.adapters.EngineAdapter;
import com.example.engine.enums.Response;

/**
* An proxy object for facilitating the communication between Engine and a Bot
* Both HexEngine and Bot use this for communication of game progress
* Every Hex Bot should provide its own implementation of this
* All these methods are called asynchronously by the Engine
* */
public abstract class Connector implements EngineAdapter {

    /**
     * This method is called by the Engine to send the client ID after the registering of this Connector
     * @param clientID - unique clientID
     * */
    public abstract void setClientID(int clientID);

    /**
     * This method is called by the Engine to send the assigned colour after the registering
     * @param colour - RED or BLUE
     * */
    public abstract void setAssignedColour(String colour);

    /**
     * Invoke this method to notify opponent's moves to the BOT
     * Hexagon's indices are passed
     * @param i - indicates the row index (0 - 10) , Top left is (0,0)
     * @param j - indicates the column index (0 - 10) , Bottom right is (10,10)
     * */
    public abstract void notifyMove(int i, int j);

    /**
     * Invoke this method to notify the BOT that opponent has used swap rule.
     * Swap rule swaps by pieces
     * */
    public abstract void notifySwap();

    /**
    * Notifies the Bot about commencement of the game
    * Also passing the colour that should make first move
    * @param firstColour COLOUR1 or COLOUR2 is passed as a String
    * Bot may map these colours to the real world colours like COLOUR1 - RED and COLOUR2 - BLUE
    * */
    public abstract void notifyStart(String firstColour);

    /**
     * Notifies the Bot about end of the game
     * Passing in the last move and winning colour
     * @param i indicates the row index (0 - 10) , Top left is (0,0)
     * @param j indicates the column index (0 - 10) , Bottom right is (10,10)
     * @param winColour indicates the colour who won
     * */
    public abstract void notifyEnd(int i, int j, String winColour);

    /**
     * Invoke this method to make your move by passing the indices of the hexagon
     * @param i - indicates the row index (0 - 10) , Top left is (0,0)
     * @param j - indicates the column index (0 - 10) , Bottom right is (10,10)
     * @return - Response
     * */
    @Override
    public final Response move(int i, int j, int clientID) {
        return Controller.External.move(i,j,clientID);
    }

    /**
     * Invoke this method for swap move. This swaps by pieces
     * @return - Response
     *
     * */
    @Override
    public final Response swap(int clientID) {
        return Controller.External.swap(clientID);
    }
}
