package com.example.engine.enums;


import static com.example.constants.HexConstants.N;

/**
* Border stores the coordinates of the 4 edges of the board
* */
public enum Border {
    Left(-1,0),
    Right(-1, N-1),
    Top(0,-1),
    Bottom(N-1,-1);

    private final int i;
    private final int j;

    Border(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
