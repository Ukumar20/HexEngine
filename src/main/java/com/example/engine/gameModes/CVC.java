package com.example.engine.gameModes;


import com.example.engine.HexEngine;
import com.example.engine.client.ClientManager;
import com.example.engine.client.Bot;
import com.example.engine.config.Configuration;
import com.example.engine.enums.Colour;
import com.example.engine.enums.Response;
import com.example.engine.utils.EngineUtils;

/**
 * A CVC Game mode implementation
 * */
public final class CVC {

    public static void init() {
        ClientManager.getAllBots().forEach(bot -> bot.notifyStart(Configuration.getFirstPlay().name()));
    }

    private static Response preMove(int i, int j, int clientID) {
        if(EngineUtils.isNotAuthenticClient(clientID)){
            return Response.NOT_AUTHENTIC;
        }
        return HexEngine.allChecks(i,j,ClientManager.getClientColour(clientID));
    }

    public synchronized static Response move(int i, int j, int clientID) {
        Response response = preMove(i,j,clientID);
        if(response.isSuccessful()){
            response = HexEngine.playersMove(i,j);
            if(response.isSuccessful()){
                postMove(response,i,j,clientID);
                return response;
            }
        }
        return response;
    }

    private static void postMove(Response moveResponse, int i, int j, int clientID) {
        Colour current = ClientManager.getClientColour(clientID);
        Colour other = EngineUtils.getOtherColour(current);
        Bot next = ClientManager.getClient(other);
        switch (moveResponse){
            case WON:
                next.notifyEnd(i, j, current.name());
                break;
            case OK:
                next.notifyMove(i,j);
                break;
        }
    }

    private static Response preSwap(int clientID) {
        if(EngineUtils.isNotAuthenticClient(clientID)){
            return Response.NOT_AUTHENTIC;
        }
        return HexEngine.turnCheck(ClientManager.getClientColour(clientID)) ? Response.OK : Response.NOT_YOUR_TURN ;
    }

    public synchronized static Response swap(int clientID) {
        Response response = preSwap(clientID);
        if(response.isSuccessful()){
            response = HexEngine.applySwapRule();
            if(response.isSuccessful()){
                postSwap(clientID);
                return response;
            }
        }
        return response;
    }

    private static void postSwap(int clientID) {
        Colour current = ClientManager.getClientColour(clientID);
        Colour other = EngineUtils.getOtherColour(current);
        Bot next = ClientManager.getClient(other);
        next.notifySwap();
    }
}
