package game;


import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Cell {
    private Rectangle rectangle;
    private Group root;
    private Text textClass;
    private boolean modify = false;

    double getX() {
        return rectangle.getX();
    }
    double getY() {
        return rectangle.getY();
    }

    boolean getModify() {
        return modify;
    }
    void setModify(boolean modify) {
        this.modify = modify;
    }

    private Text getTextClass() {
        return textClass;
    }
    void setTextClass(Text textClass) {
        this.textClass = textClass;
    }

    int getNumber() {
        return Integer.parseInt(textClass.getText());
    }

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


