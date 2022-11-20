package game;

import java.io.File;  // Import File class
import java.io.FileWriter;
import java.io.IOException;  // Import IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import Scanner class to read text files
import javafx.scene.control.Alert;

public class ScoreSaver {

    /*Get Score and Name
    public void getPlayerInfo(){
        try {
            Scanner scoreReader = new Scanner(savedHighScore);
            while (scoreReader.hasNextLine()) {
                String data = scoreReader.nextLine();
                System.out.println(data);
            }
            scoreReader.close();
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
*/

    //Write High Score and Name
    public void setPlayerInfo(long score){
        try {
            FileWriter scoreWriter = new FileWriter("PlayerInfoList.txt");
            scoreWriter.write("" + score);
            scoreWriter.close();
            System.out.println("Successfully wrote score.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void saveScoreButtonClicked(long score){

        //Create a file for storing score and name
        try {
            File savedHighScore = new File("PlayerInfoList.txt");
            if (savedHighScore.createNewFile()) {
                System.out.println("File created: " + savedHighScore.getName());

                //Calls method to write the score into the file
                setPlayerInfo(score);
            }

            else {
                //Has an existing save file, need to check if got scores....

                System.out.println("File already exists.");
                //Calls method to write the score into the file
                setPlayerInfo(score);
                System.out.println("Successfully override score.");
            }

            //Cannot save gives out a warning message to user error about saving high score
        } catch (IOException e) {
            Alert saveError = new Alert(Alert.AlertType.WARNING);
            saveError.setTitle("Error");
            saveError.setHeaderText("Error While Saving Score!");
        }

    }
}
