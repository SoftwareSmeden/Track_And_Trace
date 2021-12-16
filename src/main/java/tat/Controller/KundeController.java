package tat.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tat.Skabeloner.PakkeLabel;
import java.sql.SQLException;
import java.util.ArrayList;

public class KundeController {

    //KundeLoginScene
    @FXML
    private TextField tatNr;
    @FXML
    private Label forkertIndtastning;

    //KundePakkeInfoScene
    @FXML
    private Label pakkeStart, mellemstation1, mellemstation2, mellemstation3, pakkePaaVej, pakkeLeveret;
    private DBUtils dbUtils = new DBUtils();

    public void tilbageKnap(ActionEvent event){
        dbUtils.skiftScene(event, "Menu_Scene.fxml");
    }

    public void findPakkeKnap(ActionEvent event){
        if (!(tatNr.getText().trim().isEmpty())){
            try {
                dbUtils.login(event,tatNr.getText(),2);
                forkertIndtastning.setText("Pakken findes ikke");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            forkertIndtastning.setText("Indtast track and trace nummer");
        }
    }

    public void afslutKnap(ActionEvent event){
        dbUtils.skiftScene(event,"Kunde_Login_Scene.fxml");
    }

    public void pakkeInfo(ArrayList<PakkeLabel> liste){
        Label[] labels = {pakkeStart, mellemstation1, mellemstation2, mellemstation3, pakkePaaVej, pakkeLeveret};
        for (int i = 0; i < liste.size(); i++) {
            labels[i].setText(liste.get(i).getTi().toString());
        }
    }
}
