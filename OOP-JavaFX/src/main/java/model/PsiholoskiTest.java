package model;

public class PsiholoskiTest {
    private int psiholoskiTestID;
    private String oblast;
    private String naziv;
    private int cena;
    private int rezultat;
    private int klijentID;

    public PsiholoskiTest(int psiholoskiTestID, String oblast, String naziv, int cena, int rezultat, int klijentID) {
        this.psiholoskiTestID = psiholoskiTestID;
        this.oblast = oblast;
        this.naziv = naziv;
        this.cena = cena;
        this.rezultat = rezultat;
        this.klijentID = klijentID;
    }

    public int getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(int klijentID) {
        this.klijentID = klijentID;
    }

    public int getPsiholoskiTestID() {
        return psiholoskiTestID;
    }

    public void setPsiholoskiTestID(int psiholoskiTestID) {
        this.psiholoskiTestID = psiholoskiTestID;
    }

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getRezultat() {
        return rezultat;
    }

    public void setRezultat(int rezultat) {
        this.rezultat = rezultat;
    }
}
