package tat.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import tat.DB.DBUtils;
import tat.Skabeloner.PakkeLabel;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;


public class Label {

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
    private javafx.scene.control.Label infoTekst;
    @FXML
    private javafx.scene.control.Label forkertIndtastning;

    //PakkeLabel
    @FXML
    private javafx.scene.control.Label virkNavn, virkAdresse, virkPostBy, virkTelefon, dato, mtNavn, mtAdresse, mtPostBy, mtTelefon, forsendelsesID, stregkode;
    @FXML
    private Button printKnap;
    @FXML
    private ImageView glsLogo, postnordLogo, afhentLogo;
    private final LocalDate localDate = LocalDate.now();
    private DBUtils dbUtils = new DBUtils();
    private SceneSkift skift = new SceneSkift();
    private PakkeLabel pl = new PakkeLabel();
    private Random ran = new Random();

    public void tilbageKnap(ActionEvent event){
        skift.skiftScene(event, "Menu_Scene.fxml");
    }

    //Denne knap tilhører PakkeLabel Scenen
    public void printKnap(ArrayList<PakkeLabel> liste) throws IOException {
        printKnap.setOnAction(event -> {
            try {
                System.out.println("Label printes til .txt fil"); //TODO Konsol tjek
                pl.opretLabel(liste);
                skift.skiftScene(event,"Opret_Label_Scene.fxml");
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
    public void visPakkeLabel(ArrayList<PakkeLabel> liste){
        int forSendLabelID = ran.nextInt(165145454) + 565664613;
        int stregkodeID = ran.nextInt(91231233) + 565664613;
        if (liste.get(0).getFragt().equals("GLS")){
            glsLogo.setVisible(true);
        }else if (liste.get(0).getFragt().equals("Postnord")){
            postnordLogo.setVisible(true);
        }else if (liste.get(0).getFragt().equals("Afhentning")){
            afhentLogo.setVisible(true);
        }
        virkNavn.setText(liste.get(0).getVirk().getFirmanavn());
        virkAdresse.setText(liste.get(0).getVirk().getAdresse());
        virkPostBy.setText(liste.get(0).getVirk().getPostnummer() + " " + liste.get(0).getVirk().getBy());
        virkTelefon.setText("Telefon: " + liste.get(0).getVirk().getTelefonNr());
        dato.setText("Dato: " + localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        mtNavn.setText(liste.get(0).getModt().getFornavn() + " " + liste.get(0).getModt().getEfternavn());
        mtAdresse.setText(liste.get(0).getModt().getAdresse());
        mtPostBy.setText(liste.get(0).getModt().getPostNr() + " " + liste.get(0).getModt().getBy());
        mtTelefon.setText(liste.get(0).getModt().getTelefonNr());
        forsendelsesID.setText(String.valueOf(forSendLabelID));
        stregkode.setText(String.valueOf(stregkodeID));
    }
}
