package model;

import utility.JDBCUtils;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Termin {
    private Integer terminID;
    private Integer klijentID;
    private Integer psihoterapeutID;
    private Date datum;
    private Time vreme;
    private String klijentIme;
    private String klijentPrezime;
    private int seansaID;
    public Termin(Integer psihoterapeutID,Integer seansaID,Date datum, Time vreme){
        this.psihoterapeutID = psihoterapeutID;
        this.seansaID = seansaID;
        this.datum = datum;
        this.vreme = vreme;
        this.klijentIme = JDBCUtils.getKlijentBySeansaId(seansaID).getIme();
        this.klijentPrezime = JDBCUtils.getKlijentBySeansaId(seansaID).getPrezime();
        //treba pozvati sql upit koji ce uzeti ime i prezime klijenta na osnovu njegovog ID-ja

    }

    public Integer getTerminID() {
        return terminID;
    }

    public void setTerminID(Integer terminID) {
        this.terminID = terminID;
    }

    public Integer getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(Integer klijentID) {
        this.klijentID = klijentID;
    }

    public Integer getPsihoterapeutID() {
        return psihoterapeutID;
    }

    public void setPsihoterapeutID(Integer psihoterapeutID) {
        this.psihoterapeutID = psihoterapeutID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Time getVreme() {
        return vreme;
    }

    public void setVreme(Time vreme) {
        this.vreme = vreme;
    }

    public String getKlijentIme() {
        return klijentIme;
    }

    public void setKlijentIme(String klijentIme) {
        this.klijentIme = klijentIme;
    }

    public String getKlijentPrezime() {
        return klijentPrezime;
    }

    public void setKlijentPrezime(String klijentPrezime) {
        this.klijentPrezime = klijentPrezime;
    }

    public int getSeansaID() {
        return seansaID;
    }

    public void setSeansaID(int seansaID) {
        this.seansaID = seansaID;
    }
}
