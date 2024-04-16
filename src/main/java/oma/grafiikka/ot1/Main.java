package oma.grafiikka.ot1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        // Tässä lisätään fxml-tiedoston asiat projektiin ja ohjelma sitten näyttää sen mitä screen builderissa on tehty
        Parent root = FXMLLoader.load(getClass().getResource("/startScreen2.fxml"));
        Scene scene = new Scene(root, 600, 400);

        stage.setMaximized(true);
        stage.setScene(scene);
        stage.setTitle("FXML Example");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
