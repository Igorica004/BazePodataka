package model;

import java.time.LocalDate;

public class Klijent {
    private Integer klijentID;
    private String ime;
    private String prezime;
    private LocalDate datum;
    private String pol;
    private String email;
    private String telefon;
    private String opisProblema;
    private Integer prvaTerapija;

    // Konstruktor
    public Klijent(int klijentID, String ime, String prezime,
                   LocalDate datum, String pol, String email,
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

    public LocalDate getDatum() {
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
}
