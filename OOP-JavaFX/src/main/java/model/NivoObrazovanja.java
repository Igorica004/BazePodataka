package model;

public class NivoObrazovanja {
    Integer nivo_obrazovanja_id;
    String naziv;

    public NivoObrazovanja(Integer nivo_obrazovanja_id, String naizv) {
        this.nivo_obrazovanja_id = nivo_obrazovanja_id;
        this.naziv = naizv;
    }

    public Integer getNivo_obrazovanja_id() {
        return nivo_obrazovanja_id;
    }

    public void setNivo_obrazovanja_id(Integer nivo_obrazovanja_id) {
        this.nivo_obrazovanja_id = nivo_obrazovanja_id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
