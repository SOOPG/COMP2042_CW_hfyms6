package game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * This class displays the leaderboard UI
 * while also display the information of player name and score
 * in PlayerInfoList.txt
 * It inherits stage and scene attributes from Main
 *
 * @version 1.4
 * @since version 1.3 (Leaderboard Update)
 */

public class LeaderboardMenu extends Main{

    public static LeaderboardMenu singleInstance = null;

    /**
     * This method creates Leaderboard Menu if there is no leaderboard menu instantiated
     * @since 1.3 (Leaderboard Update)
     */

    //Construct DifficultySelectorMenu scene if there is no instance of it
    public LeaderboardMenu(){

    }

    /**
     * Check if LeaderboardMenu is already created or not
     * if already created, the LeaderboardMenu will not be instantiated again
     *
     * @since 1.3 (Leaderboard Update)
     */

    public static LeaderboardMenu getInstance(){
        if(singleInstance == null)
            singleInstance= new LeaderboardMenu();
        return singleInstance;
    }

    /**
     *This class displays a table
     * that lists down the content of file
     * in player and score list
     *
     * @param primaryStage set the leaderboard ui in stage when player is in leaderboard menu scene
     * @param menuScene go back to main menu through a button
     * @since 1.3 (Leaderboard Update)
     */

    public void displayLeaderboardMenu(Stage primaryStage,Scene menuScene) {

        Group leaderboardMenuRoot = new Group();
        setGameRoot(leaderboardMenuRoot);
        Scene leaderboardMenuScene = new Scene(leaderboardMenuRoot, WIDTH, HEIGHT, Color.rgb(155, 252, 234));
        setGameScene(leaderboardMenuScene);

        Text gameLeaderboard = new Text("Leaderboard:");
        gameLeaderboard.setFill(Color.BLACK);
        gameLeaderboard.setFont(Font.font("Nirmala UI", FontWeight.BOLD,40));
        gameLeaderboard.relocate(318,185);
        leaderboardMenuRoot.getChildren().add(gameLeaderboard);

        ColourThemeChanger.changeColourInScene(leaderboardMenuScene);

        TableView leaderboardTable = new TableView();
        TableColumn playerNameCol = new TableColumn("Player Name");
        playerNameCol.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        TableColumn highScoreCol = new TableColumn("High Score");
        highScoreCol.setCellValueFactory(new PropertyValueFactory<>("finalScore"));


        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 50, 50, 60));
        vbox.relocate(255,250);
        vbox.getChildren().addAll(leaderboardTable);

         try {
                File playerInfoFile = new File("PlayerInfoList.txt");
                Scanner myReader = new Scanner(playerInfoFile);
                ObservableList<FileData> playerInfoList = FXCollections.observableArrayList();

                //Read the file while the file has content in the next line
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                    String[] playerInfoData = data.split(" ",2);
                    playerInfoList.add(new FileData(playerInfoData[0], parseInt(playerInfoData[1])));
                }
             leaderboardTable.setItems(playerInfoList);
             leaderboardTable.getColumns().addAll(playerNameCol,highScoreCol);
         }
         catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        leaderboardMenuRoot.getChildren().add(vbox);

        Button backToMainMenuButton = new Button("Back");
        backToMainMenuButton.setTextFill(Color.BLACK);
        backToMainMenuButton.setPrefSize(100,30);
        backToMainMenuButton.relocate(387.5,700);
        leaderboardMenuRoot.getChildren().add(backToMainMenuButton);
        backToMainMenuButton.setOnMouseClicked(event-> {
            primaryStage.setScene(menuScene);
        });

        primaryStage.setScene(leaderboardMenuScene);
    }

}
