package view;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import model.Psihoterapeut;
import utility.JDBCUtils;

public class TabProfil extends Tab {
    Label lbIme = new Label();
    Label lbPrezime= new Label();
    Label lbUsmerenje= new Label();
    Label lbPsihoterapeutID= new Label();
    Label lbFakultetID = new Label();


    public TabProfil(int psihoterapeutId) {
        VBox sadrzaj = new VBox();
        sadrzaj.getChildren().addAll();
        this.setContent(sadrzaj);
        this.setText("Profil");

        Psihoterapeut psihoterapeut = JDBCUtils.getPsihoterapeutById(psihoterapeutId);


       lbIme.setText("Ime: "+psihoterapeut.getIme());
        lbPrezime.setText("Prezime: "+psihoterapeut.getPrezime());
        lbPsihoterapeutID.setText("Psihoterapeut ID: "+psihoterapeut.getPsihoterapeut_id());

        sadrzaj.getChildren().addAll(
            lbIme,lbPrezime,lbPsihoterapeutID
        );
    }
}
