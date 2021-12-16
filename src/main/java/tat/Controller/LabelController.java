package tat.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import tat.Skabeloner.PakkeLabel;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;


public class LabelController {

    //Opret label Scene
    @FXML
    private TextField afsenderFirmanavn;
    @FXML
    private TextField afsenderAdresse;
    @FXML
    private TextField afsenderPostnummer;
    @FXML
    private TextField afsenderBy;
    @FXML
    private TextField afsenderTelefon;
    @FXML
    private TextField afsenderCVR;
    @FXML
    private RadioButton gls;
    @FXML
    private RadioButton postnord;
    @FXML
    private RadioButton afhentning;
    @FXML
    private TextField modtagerFornavn;
    @FXML
    private TextField modtagerEfternavn;
    @FXML
    private TextField modtagerAdresse;
    @FXML
    private TextField modtagerPostnummer;
    @FXML
    private TextField modtagerBy;
    @FXML
    private TextField modtagerMobil;
    @FXML
    private TextField modtagerEmail;
    @FXML
    private Label infoTekst;
    @FXML
    private Label forkertIndtastning;

    //PakkeLabel
    @FXML
    private Label virkNavn, virkAdresse, virkPostBy, virkTelefon, dato, mtNavn, mtAdresse, mtPostBy, mtTelefon, forsendelsesID;
    @FXML
    private Button printKnap;
    private final LocalDate localDate = LocalDate.now();

    private DBUtils dbUtils = new DBUtils();
    private PakkeLabel pl = new PakkeLabel();
    private Random ran = new Random();

    public void tilbageKnap(ActionEvent event){
        dbUtils.skiftScene(event, "Menu_Scene.fxml");
    }

    //Denne knap tilhører PakkeLabel Scenen
    public void printKnap(ArrayList<PakkeLabel> liste) throws IOException {
        printKnap.setOnAction(event -> {
            try {
                System.out.println("Label printes til .txt fil"); //TODO Konsol tjek
                pl.opretLabel(liste);
                dbUtils.skiftScene(event,"Opret_Label_Scene.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void opretLabelKnap(ActionEvent event) throws IOException {
        String fragt = "";
        if (gls.isSelected()){
            fragt = "GLS";
        }else if (postnord.isSelected()){
            fragt = "Postnord";
        }else if (afhentning.isSelected()){
            fragt = "Afhentning";
        }

        if (!(afsenderFirmanavn.getText().trim().isEmpty()      ||
                afsenderAdresse.getText().trim().isEmpty()      ||
                afsenderPostnummer.getText().trim().isEmpty()   ||
                afsenderBy.getText().trim().isEmpty()           ||
                afsenderTelefon.getText().trim().isEmpty()      ||
                afsenderCVR.getText().trim().isEmpty()          ||
                modtagerFornavn.getText().trim().isEmpty()      ||
                modtagerEfternavn.getText().trim().isEmpty()    ||
                modtagerAdresse.getText().trim().isEmpty()      ||
                modtagerPostnummer.getText().trim().isEmpty()   ||
                modtagerBy.getText().trim().isEmpty()           ||
                modtagerMobil.getText().trim().isEmpty()        ||
                modtagerEmail.getText().trim().isEmpty()))      {

                    dbUtils.labelTabelDB(event,
                    afsenderFirmanavn.getText(),
                    afsenderAdresse.getText(),
                    afsenderPostnummer.getText(),
                    afsenderBy.getText(),
                    afsenderTelefon.getText(),
                    afsenderCVR.getText(),
                    modtagerFornavn.getText(),
                    modtagerEfternavn.getText(),
                    modtagerAdresse.getText(),
                    modtagerPostnummer.getText(),
                    modtagerBy.getText(),
                    modtagerMobil.getText(),
                    modtagerEmail.getText(),
                    fragt);
                    forkertIndtastning.setText("");
                    infoTekst.setText("Label oprettet");
        }else {
            infoTekst.setText("");
            forkertIndtastning.setText("Udfyld alle felter");
        }
    }

    //PakkeLabel Scene
    public void visPakkeLabel(ArrayList<PakkeLabel> list){
        int forSendLabelID = ran.nextInt(165145454) + 565664613;
        virkNavn.setText(list.get(0).getVirk().getFirmanavn());
        virkAdresse.setText(list.get(0).getVirk().getAdresse());
        virkPostBy.setText(list.get(0).getVirk().getPostnummer() + " " + list.get(0).getVirk().getBy());
        virkTelefon.setText("Telefon: " + list.get(0).getVirk().getTelefonNr());
        dato.setText("Dato: " + localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        mtNavn.setText(list.get(0).getModt().getFornavn() + " " + list.get(0).getModt().getEfternavn());
        mtAdresse.setText(list.get(0).getModt().getAdresse());
        mtPostBy.setText(list.get(0).getModt().getPostNr() + " " + list.get(0).getModt().getBy());
        mtTelefon.setText(list.get(0).getModt().getTelefonNr());
        forsendelsesID.setText(String.valueOf(forSendLabelID));
    }
}
