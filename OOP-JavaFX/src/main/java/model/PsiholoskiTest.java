package model;

import utility.JDBCUtils;

public class PsiholoskiTest {
    private int psiholoski_test_id;
    private String naziv;
    private String oblast;
    private int cena;
    private Double rezultat;
    private int seansa_id;
    private int klijent_id;
    private String ime;
    private String prezime;

    public PsiholoskiTest(int psiholoski_test_id, String naziv, String oblast, int cena, Double rezultat, int seansa_id, int klijent_id) {
        this.psiholoski_test_id = psiholoski_test_id;
        this.naziv = naziv;
        this.oblast = oblast;
        this.cena = cena;
        this.rezultat = rezultat;
        this.seansa_id = seansa_id;
        this.klijent_id = klijent_id;

        Klijent klijent = JDBCUtils.getKlijentById(klijent_id);
        ime = klijent.getIme();
        prezime = klijent.getPrezime();
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

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
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
}
