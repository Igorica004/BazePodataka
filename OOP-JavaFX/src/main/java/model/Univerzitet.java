package model;

public class Univerzitet {
    private Integer univerzitet_id;
    private String naziv;
    private Integer usmerenje_univerziteta_id;

    public Univerzitet(String naziv, Integer usmerenje_univerziteta_id) {
        this.naziv = naziv;
        this.usmerenje_univerziteta_id = usmerenje_univerziteta_id;
    }

    public Integer getUniverzitet_id() {
        return univerzitet_id;
    }

    public void setUniverzitet_id(Integer univerzitet_id) {
        this.univerzitet_id = univerzitet_id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getUsmerenje_univerziteta_id() {
        return usmerenje_univerziteta_id;
    }

    public void setUsmerenje_univerziteta_id(Integer usmerenje_univerziteta_id) {
        this.usmerenje_univerziteta_id = usmerenje_univerziteta_id;
    }
}
