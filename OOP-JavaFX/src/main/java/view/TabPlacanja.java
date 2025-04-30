package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Placanje;

public class TabPlacanja extends Tab {
    public TabPlacanja() {
        VBox sadrzaj = new VBox();
        this.setContent(sadrzaj);
        this.setText("Plaćanje");

        TableView<Placanje> tv = new TableView<>();
        TableColumn<Placanje,Integer> colKlijentId = new TableColumn<>("Klijent");
        TableColumn<Placanje,String> colValutaId = new TableColumn<>("Valuta");
        TableColumn<Placanje,String> colNacinPlacanjaId = new TableColumn<>("Nacin placanja");
        TableColumn<Placanje,Integer> colRata = new TableColumn<>("Rata");
        TableColumn<Placanje,Integer> colIznos = new TableColumn<>("Iznos");
        tv.setMinWidth(790);
        tv.setMaxHeight(250);
        tv.getColumns().addAll(colKlijentId,colValutaId,colNacinPlacanjaId,colRata,colIznos);
        colKlijentId.setCellValueFactory(new PropertyValueFactory<>("klijentId"));
        colValutaId.setCellValueFactory(new PropertyValueFactory<>("valutaId"));
        colNacinPlacanjaId.setCellValueFactory(new PropertyValueFactory<>("nacinPlacanjaId"));
        colRata.setCellValueFactory(new PropertyValueFactory<>("rata"));
        colIznos.setCellValueFactory(new PropertyValueFactory<>("klijentId"));

        sadrzaj.getChildren().add(tv);
    }
}
