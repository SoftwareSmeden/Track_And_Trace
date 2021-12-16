package tat.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.SQLException;

public class FlytPakkeController {
    DBUtils dbUtils = new DBUtils();

    @FXML
    private TextField trackAndTraceNr;
    @FXML
    private TextField indtastLokation;
    @FXML
    private Label findesIkke;
    
    public void flytPakkeKnap(ActionEvent event){
        if (!(trackAndTraceNr.getText().trim().isEmpty())){
            try {
                dbUtils.login(event, trackAndTraceNr.getText(),3);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void tilbageKnap(ActionEvent event){
        dbUtils.skiftScene(event, "Menu_Scene.fxml");
    }


}