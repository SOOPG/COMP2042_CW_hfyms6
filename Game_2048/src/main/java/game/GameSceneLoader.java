package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * this GameSceneLoader class extends Main class
 * due to inheriting stage attributes from Main class
 * This GameSceneLoader loads the UI of Game scene when the user/player
 * has selected the difficulty in the difficulty selector menu
 *
 * @version 1.6
 * @since version 1.1.6 ("Difficulties Update" in GitHub history)
 */

public class GameSceneLoader extends Main{

    /**
     * This method creates GameSceneLoader if there is no gameScene loader instantiated
     *
     * @since version 1.1.6 (Difficulties Update)
     */
    //Makes it so DifficultySelectorMenu scene has only 1 instance and initially is null
    public static GameSceneLoader singleInstance = null;

    /**
     * This method creates GameSceneLoader if there is no gameScene loader instantiated
     *
     * @since version 1.1.6 (Difficulties Update)
     */

    //Construct DifficultySelectorMenu scene if there is no instance of it
    public GameSceneLoader(){

    }

    /**
     * Check if GameSceneLoader is already created or not
     * if already created, the GameSceneLoader will not be instantiated again
     *
     * @since version 1.1.6 (Difficulties Update)
     * @return single instance
     */

    public static GameSceneLoader getInstance(){
        if(singleInstance == null)
            singleInstance= new GameSceneLoader();
        return singleInstance;
    }

    /**
     * This method creates the gameScene UI in the stage
     * when player clicks on the difficulty
     * @param primaryStage Create primaryStage in Stage
     * @since version 1.1.6 (Difficulties Update)
     */

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

        //Check which color theme currently is used
        ColourThemeChanger.changeColourInScene(gameplayScene);
        ColourThemeChanger.changeColourInScene(endGameScene);
    }
}
