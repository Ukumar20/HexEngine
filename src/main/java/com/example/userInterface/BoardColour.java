package com.example.userInterface;

import javafx.scene.paint.*;

/**
* The colours used by the Hex Board are maintained here
* */
public enum BoardColour {

    RED(
            Color.RED,
            Color.DARKRED,
            new Color(0.4f, 0.0f, 0.0f, 1),
            "RED Wins!"
    ),
    BLUE(
            Color.BLUE,
            Color.DARKBLUE,
            new Color(0.0f, 0.0f, 0.4f,1),
            "BLUE Wins!"
    );

    private final Color light;

    private final Color dark;

    private final Color triage;

    private final String winningMessage;

    BoardColour(Color light, Color dark, Color triage, String winningMessage) {
        this.light = light;
        this.dark = dark;
        this.triage = triage;
        this.winningMessage = winningMessage;
    }

    public Color getLight() {
        return light;
    }

    public Color getDark() {
        return dark;
    }

    public Color getTriage() {
        return triage;
    }

    public String getWinningMessage() {
        return winningMessage;
    }
}
