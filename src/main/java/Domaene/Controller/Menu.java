package Domaene.Controller;

import javafx.event.ActionEvent;

public class Menu {

    private SceneSkift skift = new SceneSkift();

    public void virksomhedKnap(ActionEvent event){
        skift.skiftScene(event, "Medarbejder_Login_Scene.fxml");
    }

    public void kundeKnap(ActionEvent event){
        skift.skiftScene(event, "Kunde_Login_Scene.fxml");
    }

    public void flytPakkeKnap(ActionEvent event){
        skift.skiftScene(event, "Flyt_Pakke_Scene.fxml");
    }
}
