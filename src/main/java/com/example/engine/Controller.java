package com.example.engine;


import com.example.engine.config.Configuration;
import com.example.engine.enums.Response;
import com.example.engine.gameModes.CVC;
import com.example.engine.gameModes.PVC;
import com.example.engine.gameModes.PVP;

/**
 * Controller is like a gateway which directs the calls from the client to the HexEngine
 * Also it facilitates to plugin new enhancement features like Game Modes without impacting HexEngine
 * */
public class Controller {

    public static void init(){
        switch (Configuration.getMode()){
            case PVC:
                PVC.init();
                break;
            case CVC:
                CVC.init();
                break;
        }
    }

    public static class Internal{

        public static void backward(){
            switch (Configuration.getMode()){
                case PVP:
                    PVP.backward();
                    break;
                case PVC:
                    PVC.P.backward();
                    break;
            }
        }

        public static void forward(){
            switch (Configuration.getMode()){
                case PVP:
                    PVP.forward();
                    break;
                case PVC:
                    PVC.P.forward();
                    break;
            }
        }

        public static void newGame(){
            switch (Configuration.getMode()){
                case PVP:
                    PVP.newGame();
                    break;
                case PVC:
                    PVC.P.newGame();
                    break;
            }
        }

        public static void move(int i,int j){
            switch (Configuration.getMode()){
                case PVP:
                    PVP.move(i,j);
                    break;
                case PVC:
                    PVC.P.move(i,j);
                    break;
            }
        }

        public static void swap(){
            switch (Configuration.getMode()){
                case PVP:
                    PVP.swap();
                    break;
                case PVC:
                    PVC.P.swap();
                    break;
            }
        }
    }

    public static class External{
        public static Response move(int i, int j, int clientID){
            switch (Configuration.getMode()){
                case CVC:
                    return CVC.move(i,j,clientID);
                case PVC:
                    return PVC.C.move(i,j,clientID);
                default:
                    return Response.NOT_APPLICABLE;
            }
        }

        public static Response swap(int clientID){
            switch (Configuration.getMode()){
                case CVC:
                    return CVC.swap(clientID);
                case PVC:
                    return PVC.C.swap(clientID);
                default:
                    return Response.NOT_APPLICABLE;
            }
        }
    }
}
