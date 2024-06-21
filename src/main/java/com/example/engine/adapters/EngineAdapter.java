package com.example.engine.adapters;

import com.example.engine.enums.Response;

/**
 * An Adapter interface for interacting with the HexEngine
 * A client (i.e. User or Bot) uses this to communicate its moves to the HexEngine
 * There can only be one implementation (i.e. Client) of EngineAdapter
 * */
public interface EngineAdapter {

    /**
     * @param i - the row index (0 - 10) , Top left is (0,0)
     * @param j - the column index (0 - 10) , Bottom right is (10,10)
     * @param clientID - the ID of client making the move
     * @return Response
     * */
    Response move(int i, int j, int clientID);

    /**
     * Invoke this method for swap move. This swaps by pieces
     * @param clientID - the ID of client making the move
     * @return Response
     * */
    Response swap(int clientID);
}
