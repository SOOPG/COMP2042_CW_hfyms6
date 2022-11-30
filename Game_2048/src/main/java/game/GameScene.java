package game;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Set;

class GameScene {
    private static int HEIGHT = 700;

    //Total Number of Cells for 2 axises (X and Y)
    public static int n;

    //How many Cells
    public static void setN(int number) {
        n = number;
        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    }

    //Distance btw Cells
    private final static int distanceBetweenCells = 10;

    private static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    private TextMaker textMaker = TextMaker.getSingleInstance();
    private Cell[][] cells = new Cell[n][n];
    private Group root;

    //Sum of Score of Current Game Session
    public long score = 0;

    public String moveVariable;

    static double getLENGTH() {
        return LENGTH;
    }

    public static void n(int i) {
        n=i;
    }

    // Fills in a number either 2 or 4 each turn in random locations
    // Within the cell that is not occupied by a number cell
    private void randomFillNumber(int turn) {

        // why a,b
        Cell[][] emptyCells = new Cell[n][n];
        int a = 0;
        int b = 0;
        int aForBound=0,bForBound=0;
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0) {
                    emptyCells[a][b] = cells[i][j];
                    if (b < n-1) {
                        bForBound=b;
                        b++;

                    } else {
                        aForBound=a;
                        a++;
                        b = 0;
                        if(a==n)
                            break outer;
                    }
                }
            }
        }

        //Input either '2' or '4' in empty cells
        Text text;
        Random random = new Random();
        boolean putTwo = true;
        if (random.nextInt() % 2 == 0)
            putTwo = false;
        int xCell, yCell;
            xCell = random.nextInt(aForBound+1);
            yCell = random.nextInt(bForBound+1);
        if (putTwo) {
            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
        } else {
            text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
        }
    }
    //Check if game scene has empty cell
    private int  haveEmptyCell() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0)
                    return 1;
                if(cells[i][j].getNumber() == 2048)
                    return 0;
            }
        }
        return -1;
    }

    private int passDestination(int i, int j, char direct) {
        int coordinate = j;
        if (direct == 'l') {
            for (int k = j - 1; k >= 0; k--) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        coordinate = j;
        if (direct == 'r') {
            for (int k = j + 1; k <= n - 1; k++) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k - 1;
                    break;
                } else if (k == n - 1) {
                    coordinate = n - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        if (direct == 'd') {
            for (int k = i + 1; k <= n - 1; k++) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k - 1;
                    break;

                } else if (k == n - 1) {
                    coordinate = n - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        if (direct == 'u') {
            for (int k = i - 1; k >= 0; k--) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        return -1;
    }

    private void moveUp() {
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                moveVertically(i, j, passDestination(i, j, 'u'), -1,"vertical");
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }

    }
    private void moveDown() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                moveVertically(i, j, passDestination(i, j, 'd'), 1,"vertical");
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }

    }
    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0)
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0) {
                return true;
            }
        return false;
    }
    private void moveVertically(int i, int j, int des, int sign,String moveVariable) {
        if (isValidDesV(i, j, des, sign)) {
            moveVariable="vertical";
            sumCellNumbersToScore(i,j,des,sign,moveVariable);
        } else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);
        }
    }

    private void moveLeft() {
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                moveHorizontally(i, j, passDestination(i, j, 'l'), -1,"horizontal");
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }
    private void moveRight() {
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                moveHorizontally(i, j, passDestination(i, j, 'r'), 1,"horizontal");
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }
    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < n && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }
    private void moveHorizontally(int i, int j, int des, int sign, String moveVariable) {
        if (isValidDesH(i, j, des, sign)) {
            moveVariable="horizontal";
            sumCellNumbersToScore(i,j,des,sign,moveVariable);
        } else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);
        }
    }

    //If it has same number near
    private boolean haveSameNumberNearly(int i, int j) {
        if (i < n - 1 && j < n - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }
        return false;
    }
    //If no empty cells set condition to end
    private boolean canNotMove() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (haveSameNumberNearly(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    //Adds up score
    private void sumCellNumbersToScore(int i, int j, int des, int sign, String moveVariable) {

        //if move vertically
        if (moveVariable.equals("vertical")){
            score+=cells[i][j].adder(cells[des + sign][j]);
            cells[des][j].setModify(true);
        }
        //if move horizontally
        else if (moveVariable.equals("horizontal")) {
            score += cells[i][j].adder(cells[i][des + sign]);
            cells[i][des].setModify(true);
        }
    }

    void game(Stage primaryStage,Scene gameScene, Group root, Scene endGameScene, Group endGameRoot) {
        this.root = root;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }

        }

        Text text = new Text();
        root.getChildren().add(text);
        text.setText("SCORE :");
        text.setFont(Font.font(30));
        text.relocate(750, 100);
        Text scoreText = new Text();
        root.getChildren().add(scoreText);
        scoreText.relocate(750, 150);
        scoreText.setFont(Font.font(20));
        scoreText.setText("0");

        // Initially spawns 2 cells of either 2 or 4
        randomFillNumber(1);
        randomFillNumber(1);

        // When a key is pressed, move the cell in tile
        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key ->{
                Platform.runLater(() -> {
                    int haveEmptyCell;
                    if (key.getCode() == KeyCode.DOWN) {
                        GameScene.this.moveDown();
                    } else if (key.getCode() == KeyCode.UP) {
                        GameScene.this.moveUp();
                    } else if (key.getCode() == KeyCode.LEFT) {
                        GameScene.this.moveLeft();
                    } else if (key.getCode() == KeyCode.RIGHT) {
                        GameScene.this.moveRight();
                    }

//
                    scoreText.setText(score + "");

                    //If there is no empty tile, and cannot move
                    haveEmptyCell = GameScene.this.haveEmptyCell();
                    if (haveEmptyCell == -1 && GameScene.this.canNotMove()) {
                        primaryStage.setScene(endGameScene);
                        EndGame.getInstance().endGameShow(primaryStage, endGameScene, endGameRoot, score);
                        root.getChildren().clear();
                        score = 0;
                    }

                    //If there is empty tile and can move, goto next move and spawns a single tile
                    else if(haveEmptyCell == 1)
                        GameScene.this.randomFillNumber(2);
                });
        });
    }
}
