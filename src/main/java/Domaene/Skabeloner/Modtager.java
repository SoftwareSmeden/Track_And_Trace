package Domaene.Skabeloner;

public class Modtager {

    private String fornavn;
    private String efternavn;
    private String adresse;
    private String postNr;
    private String by;
    private String telefonNr;
    private String email;

    public Modtager() {
    }

    public Modtager(String fornavn, String efternavn, String adresse, String postNr, String by, String email, String telefonNr) {
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.adresse = adresse;
        this.postNr = postNr;
        this.by = by;
        this.email = email;
        this.telefonNr = telefonNr;
    }

    @Override
    public String toString() {
        return "Modtager{" +
                "fornavn='" + fornavn + '\'' +
                ", efternavn='" + efternavn + '\'' +
                ", adresse='" + adresse + '\'' +
                ", postNr='" + postNr + '\'' +
                ", by='" + by + '\'' +
                ", telefonNr='" + telefonNr + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    //Getter og setters
    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPostNr() {
        return postNr;
    }

    public void setPostNr(String postNr) {
        this.postNr = postNr;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonNr() {
        return telefonNr;
    }

    public void setTelefonNr(String telefonNr) {
        this.telefonNr = telefonNr;
    }
}
