package com.example.engine.components;


import com.example.engine.enums.Border;
import com.example.engine.enums.Colour;
import com.example.engine.internal.ConnectedPath;
import com.example.engine.internal.Hexagon;

import java.util.*;

/**
* Player represents a candidate of the game
* */
public class Player {

    private final Set<Hexagon> played = new HashSet<>();

    private final Stack<Hexagon> moves = new Stack<>();

    private final Stack<Hexagon> reverseMoves = new Stack<>();

    private final Colour colour;

    public Player(Colour colour) {
        this.colour = colour;
    }

    public Colour getColour(){
        return this.colour;
    }

    public Border getBorder1(){
        return colour.getBorder1();
    }

    public Border getBorder2(){
        return colour.getBorder2();
    }

    public void move(Hexagon hexagon){
        moves.push(hexagon);
        played.add(hexagon);
    }

    public Hexagon backMove(){
        Hexagon hexagon = null;
        if(!moves.isEmpty()){
            hexagon = moves.pop();
            played.remove(hexagon);
            reverseMoves.push(hexagon);
        }
        return hexagon;
    }

    public Hexagon forwardMove(){
        Hexagon redoMove = null;
        if(!reverseMoves.isEmpty()){
            redoMove = reverseMoves.pop();
            move(redoMove);
        }
        return redoMove;
    }

    public Hexagon getLastMove(){
        if(!moves.isEmpty()){
            return moves.peek();
        }
        return null;
    }

    public void clearForwardMoves(){
        reverseMoves.clear();
    }

    public boolean hasNoForwardMoves(){
        return reverseMoves.isEmpty();
    }

    public ConnectedPath getWinningPathIfPossible(){
        Hexagon hexagon = getLastMove();
        return formsBridge(hexagon);
    }

    // Exhaustive breadth first search
    private ConnectedPath formsBridge(Hexagon hexagon){
        Set<Hexagon> visited = new HashSet<>();
        Map<Hexagon, Hexagon> nodeMap = new HashMap<>();
        Queue<Hexagon> queue = new LinkedList<>();
        queue.add(hexagon);
        visited.add(hexagon);
        nodeMap.put(hexagon, null);
        int count = queue.size();
        Hexagon connectsBorder1 = null;
        Hexagon connectsBorder2 = null;
        boolean initiallyOnBorder = hexagon.isOnBorder(getBorder1()) || hexagon.isOnBorder(getBorder2());
        while(count > 0){
            while(count-- > 0){
                Hexagon hexNode = queue.poll();
                if(hexNode.isOnBorder(getBorder1())){
                    if(connectsBorder1 == null){
                        connectsBorder1 = hexNode;
                    }
                    if(initiallyOnBorder){
                        initiallyOnBorder = false;
                    }
                    else continue;
                }
                else if(hexNode.isOnBorder(getBorder2())){
                    if(connectsBorder2 == null){
                        connectsBorder2 = hexNode;
                    }
                    if(initiallyOnBorder){
                        initiallyOnBorder = false;
                    }
                    else continue;
                }
                for(Hexagon hex : hexNode.getConnections()){
                    if(this.played.contains(hex) && !visited.contains(hex)){
                        queue.add(hex);
                        visited.add(hex);
                        nodeMap.put(hex, hexNode);
                    }
                }
            }
            count = queue.size();
        }
        if(connectsBorder1 != null && connectsBorder2 != null){
            Set<Hexagon> path1 = reTracePath(connectsBorder1, nodeMap);
            Set<Hexagon> path2 = reTracePath(connectsBorder2, nodeMap);
            path1.addAll(path2);
            return new ConnectedPath(path1);
        }
        return null;
    }

    private Set<Hexagon> reTracePath(Hexagon hexagon, Map<Hexagon, Hexagon> nodeMap){
        Set<Hexagon> path = new HashSet<>();
        while (hexagon != null){
            path.add(hexagon);
            hexagon = nodeMap.get(hexagon);
        }
        return path;
    }
}
