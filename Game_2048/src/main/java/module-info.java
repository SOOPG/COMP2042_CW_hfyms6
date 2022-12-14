/**
 * The game requires javafx to run
 * @author Soo Min Hao
 * @version 1.6
 * @since 1.0 (Initial Commit)
 */

//The game requires javafx to run
module game{
    requires javafx.controls;
    requires javafx.fxml;

    opens game to javafx.fxml;
    exports game;
}