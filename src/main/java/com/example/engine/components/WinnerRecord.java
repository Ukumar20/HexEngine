package com.example.engine.components;

import com.example.engine.internal.ConnectedPath;

/**
* WinnerRecord records the winner and the victory path when a winner is declared
* */
public class WinnerRecord {

    private final Player player;

    private final ConnectedPath path;

    public WinnerRecord(Player player, ConnectedPath path) {
        this.player = player;
        this.path = path;
    }

    public Player getPlayer() {
        return player;
    }

    public ConnectedPath getConnectedPath() {
        return path;
    }
}
