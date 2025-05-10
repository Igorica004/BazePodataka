package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Seansa;
import utility.JDBCUtils;

import java.time.LocalDate;
import java.time.LocalTime;

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



        this.setContent(sadrzaj);
        this.setText("Seanse");
    }
}
