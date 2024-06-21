package com.example.engine.utils;

import com.example.engine.client.ClientManager;
import com.example.engine.enums.Colour;

public class EngineUtils {

    public static boolean isNotAuthenticClient(int clientID){
        return !ClientManager.isAuthenticClient(clientID);
    }

    public static Colour getOtherColour(Colour colour){
        return colour == Colour.RED ? Colour.BLUE : Colour.RED;
    }
}
