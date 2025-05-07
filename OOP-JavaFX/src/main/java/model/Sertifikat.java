package model;

import utility.JDBCUtils;

import java.sql.Date;

public class Sertifikat {
    Integer sertifikat_id;
    Date datum;
    Integer psihoterapeut_id;
    Integer oblast_psihoterapije_id;
    String oblast_psihoterapije_naziv;

    public Sertifikat(Integer sertifikat_id, Date datum, Integer psihoterapeut_id, Integer oblast_psihoterapije_id) {
        this.sertifikat_id = sertifikat_id;
        this.datum = datum;
        this.psihoterapeut_id = psihoterapeut_id;
        this.oblast_psihoterapije_id = oblast_psihoterapije_id;

        OblastPsihoterapije oblast = JDBCUtils.getOblastPsihoterapijeById(oblast_psihoterapije_id);
        if(oblast != null) {
            this.oblast_psihoterapije_naziv = oblast.getNaziv();
        } else {
            this.oblast_psihoterapije_naziv = "";
        }
    }

    public Integer getSertifikat_id() {
        return sertifikat_id;
    }

    public void setSertifikat_id(Integer sertifikat_id) {
        this.sertifikat_id = sertifikat_id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Integer getPsihoterapeut_id() {
        return psihoterapeut_id;
    }

    public void setPsihoterapeut_id(Integer psihoterapeut_id) {
        this.psihoterapeut_id = psihoterapeut_id;
    }

    public Integer getOblast_psihoterapije_id() {
        return oblast_psihoterapije_id;
    }

    public void setOblast_psihoterapije_id(Integer oblast_psihoterapije_id) {
        this.oblast_psihoterapije_id = oblast_psihoterapije_id;
    }

    @Override
    public String toString() {
        return String.format("%s, (%s)",oblast_psihoterapije_naziv, datum);
    }
}
