module tat.trackandtrace {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens tat.Controller to javafx.fxml;
    exports tat.Controller;
    exports tat.Skabeloner;
    opens tat.Skabeloner to javafx.fxml;
    exports tat.DB;
    opens tat.DB to javafx.fxml;
}