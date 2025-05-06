package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TabPane;
import model.Psihoterapeut;
import utility.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

public class ProzorGlavni extends VBox {
    static ArrayList<Psihoterapeut> psihoterapeuti = new ArrayList<>();
    Label labelNaslov = new Label("Savetovalište \"Novi početak\"");
    HBox hb = new HBox();
    HBox hb1 = new HBox();
    public ProzorGlavni(int psihoterapeutId) {
        Psihoterapeut psihoterapeut = JDBCUtils.getPsihoterapeutById(psihoterapeutId);

        if(psihoterapeut == null){
            System.out.println("Pogresan ID");
            return;
        }
        TabPane tabbedPane = new TabPane();
        tabbedPane.getTabs().addAll(
                new TabKlijenti(psihoterapeutId),
                new TabPlacanja(psihoterapeutId),
                new TabProfil(psihoterapeutId),
                new TabSeanse(),
                new TabTermini(),
                new TabPsiholoskiTest())
            ;

        hb.getChildren().add(labelNaslov);
        hb.setAlignment(Pos.CENTER);
        hb.setPadding(new Insets(10));
        this.getChildren().add(hb);

        hb1.getChildren().add(tabbedPane);
        this.getChildren().add(hb1);
    }



}
