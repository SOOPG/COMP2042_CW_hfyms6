package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OptionMenu extends Main{

    public static OptionMenu singleInstance = null;

    //Construct DifficultySelectorMenu scene if there is no instance of it
    public OptionMenu(){

    }

    public static OptionMenu getInstance(){
        if(singleInstance == null)
            singleInstance= new OptionMenu();
        return singleInstance;
    }

    public void displayOptionMenu(Stage primaryStage){

        String colourTheme="default";

        Group optionMenuRoot = new Group();
        setGameRoot(optionMenuRoot);
        Scene optionMenuScene = new Scene(optionMenuRoot, WIDTH, HEIGHT, Color.rgb(155,252,234));
        setGameScene(optionMenuScene);

        Text optionText = new Text("Options:");
        optionText.setFill(Color.BLACK);
        optionText.setFont(Font.font("Nirmala UI", FontWeight.BOLD,40));
        optionText.relocate(370,185);
        optionMenuRoot.getChildren().add(optionText);

        Button changeColourThemeButton = new Button("Color Theme");
        changeColourThemeButton.setTextFill(Color.BLACK);
        changeColourThemeButton.setPrefSize(100,30);
        changeColourThemeButton.relocate(400,350);
        optionMenuRoot.getChildren().add(changeColourThemeButton);
        changeColourThemeButton.setOnMouseClicked(event-> {

        });

        Button changeMusicVolumeButton = new Button("Music");
        changeMusicVolumeButton.setTextFill(Color.BLACK);
        changeMusicVolumeButton.setPrefSize(100,30);
        changeMusicVolumeButton.relocate(400,450);
        optionMenuRoot.getChildren().add(changeMusicVolumeButton);
        changeMusicVolumeButton.setOnMouseClicked(event-> {

        });

        Button changeSoundFXVolumeButton = new Button("Sound Effect");
        changeSoundFXVolumeButton.setTextFill(Color.BLACK);
        changeSoundFXVolumeButton.setPrefSize(100,30);
        changeSoundFXVolumeButton.relocate(400,550);
        optionMenuRoot.getChildren().add(changeSoundFXVolumeButton);
        changeSoundFXVolumeButton.setOnMouseClicked(event-> {

        });


        primaryStage.setScene(optionMenuScene);
    }

}
