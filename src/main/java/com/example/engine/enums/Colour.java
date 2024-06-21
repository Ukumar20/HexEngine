package com.example.engine.enums;

/**
* Colours used in the game
* */
public enum Colour {

    RED(Border.Top, Border.Bottom),
    BLUE(Border.Left, Border.Right);

    private final Border border1;

    private final Border border2;

    Colour(Border border1, Border border2){
        this.border1 = border1;
        this.border2 = border2;
    }

    public Border getBorder1() {
        return border1;
    }

    public Border getBorder2() {
        return border2;
    }
}
