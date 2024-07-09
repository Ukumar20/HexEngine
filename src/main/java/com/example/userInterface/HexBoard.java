package com.example.userInterface;

import com.example.constants.HexConstants;
import com.example.mediator.HexManager;
import com.example.engine.internal.Point;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.constants.HexConstants.*;

/**
* HexBoard defines the UI of the Board Game
* All User activities take place from here
* */
public class HexBoard extends Application {

    private static Stage primaryStage;

    private static Runnable hideSwapButton;

    private static Runnable showSwapButton;

    private static final Polygon[][] matrix = new Polygon[N][N];

    public static void showBoardAndScheduleTask(String... params) {
        launch(params);
    }

    @Override
    public void start(Stage primaryStage) {
        setPrimaryStage(primaryStage);
        drawHexBoard(primaryStage, N, L);
        scheduleLater(getParameters());
    }

    private void setPrimaryStage(Stage primaryStage){
        HexBoard.primaryStage = primaryStage;
    }

    private void scheduleLater(Parameters parameters){
        if(parameters.getRaw().contains(START_NEW_GAME_METHOD_NAME)){
            Platform.runLater(HexManager::startNewGame);
        }
    }

    public static void close(){
        primaryStage.close();
    }

    public static void paint(int i, int j, Color color){
        matrix[i][j].setFill(color);
    }

    public static void disableClick(int i, int j){
        matrix[i][j].setMouseTransparent(true);
    }

    public static void enableClick(int i, int j){
        matrix[i][j].setMouseTransparent(false);
    }

    public static void showWinner(String title, String header){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.show();
    }

    public static void triggerNonJavaFXEvent(Runnable runnable){
        Platform.runLater(runnable);
    }

    public static void showToast(String message){
        Toast.show(message);
    }

    private void drawHexBoard(Stage primaryStage, int size, double side){

        primaryStage.setTitle(HexConstants.boardTitle);
        primaryStage.setMaximized(true);

        Rectangle2D frame = Screen.getPrimary().getVisualBounds();

        double screenWidth = frame.getWidth();
        double screenHeight = frame.getHeight();
        double actualScreenHeight = screenHeight - TASK_BAR_SIZE;
        double marginX = (screenWidth - XLength)/2 , marginY = (actualScreenHeight - YLength)/2;
        double xConst = NOTATION_SIZE + NUMERIC_NOTATION_GAP, yConst = NOTATION_SIZE + ALPHABETIC_NOTATION_GAP;
        primaryStage.setScene(buildScene(size, side, marginX+xConst, marginY+yConst));
        primaryStage.show();
    }

    private Scene buildScene(int boardSize, double sideLength, double startX, double startY){
        List<Polygon> polygons = createStoreAndGetPolygons(startX, startY, boardSize, sideLength);
        Group root = new Group();
        root.getChildren().addAll(polygons);
        root.getChildren().addAll(getBorderLines(startX, startY, boardSize, sideLength));
        root.getChildren().addAll(getImageIcons(startX, startY));
        root.getChildren().addAll(getAlphabeticNotations(startX, startY, boardSize, sideLength));
        root.getChildren().addAll(getNumericNotations(startX, startY, boardSize, sideLength));
        Scene scene = new Scene(root);
        scene.setFill(Color.GREY);
        return scene;
    }

    private List<Label> getAlphabeticNotations(double x, double y, int size, double s){

        x = x + s * sin60;
        y = y - s * cos60;
        List<Label> list = new ArrayList<>();
        int asciiA = 97;
        while(size>0){
            Label label = new Label(Character.toString((char) (asciiA+11-size)));
            label.setLayoutX(x-5);
            label.setLayoutY(y-ALPHABETIC_NOTATION_GAP);
            label.setFont(Font.font("Verdana", FontWeight.BOLD,HexConstants.NOTATION_SIZE));
            list.add(label);
            x += 2 * s * sin60;
            size--;
        }
        return list;
    }

    private List<Label> getNumericNotations(double x, double y, int size, double s){

        y = y + s/2;
        List<Label> list = new ArrayList<>();
        int dx;
        while(size>0){
            int number = 12-size;
            Label label = new Label(String.valueOf(number));
            dx = number<10 ? NUMERIC_NOTATION_GAP : NUMERIC_NOTATION_GAP+10;
            label.setLayoutX(x-dx);
            label.setLayoutY(y);
            label.setFont(Font.font("Verdana", FontWeight.BOLD, NOTATION_SIZE));
            list.add(label);
            x += s * sin60;
            y += s * (1 + cos60);
            size--;
        }
        return list;
    }

