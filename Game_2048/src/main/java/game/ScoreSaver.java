package game;

import java.io.File;  // Import File class
import java.io.IOException;  // Import IOException class to handle errors
import javafx.scene.control.Alert;

public class ScoreSaver {

    //Get Score and Name
    public void getPlayerInfo(){

    }

    //Write High Score and Name
    public void setPlayerInfo(){

    }

    public void saveScoreButtonClicked(){

        //Create a file for storing score and name
        try {
            File savedHighScore = new File("PlayerInfoList.txt");
            if (savedHighScore.createNewFile()) {
                System.out.println("File created: " + savedHighScore.getName());

            }

            else {
                System.out.println("File already exists.");

            }

            //If cannot save gives out a warning message to user error about saving high score!
        } catch (IOException e) {
            Alert saveError = new Alert(Alert.AlertType.WARNING);
            saveError.setTitle("Error");
            saveError.setHeaderText("Error While Saving Score!");
            System.out.println("Error: Unable to Save File.");
            e.printStackTrace();
        }

    }
}
