package com.example.mediator;


import com.example.engine.Controller;
import com.example.engine.HexEngine;
import com.example.engine.client.proxy.Connector;
import com.example.engine.client.ClientManager;
import com.example.engine.config.Configuration;
import com.example.engine.enums.Colour;
import com.example.engine.enums.Mode;
import com.example.userInterface.BoardColour;
import com.example.userInterface.HexBoard;
import javafx.scene.paint.Color;

import java.util.concurrent.CompletableFuture;

import static com.example.constants.HexConstants.START_NEW_GAME_METHOD_NAME;

/**
* A mediator between HexBoard and HexEngine (i.e. UI with Core)
* All communications between the two can only pass through this
* */
public class HexManager {

    public static void newGame(){
        Controller.Internal.newGame();
    }

    public static void forward(){
        Controller.Internal.forward();
    }

    public static void backward(){
        Controller.Internal.backward();
    }

    public static void swap(){
        Controller.Internal.swap();
    }

    public static void usersMove(int i, int j){
        Controller.Internal.move(i, j);
    }

    public static void startGame(){
        Configuration.setConfiguration(Mode.PVP, Colour.RED, false);
        HexBoard.showBoardAndScheduleTask(START_NEW_GAME_METHOD_NAME);
    }

    public static void startGameOfBots(String firstColour, boolean swapRule){
        Configuration.setConfiguration(Mode.CVC, Colour.valueOf(firstColour), swapRule);
        HexBoard.showBoardAndScheduleTask(START_NEW_GAME_METHOD_NAME);
    }

    public static void startGameOfBotsAgain(String firstColour, boolean swapRule){
        Configuration.setConfiguration(Mode.CVC, Colour.valueOf(firstColour), swapRule);
        startNewGame();
    }

    public static void startNewGame(){
        HexEngine.newGame();
    }

    public static void setConfig(Mode mode, Colour firstPlay, boolean swapRule, Colour playerColour){
        switch (mode){
            case PVP:
                Configuration.setConfiguration(mode, firstPlay, swapRule);
                break;
            case PVC:
                Configuration.setConfiguration(mode, firstPlay, swapRule, playerColour);
                break;
        }
    }

    public static void postSaveSettings(){
        startNewGame();
    }

    public static void showWinner(String title, Colour colour){
        HexBoard.showWinner(title, BoardColour.valueOf(colour.name()).getWinningMessage());
    }

    public static void serviceTriggeredBoardEvent(Runnable runnable){
        HexBoard.triggerNonJavaFXEvent(runnable);
    }

    public static void showToast(String message){
        HexBoard.showToast(message);
    }

    public static void showSwapButton(){
        HexBoard.showSwapButton();
    }

    public static void hideSwapButton(){
        HexBoard.hideSwapButton();
    }

    public static void disableClick(int i,int j){
        HexBoard.disableClick(i,j);
    }

    public static void enableClick(int i,int j){
        HexBoard.enableClick(i,j);
    }

    public static void highlight(int i, int j, Colour color){
        HexBoard.paint(i, j, BoardColour.valueOf(color.name()).getDark());
    }

    public static void triage(int i, int j, Colour color){
        HexBoard.paint(i, j, BoardColour.valueOf(color.name()).getTriage());
    }

    public static void unhighlight(int i, int j, Colour color){
        HexBoard.paint(i, j, BoardColour.valueOf(color.name()).getLight());
    }

    public static void eraseColour(int i, int j){
        HexBoard.paint(i, j, Color.WHITE);
    }

    public static void registerBot(Connector connector, Colour colour) {
        ClientManager.registerBot(connector, colour);
    }

    public static void launchBoard() {
        CompletableFuture.runAsync(HexBoard::showBoardAndScheduleTask);
    }

    public static void close(){
        HexBoard.close();
    }
}
