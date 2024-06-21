package com.example;

import com.example.mediator.HexManager;
import com.example.engine.client.proxy.Connector;
import com.example.engine.enums.Colour;

/**
 * The application main class also the api class
 * @author utkarshkumar
* */
public class App {

    public static void main(String[] args){
        startGame();
    }

    /**
     * Invoke this to start a new game
     */
    public static void startGame() {
        System.out.println("Welcome to the Game of Hex");
        HexManager.startGame();
        System.out.println("Engine shutdown");
    }

    /**
     * Invoke this to start the game of Bots after their registration
     */
    public static void autoPlay(String firstColour, boolean swapRule){
        System.out.println("Autoplay starts");
        HexManager.startGameOfBots(firstColour, swapRule);
    }

    /**
     * Invoke this to start a new game again
     * Do not invoke this directly before calling {@link #launch()}
     * Used by junit tests
     */
    public static void autoPlayAgain(String firstColour, boolean swapRule){
        HexManager.startGameOfBotsAgain(firstColour, swapRule);
    }

    /**
     * Invoke this to register a bot
     * Pass the clientID along in the subsequent requests as an ID
     */
    public static void registerBot(Connector connector, String colour){
        HexManager.registerBot(connector, Colour.valueOf(colour));
    }

    /**
     * Invoke this only to launch Hex board
     */
    public static void launch(){
        HexManager.launchBoard();
    }

    /**
     * Invoke this only to close Hex board
     * Invoke this only after launch
     */
    public static void close(){
        HexManager.close();
    }
}
