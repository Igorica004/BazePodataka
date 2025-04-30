package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Termin {
    private Integer terminID;
    private Integer klijentID;
    private LocalDate datum;
    private LocalTime vreme;
    private String klijentIme;
    private String klijentPrezime;

    public Termin(Integer terminID, Integer klijentID, LocalDate datum, LocalTime vreme){
        this.terminID = terminID;
        this.klijentID = klijentID;
        this.datum = datum;
        this.vreme = vreme;

        //treba pozvati sql upit koji ce uzeti ime i prezime klijenta na osnovu njegovog ID-ja

    }
}
