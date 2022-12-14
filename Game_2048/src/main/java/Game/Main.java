/**
 * Main package where all the list of classes is located
 */
package Game; //Main package where all the list of classes is located

import Game.Menu.MainMenu;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.Scanner;

/**
 * 2048 Game
 * This Main class initialize variables implemented in the program application (game)
 * It is also responsible in starting the application in the Main Menu Scene
 *
 * @author Soo Min Hao
 * @version 1.6.1
 * @since version 1.0 ("Initial Commit" in GitHub history)
 */

public class Main extends Application {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 900;

    /**
     * Create a new game root (for UI)
     */
    public Group gameRoot = new Group();


    /**
     * Create a new game scene
     */
    public Scene gameScene = new Scene(gameRoot, WIDTH, HEIGHT, Color.rgb(189, 177, 92));

    private static Scanner input= new Scanner(System.in);

    /**
     * This method is used to set a gameScene
     * A Game Scene is a scene that defines the state of the game
     * This method helps in setting which state the player should be in.
     * Example in this application includes: (Main Menu, Option Menu, Gameplay Menu, End Game Menu, etc...)
     *
     * @param gameScene Set the gameScene to current gameScene
     * @since version 1.0 ("Initial Commit" in GitHub history)
     */

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    /**
     *This method is used to set a gameRoot
     *A Game Root is a root node of scene
     *that defines how a game scene should look like
     *This gameRoot is implemented to help users/players to distinguish which game scene they are on
     *The UI is designed by implementing how the gameRoot should look like
     *
     * @param gameRoot Set the gameRoot to current gameRoot
     * @since version 1.0 ("Initial Commit" in GitHub history)
     */

    public void setGameRoot(Group gameRoot) {
        this.gameRoot = gameRoot;
    }

    /**
     * This method starts the application
     * and calls the displayMainMenu method to display main menu
     * once the application has completed booting up
     *
     * @param primaryStage top level in JavaFX container (A window)
     * @since version 1.0 ("Initial Commit" in GitHub history)
     */

    @Override
    public void start(Stage primaryStage){

        try{
            //Create an instance of Main Menu
            MainMenu gameMainMenu=new MainMenu();
            gameMainMenu.getInstance().displayMainMenu(primaryStage);

            //Set Title of Stage as '2048'
            primaryStage.setTitle("2048");
            //Starts up application once 'Run' button is clicked
            primaryStage.show();
        }
        catch (Exception errorBootUp){
            Alert warningErrorBootingUp = new Alert(Alert.AlertType.WARNING);
            warningErrorBootingUp.setTitle("Warning");
            warningErrorBootingUp.setHeaderText("Quiting this application without saving will delete your high score");
        }
    }

    /**
     * This method starts up the Game Window (Stage)
     * @param args string class which stores java command line arguements
     * @since version 1.0 ("Initial Commit" in GitHub history)
     */

    public static void main(String[] args) {
        launch(args);
    }
}
