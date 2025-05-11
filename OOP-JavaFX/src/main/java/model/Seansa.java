package model;

import javafx.collections.ObservableList;
import utility.JDBCUtils;

import java.sql.Date;
import java.sql.Time;

public class Seansa {
    int seansa_id;
    Date dan;
    Time vreme;
    int trajanje;
    int cena_id;
    Date datum_promene_cene;
    String beleske;
    Integer psihoterapeut_id;
    String ucesnici;
    String cena;

    public Seansa(int seansa_id, Date dan, Time vreme, int trajanje, int cena_id, String beleske, Integer psihoterapeut_id) {
        this.seansa_id = seansa_id;
        this.dan = dan;
        this.vreme = vreme;
        this.trajanje = trajanje;
        this.cena_id = cena_id;
        this.beleske = beleske;
        this.psihoterapeut_id = psihoterapeut_id;
        Cena cena = JDBCUtils.getCenaById(cena_id);
        Valuta valuta = JDBCUtils.getValutaById(cena.getValuta_id());
        this.cena = String.format("%s %s",cena.getIznos(),valuta.getSkracenica());
        ucesnici = "";
        ObservableList<Klijent> klijenti = JDBCUtils.getKlijentiBySeansaId(seansa_id);
        if(klijenti.size() == 0) return;
        for(int i=0; i<klijenti.size()-1; i++){
            ucesnici += klijenti.get(i).getIme() + " " + klijenti.get(i).getPrezime() + ", ";
        }
        ucesnici += klijenti.get(klijenti.size()-1).getIme() + " " + klijenti.get(klijenti.size()-1).getPrezime();
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public int getSeansa_id() {
        return seansa_id;
    }

    public void setSeansa_id(int seansa_id) {
        this.seansa_id = seansa_id;
    }

    public Date getDan() {
        return dan;
    }

    public void setDan(Date dan) {
        this.dan = dan;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public int getCena_id() {
        return cena_id;
    }

    public void setCena_id(int cena_id) {
        this.cena_id = cena_id;
    }

    public Time getVreme() {
        return vreme;
    }

    public void setVreme(Time vreme) {
        this.vreme = vreme;
    }

    public Date getDatum_promene_cene() {
        return datum_promene_cene;
    }

    public void setDatum_promene_cene(Date datum_promene_cene) {
        this.datum_promene_cene = datum_promene_cene;
    }

    public Integer getPsihoterapeut_id() {
        return psihoterapeut_id;
    }

    public void setPsihoterapeut_id(Integer psihoterapeut_id) {
        this.psihoterapeut_id = psihoterapeut_id;
    }

    public String getUcesnici() {
        return ucesnici;
    }

    public void setUcesnici(String ucesnici) {
        this.ucesnici = ucesnici;
    }

    public String getBeleske() {
        return beleske;
    }

    public void setBeleske(String beleske) {
        this.beleske = beleske;
    }


}
