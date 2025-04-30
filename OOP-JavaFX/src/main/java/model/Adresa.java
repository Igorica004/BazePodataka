package model;

public class Adresa {
    private Integer adresa_id;
    private String opsitna;
    private String ulica;
    private String broj;

    public Adresa(Integer adresa_id, String opsitna, String ulica, String broj) {
        this.adresa_id = adresa_id;
        this.opsitna = opsitna;
        this.ulica = ulica;
        this.broj = broj;
    }

    public Integer getAdresa_id() {
        return adresa_id;
    }

    public void setAdresa_id(Integer adresa_id) {
        this.adresa_id = adresa_id;
    }

    public String getOpsitna() {
        return opsitna;
    }

    public void setOpsitna(String opsitna) {
        this.opsitna = opsitna;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }
}
