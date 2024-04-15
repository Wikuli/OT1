package oma.grafiikka.ot1;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    static SqlConn conn;
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
        /*Alue alue = new Alue("Tusby");
        alue.lisaaAlue(alue);*/
        launch(args);
    }
}
