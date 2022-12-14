package game;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This class makes the text (number) located in the center of the cell
 *
 * @version 1.4
 * @since 1.0 (Initial Commit)
 */

class TextMaker {
    private static TextMaker singleInstance = null;

    /**
     * This method creates TextMaker if there is no instantiation of TextMaker
     *
     * @since 1.0 (Initial Commit)
     */
    private TextMaker() {

    }

    /**
     * Check if TextMaker is already created or not
     * if already created, the TextMaker will not be instantiated again
     *
     * @since version 1.0 (Initial Commit)
     */

    static TextMaker getSingleInstance() {
        if (singleInstance == null)
            singleInstance = new TextMaker();
        return singleInstance;
    }

    /**
     * This method is called to spawn a cell tile
     * in random position of empty cell tile in game scene
     * @param input the value of the spawned cell tile
     * @param xCell get the x-coordinate of an empty cell
     * @param yCell get the y-coordinate of an empty cell
     * @param root change the text cell in the
     * @return the updated text
     *
     * @since version 1.0 (Initial Commit)
     */

    Text madeText(String input, double xCell, double yCell, Group root) {
        double length = GameScene.getLENGTH();
        double fontSize = (3 * length) / 8.0;
        Text text = new Text(input);
        text.setFont(Font.font(fontSize));
        text.relocate((xCell + (0.6)* length / 7.0), (yCell + 2 * length / 8.0));
        text.setFill(Color.WHITE);

        return text;
    }

    /**
     * This method changes/updates the numbers when they merge/add up
     * @param first update text in first cell
     * @param second updated  text in second cell
     *
     * @since version 1.0(Initial Commit)
     */

    static void changeTwoText(Text first, Text second) {
        String temp;
        temp = first.getText();
        first.setText(second.getText());
        second.setText(temp);

        double tempNumber;
        tempNumber = first.getX();
        first.setX(second.getX());
        second.setX(tempNumber);

        tempNumber = first.getY();
        first.setY(second.getY());
        second.setY(tempNumber);

    }

}
