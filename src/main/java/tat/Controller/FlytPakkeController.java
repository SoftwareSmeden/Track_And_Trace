package tat.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class FlytPakkeController {
    DBUtils dbUtils = new DBUtils();

    @FXML
    private TextField trackAndTraceNr;
    @FXML
    private Label pakkeStatus;
    @FXML
    private RadioButton postcentral_1, postcentral_2, postcentral_3, paaLastbil, leveret;

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
        dbUtils.skiftScene(event, "Menu_Scene.fxml");
    }
}