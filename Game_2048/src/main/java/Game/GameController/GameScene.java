package Game.GameController;

import Game.UserInterfaceDesigner.TextMaker;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

/**
 * This class is responsible for moving cells
 * while also serves as the main gameplay scene
 * that player plays 2048 game in this scene
 *
 * @version 1.6.1
 * @since 1.0 (Initial Commit)
 */

public class GameScene {
    private static int HEIGHT = 700;

    //Total Number of Cells for 2 axises (X and Y)
    public static int grid;
    private static boolean isMoved = false;

    /**
     * THis method sets the number of grids (one axis) in the game based on
     * the difficulty selected by the player
     *
     * @param number the number of grids (in one axis)
     * @since 1.0 (Initial Commit)
     */

    //How many Cells
    public static void setGrid(int number) {
        grid = number;
        LENGTH = (HEIGHT - ((grid + 1) * distanceBetweenCells)) / (double) grid;
    }

    //Distance btw Cells
    private final static int distanceBetweenCells = 10;

    private static double LENGTH = (HEIGHT - ((grid + 1) * distanceBetweenCells)) / (double) grid;
    private TextMaker textMaker = TextMaker.getSingleInstance();
    private Cell[][] cells = new Cell[grid][grid];
    private Group root;

    //Sum of Score of Current Game Session
    public long score = 0;

    //Parameter to check what type of movement it is to add score
    public String moveVariable;

    /**
     * This method changes the size of cells in gameScene
     * @return the size of length of cell
     * @since 1.0 (Initial Commit)
     */

     public static double getLENGTH() {
        return LENGTH;
    }

    /**
     * This method spawns a random cell tile in the empty cell grid
     * @param turn if the turn is 1;
     * it means that game has just started,
     * initially spawns two cell tiles in an empty cell grid
     * else only spawn a cell randomly in an empty cell grid
     * @since 1.0 (Initial Commit)
     */

    // Fills in a number either 2 or 4 each turn in random locations
    // Within the cell that is not occupied by a number cell
    private void randomFillNumber(int turn) {

        // why a,b
        Cell[][] emptyCells = new Cell[grid][grid];
        int a = 0;
        int b = 0;
        int aForBound=0,bForBound=0;
        outer:
        for (int i = 0; i < grid; i++) {
            for (int j = 0; j < grid; j++) {
                if (cells[i][j].getNumber() == 0) {
                    emptyCells[a][b] = cells[i][j];
                    if (b < grid -1) {
                        bForBound=b;
                        b++;

                    } else {
                        aForBound=a;
                        a++;
                        b = 0;
                        if(a== grid)
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

    /**
     * This method checks if there is no more empty cell tile in the grid
     *
     * @return the gamestate where
     * 1 is game still in progress
     * 0 is the game is won
     * -1 is when there is no more empty cell in grid and game is considered (lost)
     * @since 1.0 (Initial Commit)
     */

    //Check if game scene has empty cell
    private int haveEmptyCell() {
        for (int i = 0; i < grid; i++) {
            for (int j = 0; j < grid; j++) {
                if (cells[i][j].getNumber() == 0)
                    return 1;
                if(cells[i][j].getNumber() == 2048){
                    return 0;
                }
            }
        }
        return -1;
    }

    /**
     * This method is invoked to move the cells to a destination which has empty cells
     * based on the direction of movement performed by player
     * @param i the x-coordinate of cell tile
     * @param j the y-axis coordinate of cell tile
     * @param direct the direction of movement
     *
     * @return the coordinate if it is a valid move (up,down,left,right)
     * @since 1.0 (Initial Commit)
     */

    private int passDestination(int i, int j, char direct) {
        int coordinate = j;
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
        coordinate = i;
        if (direct == 'd') {
            for (int k = i + 1; k <= grid - 1; k++) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k - 1;
                    break;

                } else if (k == grid - 1) {
                    coordinate = grid - 1;
                }
            }
            return coordinate;
        }
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
            for (int k = j + 1; k <= grid - 1; k++) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k - 1;
                    break;
                } else if (k == grid - 1) {
                    coordinate = grid - 1;
                }
            }
            return coordinate;
        }
        return -1;
    }

