package Game.UserInterfaceDesigner;

import Game.Main;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class is used to change the colour theme of the game
 * It extends Main due to scene attribute is inherited from Main class
 * when user presses 'Light' - it will display colour theme in light mode
 * when user presses 'Dark' - it will display colour theme in dark mode
 *
 * @version 1.6.1
 * @since 1.4 (Major Update: Colour Theme Update)
 */

public class ColourThemeChanger extends Main {

    //Initially it is in Light Mode
    String changeToMode ="Light";

     // this returns ture if the game is on light mode
    /**
     * this check if the game is on light mode
     */
    public static boolean isLightMode=true;

    String getColourTheme(String changeColourModeTo){

       this.changeToMode = changeColourModeTo;
        return this.changeToMode;
    }

    /**
     * This method checks if the user is in light mode or dark mode
     * and changes the colour of a scene according to the isLightMode boolean when called
     * isLightMode checks if user is on light mode
     *
     * @param currentScene change the colour of currentScene where the method is called
     * @since version 1.4 (Major Update: Colour Theme Update)
     */
    public static void changeColourInScene(Scene currentScene){

        if (ColourThemeChanger.isLightMode) {
            currentScene.setFill(Color.rgb(155,252,234));
        }
        else
            currentScene.setFill(Color.rgb(104,104,104));
    }

    /**
     *This method sets the colour theme of the whole game
     * @param currentScene change the scenes to the selected colour mode
     * @param changeColourModeTo pass the string to change the colour mode to either light or dark
     * @return the colour theme
     * @since version 1.4 (Major Update: Colour Theme Update)
     */

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

    /**
     * This method gets the colour mode
     * @return the colour theme
     * @since version 1.4 (Major Update: Colour Theme Update)
     */

    public static boolean getCellColourByColourMode(){
        return isLightMode;
    }

    /**
     * This method changes the cell colour by colour mode
     * @param rectangle change the colour of rectangles (cells) in Cell class
     * @since version 1.4 (Major Update: Colour Theme Update)
     */

    public static void changeRectangleColourByColourMode(Rectangle rectangle){

        if (ColourThemeChanger.isLightMode) {
            rectangle.setFill(Color.rgb(20,29,28,0.1));
        }
        else
            rectangle.setFill(Color.rgb(44, 62, 80,0.5));
    }

}
