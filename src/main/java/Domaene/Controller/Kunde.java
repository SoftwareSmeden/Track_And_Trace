package Domaene.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Domaene.DB.DBUtils;
import Domaene.Skabeloner.PakkeLabel;
import java.sql.SQLException;
import java.util.ArrayList;

public class Kunde {

    //KundeLoginScene
    @FXML
    private TextField tatNr;
    @FXML
    private Label forkertIndtastning;

    //KundePakkeInfoScene
    @FXML
    private Label pakkeStatus_1, pakkeStatus_2, pakkeStatus_3, pakkeStatus_4, pakkeStatus_5, pakkeStatus_6, pakkeStatus_7, pakkeStatus_8, pakkeStatus_9, pakkeStatus_10, pakkeStatus_11, pakkeStatus_12;
    private DBUtils dbUtils = new DBUtils();
    private SceneSkift skift = new SceneSkift();

    public void tilbageKnap(ActionEvent event){
        skift.skiftScene(event, "Menu_Scene.fxml");
    }

    public void findPakkeKnap(ActionEvent event){
        if (!(tatNr.getText().trim().isEmpty())){
            try {
                dbUtils.login(event,tatNr.getText(),2);
                forkertIndtastning.setText("Track and trace id findes ikke");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            forkertIndtastning.setText("Indtast track and trace nummer");
        }
    }

    public void afslutKnap(ActionEvent event){
        skift.skiftScene(event,"Kunde_Login_Scene.fxml");
    }

    //Kunde pakke-info Scene
    public void pakkeInfo(ArrayList<PakkeLabel> liste){
        Label[] labelsVenstreSide = {pakkeStatus_1, pakkeStatus_2, pakkeStatus_3, pakkeStatus_4, pakkeStatus_5, pakkeStatus_6};
        Label[] labelsHoejreSide = {pakkeStatus_7, pakkeStatus_8, pakkeStatus_9, pakkeStatus_10, pakkeStatus_11, pakkeStatus_12};
        for (int i = 0; i < liste.size(); i++) {
            labelsVenstreSide[i].setText(liste.get(i).getTi().getAdresse());
            labelsHoejreSide[i].setText(liste.get(i).getTi().getDato() + " " + liste.get(i).getTi().getTid());
        }
    }
}
