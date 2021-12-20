package Domaene.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Domaene.DB.DBUtils;

import java.sql.SQLException;

public class Medarbejder {

    @FXML
    private TextField medarbejderNr;
    @FXML
    private Label forkertLogin;
    private DBUtils dbUtils = new DBUtils();
    private SceneSkift skift = new SceneSkift();

    public void tilbageKnap(ActionEvent event){
        skift.skiftScene(event, "Menu_Scene.fxml");
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