    /**
     * This method is called to move the movement of tile up
     * when the player performs a move up movement
     * @since 1.0 (Initial Commit)
     */

    private void moveUp() {
        for (int j = 0; j < grid; j++) {
            for (int i = 1; i < grid; i++) {
                moveVertically(i, j, passDestination(i, j, 'u'), -1);
            }
            for (int i = 0; i < grid; i++) {
                cells[i][j].setModify(false);
            }
        }

    }

    /**
     * This method is called to notify the movement of tile down
     * when the player performs a move down movement
     * @since 1.0 (Initial Commit)
     */

    private void moveDown() {
        for (int j = 0; j < grid; j++) {
            for (int i = grid - 1; i >= 0; i--) {
                moveVertically(i, j, passDestination(i, j, 'd'), 1);
            }
            for (int i = 0; i < grid; i++) {
                cells[i][j].setModify(false);
            }
        }

    }

    /**
     * This method is called to check
     * if the vertical movement(up,down)
     * is a valid move or not
     * invalid movement includes:
     * at border of grid, destination grid is occupied by another cell
     *
     * @param i x-coordinate of cell
     * @param j y-coordinate of cell
     * @param des the destination cell where the cell will be moved to
     * @param sign the direction the grid is moving
     *
     * @return true if is a valid move
     * false if it is invalid move
     */

