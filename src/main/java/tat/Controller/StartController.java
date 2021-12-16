package tat.Controller;

import javafx.event.ActionEvent;

public class StartController {

    private DBUtils dbUtils = new DBUtils();

    public void startKnap(ActionEvent event) {
        dbUtils.skiftScene(event,"Menu_Scene.fxml");
    }
}