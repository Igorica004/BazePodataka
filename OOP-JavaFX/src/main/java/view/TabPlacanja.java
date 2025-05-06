package view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.NacinPlacanja;
import model.Placanje;
import model.Valuta;
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

        GridPane gp = new GridPane();
        Label lbIme = new Label("Ime");
        TextField tfIme = new TextField();
        Label lbPrezime = new Label("Prezime");
        TextField tfPrezima = new TextField();
        Label lbValuta = new Label("Valuta");
        ComboBox<Valuta> cbValuta = new ComboBox<>();
        Label lbNacinPlacanja = new Label("Nacin placanja");
        ComboBox<NacinPlacanja> cbNacinPlacanja = new ComboBox<>();
        Label lbSvrha = new Label("Svrha");
        TextField tfSvrha = new TextField();
        Label lbRata = new Label("Rata");
        TextField tfRata = new TextField();
        Label lbIznos = new Label("Iznos");
        TextField tfIznos = new TextField();
        gp.add(lbIme, 0, 0);
        gp.add(tfIme, 1, 0);
        gp.add(lbPrezime, 0, 1);
        gp.add(tfPrezima, 1, 1);
        gp.add(lbValuta, 0, 2);
        gp.add(cbValuta, 1, 2);
        gp.add(lbNacinPlacanja, 0, 3);
        gp.add(cbNacinPlacanja, 1, 3);
        gp.add(lbSvrha, 0, 4);
        gp.add(tfSvrha, 1, 4);
        gp.add(lbRata, 0, 5);
        gp.add(tfRata, 1, 5);
        gp.add(lbIznos, 0, 6);
        gp.add(tfIznos, 1, 6);
        Button btnNovoPlacanje = new Button("Dodaj novo placanje");
        gp.add(btnNovoPlacanje, 0, 7);

        gp.setHgap(10);
        gp.setVgap(10);
        sadrzaj.getChildren().addAll(tv, gp);
        sadrzaj.setSpacing(10);
        sadrzaj.setPadding(new Insets(10));

        cbValuta.setItems(JDBCUtils.sveValute());
        cbNacinPlacanja.setItems(JDBCUtils.sviNaciniPlacanja());

        cbValuta.getSelectionModel().select(0);
        cbNacinPlacanja.getSelectionModel().select(0);
        tv.setItems(JDBCUtils.getPlacanjaByPsihoterapeutId(psihoterapeutId));  // Popunjava TableView sa placanjem

        btnNovoPlacanje.setOnAction((action) -> {
            // Implementiraj logiku za dodavanje novog placanja
        });
    }

}
