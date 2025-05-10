package view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Klijent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Priority;
import utility.JDBCUtils;

import java.sql.Date;

public class TabKlijenti extends Tab {
    TableView<Klijent> tv = new TableView<>();
    TableColumn<Klijent, Integer> colKlijentID = new TableColumn<>("ID");
    TableColumn<Klijent, String> colIme = new TableColumn<>("Ime");
    TableColumn<Klijent, String> colPrezime = new TableColumn<>("Prezime");
    TableColumn<Klijent, String> colDatum = new TableColumn<>("Datum");
    TableColumn<Klijent, String> colPol = new TableColumn<>("Pol");
    TableColumn<Klijent, String> colEmail = new TableColumn<>("Email");
    TableColumn<Klijent, String> colTelefon = new TableColumn<>("Telefon");
    TableColumn<Klijent, String> colOpisProblema = new TableColumn<>("Opis problema");
    TableColumn<Klijent, String> colPrvaTerapija = new TableColumn<>("Prva terapija (DA/NE)");


    public TabKlijenti(Integer psihoterapeutId){
        tv.getColumns().addAll(colKlijentID, colIme, colPrezime, colDatum, colPol, colEmail, colTelefon, colOpisProblema, colPrvaTerapija);


        colKlijentID.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getKlijentID()));
        colIme.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIme()));
        colPrezime.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrezime()));
        colDatum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDatum().toString()));
        colPol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPol()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colTelefon.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefon()));
        colOpisProblema.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOpisProblema()));

        colPrvaTerapija.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getPrvaTerapija() != null && cellData.getValue().getPrvaTerapija() == 1 ? "DA" : "NE")
        );


        tv.setItems(JDBCUtils.getKlijentiByPsihoterapeutId(psihoterapeutId));


        tv.setMinWidth(790);
        tv.setMaxHeight(250);
        HBox hb = new HBox();
        HBox hb1 = new HBox();
        hb.getChildren().add(tv);
        hb.setSpacing(10);
        hb.setPadding(new Insets(5));
        hb.setAlignment(Pos.TOP_CENTER);

        Label lbPol = new Label("Pol");
        Label polZ = new Label("Zenski");
        Label polM = new Label("Muski");
        RadioButton rbZ = new RadioButton();
        RadioButton rbM = new RadioButton();
        ToggleGroup tg = new ToggleGroup();
        rbZ.setToggleGroup(tg);
        rbM.setToggleGroup(tg);
        TextField tfId = new TextField();
        TextField tfIme = new TextField();
        TextField tfPrezime = new TextField();
        DatePicker dp = new DatePicker();
        TextField tfEmail = new TextField();
        TextField tfTelefon = new TextField();
        TextField tfOpis = new TextField();
        Label lbPrvaTerapija = new Label("Prva terapija");
        Label prvaDa = new Label("Da");
        Label prvaNe = new Label("Ne");
        RadioButton rbPrvaDa = new RadioButton();
        RadioButton rbPrvaNe = new RadioButton();
        Button btnNoviKlijent = new Button("Dodaj novog klijenta");
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(20);
        //grid.add(new Label("Klijent ID"), 0, 0);
       // grid.add(tfId, 1, 0);
        grid.add(new Label("Ime klijenta"), 0, 1);
        grid.add(tfIme, 1, 1);
        grid.add(new Label("Prezime klijenta"), 0, 2);
        grid.add(tfPrezime, 1, 2);
        grid.add(new Label("Datum rodjenja"), 0, 3);
        grid.add(dp, 1, 3);
        HBox hbPol = new HBox(10, polM, rbM, polZ, rbZ);
        grid.add(lbPol, 0, 4);
        grid.add(hbPol, 1, 4);

        VBox.setVgrow(tv, Priority.ALWAYS);
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        grid.add(new Label("Email"), 0, 5);
        grid.add(tfEmail, 1, 5);
        grid.add(new Label("Telefon"), 0, 6);
        grid.add(tfTelefon, 1, 6);
        grid.add(new Label("Opis problema"), 0, 7);
        grid.add(tfOpis, 1, 7);
        grid.add(btnNoviKlijent,4,7);
        HBox hbPrva = new HBox(10, prvaDa, rbPrvaDa, prvaNe, rbPrvaNe);
        grid.add(lbPrvaTerapija, 0, 8);
        grid.add(hbPrva, 1, 8);
        VBox sadrzaj = new VBox();
        sadrzaj.getChildren().addAll(hb,hb1,grid);

        sadrzaj.setAlignment(Pos.TOP_CENTER);

        this.setContent(sadrzaj);
        this.setText("Klijenti");


        btnNoviKlijent.setOnAction((action)->{

            String ime = tfIme.getText();
            String prezime = tfPrezime.getText();
            Date datum = java.sql.Date.valueOf(dp.getValue());
            String pol="";
            if(rbM.isSelected()){
                pol = "M";
            }
            else if(rbZ.isSelected()){
                pol = "Z";
            }
            String email = tfEmail.getText();
            String telefon = tfTelefon.getText();
            String opis = tfOpis.getText();
            Integer prva=0;
            if(rbPrvaDa.isSelected()){
                prva=1;
            }
            else if(rbPrvaNe.isSelected()){
                prva=0;
            }

            Klijent klijent = new Klijent(ime,prezime,datum,pol,email,telefon,opis,prva,psihoterapeutId);
           Integer noviId = JDBCUtils.dodajKlijenta(klijent);
            tv.setItems(JDBCUtils.getKlijentiByPsihoterapeutId(psihoterapeutId));


        });
    }
}