    private List<ImageView> getImageIcons(double startX, double startY) {
        ImageView newGame = getNewGameImage(startX, startY);
        ImageView botsPlay = getPlayImage(startX, startY);
        ImageView settings = getSettingsImage(startX, startY);
        ImageView forward = getForwardImage(startX, startY);
        ImageView backward = getBackwardImage(startX, startY);
        ImageView swap = getSwapImage(startX, startY);
        assignSwapFunctions(swap);
        addEventListenersOnIcons(newGame, botsPlay, settings, forward, backward, swap);
        hideSwapButton();
        return Arrays.asList(newGame, botsPlay, settings, forward, backward, swap);
    }

    private ImageView getNewGameImage(double startX, double startY){
        return getImageView(HexConstants.newGameImage, startX+1170, startY+75, 50, 50);
    }

    private ImageView getPlayImage(double startX, double startY){
        return getImageView(HexConstants.playImage, startX+1170, startY+175, 50, 50);
    }

    private ImageView getSettingsImage(double startX, double startY){
        return getImageView(HexConstants.settingsImage, startX+1170, startY+275, 60, 60);
    }

    private ImageView getForwardImage(double startX, double startY){
        return getImageView(HexConstants.forwardImage, startX+1200, startY+375, ARROW_SIZE, ARROW_SIZE);
    }

    private ImageView getBackwardImage(double startX, double startY){
        return getImageView(HexConstants.backwardImage, startX+1110, startY+375, ARROW_SIZE, ARROW_SIZE);
    }

    private ImageView getSwapImage(double startX, double startY){
        return getImageView(HexConstants.swapImage, startX+950, startY+25, 50, 50);
    }

