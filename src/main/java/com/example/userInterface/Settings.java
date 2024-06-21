package com.example.userInterface;

import com.example.mediator.HexManager;
import com.example.engine.enums.Colour;
import com.example.engine.enums.Mode;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Settings defines control buttons of the game
 * */
public class Settings {

    private static final Stage stage = new Stage();

    private static final String GAME_MODE = "Game Mode";

    private static final String PLAYER_COLOUR = "Player color";

    private static final String FIRST_MOVE = "First Move";

    private static final String RED = "RED";

    private static final String BLUE = "BLUE";

    private static final String PVP = "PVP";

    private static final String PVC = "PVC";

    private static final String SWAP_RULE = "Swap Rule";

    private static final String SAVE = "Save";

    private static Scene scene;

    public static void showDialog(){
        stage.setTitle("Settings");
        if(scene == null){
            scene = buildDialog();
        }
        stage.setScene(scene);
        stage.show();
    }

    private static void disablePVCButtons(RadioButton red, RadioButton blue){
        red.setDisable(true);
        blue.setDisable(true);
    }

    private static void enablePVCButtons(RadioButton red, RadioButton blue){
        red.setDisable(false);
        blue.setDisable(false);
        selectPVCDefaultPlayerColour(red);
    }

    private static void selectPVCDefaultPlayerColour(RadioButton colour) {
        colour.setSelected(true);
    }

    private static Scene buildDialog(){
        Label gameMode=new Label(GAME_MODE);
        Label playerColour=new Label(PLAYER_COLOUR);
        Label firstMove=new Label(FIRST_MOVE);

        ToggleGroup toggleGroup1 = new ToggleGroup();
        RadioButton playerVsPlayer = new RadioButton(PVP);
        RadioButton playerVsComputer = new RadioButton(PVC);
        playerVsPlayer.setToggleGroup(toggleGroup1);
        playerVsComputer.setToggleGroup(toggleGroup1);

        ToggleGroup toggleGroup2 = new ToggleGroup();
        RadioButton red = new RadioButton(RED);
        RadioButton blue = new RadioButton(BLUE);
        red.setId(PLAYER_COLOUR);
        blue.setId(PLAYER_COLOUR);
        red.setToggleGroup(toggleGroup2);
        blue.setToggleGroup(toggleGroup2);

        ToggleGroup toggleGroup3 = new ToggleGroup();
        RadioButton redColor = new RadioButton(RED);
        RadioButton blueColor = new RadioButton(BLUE);
        redColor.setId(FIRST_MOVE);
        blueColor.setId(FIRST_MOVE);
        redColor.setToggleGroup(toggleGroup3);
        blueColor.setToggleGroup(toggleGroup3);

        playerVsPlayer.setOnMouseClicked(e -> {
            if(red.isSelected()){
                red.setSelected(false);
            }
            else if(blue.isSelected()){
                blue.setSelected(false);
            }
            disablePVCButtons(red, blue);
        });
        playerVsComputer.setOnMouseClicked(e -> enablePVCButtons(red, blue));

        CheckBox swapRule = new CheckBox(SWAP_RULE);
        Button save = new Button(SAVE);
        save.setOnMouseClicked(e -> {
            saveSettings();
            HexManager.postSaveSettings();
        });

        // default auto selection
        playerVsPlayer.setSelected(true);
        disablePVCButtons(red, blue);
        redColor.setSelected(true);

        GridPane root = new GridPane();
        Scene scene = new Scene(root, 400, 200);
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(15);
        root.addRow(0, gameMode, playerVsPlayer, playerVsComputer);
        root.addRow(1, playerColour, red, blue);
        root.addRow(2, firstMove, redColor, blueColor);
        root.addRow(3, swapRule);
        root.add(save, 1, 4);
        return scene;
    }

    private static void saveSettings() {

        Scene scene = stage.getScene();
        Parent root = scene.getRoot();
        ObservableList<Node> children = root.getChildrenUnmodifiable();
        Mode mode = Mode.PVP;
        Colour playerColour = Colour.RED;
        Colour firstMove = Colour.RED;
        boolean swapRule = false;
        for(Node node : children){
            if(node instanceof RadioButton){
                RadioButton radioButton = (RadioButton) node;
                if(radioButton.isSelected()){
                    if(radioButton.getText().equals(PVP)){
                        mode = Mode.PVP;
                    }
                    else if(radioButton.getText().equals(PVC)){
                        mode = Mode.PVC;
                    }
                    else if(radioButton.getId().equals(PLAYER_COLOUR)){
                        playerColour = Colour.valueOf(radioButton.getText());
                    }
                    else if(radioButton.getId().equals(FIRST_MOVE)){
                        firstMove = Colour.valueOf(radioButton.getText());
                    }
                }
            }
            if(node instanceof CheckBox){
                CheckBox checkBox = (CheckBox) node;
                if(checkBox.getText().equals(SWAP_RULE)){
                    swapRule = checkBox.isSelected();
                }
            }
        }
        HexManager.setConfig(mode, firstMove, swapRule, playerColour);
        stage.close();
    }
}
