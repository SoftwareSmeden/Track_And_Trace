package Domaene.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import Domaene.DB.DBUtils;

public class FlytPakke {

    @FXML
    private TextField trackAndTraceNr;
    @FXML
    private Label pakkeStatus;
    @FXML
    private RadioButton postcentral_1, postcentral_2, postcentral_3, paaLastbil, leveret;
    private DBUtils dbUtils = new DBUtils();
    private SceneSkift skift = new SceneSkift();

    public void flytPakkeKnap(){
        String lokation = "";
        if (postcentral_1.isSelected()){
            lokation = "Postcentral 1";
        }else if (postcentral_2.isSelected()){
            lokation = "Postcentral 2";
        }else if (postcentral_3.isSelected()){
            lokation = "Postcentral 3";
        }else if (paaLastbil.isSelected()){
            lokation = "På Lastbil";
        }else if (leveret.isSelected()){
            lokation = "Leveret";
        }
        if (!trackAndTraceNr.getText().trim().isEmpty()){
            pakkeStatus.setText(dbUtils.flytPakke(trackAndTraceNr.getText(),lokation));
        } else {
            pakkeStatus.setText("Udfyld felterne");
        }
    }

    public void tilbageKnap(ActionEvent event){
        skift.skiftScene(event, "Menu_Scene.fxml");
    }
}