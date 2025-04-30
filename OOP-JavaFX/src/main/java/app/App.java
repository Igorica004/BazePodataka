package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utility.JDBCUtils;
import view.ProzorPrijava;

public class App extends Application {
    public static Stage window;
    public static ProzorPrijava prijavaProzor;

    @Override
    public void start(Stage stage) throws Exception {
        JDBCUtils.connect();
        window = stage;
        prijavaProzor = new ProzorPrijava();
        window.setScene(new Scene(prijavaProzor,300,250));
        window.show();

    }
}