    private boolean isValidDesV(int i, int j, int des, int sign) {
        if (des + sign < grid && des + sign >= 0)
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0) {
                return true;
            }
        return false;
    }

    /**
     * This method is called to move
     * the tiles vertically
     * if is a valid move
     *calls the method to add the sum of two values of the cell
     *
     * @param i x-coordinate of cell
     * @param j y-coordinate of cell
     * @param des the destination cell where the cell will be moved to
     * @param sign the direction the grid is moving
     */

    private void moveVertically(int i, int j, int des, int sign) {
        if (isValidDesV(i, j, des, sign)) {
            moveVariable="vertical";
            sumCellNumbersToScore(i,j,des,sign,moveVariable);
        } else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);
            if (cells[des][j].getNumber() != 0)
                isMoved = true;
        }
    }

    /**
     * This method is called to notify the movement of tile left
     * when the player performs a move left movement
     * @since 1.0 (Initial Commit)
     */

    private void moveLeft() {
        for (int i = 0; i < grid; i++) {
            for (int j = 1; j < grid; j++) {
                moveHorizontally(i, j, passDestination(i, j, 'l'), -1);
            }
            for (int j = 0; j < grid; j++) {
                cells[i][j].setModify(false);
            }
        }
    }

    /**
     * This method is called to notify the movement of tile right
     * when the player performs a move right movement
     * @since 1.0 (Initial Commit)
     */

    private void moveRight() {
        for (int i = 0; i < grid; i++) {
            for (int j = grid - 1; j >= 0; j--) {
                moveHorizontally(i, j, passDestination(i, j, 'r'), 1);
            }
            for (int j = 0; j < grid; j++) {
                cells[i][j].setModify(false);
            }
        }
    }

    /**
     * This method is called to check
     * if the vertical movement(up,down)
     * is a valid move or not
     * invalid movement includes:
     * at border of grid, destination grid is occupied by another cell
     *
     * @param i x-coordinate of cell
     * @param j y-coordinate of cell
     * @param des the destination cell where the cell will be moved to
     * @param sign the direction the grid is moving
     *
     * @return true if is a valid move
     * false if it is invalid move
     * @since 1.0 (Initial Commit)
     */

    private boolean isValidDesH(int i, int j, int des, int sign) {
        if (des + sign < grid && des + sign >= 0) {
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is called to move
     * the tiles horizontally
     * if is a valid move
     *calls the method to add the sum of two values of the cell
     *
     * @param i x-coordinate of cell
     * @param j y-coordinate of cell
     * @param des the destination cell where the cell will be moved to
     * @param sign the direction the grid is moving
     *
     * @since 1.0 (Initial Commit)
     */

    private void moveHorizontally(int i, int j, int des, int sign) {
        if (isValidDesH(i, j, des, sign)) {
            moveVariable="horizontal";
            sumCellNumbersToScore(i,j,des,sign,moveVariable);
        } else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);
            if (cells[i][des].getNumber() != 0)
                isMoved = true;
        }
    }

    /**
     * Check if there is same number next to each other
     * @param i x-coordinate of cell
     * @param j y-coordinate of cell
     * @return true if the cells are near
     * else false
     *
     * @since 1.0 (Initial Commit)
     */

    //If it has same number near
    private boolean haveSameNumberNearly(int i, int j) {
        if (i < grid - 1 && j < grid - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            if (cells[i][j + 1].getNumber() == cells[i][j].getNumber())
                return true;
        }
        return false;
    }

    /**
     * this method is called to check if there is no valid move in the grid
     *
     * @return true (game is lost) if there is no valid movement left
     * @since 1.0 (Initial Commit)
     */

    //If no empty cells set condition to end
    private boolean canNotMove() {
        for (int i = 0; i < grid; i++) {
            for (int j = 0; j < grid; j++) {
                if (haveSameNumberNearly(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *This method is called to sum the values of two same value cells
     *and add them to the total score
     * based on vertical and horizontal summing
     *
     * @param i x-coordinate of cell
     * @param j y-coordinate of cell
     * @param des the destination cell where the cell will be moved to
     * @param sign the direction the grid is moving
     *
     * @param moveVariable is passed to c
     * @since 1.0 (Initial Commit)
     */

    //Adds up score
    private void sumCellNumbersToScore(int i, int j, int des, int sign, String moveVariable) {

        //if move vertically
        if (moveVariable.equals("vertical")){
            score +=cells[i][j].adder(cells[des + sign][j]);
            cells[des+sign][j].setModify(true);
        }
        //if move horizontally
        else if (moveVariable.equals("horizontal")) {
            score += cells[i][j].adder(cells[i][des + sign]);
            cells[i][des+sign].setModify(true);
        }
        isMoved = true;
    }

    /**
     *
     * @param primaryStage set the gameScene ui in stage when player is in leaderboard menu scene
     * @param gameScene get the UI of game scene from GameSceneLoader
     * @param root change the UI to game scene ui when the scene is in gameplay scene
     * @param endGameScene change scene to end game scene if the player loses/ wins the game
     * @param endGameRoot add the UI of end game scene when the scene is in end game
     *
     * @since 1.0 (Initial Commit)
     */

    public void game(Stage primaryStage,Scene gameScene, Group root, Scene endGameScene, Group endGameRoot) {
        this.root = root;
        for (int i = 0; i < grid; i++) {
            for (int j = 0; j < grid; j++) {
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

                    if (key.getCode() == KeyCode.UP) {
                        GameScene.this.moveUp();
                    } else if(key.getCode() == KeyCode.DOWN) {
                        GameScene.this.moveDown();
                    } else if (key.getCode() == KeyCode.LEFT) {
                        GameScene.this.moveLeft();
                    } else if (key.getCode() == KeyCode.RIGHT) {
                        GameScene.this.moveRight();
                    }

                    scoreText.setText(score + "");

                    //If there is no empty tile, and cannot move
                    haveEmptyCell = GameScene.this.haveEmptyCell();
                    if (haveEmptyCell == -1 && GameScene.this.canNotMove()) {
                        primaryStage.setScene(endGameScene);
                        EndGame.getInstance().endGameShow(primaryStage, endGameScene, endGameRoot, score);
                        root.getChildren().clear();
                        score = 0;
                    }

                    //If there is empty tile(can move) and arrow key is pressed, spawn a cell
                    else if(haveEmptyCell == 1 && (key.getCode() == KeyCode.UP || (key.getCode() == KeyCode.DOWN) || (key.getCode() == KeyCode.LEFT) || (key.getCode() == KeyCode.RIGHT)) && isMoved) {
                        GameScene.this.randomFillNumber(2);
                        isMoved = false;
                    }
                });
        });
    }
}
