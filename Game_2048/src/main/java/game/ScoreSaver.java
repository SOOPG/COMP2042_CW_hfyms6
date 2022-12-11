package game;

import java.io.*;

import javafx.scene.control.Alert;

public class ScoreSaver {

    public void saveScoreButtonClicked(long score, String playerName){

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

    //Write High Score and Name
    public void savePlayerInfo(long score, StringBuffer player){
        try {
            BufferedWriter playerInfoWriter = new BufferedWriter(new FileWriter("PlayerInfoList.txt",true));

            playerInfoWriter.write(String.valueOf(player.append(score)));
            /*
            playerInfoWriter.append(System.lineSeparator());
            playerInfoWriter.append(Long.toString(score));
             */
            playerInfoWriter.newLine();
            playerInfoWriter.close();

            System.out.println("Successfully wrote score into new file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
