package view;

import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Klijent;
import model.Placanje;
import model.Psihoterapeut;
import utility.JDBCUtils;

import java.sql.Date;

public class TabPregledPsihoterapeuta  extends Tab {
    public TabPregledPsihoterapeuta(Integer psihoterapeutId) {
        VBox sadrzaj = new VBox();
        this.setContent(sadrzaj);
        this.setText("Pregled svih psihoterapeuta");

        TableView<Psihoterapeut> tv = new TableView<>();
        TableColumn<Psihoterapeut, String> colIme = new TableColumn<>("Ime");
        TableColumn<Psihoterapeut, String> colPrezime = new TableColumn<>("Prezime");
        TableColumn<Psihoterapeut, Long> colJMBG = new TableColumn<>("JMBG");
        TableColumn<Psihoterapeut, Date> colDatum = new TableColumn<>("Datum rodjenja");
        TableColumn<Psihoterapeut, String> colTelefon = new TableColumn<>("Telefon");
        TableColumn<Psihoterapeut, String> colEmail = new TableColumn<>("Email");
        TableColumn<Psihoterapeut, String> colAdresa = new TableColumn<>("Adresa");
        TableColumn<Psihoterapeut, String> colTip = new TableColumn<>("Tip");
        TableColumn<Psihoterapeut, String> colNivo = new TableColumn<>("Nivo obrazovanja");
        TableColumn<Psihoterapeut, String> colSupervizor = new TableColumn<>("Supervizor");

        tv.setMinWidth(790);
        tv.setMaxHeight(250);
        tv.getColumns().addAll(colIme, colPrezime, colJMBG, colDatum, colTelefon, colEmail, colAdresa, colTip, colNivo, colSupervizor);

        colIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
        colPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        colJMBG.setCellValueFactory(new PropertyValueFactory<>("JMBG"));
        colDatum.setCellValueFactory(new PropertyValueFactory<>("datum_rodjenja"));
        colTelefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAdresa.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        colTip.setCellValueFactory(new PropertyValueFactory<>("tip"));
        colNivo.setCellValueFactory(new PropertyValueFactory<>("nivo"));
        colSupervizor.setCellValueFactory(new PropertyValueFactory<>("supervizor"));

        sadrzaj.getChildren().addAll(tv);
        sadrzaj.setSpacing(10);
        sadrzaj.setPadding(new Insets(10));


       tv.setItems(JDBCUtils.getPsihoterapeuti());


    }
}
