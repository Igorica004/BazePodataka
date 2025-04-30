package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Klijent;
import model.Seansa;

import java.time.LocalDate;
import java.time.LocalTime;

public class TabSeanse extends Tab {

    TableView<Seansa> tv = new TableView<>();
    TableColumn<Seansa, Integer> colSeansaID = new TableColumn<>("ID");
    TableColumn<Seansa, String> colDan = new TableColumn<>("Dan");
    TableColumn<Seansa, Integer> colTrajanje = new TableColumn<>("Trajanje");
    TableColumn<Seansa, Integer> colCenaPoSatu = new TableColumn<>("Cena po satu");
    TableColumn<Seansa, LocalDate> colDatumCene = new TableColumn<>("Datum cene");
    TableColumn<Seansa, LocalTime> colPocetak = new TableColumn<>("Vreme pocetka");

    public TabSeanse() {

        tv.getColumns().addAll(colSeansaID, colDan, colTrajanje, colCenaPoSatu, colDatumCene,colPocetak);
        colSeansaID.setCellValueFactory(new PropertyValueFactory<>("seansaID"));
        colDan.setCellValueFactory(new PropertyValueFactory<>("dan"));
        colTrajanje.setCellValueFactory(new PropertyValueFactory<>("trajanje"));
        colCenaPoSatu.setCellValueFactory(new PropertyValueFactory<>("cena_po_satu"));
        colDatumCene.setCellValueFactory(new PropertyValueFactory<>("datum_cene"));
        colPocetak.setCellValueFactory(new PropertyValueFactory<>("pocetak"));

        tv.setMinWidth(790);
        tv.setMaxHeight(250);
        HBox hb = new HBox();
        hb.getChildren().add(tv);
        hb.setSpacing(10);
        hb.setPadding(new Insets(5));
        hb.setAlignment(Pos.TOP_CENTER);

        VBox sadrzaj = new VBox();
        sadrzaj.getChildren().addAll(hb);
        sadrzaj.setAlignment(Pos.TOP_CENTER);



        this.setContent(sadrzaj);
        this.setText("Seanse");
    }
}
