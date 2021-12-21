package Domaene.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import Domaene.DB.DBUtils;
import Domaene.Skabeloner.PakkeLabel;
import java.io.IOException;
import java.sql.SQLException;
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

    //Importeret fra JavaFX docs
    private Boolean tjekBogstaver(String text){
        return !text.matches("[0-9]+");
    }

    //Importeret fra JavaFX docs
    private Boolean tjekTal(String text, int laengde) {
        return text.matches("[0-9]+") && text.length() == laengde;
    }

    //Importeret fra JavaFX docs
    private Boolean tjekEmail(String text){
        String emailTjek = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        return text.matches(emailTjek);
    }

    public void opretLabelKnap(ActionEvent event) throws IOException, SQLException {
        String fragt = "";
        String tjekStatus = "Udfyld alle felter";
        if (gls.isSelected()){
            fragt = "GLS";
        }else if (postnord.isSelected()){
            fragt = "Postnord";
        }else if (afhentning.isSelected()){
            fragt = "Afhentning";
        }
        if (!(afsenderFirmanavn.getText().trim().isEmpty() || afsenderAdresse.getText().trim().isEmpty() || afsenderPostnummer.getText().trim().isEmpty())){
            if (tjekTal(afsenderPostnummer.getText(), 4)){
                if(!(afsenderBy.getText().trim().isEmpty() || afsenderTelefon.getText().trim().isEmpty())){
                    if(tjekTal(afsenderTelefon.getText(), 8)){
                        if(!afsenderCVR.getText().trim().isEmpty()){
                            if(tjekTal(afsenderCVR.getText(),10)){
                                if (!(modtagerFornavn.getText().trim().isEmpty())){
                                    if (tjekBogstaver(modtagerFornavn.getText())){
                                        if (!modtagerEfternavn.getText().trim().isEmpty()){
                                            if (tjekBogstaver(modtagerEfternavn.getText())){
                                                if (!(modtagerAdresse.getText().trim().isEmpty() || modtagerPostnummer.getText().trim().isEmpty())){
                                                    if (tjekTal(modtagerPostnummer.getText(),4)){
                                                        if (!modtagerBy.getText().trim().isEmpty()){
                                                            if (tjekBogstaver(modtagerBy.getText())){
                                                                if (!modtagerMobil.getText().trim().isEmpty()){
                                                                    if (tjekTal(modtagerMobil.getText(),8)){
                                                                        if (!modtagerEmail.getText().trim().isEmpty()){
                                                                            if (tjekEmail(modtagerEmail.getText())){
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
                                                                            }else{
                                                                                //Tjekker om email er gyldig
                                                                                tjekStatus = "Ugyldig email";
                                                                                forkertIndtastning.setText(tjekStatus);
                                                                            }
                                                                        }else{
                                                                            //Tjekker om feltet er tomt
                                                                            tjekStatus = "Udfyld email";
                                                                            forkertIndtastning.setText(tjekStatus);
                                                                        }
                                                                    }else{
                                                                        //Tjekker mobil for ugyldige tegn
                                                                        tjekStatus = "Mobilnummer må ikke indeholde bogstaver";
                                                                        forkertIndtastning.setText(tjekStatus);
                                                                    }
                                                                }else{
                                                                    //Tjekker om feltet er tomt
                                                                    tjekStatus = "Udfyld mobil";
                                                                    forkertIndtastning.setText(tjekStatus);
                                                                }
                                                            }else{
                                                                //Tjekker feltet for ugyldige tegn
                                                                tjekStatus = "By må ikke indeholde tal";
                                                                forkertIndtastning.setText(tjekStatus);
                                                            }
                                                        }else{
                                                            //Tjekker om feltet er tomt
                                                            tjekStatus = "Udfyld by";
                                                            forkertIndtastning.setText(tjekStatus);
                                                        }
                                                    }else{
                                                        //Tjekker postnummer for ugyldige tegn
                                                        tjekStatus = "Postnummer må ikke indeholde bogstaver";
                                                        forkertIndtastning.setText(tjekStatus);
                                                    }
                                                }else {
                                                    //Tjekker for tomme felter
                                                    tjekStatus = "Udfyld alle felter";
                                                    forkertIndtastning.setText(tjekStatus);
                                                }
                                            }else{
                                                //Tjekker efternavn for ugyldige tegn
                                                tjekStatus = "Efternavn må ikke indeholde tal";
                                                forkertIndtastning.setText(tjekStatus);
                                            }
                                        }else{
                                            //Tjekker om feltet er tomt
                                            tjekStatus = "Udfyld efternavn";
                                            forkertIndtastning.setText(tjekStatus);
                                        }
                                    }else{
                                        //Tjekker fornavn for ugyldige tegn
                                        tjekStatus = "Fornavn må ikke indeholde tal";
                                        forkertIndtastning.setText(tjekStatus);
                                    }
                                }else{
                                    //Tjekker om feltet er tomt
                                    tjekStatus = "Udfyld fornavn";
                                    forkertIndtastning.setText(tjekStatus);
                                }
                            }else{
                                //Tjekker CVR-nr
                                tjekStatus = "Ugyldigt CVR-nummer";
                                forkertIndtastning.setText(tjekStatus);
                            }
                        }else{
                            //Tjekker om CVR-nr. feltet er tomt
                            tjekStatus = "Udfyld CVR-nr";
                            forkertIndtastning.setText(tjekStatus);
                        }
                    }else{
                        //Tjekker tlf-nr.
                        tjekStatus = "Ugyldigt telefonnummer";
                        forkertIndtastning.setText(tjekStatus);
                    }
                }else{
                    //Tjekker om felter er tomme
                    tjekStatus = "Udfyld alle felter";
                    forkertIndtastning.setText(tjekStatus);
                }
            }else{
                //Tjekker postnummer
                tjekStatus = "Ugyldigt postnummer";
                forkertIndtastning.setText(tjekStatus);
            }
        }else {
            infoTekst.setText("");
            forkertIndtastning.setText(tjekStatus);
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

    //Denne knap tilhører PakkeLabel Scenen
    public void printKnap(ArrayList<PakkeLabel> liste) throws IOException {
        printKnap.setOnAction(event -> {
            try {
                System.out.println("Label skrives til .txt fil");
                pl.opretLabel(liste);
                skift.skiftScene(event,"Opret_Label_Scene.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
