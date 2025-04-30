package model;

public class TipPsihoterapeuta {
    Integer id;
    String naziv;

    public TipPsihoterapeuta(Integer id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
