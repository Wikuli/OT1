package oma.grafiikka.ot1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/Test.fxml"));

        Scene scene = new Scene(root, 600, 400);

        stage.setScene(scene);

        stage.setTitle("FXML Example");

        stage.show();
    }


    public static void main(String[] args) {
        //SqlConn conn = new SqlConn();
        //conn.connect();
        launch(args);
    }
}
