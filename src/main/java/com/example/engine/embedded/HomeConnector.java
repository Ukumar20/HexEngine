package com.example.engine.embedded;

import com.example.engine.client.proxy.Connector;

/**
* A PVC game mode uses this as an embedded BOT by default
* */
public class HomeConnector extends Connector {

    private int clientID;

    private String colour;

    private int x=0,y=0,dx=0,dy=0;

    @Override
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    @Override
    public void setAssignedColour(String colour) {
        this.colour = colour;
    }

    @Override
    public void notifyMove(int i, int j) {
        suspend();
        System.out.println(String.format("NotifyMove %d %d",i,j));
        move(x,y,clientID);
        x+=dx;y+=dy;
    }

    @Override
    public void notifySwap() {
        System.out.println("NotifySwap");
    }

    @Override
    public void notifyStart(String firstColour) {
        System.out.println(String.format("NotifyStart %s",firstColour));
        if(this.colour.equals("RED")){
            dx=1;
        }
        else{
            dy=1;
        }
        if(this.colour.equals(firstColour)){
            move(x,y,clientID);
            x+=dx;y+=dy;
        }
    }

    @Override
    public void notifyEnd(int i, int j, String winColour) {
        System.out.println(String.format("NotifyEnd %d %d %s",i,j,winColour));
    }

    private void suspend() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
