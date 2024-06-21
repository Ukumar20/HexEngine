package com.example.constants;

public class HexConstants {

    public static final int N = 11;

    public static final int L = 40;

    public static final int ARROW_SIZE = 80;

    public static final int ARROW_GAP = 10;

    public static final int ALPHABETIC_NOTATION_GAP = 35;

    public static final int NUMERIC_NOTATION_GAP = 30;

    public static final double NOTATION_SIZE = 17;

    public static final double TASK_BAR_SIZE = 35;

    public static final double sin60 = Math.sin(Math.toRadians(60));

    public static final double cos60 = Math.cos(Math.toRadians(60));

    public static final double sin30 = Math.sin(Math.toRadians(30));

    public static final double cos30 = Math.cos(Math.toRadians(30));

    public static final double XLength = L*sin60*(3*N-1)+2*ARROW_SIZE+ARROW_GAP + NUMERIC_NOTATION_GAP + NOTATION_SIZE;

    public static final double YLength = N*L+L*cos60*(N+1) + ALPHABETIC_NOTATION_GAP + NOTATION_SIZE ;

    public static final String boardTitle = "Hex";

    public static final String alertTitle = "Game Ends";

    public static final String playSoundFilePath = "src/main/resources/sound/play.wav";

    public static final String winSoundFilePath = "src/main/resources/sound/notify.wav";

    public static final String newGameImage = "/images/newGame.png";

    public static final String settingsImage = "/images/settings.png";

    public static final String playImage = "/images/play.png";

    public static final String swapImage = "/images/swap.png";

    public static final String forwardImage = "/images/forward.png";

    public static final String backwardImage = "/images/backward.png";

    public static final String START_NEW_GAME_METHOD_NAME = "startNewGame";
}
