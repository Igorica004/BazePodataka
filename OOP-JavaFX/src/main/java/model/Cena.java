package model;

import java.sql.Date;

public class Cena {
    private Integer cena_id;
    private Double iznos;
    private Date datum_izmene;
    private Integer valuta_id;

    public Cena(Integer cena_id, Double iznos, Date datum_izmene, Integer valuta_id) {
        this.cena_id = cena_id;
        this.iznos = iznos;
        this.datum_izmene = datum_izmene;
        this.valuta_id = valuta_id;
    }

    public Integer getValuta_id() {
        return valuta_id;
    }

    public void setValuta_id(Integer valuta_id) {
        this.valuta_id = valuta_id;
    }

    public Integer getCena_id() {
        return cena_id;
    }

    public void setCena_id(Integer cena_id) {
        this.cena_id = cena_id;
    }

    public Double getIznos() {
        return iznos;
    }

    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    public Date getDatum_izmene() {
        return datum_izmene;
    }

    public void setDatum_izmene(Date datum_izmene) {
        this.datum_izmene = datum_izmene;
    }

    public Cena(Double iznos, Date datum_izmene) {
        this.iznos = iznos;
        this.datum_izmene = datum_izmene;
    }

}
