package Domaene.Skabeloner;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class PakkeLabel {

    private String fragt;
    private Virksomhed virk = new Virksomhed();
    private TransportInfo ti = new TransportInfo();
    private Modtager modt = new Modtager();
    private LocalDate localDate = LocalDate.now();

    public PakkeLabel() {
    }

    public PakkeLabel(String fragt, Virksomhed virk, Modtager modt, TransportInfo ti) {
        this.fragt = fragt;
        this.virk = virk;
        this.modt = modt;
        this.ti = ti;
    }

    @Override
    public String toString() {
        return "PakkeLabel{" +
                "fragt='" + fragt + '\'' +
                ", virksomhed=" + virk +
                ", modtager=" + modt +
                '}';
    }

    //Udskriv pakkelabel til .txt fil
    public void opretLabel(ArrayList<PakkeLabel> pl) throws IOException {
        Random ran = new Random();
        int forsendelseID = ran.nextInt(165145454) + 565664613;
        int barcodeID = ran.nextInt(321231-52152) + 321535;
        int barcodeID2 = ran.nextInt(321231-52152) + 321535;

        File labelPrint = new File("Label.txt");
            if (labelPrint.createNewFile()){
                System.out.println("Label oprettet");
            }
        try {
            PrintWriter ud = new PrintWriter(new BufferedWriter(new FileWriter("label.txt")));
            ud.print("██╗      █████╗ ██████╗ ███████╗██╗     \n" +
                     "██║     ██╔══██╗██╔══██╗██╔════╝██║     \n" +
                     "██║     ███████║██████╔╝█████╗  ██║     \n" +
                     "██║     ██╔══██║██╔══██╗██╔══╝  ██║     \n" +
                     "███████╗██║  ██║██████╔╝███████╗███████╗\n" +
                     "╚══════╝╚═╝  ╚═╝╚═════╝ ╚══════╝╚══════╝-----------------");
            ud.format("\nFra:                                    Dato: "+localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                    " \n%s \n%s \n%s %s \n%s",
                    pl.get(0).getVirk().getFirmanavn(),
                    pl.get(0).getVirk().getAdresse(),
                    pl.get(0).getVirk().getPostnummer(),
                    pl.get(0).getVirk().getBy(),
                    pl.get(0).getVirk().getTelefonNr());
            ud.print("\n_________________________________________________________\n");

            ud.format("%s %s \n%s \n%s %s \n%s",
                    pl.get(0).getModt().getFornavn(),
                    pl.get(0).getModt().getEfternavn(),
                    pl.get(0).getModt().getAdresse(),
                    pl.get(0).getModt().getPostNr(),
                    pl.get(0).getModt().getBy(),
                    pl.get(0).getModt().getTelefonNr());
            ud.print("\n" +
                    " ▄▄▄▄▄▄▄ ▄ ▄▄▄ ▄▄▄▄▄▄▄ \n" +
                    " █ ▄▄▄ █ ▄▄▀█  █ ▄▄▄ █ \n" +
                    " █ ███ █ █▀ ▄▀ █ ███ █ \n" +
                    " █▄▄▄▄▄█ ▄▀█▀█ █▄▄▄▄▄█ \n" +
                    " ▄▄▄▄  ▄ ▄▄▄██▄  ▄▄▄ ▄ \n" +
                    " █ ▀ ▀▀▄█▄▀ █▀▀▀  ▀██▄ \n" +
                    " ▄▀██▄ ▄█▄█▄▀▄ ▀█▀ ▄██                 █║▌│█│║▌║││█║▌║▌║\n" +
                    " ▄▄▄▄▄▄▄ ▀▄▀█▄ ▀▄ ▀ ▄▄                 "+barcodeID2+"    \n" +
                    " █ ▄▄▄ █  ▀  ▄▄▄██▄▀▀▄ \n" +
                    " █ ███ █ █▄▄▀▄ ██▀▀▀▀                  ║█║▌║█║▌│║▌█║▌║  \n" +
                    " █▄▄▄▄▄█ █▄▀  █ ▀▄ ▀▄                  "+barcodeID+"    \n");
            ud.print("_________________________________________________________\n");
            ud.format("%s","Forsendelses ID: "+forsendelseID);
            ud.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFragt() {
        return fragt;
    }

    public void setFragt(String fragt) {
        this.fragt = fragt;
    }

    public Virksomhed getVirk() {
        return virk;
    }

    public Modtager getModt() {
        return modt;
    }

    public TransportInfo getTi() {return ti;}
}
