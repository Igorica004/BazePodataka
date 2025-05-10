package model;

import java.sql.Date;
import java.time.LocalDate;

public class Klijent {
    private Integer klijentID;
    private String ime;
    private String prezime;
    private Date datum;
    private String pol;
    private String email;
    private String telefon;
    private String opisProblema;
    private Integer prvaTerapija;
    private Integer psihoterapeut_id;

    // Konstruktor
    public Klijent(int klijentID, String ime, String prezime,
                   Date datum, String pol, String email,
                   String telefon, String opisProblema,
                   Integer prvaTerapija) {
        this.klijentID = klijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.datum = datum;
        this.pol = pol;
        this.email = email;
        this.telefon = telefon;
        this.opisProblema = opisProblema;
        this.prvaTerapija = prvaTerapija;
    }

    public Klijent( String ime, String prezime,
                   Date datum, String pol, String email,
                   String telefon, String opisProblema,
                   Integer prvaTerapija,Integer psihoterapeut_id) {

        this.ime = ime;
        this.prezime = prezime;
        this.datum = datum;
        this.pol = pol;
        this.email = email;
        this.telefon = telefon;
        this.opisProblema = opisProblema;
        this.prvaTerapija = prvaTerapija;
        this.psihoterapeut_id = psihoterapeut_id;
    }

    public Integer getPsihoterapeut_id() {
        return psihoterapeut_id;
    }

    public void setPsihoterapeut_id(Integer psihoterapeut_id) {
        this.psihoterapeut_id = psihoterapeut_id;
    }

    // Getteri i setteri
    public Integer getKlijentID() {
        return klijentID;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public Date getDatum() {
        return datum;
    }

    public String getPol() {
        return pol;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getOpisProblema() {
        return opisProblema;
    }

    public Integer getPrvaTerapija() {
        return prvaTerapija;
    }

    public void setKlijentID(Integer klijentID) {
        this.klijentID = klijentID;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setOpisProblema(String opisProblema) {
        this.opisProblema = opisProblema;
    }

    public void setPrvaTerapija(Integer prvaTerapija) {
        this.prvaTerapija = prvaTerapija;
    }


    @Override
    public String toString() {
        return ime+" "+prezime;
    }


}
