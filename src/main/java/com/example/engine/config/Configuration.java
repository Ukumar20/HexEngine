package com.example.engine.config;

import com.example.engine.enums.Colour;
import com.example.engine.enums.Mode;

/**
* Configuration class stores all the configuration details necessary for the Game
* */
public class Configuration {

    private static Mode mode;

    private static Colour firstPlay;

    private static boolean swapRule;

    private static Colour usersColour;

    public static Mode getMode() {
        return mode;
    }

    public static Colour getFirstPlay() {
        return firstPlay;
    }

    public static boolean getSwapRule() {
        return swapRule;
    }

    public static Colour getUsersColour() {
        return usersColour;
    }

    public static void setConfiguration(Mode mode, Colour firstPlay, boolean swapRule){
        Configuration.mode = mode;
        Configuration.firstPlay = firstPlay;
        Configuration.swapRule = swapRule;
    }

    public static void setConfiguration(Mode mode, Colour firstPlay, boolean swapRule, Colour usersColour){
        setConfiguration(mode, firstPlay, swapRule);
        Configuration.usersColour = usersColour;
    }
}
