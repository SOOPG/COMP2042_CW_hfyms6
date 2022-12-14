package Game.GameController;


import Game.UserInterfaceDesigner.ColourThemeChanger;
import Game.UserInterfaceDesigner.TextMaker;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *This class focuses on cells
 *that it controls the cell's
 * numbers and colour of the cells
 * in game scene
 *
 * @version 1.6.1
 * @since 1.0 (Initial Commit)
 */

public class Cell {
    private Rectangle rectangle;
    private Group root;
    private Text textClass;
    private boolean modify = false;

    /**
     * This method gets the X-axis in GameScene
     * @return the x-axis
     * @since 1.0 (Initial Commit)
     */

    double getX() {
        return rectangle.getX();
    }

    /**
     *This method gets the Y-axis in GameScene
     * @return the y-axis
     * @since 1.0 (Initial Commit)
     */

    double getY() {
        return rectangle.getY();
    }

    /**
     *This method checks if the cell is modified
     * @return boolean of modify
     * @since 1.0 (Initial Commit)
     */

    boolean getModify() {
        return modify;
    }

    /**
     *This method sets the cell to
     * either modified or not
     * @return boolean of modify
     * @since 1.0 (Initial Commit)
     */

    void setModify(boolean modify) {
        this.modify = modify;
    }

    /**
     *This method gets the number inside the cell
     * @return the number text
     * @since 1.0 (Initial Commit)
     */

    private Text getTextClass() {
        return textClass;
    }

    /**
     *This method sets the number inside the cell
     * @param textClass the number text
     * @since 1.0 (Initial Commit)
     */

    void setTextClass(Text textClass) {
        this.textClass = textClass;
    }

    /**
     * This method gets the text class and converts it into an integer
     * @return integer of the number in the cell
     * @since 1.0 (Initial Commit)
     */

    int getNumber() {
        return Integer.parseInt(textClass.getText());
    }

    /**
     *This method is responsible for creating cells in gameScene
     * depending on the number of grid
     * @param x set the length of rectangle
     * @param y set the width of rectangle
     *
     * @param scale set the scale depending on how large the grid size is
     * @param root set how the cells should look like in UI in gameScene
     * @since 1.0 (Initial Commit)
     */

    Cell(double x, double y, double scale, Group root) {
        rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setHeight(scale);
        rectangle.setWidth(scale);
        rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
        ColourThemeChanger.changeRectangleColourByColourMode(rectangle);
        this.root = root;
        this.textClass = TextMaker.getSingleInstance().madeText("0", x, y, root);
        root.getChildren().add(rectangle);
    }

    /**
     * This method changes the cell if two cells merge
     * and also change the number in the cell
     * @param cell change that particular cell
     * @since 1.0 (Initial Commit)
     */

    void changeCell(Cell cell) {
        TextMaker.changeTwoText(textClass, cell.getTextClass());
        root.getChildren().remove(cell.getTextClass());
        root.getChildren().remove(textClass);

        if (!cell.getTextClass().getText().equals("0")) {
            root.getChildren().add(cell.getTextClass());
        }
        if (!textClass.getText().equals("0")) {
            root.getChildren().add(textClass);
        }
        setColorByNumber(getNumber());
        cell.setColorByNumber(cell.getNumber());
    }

    /**
     * This method sets the color of cells
     * by the number in the cells
     * @param number the number in the cell
     * @since 1.0 (Initial Commit)
     */

    void setColorByNumber(int number) {

        ColourThemeChanger.getCellColourByColourMode();

        if (ColourThemeChanger.isLightMode==true) {

            switch (number) {
                case 0 -> rectangle.setFill(Color.rgb(20,29,28,0.1));

                case 2 -> rectangle.setFill(Color.rgb(25, 145, 0));
                case 4 -> rectangle.setFill(Color.rgb(26, 156, 0));
                case 8 -> rectangle.setFill(Color.rgb(29, 171, 0));

                case 16 -> rectangle.setFill(Color.rgb(35, 209, 0));
                case 32 -> rectangle.setFill(Color.rgb(38, 225, 0));
                case 64 -> rectangle.setFill(Color.rgb(41, 245, 0));

                case 128 -> rectangle.setFill(Color.rgb(136, 255, 113));
                case 256 -> rectangle.setFill(Color.rgb(149, 255, 128));

                case 512 -> rectangle.setFill(Color.rgb(181, 255, 165));
                case 1024 -> rectangle.setFill(Color.rgb(202, 255, 191));

                case 2048 -> rectangle.setFill(Color.rgb(219, 165, 7));
            }
        }
        else {
            switch (number) {
                case 0 -> rectangle.setFill(Color.rgb(44, 62, 80,0.5));

                case 2 -> rectangle.setFill(Color.rgb(0, 0, 0));
                case 4 -> rectangle.setFill(Color.rgb(20, 35, 5));
                case 8 -> rectangle.setFill(Color.rgb(32, 51, 17));
                case 16 -> rectangle.setFill(Color.rgb(48, 77, 26));

                case 32 -> rectangle.setFill(Color.rgb(65, 102, 35));
                case 64 -> rectangle.setFill(Color.rgb(81, 128, 43));

                case 128 -> rectangle.setFill(Color.rgb(97, 153, 52));
                case 256 -> rectangle.setFill(Color.rgb(113, 179, 60));

                case 512 -> rectangle.setFill(Color.rgb(129, 204, 69));
                case 1024 -> rectangle.setFill(Color.rgb(145, 230, 78));

                case 2048 -> rectangle.setFill(Color.rgb(219, 165, 7));
            }
        }
    }

    /**
     * This method is called
     * when two cells are merged and added up
     * to change the number in the cell
     * @param cell the new added cell
     * @return the summed up number
     * @since version 1.0 (Initial Commit)
     */

    long adder(Cell cell) {
        cell.getTextClass().setText((cell.getNumber() + this.getNumber()) + "");
        textClass.setText("0");
        root.getChildren().remove(textClass);
        cell.setColorByNumber(cell.getNumber());
        setColorByNumber(getNumber());
        //Return the Summed up Number
        return (cell.getNumber() + this.getNumber());
    }
}


