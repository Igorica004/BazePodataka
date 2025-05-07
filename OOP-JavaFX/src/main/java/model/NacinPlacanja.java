package model;

public class NacinPlacanja {
    Integer nacinPlacanjaId;
    String naziv;

    public NacinPlacanja(String naziv) {
        this.naziv = naziv;
    }

    public NacinPlacanja(Integer nacinPlacanjaId, String naziv) {
        this.nacinPlacanjaId = nacinPlacanjaId;
        this.naziv = naziv;
    }

    public Integer getNacinPlacanjaId() {
        return nacinPlacanjaId;
    }

    public void setNacinPlacanjaId(Integer nacinPlacanjaId) {
        this.nacinPlacanjaId = nacinPlacanjaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
