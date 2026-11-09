package kelas;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import kelas.data.DataProduk;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        DataProduk data = new DataProduk();
        Navigator nav = new Navigator(stage, data);
        nav.bukaForm();
    }

    public static void main(String[] args) {
        launch(args);
    }
}