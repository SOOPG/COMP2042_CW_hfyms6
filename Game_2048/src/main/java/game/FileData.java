package game;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class FileData {
    SimpleStringProperty playerName;
    SimpleIntegerProperty finalScore;

    FileData(String player, int score) {
        this.playerName = new SimpleStringProperty(player);
        this.finalScore = new SimpleIntegerProperty(score);

    }

    public String getPlayerName(){
        return playerName.get();
    }
    public void setPlayerName(String player){
        playerName.set(player);
    }

    public int getFinalScore(){
        return finalScore.get();
    }
    public void setFinalScore(int score){
        finalScore.set(score);
    }
}
