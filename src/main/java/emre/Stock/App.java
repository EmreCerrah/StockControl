package emre.Stock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void init() throws Exception {
        super.init();
        DataStock.getInstance().loadItems();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("mainScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setResizable(false);
        stage.setTitle("Stok Kontrol");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() throws Exception {
        DataStock.getInstance().saveItems();
        super.stop();

    }
}