package com.example.engine.internal;

/**
* This stores all the hexagons in a 2D matrix
* */
public class HexGrid {

    private final Hexagon[][] hexMatrix;

    private final int size;

    public HexGrid(int size) {
        this.size = size;
        hexMatrix = new Hexagon[size][size];
        buildHexConnections();
    }

    private void buildHexConnections(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                hexMatrix[i][j]=new Hexagon(i,j);
                // left
                connect(i,j,i,j-1);
                // upper left
                connect(i,j,i-1,j);
                // upper right
                connect(i,j,i-1,j+1);
            }
        }
    }

    private void connect(int i1, int j1, int i2, int j2){
        if(outOfBounds(i1) || outOfBounds(j1) || outOfBounds(i2) || outOfBounds(j2)){
            return;
        }
        hexMatrix[i1][j1].addConnection(hexMatrix[i2][j2]);
        hexMatrix[i2][j2].addConnection(hexMatrix[i1][j1]);
    }

    private boolean outOfBounds(int index){
        return index<0 || index>=size;
    }

    public Hexagon getHexagon(int i,int j){
        return hexMatrix[i][j];
    }
}
