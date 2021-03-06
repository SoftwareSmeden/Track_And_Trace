package Domaene.DB;

import javafx.event.ActionEvent;
import Domaene.Controller.SceneSkift;
import Domaene.Skabeloner.PakkeLabel;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class DBUtils {

    private final String URL = "jdbc:sqlite:E://IntelliJ Projects/TrackAndTrace/src/main/java/database/TATDB.db";
    private SceneSkift skift = new SceneSkift();
    private ArrayList<PakkeLabel> liste = new ArrayList<>();

    public String login(ActionEvent event, String id, int valg) throws SQLException {
        String status = "";
        if (valg == 1) {
            String sql = "SELECT * FROM Virksomhed WHERE MedarbID = ?";
            Connection conn = DriverManager.getConnection(URL);
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, id);
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()) {
                skift.skiftScene(event,"Opret_Label_Scene.fxml");
            }else{
                status = "Medarbejder nummer findes ikke";
            }
            conn.close();
            preStmt.close();
            rs.close();

        } else if (valg == 2) {
            String sql = "SELECT * FROM Lokation WHERE TatID = ?";
            Connection conn = DriverManager.getConnection(URL);
            PreparedStatement preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, id);
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()) {
                String sqlPrint = "SELECT * FROM Lokation WHERE TatID =  "+id+"";
                Statement stmt = conn.createStatement();
                ResultSet resSet = stmt.executeQuery(sqlPrint);
                while(resSet.next()){
                    PakkeLabel pl = new PakkeLabel();
                    pl.getTi().setAdresse(resSet.getString("Adresse"));
                    pl.getTi().setDato(resSet.getString("Dato"));
                    pl.getTi().setTid(resSet.getString("Tid"));
                    liste.add(pl);
                }
                stmt.close();
                resSet.close();
                skift.skiftSceneListe(event,"Kunde_PakkeInfo_Scene.fxml",liste, 2);
            }
            conn.close();
            preStmt.close();
            rs.close();
        }
        return status;
    }

    //Der skrives til databasen samtidig med at der oprettes en liste, som sendes videre til en ny scene.
    public void labelTabelDB(ActionEvent event, String afFirmanavn, String afAdresse, String afPostnummer, String afBy, String afTelefon, String afCVR, String mtFornavn, String mtEfternavn, String mtAdresse, String mtPostnummer, String mtBy, String mtMobil, String mtEmail, String fragt) throws IOException, SQLException {
        String sqlLabel = "INSERT INTO Label (Af_Firmanavn, Af_Adresse, Af_Postnummer, Af_By, Af_Telefon, Af_CVR, Fragt, Mt_Fornavn, Mt_Efternavn, Mt_Adresse, Mt_Postnummer, Mt_By, Mt_Telefon, Mt_Email) VALUES ('"+afFirmanavn+"','"+afAdresse+"','"+afPostnummer+"','"+afBy+"','"+afTelefon+"','"+afCVR+"','"+fragt+"','"+mtFornavn+"','"+mtEfternavn+"','"+mtAdresse+"','"+mtPostnummer+"','"+mtBy+"','"+mtMobil+"','"+mtEmail+"')";
        String sqlTatID = "INSERT INTO Lokation (TatID) SELECT (TatID) FROM Label ORDER BY TatID DESC LIMIT 1";
        String sqlLokation = "UPDATE Lokation SET Adresse = 'Fem??vej 2 4700 N??stved', Dato = strftime('%d/%m/%Y','now'), Tid = time('now','localtime') WHERE TatID = (SELECT max(TatID) FROM Lokation)";
        try {
            Connection conn = DriverManager.getConnection(URL);
            Statement stmtLabel = conn.createStatement();
            Statement stmtTatID = conn.createStatement();
            Statement stmtLokation = conn.createStatement();
            stmtLabel.execute(sqlLabel);
            stmtTatID.execute(sqlTatID);
            stmtLokation.execute(sqlLokation);
            stmtLabel.close();
            stmtTatID.close();
            stmtLokation.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        PakkeLabel pl = new PakkeLabel();
        pl.getVirk().setFirmanavn(afFirmanavn);
        pl.getVirk().setAdresse(afAdresse);
        pl.getVirk().setPostnummer(afPostnummer);
        pl.getVirk().setBy(afBy);
        pl.getVirk().setTelefonNr(afTelefon);
        pl.getVirk().setCVR(afCVR);
        pl.setFragt(fragt);
        pl.getModt().setFornavn(mtFornavn);
        pl.getModt().setEfternavn(mtEfternavn);
        pl.getModt().setAdresse(mtAdresse);
        pl.getModt().setPostNr(mtPostnummer);
        pl.getModt().setBy(mtBy);
        pl.getModt().setTelefonNr(mtMobil);
        pl.getModt().setEmail(mtEmail);
        liste.add(pl);
        skift.skiftSceneListe(event, "PakkeLabel_Scene.fxml", liste,1);

        udskriv();
    }

    //Flyt pakke simulator funktionen
    public String flytPakke(String tatId, String lokation){
        String sqlCheckAll = "SELECT * FROM Lokation WHERE TatID = ? AND Adresse = ?";
        String sqlCheckID = "SELECT * FROM Lokation WHERE TatID = ?";
        String sqlIndsaet = "INSERT INTO Lokation (TatID, Adresse, Dato, Tid) VALUES ('"+tatId+"','"+lokation+"', strftime('%d/%m/%Y','now'), time('now', 'localtime'))";
        String pakkeStatus = "";
        try {
            Connection conn = DriverManager.getConnection(URL);
            PreparedStatement preStmt = conn.prepareStatement(sqlCheckAll);
            preStmt.setString(1, tatId);
            preStmt.setString(2, lokation);
            ResultSet rs = preStmt.executeQuery();
            if (!rs.next()) {
                PreparedStatement preStmtCheck = conn.prepareStatement(sqlCheckID);
                preStmtCheck.setString(1, tatId);
                ResultSet rsCheck = preStmtCheck.executeQuery();
                if (rsCheck.next()) {
                    Statement stmt = conn.createStatement();
                    stmt.execute(sqlIndsaet);
                    stmt.close();
                    pakkeStatus = "Pakken er flyttet";
                }else{
                    pakkeStatus = "Track and trace id findes ikke";
                }
            }else{
                pakkeStatus = "Pakke og lokation findes allerede i systemet";
            }
            conn.close();
            preStmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pakkeStatus;
    }

    //Track and trace ID printes i konsol ved oprettelse af ny label
    //Denne funktion er en del af simuleringen.
    public void udskriv() throws SQLException {
        String sql = "SELECT (TatID) FROM Label ORDER BY TatID DESC LIMIT 1";
        Connection conn = DriverManager.getConnection(URL);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()){
            System.out.println("Track and trace ID: " + rs.getString("TatID"));
        }
        conn.close();
        stmt.close();
        rs.close();
    }
}
