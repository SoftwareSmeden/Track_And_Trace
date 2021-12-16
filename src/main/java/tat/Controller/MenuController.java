package tat.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MenuController {

    @FXML
    private TextField trackAndTraceNr;

    DBUtils dbUtils = new DBUtils();


    public void virksomhedKnap(ActionEvent event){
        dbUtils.skiftScene(event, "Medarbejder_Login_Scene.fxml");
    }

    public void kundeKnap(ActionEvent event){
        dbUtils.skiftScene(event, "Kunde_Login_Scene.fxml");
    }

    public void flytPakkeKnap(ActionEvent event){
        dbUtils.skiftScene(event, "Flyt_Pakke_Scene.fxml");
    }

    public void findPakke(){
        //send til login metode i DBUtils
    }

}
