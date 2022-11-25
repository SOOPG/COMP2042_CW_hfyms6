package game;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Optional;
import java.util.Scanner;

public class MainMenu extends Main {

   public void displayMainMenu(Stage primaryStage, Scene gameScene, Group root) {

       Group menuRoot = new Group();
       setGameRoot(menuRoot);
       Scene menuScene = new Scene(menuRoot, WIDTH, HEIGHT,Color.rgb(155,252,234));
       setGameScene(menuScene);
       Rectangle backgroundOfMenu = new Rectangle(240, 120, Color.rgb(0, 120, 120, 0.2));
       backgroundOfMenu.setX(WIDTH / 2 - 120);
       backgroundOfMenu.setY(180);
       menuRoot.getChildren().add(backgroundOfMenu);

       Text gameTitle = new Text("2048");
       gameTitle.setFill(Color.BLACK);
       gameTitle.setFont(Font.font("Nirmala UI", FontWeight.BOLD,80));
       gameTitle.relocate(360,185);
       menuRoot.getChildren().add(gameTitle);

       Button playButton = new Button("Play");
       playButton.setTextFill(Color.BLACK);
       playButton.setPrefSize(100,30);
       playButton.relocate(395,450);
       menuRoot.getChildren().add(playButton);

       playButton.setOnMouseClicked(event->{
           menuRoot.getChildren().clear();
           primaryStage.setScene(gameScene);
       });
/*
       Button leaderboardButton = new Button("Leaderboard");
       leaderboardButton.setTextFill(Color.BLACK);
       leaderboardButton.setPrefSize(100,30);
       leaderboardButton.relocate(390,800);
       menuRoot.getChildren().add(leaderboardButton);
       leaderboardButton.setOnMouseClicked(event->{

            //primaryStage.setScene();
       });

       Button optionButton = new Button("Option");
       optionButton.setTextFill(Color.BLACK);
       optionButton.setPrefSize(100,30);
       optionButton.relocate(450,800);
       MenuRoot.getChildren().add(optionButton);
       optionButton.setOnMouseClicked(event->{

           //primaryStage.setScene();
       });

       Button exitButton = new Button("Exit");
       exitButton.setTextFill(Color.BLACK);
       exitButton.setPrefSize(100,30);
       exitButton.relocate(550,800);
       exitButton.setOnMouseClicked(event->{
           Alert alertQuitConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
           alertQuitConfirmation.setTitle("Quit Application");
           alertQuitConfirmation.setHeaderText("Confirm Quit");
           alertQuitConfirmation.setContentText("Are you sure you want to quit this game?");

           Optional<ButtonType> result = alertQuitConfirmation.showAndWait();
           if (result.get() == ButtonType.OK) {
               Platform.exit();
           }
       });
    */
       primaryStage.setScene(menuScene);
    }
}