package com.example.engine.internal;

import com.example.engine.enums.Border;

import java.util.LinkedList;
import java.util.List;

/**
* Hexagon is a class representing coordinates of a Hexagon
* */
public class Hexagon {

    private final int i;
    private final int j;

    private final List<Hexagon> connections;

    public Hexagon(int i, int j) {
        this.i = i;
        this.j = j;
        connections = new LinkedList<>();
    }

    public void addConnection(Hexagon hexagon){
        connections.add(hexagon);
    }

    public List<Hexagon> getConnections(){
        return this.connections;
    }

    public boolean isOnBorder(Border border){
        return this.i == border.getI() || this.j == border.getJ();
    }

    public int getI(){
        return this.i;
    }

    public int getJ(){
        return this.j;
    }
}