    private ImageView getImageView(String imagePath, double x, double y, int width, int height){
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    private void assignSwapFunctions(ImageView swap) {
        showSwapButton = () -> {
            swap.setMouseTransparent(false);
            swap.setOpacity(1.0);
        };
        hideSwapButton = () -> {
            swap.setMouseTransparent(true);
            swap.setOpacity(0.2);
        };
    }

    public static void showSwapButton(){
        showSwapButton.run();
    }

    public static void hideSwapButton(){
        hideSwapButton.run();
    }

    private void addEventListenersOnIcons(ImageView newGame, ImageView botsPlay, ImageView settings, ImageView forward, ImageView backward, ImageView swap) {
        newGame.setOnMouseClicked(event -> HexManager.newGame());
        botsPlay.setOnMouseClicked(event -> {});
        settings.setOnMouseClicked(event -> showSettings());
        forward.setOnMouseClicked(event -> HexManager.forward());
        backward.setOnMouseClicked(event -> HexManager.backward());
        swap.setOnMouseClicked(event -> HexManager.swap());
        addToolTip(newGame, "New Game");
        addToolTip(botsPlay, "Bot Vs Bot");
        addToolTip(settings, "Settings");
        addToolTip(forward, "Forward");
        addToolTip(backward, "Backward");
        addToolTip(swap, "Swap");
    }

    private void addToolTip(Node node, String text){
        Tooltip toolTip = new Tooltip(text);
        toolTip.setShowDelay(Duration.ZERO);
        Tooltip.install(node, toolTip);
    }

    private void showSettings() {
        Settings.showDialog();
    }

    private List<Line> getBorderLines(double x, double y, int size, double s) {

        List<Line> lines = new ArrayList<>();
        lines.addAll(getLeftLines(x,y,size,s));
        lines.addAll(getRightLines(x,y,size,s));
        lines.addAll(getTopLines(x,y,size,s));
        lines.addAll(getBottomLines(x,y,size,s));
        return lines;
    }

    private List<Line> getLeftLines(double x, double y, int size, double s){
        List<Line> lines = new ArrayList<>();
        double lx=x,ly=y;
        while(size-->0){
            lines.add(drawLine(lx,ly,lx, ly+s, -cos30, sin30, Color.BLUE));
            lines.add(drawLine(lx, ly+s, lx+s*sin60,
                    ly+s+s*cos60, -cos30, sin30, Color.BLUE));
            lx+=s*sin60;
            ly+=s*(1+cos60);
        }
        return lines;
    }

    private List<Line> getRightLines(double x, double y, int size, double s){
        List<Line> lines = new ArrayList<>();
        double rx=x+(2*size-1)*s*sin60,ry=y-s*cos60;
        while(size-->0){
            lines.add(drawLine(rx,ry,rx+s*sin60, ry+s*cos60,
                    cos30, -sin30, Color.BLUE));
            lines.add(drawLine(rx+s*sin60, ry+s*cos60,
                    rx+s*sin60, ry+s+s*cos60, cos30, -sin30, Color.BLUE));
            rx+=s*sin60;
            ry+=s*(1+cos60);
        }
        return lines;
    }

    private List<Line> getTopLines(double x, double y, int size, double s){
        List<Line> lines = new ArrayList<>();
        double ux=x;
        while(size-->0){
            lines.add(drawLine(ux, y,ux+s*sin60, y -s*cos60, 0, -1, Color.RED));
            lines.add(drawLine(ux+s*sin60, y -s*cos60,
                    ux+2*s*sin60, y,0,-1, Color.RED));
            ux+=2*s*sin60;
        }
        return lines;
    }

    private List<Line> getBottomLines(double x, double y, int size, double s){
        List<Line> lines = new ArrayList<>();
        double bx=x+(size-1)*s*sin60,by=y+s+(size-1)*s*(1+cos60);
        while(size-->0){
            lines.add(drawLine(bx,by,bx+s*sin60, by+s*cos60, 0,1, Color.RED));
            lines.add(drawLine(bx+s*sin60, by+s*cos60,
                    bx+2*s*sin60, by, 0,1, Color.RED));
            bx+=2*s*sin60;
        }
        return lines;
    }

    private Line drawLine(double x1, double y1, double x2, double y2, double dx, double dy, Color color){
        double k=4.25;
        double kdx=k*dx;
        double kdy=k*dy;
        Line line = new Line(x1+kdx,y1+kdy,x2+kdx,y2+kdy);
        line.setStrokeType(StrokeType.OUTSIDE);
        line.setStrokeWidth(2);
        line.setStroke(color);
        return line;
    }

    private List<Polygon> createStoreAndGetPolygons(double x, double y, int size, double s) {
        List<Polygon> polygons = new ArrayList<>();
        int row = 0, col;
        while(row < size){
            double dx = x;
            col = 0;
            while(col < size){
                Polygon polygon = buildPolygon(dx, y, s);
                // set location coordinates of this polygon which will be used later
                polygon.setUserData(new Point(row,col));
                matrix[row][col]=polygon;
                polygons.add(polygon);
                dx += 2 * s * sin60;
                col++;
            }
            x+= s * sin60;
            y+= s + s * cos60;
            row++;
        }
        return polygons;
    }

    /**
     * x,y denote first upper coordinate parallel to y axis in a hexagon
     */
    private Polygon buildPolygon(double x, double y, double side){
        Polygon polygon = new Polygon(getHexagonPoints(x, y, side));
        polygon.setFill(Color.WHITE);
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeWidth(1.1);
        addMouseEvent(polygon);
        return polygon;
    }

    private static void addMouseEvent(Polygon polygon){
        polygon.setOnMouseClicked(event -> {
            Point point = (Point) polygon.getUserData();
            HexManager.usersMove(point.getI(), point.getJ());
        });
    }

    /*
         returns all the Hexagon points with x,y as the upper left coordinate parallel to y-axis
     */
    private double[] getHexagonPoints(double x, double y, double s){

        return new double[]{
                /*
                * 1st point   *
                *           *
                *      -> *
                *         *
                *         *
                * */
                x                          ,                              y,
                /*
                * 2nd point      -> *
                *                 *   *
                *               *       *
                * */
                x + s * sin60     ,         y - s * cos60,
                /*
                * 3rd point   *
                *               *
                *                 * <-
                *                 *
                *                 *
                * */
                x + 2 * s * sin60 ,                              y,
                /*
                * 4th point       *
                *                 *
                *                 * <-
                *               *
                *             *
                * */
                x + 2 * s * sin60 ,                          y + s,
                /*
                * 5th point  *          *
                *              *     *
                *                 * <-
                * */
                x + s * sin60     ,     y + s + s * cos60,
                /*
                * 6th point *
                *           *
                *        -> *
                *             *
                *               *
                * */
                x                          ,                          y + s,
        };
    }
}
