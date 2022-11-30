package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameSceneLoader extends Main{

    //Makes it so DifficultySelectorMenu scene has only 1 instance and initially is null
    public static GameSceneLoader singleInstance = null;

    //Construct DifficultySelectorMenu scene if there is no instance of it
    public GameSceneLoader(){

    }

    public static GameSceneLoader getInstance(){
        if(singleInstance == null)
            singleInstance= new GameSceneLoader();
        return singleInstance;
    }
    public void loadGameScene(Stage primaryStage){
        
        //Create Game Scene
        Group gameplayRoot = new Group();
        setGameRoot(gameplayRoot);
        Scene gameplayScene = new Scene(gameplayRoot, WIDTH, HEIGHT, Color.rgb(155,252,234));
        setGameScene(gameplayScene);

        //Create End Game Scene
        Group endGameRoot = new Group();
        setGameRoot(endGameRoot);
        Scene endGameScene = new Scene(endGameRoot, WIDTH, HEIGHT, Color.rgb(155,252,234));
        setGameScene(endGameScene);

        //Create Load Game Scene
        GameScene game = new GameScene();
        game.game(primaryStage, gameplayScene, gameplayRoot, endGameScene, endGameRoot);
        primaryStage.setScene(gameplayScene);
    }
}
