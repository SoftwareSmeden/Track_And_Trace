package tat.Skabeloner;

public class Virksomhed {

    private String firmanavn;
    private String adresse;
    private String postnummer;
    private String by;
    private String telefonNr;
    private String CVR;

    public Virksomhed() {
    }

    public Virksomhed(String firmanavn, String adresse, String telefonNr, String CVR) {
        this.firmanavn = firmanavn;
        this.adresse = adresse;
        this.telefonNr = telefonNr;
        this.CVR = CVR;
    }

    @Override
    public String toString() {
        return "Virksomhed{" +
                "firmanavn='" + firmanavn + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telefonNr='" + telefonNr + '\'' +
                ", CVR='" + CVR + '\'' +
                '}';
    }

    //Getters og setters
    public String getFirmanavn() {
        return firmanavn;
    }

    public void setFirmanavn(String firmanavn) {
        this.firmanavn = firmanavn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(String postnummer) {
        this.postnummer = postnummer;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getTelefonNr() {
        return telefonNr;
    }

    public void setTelefonNr(String telefonNr) {
        this.telefonNr = telefonNr;
    }

    public String getCVR() {
        return CVR;
    }

    public void setCVR(String CVR) {
        this.CVR = CVR;
    }

}
