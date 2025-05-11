package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import utility.JDBCUtils;
import view.ProzorPrijava;
import view.TabObjavljivanja;

public class App extends Application {
    public static Stage window;
    public static ProzorPrijava prijavaProzor;
    public static TabObjavljivanja tabObjavljivanja;

    @Override
    public void start(Stage stage) throws Exception {
        JDBCUtils.connect();
        window = stage;
        prijavaProzor = new ProzorPrijava();
        window.setScene(new Scene(prijavaProzor,300,250));
        window.show();

    }
}
