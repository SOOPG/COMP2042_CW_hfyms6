package game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Scanner;

public class Main extends Application {
    static final int WIDTH = 900;
    static final int HEIGHT = 900;
    public Group gameRoot = new Group();
    public Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));
    private static Scanner input= new Scanner(System.in);

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Create an instance of Main Menu
        MainMenu gameMainMenu=new MainMenu();
        gameMainMenu.getInstance().displayMainMenu(primaryStage);

        //Set Title of Stage as '2048'
        primaryStage.setTitle("2048");
        //Starts up application once 'Run' button is clicked
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
