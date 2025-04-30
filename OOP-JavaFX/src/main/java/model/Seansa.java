package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Seansa {
    int seansaID;
    String dan;
    int trajanje;
    int cena_po_satu;
    LocalTime pocetak;
    LocalDate datum_cene;

    public Seansa(int seansaID, String dan, int trajanje, int cena_po_satu, LocalTime pocetak, LocalDate datum_cene) {
        this.seansaID = seansaID;
        this.dan = dan;
        this.trajanje = trajanje;
        this.cena_po_satu = cena_po_satu;
        this.pocetak = pocetak;
        this.datum_cene = datum_cene;
    }

    public LocalTime getPocetak() {
        return pocetak;
    }

    public void setPocetak(LocalTime pocetak) {
        this.pocetak = pocetak;
    }

    public int getSeansaID() {
        return seansaID;
    }

    public void setSeansaID(int seansaID) {
        this.seansaID = seansaID;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public int getCena_po_satu() {
        return cena_po_satu;
    }

    public void setCena_po_satu(int cena_po_satu) {
        this.cena_po_satu = cena_po_satu;
    }

    public LocalDate getDatum_cene() {
        return datum_cene;
    }

    public void setDatum_cene(LocalDate datum_cene) {
        this.datum_cene = datum_cene;
    }
}
