package model;

import javafx.collections.ObservableList;
import utility.JDBCUtils;

public class Objavljivanje {
    private Integer objavljivanje_id;
    private Integer seansa_id;
    private String tip_objavljivanja;
    private String seansa;

    public Objavljivanje(Integer objavljivanje_id, Integer seansa_id, String tip_objavljivanja) {
        this.objavljivanje_id = objavljivanje_id;
        this.seansa_id = seansa_id;
        this.tip_objavljivanja = tip_objavljivanja;

        Seansa s = JDBCUtils.getSeansaById(seansa_id);
        ObservableList<Klijent> klijenti = JDBCUtils.getKlijentiBySeansaId(seansa_id);
        Klijent k = klijenti.get(0);
        seansa = String.format("%s %s, %s %s",k.getIme(),k.getPrezime(),s.getDan(),s.getVreme().toString());
    }

    public Integer getObjavljivanje_id() {
        return objavljivanje_id;
    }

    public void setObjavljivanje_id(Integer objavljivanje_id) {
        this.objavljivanje_id = objavljivanje_id;
    }

    public Integer getSeansa_id() {
        return seansa_id;
    }

    public void setSeansa_id(Integer seansa_id) {
        this.seansa_id = seansa_id;
    }

    public String getTip_objavljivanja() {
        return tip_objavljivanja;
    }

    public void setTip_objavljivanja(String tip_objavljivanja) {
        this.tip_objavljivanja = tip_objavljivanja;
    }

    public String getSeansa() {
        return seansa;
    }

    public void setSeansa(String seansa) {
        this.seansa = seansa;
    }
}
