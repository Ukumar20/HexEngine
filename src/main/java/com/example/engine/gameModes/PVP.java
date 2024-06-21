package com.example.engine.gameModes;


import com.example.engine.HexEngine;
import com.example.engine.enums.Response;

/**
* A PVP Game mode implementation
* */
public final class PVP {

    public static void backward() {
        HexEngine.backward();
    }

    public static void forward() {
        Response response = HexEngine.forward();
        postMove(response);
    }

    public static void newGame() {
        HexEngine.newGame();
    }

    public static void move(int i, int j) {
        Response response = HexEngine.playersMove(i,j);
        postMove(response);
    }

    private static void postMove(Response moveResponse){
        if(moveResponse == Response.WON){
            HexEngine.showWinner();
        }
    }

    public static void swap() {
        HexEngine.applySwapRule();
    }
}
