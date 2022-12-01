package game;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Optional;

public class EndGame{

    //Makes it so EndGame has only 1 instance and initially is null
    private static EndGame singleInstance = null;

    //Construct EndGame scene if there is no instance of it
    private EndGame(){

    }

    public static EndGame getInstance(){
        if(singleInstance == null)
            singleInstance= new EndGame();
        return singleInstance;
    }

    //Create new instances and check if its null
    public ScoreSaver scoreSaved=new ScoreSaver();
    public MainMenu backToMainMenu=new MainMenu();
    public GameSceneLoader startNewGame =new GameSceneLoader();

    public void endGameShow(Stage primaryStage, Scene gameScene, Group root, long score){

        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFont(Font.font(80));
        gameOverText.relocate(250,250);
        root.getChildren().add(gameOverText);

        Text scoreText = new Text("Score:"+score+"");
        scoreText.setFill(Color.BLACK);
        scoreText.setFont(Font.font(80));
        scoreText.relocate(250,600);
        root.getChildren().add(scoreText);

        Button newGameButton = new Button("New Game");
        newGameButton.setTextFill(Color.BLACK);
        newGameButton.setPrefSize(100,30);
        newGameButton.relocate(240,800);
        root.getChildren().add(newGameButton);
        newGameButton.setOnMouseClicked(event -> {
            startNewGame.getInstance().loadGameScene(primaryStage);
        });

        Button saveScoreButton=new Button("Save Score");
        saveScoreButton.setTextFill(Color.BLACK);
        saveScoreButton.setPrefSize(100,30);
        saveScoreButton.relocate(356.7,800);
        root.getChildren().add(saveScoreButton);
        //Save score when user clicks on button
        saveScoreButton.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Save High Score");
            alert.setHeaderText("Are you sure you want to save this score?");

            Optional<ButtonType> resultOfSaveButton = alert.showAndWait();

            if (resultOfSaveButton.get() == ButtonType.OK){

                //Writes score into save file
                scoreSaved.saveScoreButtonClicked(score);

                //Alerts User that score is saved
                Alert alertSaved = new Alert(Alert.AlertType.INFORMATION);
                alertSaved.setTitle("Saved");
                alertSaved.setHeaderText("Score and Name saved successfully");
                alertSaved.show();
            }
        });

        Button menuButton = new Button("Main Menu");
        menuButton.setTextFill(Color.BLACK);
        menuButton.setPrefSize(100,30);
        menuButton.relocate(478,800);
        root.getChildren().add(menuButton);
        menuButton.setOnMouseClicked(event -> {
            backToMainMenu.getInstance().displayMainMenu(primaryStage);
        });

        Button quitButton = new Button("Quit");
        quitButton.setTextFill(Color.BLACK);
        quitButton.setPrefSize(100,30);
        quitButton.relocate(590,800);
        root.getChildren().add(quitButton);

        //Set Quit button taking input as mouse click then perform the event
        quitButton.setOnMouseClicked(event -> {

            //Prompts a Warning Dialog warningOnQuit users to save before quiting the game
            Alert warningOnQuit = new Alert(Alert.AlertType.WARNING);
            warningOnQuit.setTitle("Warning");
            warningOnQuit.setHeaderText("Quiting this application without saving will delete your high score");

            //Wait for user's mouse prompt
            Optional<ButtonType> warningSaveResult = warningOnQuit.showAndWait();

            if (warningSaveResult.get() == ButtonType.OK) {
                //Prompts a Confirming Dialog asking users to confirm in quiting the game
                Alert alertQuitConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
                alertQuitConfirmation.setTitle("Quit");
                alertQuitConfirmation.setHeaderText("Confirm Exit?");
                alertQuitConfirmation.setContentText("Are you sure you want to quit this game?");

                //Wait for user's mouse prompt
                Optional<ButtonType> quitResult = alertQuitConfirmation.showAndWait();

                  /*
                    If button 'OK' is clicked,
                    Clear Text and UI in the Scene in Primary Stage
                    Terminate and Exit the Application
                 */
                if (quitResult.get() == ButtonType.OK){
                    root.getChildren().clear();
                    //Exit Application
                    Platform.exit();
                }
            }
        });
    }
}
