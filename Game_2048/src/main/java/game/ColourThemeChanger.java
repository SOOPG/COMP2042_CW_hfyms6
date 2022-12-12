package game;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColourThemeChanger extends Main {

//Initially it is in Light Mode
    String changeToMode ="Light";

    public static boolean isLightMode=true;

    String getColourTheme(String changeColourModeTo){

       this.changeToMode = changeColourModeTo;
        return this.changeToMode;
    }

    public static void changeColourInScene(Scene currentScene){

        if (ColourThemeChanger.isLightMode) {
            currentScene.setFill(Color.rgb(155,252,234));
        }
        else
            currentScene.setFill(Color.rgb(104,104,104));
    }

    public boolean setColourTheme(Scene currentScene, String changeColourModeTo){

        getColourTheme(changeColourModeTo);

        if (changeToMode.equals("Light")){
            currentScene.setFill(Color.rgb(155,252,234));
            System.out.println("Activated Light Mode!");
            isLightMode=true;
        }

        else if (changeToMode.equals("Dark")) {
            currentScene.setFill(Color.rgb(104,104,104));
            System.out.println("Activated Dark Mode!");
            isLightMode=false;
        }

        return isLightMode;
    }

    public static boolean getCellColourByColourMode(){
        return isLightMode;
    }

    public static void changeRectangleColourByColourMode(Rectangle rectangle){

        if (ColourThemeChanger.isLightMode) {
            rectangle.setFill(Color.rgb(20,29,28,0.1));
        }
        else
            rectangle.setFill(Color.rgb(44, 62, 80,0.5));
    }

}
