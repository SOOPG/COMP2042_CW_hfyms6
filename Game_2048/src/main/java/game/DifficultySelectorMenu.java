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

/**
 * this DifficultySelector class extends Main class
 * due to inheriting stage and scene attributes from Main class
 * This DifficultySelectorMenu displays the UI when the user/player is in difficulty selection scene
 * The player can select the difficulties they wish to play in Easy,Normal,Hard mode
 *
 * @version 1.6
 * @since version 1.1.4.5 (Added Difficulty Selector Menu)
 */

public class DifficultySelectorMenu extends Main {

    //Makes it so DifficultySelectorMenu scene has only 1 instance and initially is null
    private static DifficultySelectorMenu singleInstance = null;

    /**
     * This method creates DifficultySelectorMenu if there is no difficulty selector menu instantiated
     *
     * @since version 1.1.4.5 (Added Difficulty Selector Menu)
     */

    //Construct DifficultySelectorMenu scene if there is no instance of it
    public DifficultySelectorMenu(){

    }

    /**
     * Check if OptionMenu is already created or not
     * if already created, the OptionMenu will not be instantiated again
     *
     * @since version 1.1.4.5 (Added Difficulty Selector Menu)
     * @return single instance of the obj
     */

    public static DifficultySelectorMenu getInstance(){
        if(singleInstance == null)
            singleInstance= new DifficultySelectorMenu();
        return singleInstance;
    }

    /**
     * This method creates the difficulty selector UI in the stage
     * once the user clicked on 'Play' button in the scene.
     * @param primaryStage Create Main Menu in Stage
     * @param menuScene Go back to main menu through a button
     * @since version 1.1.7.6 (Added Main Menu)
     */

    public void displaySelectorMenu(Stage primaryStage,Scene menuScene){

        Group difficultySelectorMenuRoot = new Group();
        setGameRoot(difficultySelectorMenuRoot);
        Scene difficultySelectorMenuScene = new Scene(difficultySelectorMenuRoot, WIDTH, HEIGHT,Color.rgb(155,252,234));
        setGameScene(difficultySelectorMenuScene);
        Rectangle backgroundOfDifficultySelectorMenu = new Rectangle(240, 120, Color.rgb(0, 120, 120, 0.2));
        backgroundOfDifficultySelectorMenu.setX(WIDTH / 2 - 120);
        backgroundOfDifficultySelectorMenu.setY(180);

        Text selectDifficultyText = new Text("Select Difficulty:");
        selectDifficultyText.setFill(Color.BLACK);
        selectDifficultyText.setFont(Font.font("Nirmala UI", FontWeight.BOLD,50));
        selectDifficultyText.relocate(275,185);
        difficultySelectorMenuRoot.getChildren().add(selectDifficultyText);

        ColourThemeChanger.changeColourInScene(difficultySelectorMenuScene);

        Button selectEasyDifficultyButton = new Button("Easy");
        selectEasyDifficultyButton.setTextFill(Color.BLACK);
        selectEasyDifficultyButton.setPrefSize(100,30);
        selectEasyDifficultyButton.relocate(400,350);
        difficultySelectorMenuRoot.getChildren().add(selectEasyDifficultyButton);
        selectEasyDifficultyButton.setOnMouseClicked(event->{

            GameScene.grid =6;
            GameScene.setGrid(6);
            GameSceneLoader loadGame=new GameSceneLoader();
            loadGame.getInstance().loadGameScene(primaryStage);
        });

        Button selectMediumDifficultyButton = new Button("Medium");
        selectMediumDifficultyButton.setTextFill(Color.BLACK);
        selectMediumDifficultyButton.setPrefSize(100,30);
        selectMediumDifficultyButton.relocate(400,450);
        difficultySelectorMenuRoot.getChildren().add(selectMediumDifficultyButton);
        selectMediumDifficultyButton.setOnMouseClicked(event->{

            GameScene.grid =4;
            GameScene.setGrid(4);
            GameSceneLoader loadGame=new GameSceneLoader();
            loadGame.getInstance().loadGameScene(primaryStage);
        });

        Button selectHardDifficultyButton = new Button("Hard");
        selectHardDifficultyButton.setTextFill(Color.BLACK);
        selectHardDifficultyButton.setPrefSize(100,30);
        selectHardDifficultyButton.relocate(400,550);
        difficultySelectorMenuRoot.getChildren().add(selectHardDifficultyButton);
        selectHardDifficultyButton.setOnMouseClicked(event->{

            GameScene.grid =3;
            GameScene.setGrid(3);
            GameSceneLoader loadGame=new GameSceneLoader();
            loadGame.getInstance().loadGameScene(primaryStage);
        });

        Button backToMainMenuButton = new Button("Back");
        backToMainMenuButton.setTextFill(Color.BLACK);
        backToMainMenuButton.setPrefSize(100,30);
        backToMainMenuButton.relocate(400,650);
        difficultySelectorMenuRoot.getChildren().add(backToMainMenuButton);
        backToMainMenuButton.setOnMouseClicked(event-> {

            primaryStage.setScene(menuScene);

        });

        //Show Difficulty Selector Menu
        primaryStage.setScene(difficultySelectorMenuScene);
    }
}
