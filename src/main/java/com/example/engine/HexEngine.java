package com.example.engine;

import com.example.constants.HexConstants;
import com.example.mediator.HexManager;
import com.example.engine.components.Player;
import com.example.engine.components.WinnerRecord;
import com.example.engine.config.Configuration;
import com.example.engine.enums.Colour;
import com.example.engine.enums.Response;
import com.example.engine.internal.ConnectedPath;
import com.example.engine.internal.HexGrid;
import com.example.engine.internal.Hexagon;
import com.example.engine.sound.SoundEffects;

import java.util.HashSet;
import java.util.Set;

import static com.example.constants.HexConstants.N;

/**
* HexEngine is the place where core logic of the game resides
* All functional behaviour and rules of the game are defined here
 * It also maintains the Game state
* */
public class HexEngine {

    private static final HexGrid hexGrid = new HexGrid(N);
    private static final Player red = new Player(Colour.RED);
    private static final Player blue = new Player(Colour.BLUE);
    private static final Set<Hexagon> unPlayed = new HashSet<>();
    private static Player turn;
    private static int movesCompleted;
    private static boolean isSwapButtonDisplayed;
    private static boolean isSwapRuleUsed;
    private static WinnerRecord winnerRecord;

    static{
        initCollections();
    }

    private static void initCollections(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                unPlayed.add(hexGrid.getHexagon(i,j));
            }
        }
    }

    private static void initGameAsPerConfig() {
        Controller.init();
    }

    private static Player getFirstPlayer() {
        switch (Configuration.getFirstPlay()){
            case RED:
                return red;
            case BLUE:
                return blue;
        }
        return red;
    }

    private static void initVariables(){
        movesCompleted=0;
        isSwapButtonDisplayed=false;
        isSwapRuleUsed=false;
        turn = getFirstPlayer();
    }

    public static void newGame(){
        resetGameState();
        initGameAsPerConfig();
    }

    private static void resetGameState(){
        if(isGameOver()){
            resetUnplayedPolygons();
            resetWinner();
        }
        resetBoardState();
        resetBothPlayers();
        initVariables();
    }

    private static void resetBoardState() {
        if(isSwapButtonDisplayed){
            HexManager.hideSwapButton();
        }
    }

    private static void resetUnplayedPolygons(){
        unPlayed.forEach(hexagon -> HexManager.enableClick(hexagon.getI(), hexagon.getJ()));
    }

    private static void resetBothPlayers(){
        unPlayed.addAll(resetPlayer(red));
        unPlayed.addAll(resetPlayer(blue));
    }

    private static Set<Hexagon> resetPlayer(Player player){
        Set<Hexagon> reversed = new HashSet<>();
        Hexagon hexagon = player.backMove();
        while(hexagon != null){
            reversed.add(hexagon);
            HexManager.eraseColour(hexagon.getI(), hexagon.getJ());
            HexManager.enableClick(hexagon.getI(), hexagon.getJ());
            hexagon = player.backMove();
        }
        player.clearForwardMoves();
        return reversed;
    }

    public static boolean turnCheck(Colour colour){
        return colour == turn.getColour();
    }

    public static Response allChecks(int i, int j, Colour colour){
        if(isGameOver()){
            return Response.NOT_APPLICABLE;
        }
        else if(!turnCheck(colour)){
            return Response.NOT_YOUR_TURN;
        }
        else if(!isValid(i,j)){
            return Response.INVALID;
        }
        else if(!unPlayed.contains(hexGrid.getHexagon(i,j))){
            return Response.NOT_EMPTY;
        }
        return Response.OK;
    }

    public static Response playersMove(int i, int j){
        Player otherPlayer = switchPlayer();
        Hexagon hexagon = hexGrid.getHexagon(i,j);
        turn.clearForwardMoves();
        otherPlayer.clearForwardMoves();
        turn.move(hexagon);
        unPlayed.remove(hexagon);
        highlightAndDisableClick(i,j);
        unHighlightLastMove(otherPlayer);
        playMoveSound();
        movesCompleted++;
        boolean wins = declareWinnerIfGameEnds();
        turn = otherPlayer;
        postMove();
        return wins ? Response.WON : Response.OK;
    }

    private static boolean isValid(int i, int j) {
        return i>=0 && i<N && j>=0 && j<N;
    }

    private static Hexagon undo(){
        if(isGameOver()){
            resetUnplayedPolygons();
            unhighlightWinPath();
            resetWinner();
        }
        Player otherPlayer = switchPlayer();
        Hexagon hexagon = otherPlayer.backMove();
        if(hexagon != null) {
            unPlayed.add(hexagon);
            eraseAndEnableClick(hexagon.getI(), hexagon.getJ());
            highlightLastMove(turn);
            playMoveSound();
            movesCompleted--;
            turn = otherPlayer;
            postMove();
        }
        return hexagon;
    }

    public static void backward(){
        if(movesCompleted == 1 && isSwapRuleUsed && Configuration.getSwapRule()){
            swapPieces(false);
        }
        else undo();
    }

    public static Response forward(){
        boolean wins = false;
        Hexagon hexagon = turn.forwardMove();
        if(hexagon != null){
            Player otherPlayer = switchPlayer();
            unPlayed.remove(hexagon);
            highlightAndDisableClick(hexagon.getI(), hexagon.getJ());
            unHighlightLastMove(otherPlayer);
            playMoveSound();
            movesCompleted++;
            if(turn.hasNoForwardMoves()){
                wins = declareWinnerIfGameEnds();
            }
            turn = otherPlayer;
            postMove();
        }
        return wins ? Response.WON : Response.OK;
    }

    private static void postMove() {
        toggleSwapButtonIfApplicable();
    }

    private static void toggleSwapButtonIfApplicable(){
        if(Configuration.getSwapRule()){
            if(movesCompleted == 1 && !isSwapButtonDisplayed && !isSwapRuleUsed){
                HexManager.showSwapButton();
                isSwapButtonDisplayed=true;
            }
            else if(isSwapButtonDisplayed){
                HexManager.hideSwapButton();
                isSwapButtonDisplayed=false;
            }
        }
    }

    private static void playMoveSound(){
        SoundEffects.play(HexConstants.playSoundFilePath);
    }

    private static void playWinSound(){
        SoundEffects.play(HexConstants.winSoundFilePath);
    }

    private static void highlightAndDisableClick(int i, int j){
        HexManager.highlight(i, j, turn.getColour());
        HexManager.disableClick(i, j);
    }

    private static void eraseAndEnableClick(int i, int j){
        HexManager.eraseColour(i, j);
        HexManager.enableClick(i, j);
    }

    private static void highlightLastMove(Player player){
        Hexagon lastHex = player.getLastMove();
        if(lastHex != null){
            HexManager.highlight(lastHex.getI(), lastHex.getJ(), player.getColour());
        }
    }

    private static void unHighlightLastMove(Player player){
        Hexagon lastHex = player.getLastMove();
        if(lastHex != null){
            HexManager.unhighlight(lastHex.getI(), lastHex.getJ(), player.getColour());
        }
    }

    private static void unhighlightWinPath(){
        Set<Hexagon> path = winnerRecord.getConnectedPath().getPath();
        Colour color = winnerRecord.getPlayer().getColour();
        path.forEach(hexagon -> HexManager.unhighlight(hexagon.getI(), hexagon.getJ(), color));
    }

    private static boolean isGameOver(){
        return winnerRecord != null;
    }

    private static boolean declareWinnerIfGameEnds(){
        ConnectedPath path = turn.getWinningPathIfPossible();
        if(path!=null){
            path.getPath().forEach(hexagon -> HexManager.triage(hexagon.getI(), hexagon.getJ(), turn.getColour()));
            unPlayed.forEach(hexagon -> HexManager.disableClick(hexagon.getI(), hexagon.getJ()));
            winnerRecord = new WinnerRecord(turn, path);
            playWinSound();
            return true;
        }
        return false;
    }

    private static Player switchPlayer(){
        return turn == red ? blue : red;
    }

    private static void resetWinner(){
        winnerRecord = null;
    }

    public static Response applySwapRule(){
        if(isSwapButtonDisplayed){
            swapPieces(true);
            return Response.OK;
        }
        return Response.NOT_APPLICABLE;
    }

    private static void swapPieces(boolean swapRuleUsed){
        Hexagon lastHex = undo();
        isSwapRuleUsed = swapRuleUsed;
        turn = switchPlayer();
        playersMove(lastHex.getJ(), lastHex.getI());
    }

    public static void showWinner() {
        HexManager.showWinner(HexConstants.alertTitle, winnerRecord.getPlayer().getColour());
    }
}
