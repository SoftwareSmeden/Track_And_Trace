package Domaene.Skabeloner;

public class TransportInfo {

    private String adresse;
    private String dato;
    private String tid;

    public TransportInfo() {
    }

    public TransportInfo(String adresse, String dato, String tid) {
        this.adresse = adresse;
        this.dato = dato;
        this.tid = tid;
    }

    @Override
    public String toString() {
        return adresse + " " + dato + " " + tid;
    }

    //Getter og setters
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
