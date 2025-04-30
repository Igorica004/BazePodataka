package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Termin;

import java.time.LocalDate;
import java.time.LocalTime;

public class TabTermini extends Tab {
    private DatePicker datePicker = new DatePicker(LocalDate.now());
    private ComboBox<Integer> cbSati = new ComboBox<>();
    private Button btnDodaj = new Button("Dodaj");
    public TabTermini() {
        VBox sadrzaj = new VBox();
        this.setContent(sadrzaj);
        this.setText("Zauzeti termini");
        sadrzaj.setPadding(new Insets(10));
        sadrzaj.setSpacing(10);


        TableView<Termin> tv = new TableView<>();
        TableColumn<Termin,LocalDate> colDatum = new TableColumn<>("Datum");
        TableColumn<Termin,LocalTime> colVreme = new TableColumn<>("Vreme");
        TableColumn<Termin,String> colKlijentIme = new TableColumn<>("Ime");
        TableColumn<Termin,String> colKlijentPrezime = new TableColumn<>("Prezime");

        colDatum.setCellValueFactory(new PropertyValueFactory<>("datum"));
        colVreme.setCellValueFactory(new PropertyValueFactory<>("vreme"));
        colVreme.setCellValueFactory(new PropertyValueFactory<>("klijentIme"));
        colVreme.setCellValueFactory(new PropertyValueFactory<>("klijentPrezime"));
        tv.setMinWidth(790);
        tv.setMaxHeight(250);
        tv.getColumns().addAll(colDatum,colVreme,colKlijentIme,colKlijentPrezime);
        sadrzaj.getChildren().add(tv);
        sadrzaj.setAlignment(Pos.TOP_CENTER);
        sadrzaj.setSpacing(10);
        sadrzaj.setPadding(new Insets(10));
        cbSati.setItems(FXCollections.observableArrayList(
            8,9,10,11,12,13,14,15,16,17,18,19,20
        ));
        sadrzaj.getChildren().addAll(datePicker,cbSati,btnDodaj);
    }
}
