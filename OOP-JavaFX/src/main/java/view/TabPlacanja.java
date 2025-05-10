package view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.*;
import utility.JDBCUtils;

public class TabPlacanja extends Tab {
    public TabPlacanja(Integer psihoterapeutId) {
        VBox sadrzaj = new VBox();
        this.setContent(sadrzaj);
        this.setText("Plaćanje");

        TableView<Placanje> tv = new TableView<>();
        TableColumn<Placanje, Integer> colKlijentId = new TableColumn<>("Klijent ID");
        TableColumn<Placanje, String> colIme = new TableColumn<>("Ime");
        TableColumn<Placanje, String> colPrezime = new TableColumn<>("Prezime");
        TableColumn<Placanje, String> colValuta = new TableColumn<>("Valuta");
        TableColumn<Placanje, String> colNacinPlacanjaId = new TableColumn<>("Nacin placanja");
        TableColumn<Placanje, String> colSvrha = new TableColumn<>("Svrha");
        TableColumn<Placanje, Integer> colRata = new TableColumn<>("Rata");
        TableColumn<Placanje, String> colIznos = new TableColumn<>("Iznos");
        tv.setMinWidth(790);
        tv.setMaxHeight(250);
        tv.getColumns().addAll(colIme, colPrezime, colValuta, colSvrha, colNacinPlacanjaId, colRata, colIznos);
        colKlijentId.setCellValueFactory(new PropertyValueFactory<>("klijentId"));

        colIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
        colPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));


        colValuta.setCellValueFactory(new PropertyValueFactory<>("valuta"));  // Promeni naziv sa "valutaId" na "valuta"
        colNacinPlacanjaId.setCellValueFactory(new PropertyValueFactory<>("nacinPlacanjaId"));
        colSvrha.setCellValueFactory(new PropertyValueFactory<>("svrha"));
        colRata.setCellValueFactory(new PropertyValueFactory<>("rata"));
        colIznos.setCellValueFactory(new PropertyValueFactory<>("iznos"));


       ComboBox<Klijent> cbKlijenti = new ComboBox<>();
        cbKlijenti.setItems(JDBCUtils.getKlijentiByPsihoterapeutId(psihoterapeutId));
        cbKlijenti.getSelectionModel().select(0);


        sadrzaj.getChildren().addAll(tv);
        sadrzaj.setSpacing(10);
        sadrzaj.setPadding(new Insets(10));


        tv.setItems(JDBCUtils.getPlacanjaByPsihoterapeutId(psihoterapeutId));  // Popunjava TableView sa placanjem



    }

}
