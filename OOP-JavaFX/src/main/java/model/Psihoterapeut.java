package model;

import java.time.LocalDate;

public class Psihoterapeut {
    private int psihoterapeut_id;
    private String ime;
    private String prezime;
    private Long JMBG;
    private LocalDate datum_rodjenja;
    private String telefon;
    private String email;
    private Integer adresa_id;
    private Integer tip_psihoterapeuta_id;
    private Integer nivo_obrazovanja_id;
    private Integer supervizor_id;


    public Psihoterapeut(int psihoterapeut_id, String ime, String prezime, Long JMBG, LocalDate datum_rodjenja,
                         String telefon, String email, Integer adresa_id, Integer tip_psihoterapeuta_id,
                         Integer nivo_obrazovanja_id, Integer supervizor_id) {
        this.psihoterapeut_id = psihoterapeut_id;
        this.prezime = prezime;
        this.JMBG = JMBG;
        this.datum_rodjenja = datum_rodjenja;
        this.telefon = telefon;
        this.email = email;
        this.adresa_id = adresa_id;
        this.tip_psihoterapeuta_id = tip_psihoterapeuta_id;
        this.nivo_obrazovanja_id = nivo_obrazovanja_id;
        this.supervizor_id = supervizor_id;

        this.ime = ime;
    }

    public Psihoterapeut(String ime, String prezime, Long JMBG, LocalDate datum_rodjenja, String telefon,
                         String email, Integer adresa_id, Integer tip_psihoterapeuta_id,
                         Integer nivo_obrazovanja_id, Integer supervizor_id) {
        this.ime = ime;
        this.prezime = prezime;
        this.JMBG = JMBG;
        this.datum_rodjenja = datum_rodjenja;
        this.telefon = telefon;
        this.email = email;
        this.adresa_id = adresa_id;
        this.tip_psihoterapeuta_id = tip_psihoterapeuta_id;
        this.nivo_obrazovanja_id = nivo_obrazovanja_id;
        this.supervizor_id = supervizor_id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getPsihoterapeut_id() {
        return psihoterapeut_id;
    }

    public void setPsihoterapeut_id(int psihoterapeut_id) {
        this.psihoterapeut_id = psihoterapeut_id;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Long getJMBG() {
        return JMBG;
    }

    public void setJMBG(Long JMBG) {
        this.JMBG = JMBG;
    }

    public LocalDate getDatum_rodjenja() {
        return datum_rodjenja;
    }

    public void setDatum_rodjenja(LocalDate datum_rodjenja) {
        this.datum_rodjenja = datum_rodjenja;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAdresa_id() {
        return adresa_id;
    }

    public void setAdresa_id(Integer adresa_id) {
        this.adresa_id = adresa_id;
    }

    public Integer getTip_psihoterapeuta_id() {
        return tip_psihoterapeuta_id;
    }

    public void setTip_psihoterapeuta_id(Integer tip_psihoterapeuta_id) {
        this.tip_psihoterapeuta_id = tip_psihoterapeuta_id;
    }

    public Integer getNivo_obrazovanja_id() {
        return nivo_obrazovanja_id;
    }

    public void setNivo_obrazovanja_id(Integer nivo_obrazovanja_id) {
        this.nivo_obrazovanja_id = nivo_obrazovanja_id;
    }

    public Integer getSupervizor_id() {
        return supervizor_id;
    }

    public void setSupervizor_id(Integer supervizor_id) {
        this.supervizor_id = supervizor_id;
    }

    @Override
    public String toString() {
        return String.format("%d | %s %s",psihoterapeut_id,ime,prezime);
    }
}
