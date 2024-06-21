package com.example.base;

import com.example.engine.enums.Response;
import com.example.engine.client.proxy.Connector;

/**
 * A sample test bot used in test cases
 */
public class TestBot extends Connector  {

    private int clientID;

    @Override
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    @Override
    public void setAssignedColour(String colour) {}

    @Override
    public void notifyMove(int i, int j) {}

    @Override
    public void notifySwap() {}

    @Override
    public void notifyStart(String firstColour) {}

    @Override
    public void notifyEnd(int i, int j, String winColour) {}

    public Response move(int i, int j){
        suspend(1000);
        return move(i, j, clientID);
    }

    public Response swap(){
        suspend(1000);
        return swap(clientID);
    }

    private static void suspend(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
