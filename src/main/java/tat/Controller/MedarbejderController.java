package tat.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class MedarbejderController {

    @FXML
    private TextField medarbejderNr;
    @FXML
    private Label forkertLogin;
    private DBUtils dbUtils = new DBUtils();

    public void tilbageKnap(ActionEvent event){
        dbUtils.skiftScene(event, "Menu_Scene.fxml");
    }

    public void loginKnap(ActionEvent event){
        if (!(medarbejderNr.getText().trim().isEmpty())){
            try {
                forkertLogin.setText(dbUtils.login(event, medarbejderNr.getText(),1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            forkertLogin.setText("Udfyld feltet");
        }
    }
}
