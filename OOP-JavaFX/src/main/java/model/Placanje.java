package model;

import utility.JDBCUtils;

public class Placanje {
    private int placanje_id;
    private int klijentId;
    String ime;
    String prezime;

    Integer nacinPlacanjaId;
    String  nacinPlacanja;
    String svrha;
    int rata;
    Double iznos;
    Integer valuta_id;
    Integer seansa_id;
    String valuta;
    /*

    placanje_id int AI PK
svrha varchar(50)
rata int
iznos double(6,3)
nacin_placanja_id int
valuta_id int
seansa_id int
klijent_id int
     */


    public Placanje(Integer placanje_id, String svrha, int rata, Double iznos, Integer nacinPlacanjaId, Integer valuta_id,
                    Integer seansa_id, Integer klijentId) {

        this.placanje_id = placanje_id;
        this.svrha = svrha;
        this.rata = rata;
        this.iznos = iznos;
        this.nacinPlacanjaId = nacinPlacanjaId;
        this.valuta_id = valuta_id;
        this.seansa_id = seansa_id;
        this.klijentId = klijentId;
        Klijent klijent = JDBCUtils.getKlijentById(klijentId);
        ime = klijent.getIme();
        prezime = klijent.getPrezime();
        Valuta valuta = JDBCUtils.getValutaById(valuta_id);
        this.valuta = valuta.getNaziv();
        if(this.seansa_id==null){
            this.seansa_id = 0;
        }
    }
/*
Columns:
placanje_id int AI PK
svrha varchar(50)
rata int
iznos double(6,3)
nacin_placanja_id int
valuta_id int
seansa_id int
klijent_id int
 */
    public Placanje( String svrha, int rata, Double iznos, Integer nacinPlacanjaId, Integer valuta_id,
                    Integer seansa_id, Integer klijentId) {

        this.svrha = svrha;
        this.rata = rata;
        this.iznos = iznos;
        this.nacinPlacanjaId = nacinPlacanjaId;
        this.valuta_id = valuta_id;
        this.seansa_id = seansa_id;
        this.klijentId = klijentId;

        //Valuta valuta = JDBCUtils.getValutaById(valuta_id);
        //this.valuta = valuta.getNaziv();
        if(this.seansa_id==null){
            this.seansa_id = 1;
        }
    }


    public String getNacinPlacanja() {
        return nacinPlacanja;
    }

    public void setNacinPlacanja(String nacinPlacanja) {
        this.nacinPlacanja = nacinPlacanja;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Integer getValuta_id() {
        return valuta_id;
    }

    public void setValuta_id(Integer valuta_id) {
        this.valuta_id = valuta_id;
    }

    public Integer getSeansa_id() {
        return seansa_id;
    }

    public void setSeansa_id(Integer seansa_id) {
        this.seansa_id = seansa_id;
    }

    public int getPlacanje_id() {
        return placanje_id;
    }

    public void setPlacanje_id(int placanje_id) {
        this.placanje_id = placanje_id;
    }

    public int getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(int klijentId) {
        this.klijentId = klijentId;
    }




    public Integer getNacinPlacanjaId() {
        return nacinPlacanjaId;
    }

    public void setNacinPlacanjaId(Integer nacinPlacanjaId) {
        this.nacinPlacanjaId = nacinPlacanjaId;
    }

    public String getSvrha() {
        return svrha;
    }

    public void setSvrha(String svrha) {
        this.svrha = svrha;
    }

    public int getRata() {
        return rata;
    }

    public void setRata(int rata) {
        this.rata = rata;
    }

    public Double getIznos() {
        return iznos;
    }

    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }
}
