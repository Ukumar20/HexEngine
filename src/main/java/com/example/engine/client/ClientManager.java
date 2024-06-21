package com.example.engine.client;


import com.example.engine.client.proxy.Connector;
import com.example.engine.embedded.HomeConnector;
import com.example.engine.enums.Colour;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
* A Manager that registers and manages all the clients
* */
public class ClientManager {

    private static Map<Integer, Bot> clientIDMap;

    private static Map<Colour, Bot> clientColourMap;

    public static Colour getClientColour(int clientID){
        return clientIDMap.get(clientID).getColour();
    }

    public static Bot getClient(Colour clientColour){
        return clientColourMap.get(clientColour);
    }

    public static boolean isAuthenticClient(int clientID){
        return clientIDMap.containsKey(clientID);
    }

    public static Collection<Bot> getAllBots(){
        return clientIDMap.values();
    }

    public static void registerBot(Connector connector, Colour colour){
        int clientID = generateID();
        Bot bot = new Bot(connector, colour);
        putToClientIDMap(clientID, bot);
        putToClientColourMap(colour, bot);
        connector.setClientID(clientID);
        connector.setAssignedColour(colour.name());
    }

    public static void registerHomeBot(Colour colour){
        registerBot(new HomeConnector(), colour);
    }

    private static void putToClientIDMap(int clientID, Bot client) {
        if(clientIDMap == null){
            clientIDMap = new HashMap<>();
        }
        clientIDMap.put(clientID, client);
    }

    private static void putToClientColourMap(Colour colour, Bot client) {
        if(clientColourMap == null){
            clientColourMap = new HashMap<>();
        }
        clientColourMap.put(colour, client);
    }

    private static int generateID() {
        return (int)(Math.random()*Math.pow(10, 4));
    }
}
