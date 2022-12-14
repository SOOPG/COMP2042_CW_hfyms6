package game;

import java.io.*;

import javafx.scene.control.Alert;

/**
 * This class saves score when user clicks on the button "Save Score" in EndGame Scene
 *
 * @version 1.4
 * @since version 1.1.3.1 (Added Difficulty Selector Menu)
 */

public class ScoreSaver {

    /**
     * This method is called when user clicks on the button "Save Score" in EndGame Scene
     * It will attempt to create a save file named PlayerInfoList.txt
     * then calls savePlayerInfo method
     * to save the player name and score into the file
     *
     * @param score the final score of player
     * @param playerName the name of player input in the text dialog
     * @since version 1.1.3.1 (Added Difficulty Selector Menu)
     */
    public void createSaveFile(long score, String playerName){

        StringBuffer player=new StringBuffer(playerName+" ");

        //Create a file for storing score and name
        try {
            File savedHighScore = new File("PlayerInfoList.txt");
            if (savedHighScore.createNewFile()) {
                System.out.println("File created: " + savedHighScore.getName());

                //Calls method to write the score into the file
                savePlayerInfo(score,player);
            }

            else {
                //Has an existing save file, need to check if got scores....
                System.out.println("File already exists.");
                savePlayerInfo(score,player);
                System.out.println("Successfully override score.");
            }

            //Cannot save gives out a warning message to user error about saving high score
        } catch (IOException e) {
            Alert saveError = new Alert(Alert.AlertType.WARNING);
            saveError.setTitle("Error");
            saveError.setHeaderText("Error While Saving Score!");
        }

    }

    /**
     * This method saves the player information into file called PlayerInfoList.txt
     * as the format: "playerName score"
     * @param score save the score of player into file called PlayerInfoList.txt
     * @param player save the name of player into file called PlayerInfoList.txt
     *
     * @since version 1.2.1 (Added Saving Feature)
     */

    //Write High Score and Name
    public void savePlayerInfo(long score, StringBuffer player){
        try {
            BufferedWriter playerInfoWriter = new BufferedWriter(new FileWriter("PlayerInfoList.txt",true));

            playerInfoWriter.write(String.valueOf(player.append(score)));
            playerInfoWriter.newLine();
            playerInfoWriter.close();

            System.out.println("Successfully wrote score into new file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
