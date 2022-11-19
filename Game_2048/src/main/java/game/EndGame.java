package game;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;


public class EndGame {

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

    public void endGameShow(Scene endGameScene, Group root, Stage primaryStage,long score){
        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFont(Font.font(80));
        gameOverText.relocate(250,250);
        root.getChildren().add(gameOverText);

        Text scoreText = new Text("Score:"+score+"");
        scoreText.setFill(Color.BLACK);
        scoreText.setFont(Font.font(80));
        scoreText.relocate(250,600);
        root.getChildren().add(scoreText);

        Button quitButton = new Button("QUIT");
        quitButton.setTextFill(Color.BLACK);
        quitButton.setPrefSize(100,30);
        quitButton.relocate(100,800);
        root.getChildren().add(quitButton);

        //Set Quit button taking input as mouse click then perform the event
        quitButton.setOnMouseClicked(event -> {

            /*Prompts a Warning Dialog warning users to save before quiting the game
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Warning");
            warning.setHeaderText("Quiting This Application Without Saving Will Delete Your High Score");

            //Wait for user's mouse prompt
            Optional<ButtonType> warningResult = warning.showAndWait();
            */

            //Prompts a Confirming Dialog asking users to confirm in quiting the game
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Quit Application");
            alert.setHeaderText("Confirm Quit");
            alert.setContentText("Are you sure you want to quit this game?");

            //Wait for user's mouse prompt
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK){
                root.getChildren().clear();
                Platform.exit(); //Exit Application
                /*
                If button 'OK' is clicked,
                Clear Text and UI in the Scene in Primary Stage
                Terminate and Exit the Application
                 */
            }
        });
    }
}
