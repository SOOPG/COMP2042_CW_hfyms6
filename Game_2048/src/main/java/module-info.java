/**
 * The game requires javafx to run
 * @author Soo Min Hao
 * @version 1.6
 * @since 1.0 (Initial Commit)
 */

//The game requires javafx to run
module Game{
    requires javafx.controls;
    requires javafx.fxml;

    opens Game to javafx.fxml;
    exports Game;
    exports Game.Menu;
    opens Game.Menu to javafx.fxml;
    exports Game.DataSaver;
    opens Game.DataSaver to javafx.fxml;
    exports Game.UserInterfaceDesigner;
    opens Game.UserInterfaceDesigner to javafx.fxml;
    exports Game.GameController;
    opens Game.GameController to javafx.fxml;
}