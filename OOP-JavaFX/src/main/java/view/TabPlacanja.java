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
       ComboBox<Klijent> cbKlijenti = new ComboBox<>();
        cbKlijenti.setItems(JDBCUtils.getKlijentiByPsihoterapeutId(psihoterapeutId));
        cbKlijenti.getSelectionModel().select(0);
        Label l;
        gp.add( l = new Label("Klijent"), 0, 0);
        gp.add(cbKlijenti, 1, 0);
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
        ComboBox<Seansa> cbSeansa = new ComboBox<>();
        gp.add(l = new Label("Seansa"), 0, 7);
        gp.add(cbSeansa, 1, 7);
        gp.add(btnNovoPlacanje, 0, 8);

       cbKlijenti.setOnAction((action) -> {
           cbSeansa.setItems(JDBCUtils.getNeplaceneSeanseByKlijentId(cbKlijenti.getSelectionModel().getSelectedItem().getKlijentID()));
       });

        gp.setHgap(10);
        gp.setVgap(10);
        sadrzaj.getChildren().addAll(tv, gp);
        sadrzaj.setSpacing(10);
        sadrzaj.setPadding(new Insets(10));

        cbValuta.setItems(JDBCUtils.getValute());
        cbNacinPlacanja.setItems(JDBCUtils.getNaciniPlacanja());

        cbValuta.getSelectionModel().select(0);
        cbNacinPlacanja.getSelectionModel().select(0);
        tv.setItems(JDBCUtils.getPlacanjaByPsihoterapeutId(psihoterapeutId));  // Popunjava TableView sa placanjem

        btnNovoPlacanje.setOnAction((action) -> {
            Klijent k = cbKlijenti.getSelectionModel().getSelectedItem();
            Valuta valuta = cbValuta.getSelectionModel().getSelectedItem();
            NacinPlacanja nacinPlacanja = cbNacinPlacanja.getSelectionModel().getSelectedItem();
            String svrha = tfSvrha.getText();
            int rata = Integer.parseInt(tfRata.getText());
            double iznos = Double.parseDouble(tfIznos.getText());
            int seansaId = 1;

/*
Columns:
placanje_id int AI PK
svrha varchar(50)
rata int
iznos double(6,3)
nacin_placanja_id int
valuta_id int
seansa_id int
klijent_id int
 */
           Placanje placanje = new Placanje(svrha,rata,iznos,nacinPlacanja.getNacinPlacanjaId(),valuta.getValutaId(),seansaId,k.getKlijentID());
           tv.setItems(JDBCUtils.dodajPlacanje(placanje));
        });

    }

}
