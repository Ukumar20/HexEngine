package com.example.engine.internal;

import java.util.Set;

/**
* This class is used to store the final victory path
* */
public class ConnectedPath {

    private final Set<Hexagon> path;

    public ConnectedPath(Set<Hexagon> path) {
        this.path = path;
    }

    public Set<Hexagon> getPath() {
        return path;
    }
}
