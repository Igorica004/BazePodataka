package view;

import app.App;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.*;
import utility.JDBCUtils;

import java.sql.Date;

import static utility.JDBCUtils.getNivoObrazovanja;
import static utility.JDBCUtils.getTipoviPsihoterapeuta;

public class ProzorSignUp extends VBox {
    Button btnDodaj1 = new Button("Dodaj");
    Button btnDodaj2 = new Button("Dodaj");
    ComboBox<Psihoterapeut> cbPostojeci = new ComboBox<Psihoterapeut>();
    Label lbNoviPsihoterapeut = new Label("Novi psihoterapeut");
    Label lbPostojeciPsihoterapeut = new Label("Postojeci psihoterapeut");
    RadioButton rbPostojeci = new RadioButton();
    RadioButton rbNovi = new RadioButton();
    ToggleGroup tg = new ToggleGroup();

    public ProzorSignUp() {
        HBox hb1 = new HBox();
        HBox hb2 = new HBox();

        TextField tfUsername = new TextField();
        PasswordField tfPassword = new PasswordField();
        Label lbUsername = new Label("Usename");
        Label lbPassword = new Label("Password");
        GridPane gp0 = new GridPane();
        gp0.add(lbUsername,0,0);
        gp0.add(lbPassword,0,1);
        gp0.add(tfUsername,1,0);
        gp0.add(tfPassword, 1, 1);
        gp0.setHgap(10);
        gp0.setVgap(10);
        hb1.getChildren().addAll(lbNoviPsihoterapeut,rbNovi);
        hb2.getChildren().addAll(lbPostojeciPsihoterapeut,rbPostojeci);
        hb1.setSpacing(32);
        hb2.setSpacing(10);

        this.getChildren().addAll(gp0,hb1, hb2);
        rbNovi.setToggleGroup(tg);
        rbPostojeci.setToggleGroup(tg);

        HBox hbNovi = new HBox();
        HBox hbPostojeci = new HBox();
        cbPostojeci.setItems(JDBCUtils.getPsihoterapeutiBezNaloga());
        cbPostojeci.getSelectionModel().select(0);

        hbPostojeci.getChildren().addAll(cbPostojeci, btnDodaj2);
        hbPostojeci.setSpacing(10);

        Label lbIme = new Label("Ime");
        TextField tfIme = new TextField();
        Label lbPrezime = new Label("Prezime");
        TextField tfPrezime = new TextField();
        Label lbJMBG = new Label("JMBG");
        TextField tfJmbg= new TextField();
        Label lbTelefon = new Label("Telefon");
        TextField tfTelefon = new TextField();
        Label lbEmail = new Label("Email");
        TextField tfEmail = new TextField();
        Label lbUlica = new Label("Ulica");
        TextField tfUlica = new TextField();
        Label lbBroj = new Label("Broj");
        TextField tfBroj = new TextField();

        Label lbOpstina = new Label("Opstina");
        TextField tfOpstina = new TextField();

        Label lbDatumRodjenja = new Label("Datum rodjenja");
        DatePicker dpDatumRodjenja = new DatePicker();

        Label lbTip = new Label("Tip");
        Label lbNivoObrazovanja = new Label("Nivo obrazovanja");
        Label lbSuperVizor = new Label("Supervizor");

        GridPane gp = new GridPane();
        gp.add(lbIme,0,0);
        gp.add(lbPrezime,0,1);
        gp.add(lbJMBG,0,2);
        gp.add(lbTelefon,0,3);
        gp.add(lbEmail,0,4);
        gp.add(lbUlica,0,5);
        gp.add(tfUlica,1,5);
        gp.add(lbBroj,2,5);
        gp.add(tfBroj,3,5);
        gp.add(lbOpstina,4,5);
        gp.add(tfOpstina,5,5);

        gp.add(lbDatumRodjenja,0,6);
        gp.add(lbTip,0,7);
        gp.add(lbNivoObrazovanja,0,8);
        gp.add(lbSuperVizor,0,9);

        gp.add(tfIme,1,0);
        gp.add(tfPrezime,1,1);
        gp.add(tfJmbg,1,2);
        gp.add(tfTelefon,1,3);
        gp.add(tfEmail,1,4);

        ComboBox<TipPsihoterapeuta> cbTip = new ComboBox<>();
        ComboBox<NivoObrazovanja> cbNivoObrazovanja = new ComboBox<>();
        ComboBox<Psihoterapeut> cbSupervizor = new ComboBox<>();
        cbSupervizor.setItems(JDBCUtils.getSertifikovaniPsihoterapeuti());
        cbSupervizor.getItems().add(0,null);
        cbSupervizor.getSelectionModel().select(0);

        cbTip.setItems(getTipoviPsihoterapeuta());
        cbTip.getSelectionModel().select(0);

        cbNivoObrazovanja.setItems(getNivoObrazovanja());
        cbNivoObrazovanja.getSelectionModel().select(0);

        gp.add(dpDatumRodjenja,1,6);
        gp.add(cbTip,1,7);
        gp.add(cbNivoObrazovanja,1,8);
        gp.add(cbSupervizor,1,9);
        gp.add(btnDodaj1,2,9);
        gp.setHgap(10);
        gp.setVgap(10);
        hbNovi.getChildren().add(gp);
        hbNovi.setManaged(false);
        hbNovi.setVisible(false);
        hbPostojeci.setManaged(false);
        hbPostojeci.setVisible(false);

       getChildren().addAll(hbPostojeci,hbNovi);
        setSpacing(10);
        setPadding(new Insets(10));
        tg.selectedToggleProperty().addListener((obs,stari,novi)->{
            if(rbNovi.isSelected()){
                hbNovi.setVisible(true);
                hbNovi.setManaged(true);
                hbPostojeci.setVisible(false);
                hbPostojeci.setManaged(false);

            }
            else if(rbPostojeci.isSelected()){
                hbPostojeci.setVisible(true);
                hbPostojeci.setManaged(true);
                hbNovi.setVisible(false);
                hbNovi.setManaged(false);
            }
            else{
                throw new RuntimeException("Greska!!!");
            }
        });

        btnDodaj1.setOnAction((action)->{
           String username = tfUsername.getText();
           String password = tfPassword.getText();
           String ime = tfIme.getText();
           String prezime = tfPrezime.getText();
           Long jmbg = Long.parseLong(tfJmbg.getText());
           String telefon = tfTelefon.getText();
           String email = tfEmail.getText();
           String ulica = tfUlica.getText();
           String broj = tfBroj.getText();
           String opstina = tfOpstina.getText();
           Date datumRodjenja = Date.valueOf(dpDatumRodjenja.getValue());
           Integer tip_id = cbTip.getSelectionModel().getSelectedItem().getId();
           Integer nivo_obrazovanja = cbNivoObrazovanja.getSelectionModel().getSelectedItem().getNivo_obrazovanja_id();
           Integer supervizor = cbSupervizor.getSelectionModel().getSelectedItem().getPsihoterapeut_id();

           Adresa adresa = new Adresa(opstina,ulica,broj);
           Integer adresaId = JDBCUtils.dodajAdresu(adresa);
           Psihoterapeut psihoterapeut = new Psihoterapeut(ime,prezime,jmbg,datumRodjenja,telefon,email,adresaId,tip_id,nivo_obrazovanja,supervizor);
        });

        btnDodaj2.setOnAction((action)->{
            String username = tfUsername.getText();
            String password = tfPassword.getText();
            Integer id = cbPostojeci.getSelectionModel().getSelectedItem().getTip_psihoterapeuta_id();
            Nalog nalog = new Nalog(username, password, id);
            JDBCUtils.dodajNalog(nalog);

           App.window.setScene(new Scene(new ProzorGlavni(id),800,720));

        });
    }
}
