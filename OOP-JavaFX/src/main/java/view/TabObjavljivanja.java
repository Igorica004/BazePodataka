package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Objavljivanje;
import utility.JDBCUtils;

public class TabObjavljivanja extends Tab {
    TableView tv = new TableView();
    public TabObjavljivanja(int psihoterapeut_id) {

        VBox sadrzaj = new VBox();
        setContent(sadrzaj);
        setText("Objavljivanja");

        TableColumn<Objavljivanje,Integer> colSeansa = new TableColumn<>("Seansa");
        TableColumn<Objavljivanje,String> colPrimalac = new TableColumn<>("Primalac");

        colSeansa.setCellValueFactory(new PropertyValueFactory<>("seansa"));
        colPrimalac.setCellValueFactory(new PropertyValueFactory<>("tip_objavljivanja"));

        tv.getColumns().addAll(colSeansa,colPrimalac);
        tv.setItems(JDBCUtils.getObjavljivanjaByPsihoterapeutId(psihoterapeut_id));

        sadrzaj.getChildren().add(tv);
        sadrzaj.setSpacing(10);
        sadrzaj.setPadding(new javafx.geometry.Insets(10));
    }
}
