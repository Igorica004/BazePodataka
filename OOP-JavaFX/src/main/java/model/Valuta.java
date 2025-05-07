package model;

public class Valuta {
    String naziv;
    String skracenica;
    Integer valutaId;

    public Valuta(Integer valutaId,String naziv, String skracenica) {
        this.valutaId = valutaId;
        this.naziv = naziv;
        this.skracenica = skracenica;
    }
    public Valuta(String naziv, String skracenica) {

        this.naziv = naziv;
        this.skracenica = skracenica;
    }

    public Integer getValutaId() {
        return valutaId;
    }

    public void setValutaId(Integer valutaId) {
        this.valutaId = valutaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSkracenica() {
        return skracenica;
    }

    public void setSkracenica(String skracenica) {
        this.skracenica = skracenica;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
