module tat.trackandtrace {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens Domaene.Controller to javafx.fxml;
    exports Domaene.Controller;
    exports Domaene.Skabeloner;
    opens Domaene.Skabeloner to javafx.fxml;
    exports Domaene.DB;
    opens Domaene.DB to javafx.fxml;
}