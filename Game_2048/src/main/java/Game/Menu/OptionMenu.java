package Game.Menu;

import Game.UserInterfaceDesigner.ColourThemeChanger;
import Game.Main;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * this OptionMenu extends Main class
 * due to inheriting stage,scene and root attributes from Main class
 * This Option Menu displays the UI of option menu when the user/player is in option scene
 * The option menu allows users to change colour theme of the
 * @version 1.6.1
 * @since version 1.1.7.6
 */

public class OptionMenu extends Main {

    //Makes it so OptionMenu scene has only 1 instance and initially is null
    /**
     * Initially OptionMenu is not instantiated
     */
    public static OptionMenu singleInstance = null;

    /**
     * This method creates OptionMenu if there is no option menu instantiated
     *
     * @since version 1.1.7.6
     */

    //Construct DifficultySelectorMenu scene if there is no instance of it
    public OptionMenu(){

    }

    /**
     * Check if OptionMenu is already created or not
     * if already created, the OptionMenu will not be instantiated again
     * @return single instance
     *
     * @since version 1.1.7.6
     */

    public static OptionMenu getInstance(){
        if(singleInstance == null)
            singleInstance= new OptionMenu();
        return singleInstance;
    }

    /**
     * This method creates the Option Menu UI in the stage
     * once the player clicked on Option in the main menu
     *
     * @param primaryStage Create Main Menu in Stage
     * @param mainMenuScene Go back to main menu through a button
     * @since version 1.1.7.6 ("Added Main Menu" in GitHub history)
     */

    public void displayOptionMenu(Stage primaryStage,Scene mainMenuScene){

        Group optionMenuRoot = new Group();
        setGameRoot(optionMenuRoot);
        Scene optionMenuScene = new Scene(optionMenuRoot, WIDTH, HEIGHT, Color.rgb(155,252,234));
        setGameScene(optionMenuScene);

        Text optionText = new Text("Options:");
        optionText.setFill(Color.BLACK);
        optionText.setFont(Font.font("Nirmala UI", FontWeight.BOLD,40));
        optionText.relocate(370,185);
        optionMenuRoot.getChildren().add(optionText);

        ColourThemeChanger.changeColourInScene(optionMenuScene);

        Button changeColourThemeButton = new Button("Light Mode");
        changeColourThemeButton.setTextFill(Color.BLACK);
        changeColourThemeButton.setPrefSize(100,30);
        changeColourThemeButton.relocate(400,350);
        optionMenuRoot.getChildren().add(changeColourThemeButton);
        changeColourThemeButton.setOnMouseClicked(event-> {

            ColourThemeChanger changeColour=new ColourThemeChanger();
            changeColour.setColourTheme(optionMenuScene,"Light");

        });

        Button changeMusicVolumeButton = new Button("Dark Mode");
        changeMusicVolumeButton.setTextFill(Color.BLACK);
        changeMusicVolumeButton.setPrefSize(100,30);
        changeMusicVolumeButton.relocate(400,450);
        optionMenuRoot.getChildren().add(changeMusicVolumeButton);
        changeMusicVolumeButton.setOnMouseClicked(event-> {

            ColourThemeChanger changeColour=new ColourThemeChanger();
            changeColour.setColourTheme(optionMenuScene,"Dark");

        });

        Button backToMainMenuButton = new Button("Back");
        backToMainMenuButton.setTextFill(Color.BLACK);
        backToMainMenuButton.setPrefSize(100,30);
        backToMainMenuButton.relocate(400,550);
        optionMenuRoot.getChildren().add(backToMainMenuButton);
        backToMainMenuButton.setOnMouseClicked(event-> {

            primaryStage.setScene(mainMenuScene);
            ColourThemeChanger.changeColourInScene(mainMenuScene);

        });
        primaryStage.setScene(optionMenuScene);
    }
}
