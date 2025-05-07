package model;

public class OblastPsihoterapije {
    Integer oblast_psihoterapije_id;
    String naziv;

    public OblastPsihoterapije(Integer oblast_psihoterapije_id, String naziv) {
        this.oblast_psihoterapije_id = oblast_psihoterapije_id;
        this.naziv = naziv;
    }

    public Integer getOblast_psihoterapije_id() {
        return oblast_psihoterapije_id;
    }

    public void setOblast_psihoterapije_id(Integer oblast_psihoterapije_id) {
        this.oblast_psihoterapije_id = oblast_psihoterapije_id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
