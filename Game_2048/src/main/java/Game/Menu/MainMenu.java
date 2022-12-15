package Game.Menu;

import Game.*;
import Game.UserInterfaceDesigner.ColourThemeChanger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

/**
 * this MainMenu class extends Main class
 * due to inheriting stage,scene and root attributes from Main class
 * This Main Menu displays the UI when the user/player is in main menu scene
 * @version 1.6.1
 * @since version 1.1.4 ("Added Main Menu" in GitHub history)
 */

public class MainMenu extends Main {

    /**
     * Initially no instance of MainMenu
     */

    public static MainMenu singleInstance = null;

    /**
     * This method creates Main Menu if there is no main menu instantiated
     *
     * @since version 1.1.4.5 (added Difficulty Selector Menu)
     */
    //Construct DifficultySelectorMenu scene if there is no instance of it
    public MainMenu(){

    }

    /**
     * Check if MainMenu is already created or not
     * if already created, the MainMenu will not be instantiated again
     *
     * @since version 1.1.4.5 (added Difficulty Selector Menu)
     * @return single instance
     */

    public static MainMenu getInstance(){
        if(singleInstance == null)
            singleInstance= new MainMenu();
        return singleInstance;
    }

    /**
     * This method creates the Main Menu UI in the stage
     * once the application is launched successfully
     *
     * @param primaryStage Create Main Menu in Stage
     * @since version 1.1.4 ("Added Main Menu" in GitHub history)
     */

    // Display the main menu when starting up the application
   public void displayMainMenu(Stage primaryStage) {

       Group mainMenuRoot = new Group();
       setGameRoot(mainMenuRoot);
       Scene mainMenuScene = new Scene(mainMenuRoot, WIDTH, HEIGHT,Color.rgb(155,252,234));
       setGameScene(mainMenuScene);

       Rectangle backgroundOfMenu = new Rectangle(240, 120, Color.rgb(0, 120, 120, 0.2));
       backgroundOfMenu.setX(WIDTH / 2 - 120);
       backgroundOfMenu.setY(180);
       mainMenuRoot.getChildren().add(backgroundOfMenu);

       ColourThemeChanger.changeColourInScene(mainMenuScene);

       Text gameTitle = new Text("2048");
       gameTitle.setFill(Color.BLACK);
       gameTitle.setFont(Font.font("Nirmala UI", FontWeight.BOLD,80));
       gameTitle.relocate(360,185);
       mainMenuRoot.getChildren().add(gameTitle);

       Button playButton = new Button("Play");
       playButton.setTextFill(Color.BLACK);
       playButton.setPrefSize(100,30);
       playButton.relocate(400,450);
       mainMenuRoot.getChildren().add(playButton);

       playButton.setOnMouseClicked(event->{
           //Create an instance of Difficulty Selector Menu
           DifficultySelectorMenu selectDifficultyMenu=new DifficultySelectorMenu();
           selectDifficultyMenu.getInstance().displaySelectorMenu(primaryStage,mainMenuScene);
       });

        //Change to Leaderboard
       Button leaderboardButton = new Button("Leaderboard");
       leaderboardButton.setTextFill(Color.BLACK);
       leaderboardButton.setPrefSize(100,30);
       leaderboardButton.relocate(400,550);
       mainMenuRoot.getChildren().add(leaderboardButton);
       leaderboardButton.setOnMouseClicked(event->{

            LeaderboardMenu viewLeaderboard=new LeaderboardMenu();
            viewLeaderboard.getInstance().displayLeaderboardMenu(primaryStage,mainMenuScene);
       });

       //Change to Option Menu
       Button optionButton = new Button("Option");
       optionButton.setTextFill(Color.BLACK);
       optionButton.setPrefSize(100,30);
       optionButton.relocate(400,650);
       mainMenuRoot.getChildren().add(optionButton);
       optionButton.setOnMouseClicked(event->{

           OptionMenu GameSettings=new OptionMenu();
           GameSettings.getInstance().displayOptionMenu(primaryStage,mainMenuScene);
       });

       //Exit Game
       Button exitButton = new Button("Exit");
       exitButton.setTextFill(Color.BLACK);
       exitButton.setPrefSize(100,30);
       exitButton.relocate(400,750);
       mainMenuRoot.getChildren().add(exitButton);
       exitButton.setOnMouseClicked(event->{
           Alert alertQuitConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
           alertQuitConfirmation.setTitle("Quit Application");
           alertQuitConfirmation.setHeaderText("Confirm Exit?");
           alertQuitConfirmation.setContentText("Are you sure you want to quit this game?");

           Optional<ButtonType> result = alertQuitConfirmation.showAndWait();
           if (result.get() == ButtonType.OK) {
               Platform.exit();
           }
       });
       //Show the Main Menu
       primaryStage.setScene(mainMenuScene);

    }
}