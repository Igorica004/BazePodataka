package model;

public class Fakultet {
    private Integer fakultet_id;
    private String naziv;
    private Integer univerzitet_id;

    public Fakultet(String naziv, Integer univerzitet_id) {
        this.naziv = naziv;
        this.univerzitet_id = univerzitet_id;
    }

    public Integer getFakultet_id() {
        return fakultet_id;
    }

    public void setFakultet_id(Integer fakultet_id) {
        this.fakultet_id = fakultet_id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getUniverzitet_id() {
        return univerzitet_id;
    }

    public void setUniverzitet_id(Integer univerzitet_id) {
        this.univerzitet_id = univerzitet_id;
    }
}
