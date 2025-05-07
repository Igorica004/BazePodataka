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
    int cena_po_satu;
    Date datum_promene_cene;
    String beleske;
    Integer psihoterapeut_id;
    String ucesnici;

    public Seansa(int seansa_id, Date dan, Time vreme, int trajanje, int cena_po_satu, Date datum_promene_cene, String beleske, Integer psihoterapeut_id) {
        this.seansa_id = seansa_id;
        this.dan = dan;
        this.vreme = vreme;
        this.trajanje = trajanje;
        this.cena_po_satu = cena_po_satu;
        this.datum_promene_cene = datum_promene_cene;
        this.beleske = beleske;
        this.psihoterapeut_id = psihoterapeut_id;

        ucesnici = "";
        ObservableList<Klijent> klijenti = JDBCUtils.getKlijentiBySeansaId(seansa_id);
        for(int i=0; i<klijenti.size()-1; i++){
            ucesnici += klijenti.get(i).getIme() + " " + klijenti.get(i).getPrezime() + ", ";
        }
        ucesnici += klijenti.get(klijenti.size()-1).getIme() + " " + klijenti.get(klijenti.size()-1).getPrezime();
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

    public int getCena_po_satu() {
        return cena_po_satu;
    }

    public void setCena_po_satu(int cena_po_satu) {
        this.cena_po_satu = cena_po_satu;
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
