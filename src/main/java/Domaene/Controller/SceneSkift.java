package Domaene.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Domaene.Skabeloner.PakkeLabel;
import java.io.IOException;
import java.util.ArrayList;

public class SceneSkift {

    //Almin. sceneskift uden dataflytning
    public void skiftScene(ActionEvent event, String fxmlFile) {
        Parent root = null;
        try {
            root = FXMLLoader.load(SceneSkift.class.getResource(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Sceneskift funktion, der flytter data fra en scene til en anden
    //Overfører listens information til næste scene
    public void skiftSceneListe(ActionEvent event, String fxmlFile, ArrayList<PakkeLabel> list, int controllerValg) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(SceneSkift.class.getResource(fxmlFile));
            root = loader.load();
            if(controllerValg == 1){
                Label lc = loader.getController();
                lc.visPakkeLabel(list);
                lc.printKnap(list);
            } else if(controllerValg == 2){
                Kunde kc = loader.getController();
                kc.pakkeInfo(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
