package view;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import model.*;
import utility.JDBCUtils;

public class TabProfil extends Tab {
    Label lbIme = new Label();
    Label lbPrezime= new Label();
    Label lbPsihoterapeut_id= new Label();
    Label lbJMBG = new Label();
    Label lbBrojTelefona= new Label();
    Label lbEmail= new Label();
    Label lbAdresa= new Label();
    Label lbTipPsihoterapeuta = new Label();
    Label lbNivoObrazovanja = new Label();
    Label lbSupervizor = new Label();

    public TabProfil(int psihoterapeut_id) {
        VBox sadrzaj = new VBox();
        sadrzaj.getChildren().addAll();
        this.setContent(sadrzaj);
        this.setText("Profil");

        Psihoterapeut psihoterapeut = JDBCUtils.getPsihoterapeutById(psihoterapeut_id);

        lbIme.setText("Ime: "+psihoterapeut.getIme());
        lbPrezime.setText("Prezime: "+psihoterapeut.getPrezime());
        lbPsihoterapeut_id.setText("Psihoterapeut ID: "+psihoterapeut.getPsihoterapeut_id());
        lbJMBG.setText("JMBG: "+psihoterapeut.getJMBG());
        lbBrojTelefona.setText("Broj telefona: "+ psihoterapeut.getTelefon());
        lbEmail.setText("Email: "+psihoterapeut.getEmail());

        Adresa adresa = JDBCUtils.getAdresaById(psihoterapeut.getAdresa_id());
        lbAdresa.setText(String.format("Adresa: %s %s, %s",adresa.getUlica(),adresa.getBroj(),adresa.getOpsitna()));

        TipPsihoterapeuta tipPsihoterapeuta = JDBCUtils.getTipPsihoterapeutaById(psihoterapeut.getTip_psihoterapeuta_id ());
        lbTipPsihoterapeuta.setText(String.format("Tip psihoterapeuta: %s",tipPsihoterapeuta.getNaziv()));

        NivoObrazovanja nivoObrazovanja = JDBCUtils.getNivoObrazovanjaById(psihoterapeut.getNivo_obrazovanja_id());
        lbNivoObrazovanja.setText(String.format("Nivo obrazovanja: %s",nivoObrazovanja.getNaziv()));

        Psihoterapeut supervizor = JDBCUtils.getPsihoterapeutById(psihoterapeut.getSupervizor_id());
        if(supervizor != null){
            lbSupervizor.setText(String.format("Supervizor: %s %s",supervizor.getIme(),supervizor.getPrezime()));
        }

        sadrzaj.getChildren().addAll(
            lbIme,lbPrezime,lbPsihoterapeut_id,lbJMBG,lbBrojTelefona,lbEmail,lbAdresa, lbTipPsihoterapeuta,lbNivoObrazovanja,lbSupervizor
        );
        ObservableList<Fakultet> fakulteti = JDBCUtils.getFakultetiByPsihoterapeutId(psihoterapeut_id);
        if(fakulteti.size() == 1){
            Fakultet fakultet = fakulteti.get(0);
            Univerzitet univerzitet = JDBCUtils.getUniverzitetById(fakultet.getUniverzitet_id());
            sadrzaj.getChildren().add(new Label(String.format("Fakultet: %s, %s ",fakultet.getNaziv(),univerzitet.getNaziv())));
        }
        else if(fakulteti.size() > 1){
            sadrzaj.getChildren().add(new Label("Fakulteti: "));
            for(Fakultet fakultet: fakulteti){
                Univerzitet univerzitet = JDBCUtils.getUniverzitetById(fakultet.getUniverzitet_id());
                sadrzaj.getChildren().add(new Label(String.format("%s, %s ",fakultet.getNaziv(),univerzitet.getNaziv())));
            }
        }

        ObservableList<Sertifikat> sertifikati = JDBCUtils.getSertifikatiByPsihoterapeutId(psihoterapeut_id);
        if(sertifikati.size() == 1){
            Sertifikat sertifikat = sertifikati.get(0);
            sadrzaj.getChildren().add(new Label("Sertifikat: "+sertifikat));
        }
        else if(sertifikati.size() > 1){
            sadrzaj.getChildren().add(new Label("Sertifikati: "));
            for(Sertifikat sertifikat: sertifikati){
                sadrzaj.getChildren().add(new Label(sertifikat.toString()));
            }
        }
        
    }
}
