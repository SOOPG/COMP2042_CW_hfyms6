package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DifficultySelectorMenu extends MainMenu {

    //Makes it so DifficultySelectorMenu scene has only 1 instance and initially is null
    public static DifficultySelectorMenu singleInstance = null;

    //Construct DifficultySelectorMenu scene if there is no instance of it
    public DifficultySelectorMenu(){

    }

    public static DifficultySelectorMenu getInstance(){
        if(singleInstance == null)
            singleInstance= new DifficultySelectorMenu();
        return singleInstance;
    }

    public void displaySelectorMenu(Stage primaryStage, Scene gameScene, Group root){

        Group difficultySelectorMenuRoot = new Group();
        setGameRoot(difficultySelectorMenuRoot);
        Scene difficultySelectorMenuScene = new Scene(difficultySelectorMenuRoot, WIDTH, HEIGHT,Color.rgb(155,252,234));
        setGameScene(difficultySelectorMenuScene);
        Rectangle backgroundOfDifficultySelectorMenu = new Rectangle(240, 120, Color.rgb(0, 120, 120, 0.2));
        backgroundOfDifficultySelectorMenu.setX(WIDTH / 2 - 120);
        backgroundOfDifficultySelectorMenu.setY(180);

        Text selectDifficultyText = new Text("Select Difficulty:");
        selectDifficultyText.setFill(Color.BLACK);
        selectDifficultyText.setFont(Font.font("Nirmala UI", FontWeight.BOLD,40));
        selectDifficultyText.relocate(315,185);
        difficultySelectorMenuRoot.getChildren().add(selectDifficultyText);
        primaryStage.setScene(difficultySelectorMenuScene);

        Button selectEasyDifficultyButton = new Button("Easy");
        selectEasyDifficultyButton.setTextFill(Color.BLACK);
        selectEasyDifficultyButton.setPrefSize(100,30);
        selectEasyDifficultyButton.relocate(400,350);
        difficultySelectorMenuRoot.getChildren().add(selectEasyDifficultyButton);
        selectEasyDifficultyButton.setOnMouseClicked(event->{

            primaryStage.setScene(gameScene);
        });

        Button selectMediumDifficultyButton = new Button("Medium");
        selectMediumDifficultyButton.setTextFill(Color.BLACK);
        selectMediumDifficultyButton.setPrefSize(100,30);
        selectMediumDifficultyButton.relocate(400,450);
        difficultySelectorMenuRoot.getChildren().add(selectMediumDifficultyButton);
        selectMediumDifficultyButton.setOnMouseClicked(event->{

            primaryStage.setScene(gameScene);
        });

        Button selectHardDifficultyButton = new Button("Hard");
        selectHardDifficultyButton.setTextFill(Color.BLACK);
        selectHardDifficultyButton.setPrefSize(100,30);
        selectHardDifficultyButton.relocate(400,550);
        difficultySelectorMenuRoot.getChildren().add(selectHardDifficultyButton);
        selectHardDifficultyButton.setOnMouseClicked(event->{

            primaryStage.setScene(gameScene);
        });
        

    }
}
