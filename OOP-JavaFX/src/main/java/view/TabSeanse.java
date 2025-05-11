package view;

import app.App;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Seansa;
import utility.JDBCUtils;

import java.time.LocalDate;
import java.time.LocalTime;

import static utility.JDBCUtils.daLiJeSeansaObjavljena;

public class TabSeanse extends Tab {

    TableView<Seansa> tv = new TableView<>();
    TableColumn<Seansa, Integer> colSeansaID = new TableColumn<>("ID");
    TableColumn<Seansa, String> colDan = new TableColumn<>("Dan");
    TableColumn<Seansa, Integer> colVreme = new TableColumn<>("Vreme");
    TableColumn<Seansa, Integer> colTrajanje = new TableColumn<>("Trajanje");
    TableColumn<Seansa, String> colCenaPoSatu = new TableColumn<>("Cena po satu");
    TableColumn<Seansa, String> colUcesnici = new TableColumn<>("Učesnici");
    TableColumn<Seansa, String> colBeleske = new TableColumn<>("Beleške");

    public TabSeanse(int psihoterapeut_id) {

        tv.getColumns().addAll(colSeansaID, colDan, colVreme, colTrajanje, colCenaPoSatu, colUcesnici, colBeleske);
        colSeansaID.setCellValueFactory(new PropertyValueFactory<>("seansa_id"));
        colDan.setCellValueFactory(new PropertyValueFactory<>("dan"));
        colVreme.setCellValueFactory(new PropertyValueFactory<>("vreme"));
        colTrajanje.setCellValueFactory(new PropertyValueFactory<>("trajanje"));
        colCenaPoSatu.setCellValueFactory(new PropertyValueFactory<>("cena"));
        colUcesnici.setCellValueFactory(new PropertyValueFactory<>("ucesnici"));
        colBeleske.setCellValueFactory(new PropertyValueFactory<>("beleske"));

        tv.setMinWidth(790);
        tv.setMaxHeight(250);
        tv.setItems(JDBCUtils.getSeanseByPsihoterapeutId(psihoterapeut_id));
        HBox hb = new HBox();
        hb.getChildren().add(tv);
        hb.setSpacing(10);
        hb.setPadding(new Insets(5));
        hb.setAlignment(Pos.TOP_CENTER);

        VBox sadrzaj = new VBox();
        sadrzaj.getChildren().addAll(hb);
        sadrzaj.setAlignment(Pos.TOP_CENTER);

        HBox hbObjavljivanje = new HBox();
        hbObjavljivanje.setSpacing(10);
        hbObjavljivanje.setAlignment(Pos.CENTER);
        TextField tfPrimalacObjavljivanja = new TextField();
        Button btnObjavi = new Button("Objavi");
        Label lbGreska = new Label();
        btnObjavi.setOnAction(e -> {
            Integer seansa_id = tv.getSelectionModel().getSelectedItem().getSeansa_id();
            String primalac = tfPrimalacObjavljivanja.getText();
            if(daLiJeSeansaObjavljena(seansa_id,primalac)){
                lbGreska.setText("Seansa je vec objavljena za navedenog primaoca.");
                return;
            }
            JDBCUtils.objaviSeansu(seansa_id,tfPrimalacObjavljivanja.getText());
            lbGreska.setText("Uspesno objavljeno.");
            App.tabObjavljivanja.tv.setItems(JDBCUtils.getObjavljivanjaByPsihoterapeutId(psihoterapeut_id));
        });
        hbObjavljivanje.getChildren().addAll(tfPrimalacObjavljivanja,btnObjavi,lbGreska);
        sadrzaj.getChildren().add(hbObjavljivanje);

        this.setContent(sadrzaj);
        this.setText("Seanse");
    }
}
