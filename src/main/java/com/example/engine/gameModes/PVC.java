package com.example.engine.gameModes;

import com.example.constants.HexConstants;
import com.example.mediator.HexManager;
import com.example.engine.HexEngine;
import com.example.engine.client.ClientManager;
import com.example.engine.config.Configuration;
import com.example.engine.enums.Colour;
import com.example.engine.enums.Response;
import com.example.engine.utils.EngineUtils;

/**
 * A PVC Game mode implementation
 * */
public final class PVC {

    private static Colour usersColour;

    private static Colour botsColour;

    public static void init() {
        usersColour = Configuration.getUsersColour();
        botsColour = EngineUtils.getOtherColour(usersColour);
        ClientManager.registerHomeBot(botsColour);
        ClientManager.getClient(botsColour).notifyStart(Configuration.getFirstPlay().name());
    }

    public static class P {
        public static void backward() {
            HexManager.showToast(Response.NOT_APPLICABLE.getMessage());
        }

        public static void forward() {
            HexManager.showToast(Response.NOT_APPLICABLE.getMessage());
        }

        public static void newGame() {
            HexManager.showToast(Response.NOT_APPLICABLE.getMessage());
        }

        private static Response preMove(){
            if(!HexEngine.turnCheck(usersColour)){
                HexManager.showToast(Response.NOT_YOUR_TURN.getMessage());
                return Response.NOT_YOUR_TURN;
            }
            return Response.OK;
        }

        public static void move(int i, int j){
            Response response = preMove();
            if(response.isSuccessful()){
                response = HexEngine.playersMove(i,j);
                if(response.isSuccessful()){
                    postMove(response,i,j);
                }
            }
        }

        private static void postMove(Response moveResponse, int i, int j){
            switch (moveResponse){
                case WON:
                    HexManager.showWinner(HexConstants.alertTitle, usersColour);
                    ClientManager.getClient(botsColour).notifyEnd(i,j,usersColour.name());
                    break;
                case OK:
                    ClientManager.getClient(botsColour).notifyMove(i,j);
                    break;
            }
        }

        private static Response preSwap(){
            return preMove();
        }

        public static void swap(){
            Response response = preSwap();
            if(response.isSuccessful()){
                response = HexEngine.applySwapRule();
                if(response.isSuccessful()){
                    postSwap();
                }
            }
        }

        private static void postSwap(){
            ClientManager.getClient(botsColour).notifySwap();
        }
    }

    public static class C {

        private static Response preMove(int i, int j, int clientID) {
            if(EngineUtils.isNotAuthenticClient(clientID)){
                return Response.NOT_AUTHENTIC;
            }
            return HexEngine.allChecks(i,j,ClientManager.getClientColour(clientID));
        }

        public static Response move(int i, int j, int clientID){
            Response response = preMove(i,j,clientID);
            if(response.isSuccessful()){
                response = HexEngine.playersMove(i,j);
                if(response.isSuccessful()){
                    postMove(response,clientID);
                }
            }
            return response;
        }

        private static void postMove(Response moveResponse, int clientID) {
            if (moveResponse == Response.WON) {
                HexManager.serviceTriggeredBoardEvent(() -> HexManager.showWinner(HexConstants.alertTitle, ClientManager.getClientColour(clientID)));
            }
        }

        private static Response preSwap(int clientID) {
            if(EngineUtils.isNotAuthenticClient(clientID)){
                return Response.NOT_AUTHENTIC;
            }
            return HexEngine.turnCheck(ClientManager.getClientColour(clientID)) ? Response.OK : Response.NOT_YOUR_TURN ;
        }

        public static synchronized Response swap(int clientID) {
            Response response = preSwap(clientID);
            if(response.isSuccessful()){
                response = HexEngine.applySwapRule();
                if(response.isSuccessful()){
                    postSwap();
                }
            }
            return response;
        }

        private static void postSwap() {}
    }
}
