package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Klijent;
import model.PsiholoskiTest;
import utility.JDBCUtils;

public class TabPsiholoskiTest extends Tab {
    TableView<PsiholoskiTest> tv = new TableView<>();
    TableColumn<PsiholoskiTest, Integer> colPsiholoskiTestID = new TableColumn<>("Test ID");
    TableColumn<PsiholoskiTest, String> colOblast = new TableColumn<>("Oblast");
    TableColumn<PsiholoskiTest, String> colNaziv = new TableColumn<>("Naziv");
    TableColumn<PsiholoskiTest, Integer> colCena = new TableColumn<>("Cena");
    TableColumn<PsiholoskiTest, Integer> colRezultat = new TableColumn<>("Rezultat");
    TableColumn<PsiholoskiTest, String> colKlijentID = new TableColumn<>("Klijent ID");
    TableColumn<PsiholoskiTest, String> colIme = new TableColumn<>("Ime");
    TableColumn<PsiholoskiTest, Boolean> colPrezime = new TableColumn<>("Prezime");
    Label opis = new Label("");

    public TabPsiholoskiTest(int psihoterapeut_id){
        tv.getColumns().addAll(colKlijentID, colIme, colPrezime, colPsiholoskiTestID,
                colNaziv, colOblast, colCena, colRezultat);
        colKlijentID.setCellValueFactory(new PropertyValueFactory<>("klijent_id"));
        colIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
        colPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        colPsiholoskiTestID.setCellValueFactory(new PropertyValueFactory<>("psiholoski_test_id"));
        colNaziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        colOblast.setCellValueFactory(new PropertyValueFactory<>("oblast"));
        colCena.setCellValueFactory(new PropertyValueFactory<>("cena"));
        colRezultat.setCellValueFactory(new PropertyValueFactory<>("rezultat"));

        tv.setMinWidth(790);
        tv.setMaxHeight(250);
        tv.setItems(JDBCUtils.getPsiholoskiTestByPsihoterapeutId(psihoterapeut_id));

        HBox hb = new HBox();
        hb.getChildren().add(tv);
        hb.setSpacing(10);
        hb.setPadding(new Insets(5));
        hb.setAlignment(Pos.CENTER);

        VBox sadrzaj = new VBox();
        sadrzaj.getChildren().addAll(hb,opis);
        sadrzaj.setAlignment(Pos.TOP_CENTER);

        this.setContent(sadrzaj);
        this.setText("Psiholoski testovi");

    }
}
