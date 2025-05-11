package model;

import utility.JDBCUtils;

public class PsiholoskiTest {
    private int psiholoski_test_id;
    private String naziv;
    private String oblast;
    private int cena_id;
    private Double rezultat;
    private int seansa_id;
    private int klijent_id;
    private String ime;
    private String prezime;
    private String cena;
    private String seansa;

    public PsiholoskiTest(int psiholoski_test_id, String naziv, String oblast, int cena_id, Double rezultat, int seansa_id, int klijent_id) {
        this.psiholoski_test_id = psiholoski_test_id;
        this.naziv = naziv;
        this.oblast = oblast;
        this.cena_id = cena_id;
        this.rezultat = rezultat;
        this.seansa_id = seansa_id;
        this.klijent_id = klijent_id;

        Cena cena = JDBCUtils.getCenaById(cena_id);
        Valuta valuta = JDBCUtils.getValutaById(cena.getValuta_id());
        this.cena = String.format("%s %s",cena.getIznos(),valuta.getSkracenica());

        Klijent klijent = JDBCUtils.getKlijentById(klijent_id);
        ime = klijent.getIme();
        prezime = klijent.getPrezime();

        Seansa s = JDBCUtils.getSeansaById(seansa_id);
        seansa = String.format("%s %s",s.getDan(),s.getVreme().toString());
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getPsiholoski_test_id() {
        return psiholoski_test_id;
    }

    public void setPsiholoski_test_id(int psiholoski_test_id) {
        this.psiholoski_test_id = psiholoski_test_id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public int getCena_id() {
        return cena_id;
    }

    public void setCena_id(int cena_id) {
        this.cena_id = cena_id;
    }

    public Double getRezultat() {
        return rezultat;
    }

    public void setRezultat(Double rezultat) {
        this.rezultat = rezultat;
    }

    public int getSeansa_id() {
        return seansa_id;
    }

    public void setSeansa_id(int seansa_id) {
        this.seansa_id = seansa_id;
    }

    public int getKlijent_id() {
        return klijent_id;
    }

    public void setKlijent_id(int klijent_id) {
        this.klijent_id = klijent_id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getSeansa() {
        return seansa;
    }
    public void setSeansa(String seansa) {
        this.seansa = seansa;
    }


}
