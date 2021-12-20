package Domaene.Controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class StartScene implements Initializable {

    @FXML
    private ImageView box;
    private SceneSkift skift = new SceneSkift();

    public void startKnap(ActionEvent event) {
        skift.skiftScene(event,"Menu_Scene.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TranslateTransition trans = new TranslateTransition();
        trans.setNode(box);
        trans.setDuration(Duration.millis(2500));
        trans.setCycleCount(TranslateTransition.INDEFINITE);
        trans.setByY(-20);
        trans.setAutoReverse(true);
        trans.play();
    }
}