package game;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * This class focuses on reading the file PlayerInfoList.txt
 * then saves it and place them in a table
 * When user clicks on leaderboard button in main menu
 *
 * @version 1.6
 * @since version 1.3 (Leaderboard Update)
 */

public class FileData {
    SimpleStringProperty playerName;
    SimpleIntegerProperty finalScore;

    /**
     * This method takes the string
     * @param player the player name in save file PlayerInfoList.txt
     * @param score the score in save file PlayerInfoList.txt
     * @since version 1.3 (Leaderboard Update)
     */

    FileData(String player, int score) {
        this.playerName = new SimpleStringProperty(player);
        this.finalScore = new SimpleIntegerProperty(score);

    }

    /**
     * This method get the player name in file
     * @return the player name
     * @since version 1.3 (Leaderboard Update)
     */

    public String getPlayerName(){
        return playerName.get();
    }

    /**
     * This method set the player name in file
     * @param player set the player name to a row in the table
     * @since version 1.3 (Leaderboard Update)
     */

    public void setPlayerName(String player){
        playerName.set(player);
    }

    /**
     * This method gets the final score
     * @return the final score
     * @since version 1.3 (Leaderboard Update)
     */

    public int getFinalScore(){
        return finalScore.get();
    }

    /**
     *This method set the final score
     * @param score set the score to a row in the table
     * @since version 1.3 (Leaderboard Update)
     */

    public void setFinalScore(int score){
        finalScore.set(score);
    }
}
