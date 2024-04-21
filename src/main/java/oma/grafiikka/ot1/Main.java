package oma.grafiikka.ot1;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main extends Application {
    static SessionFactory sessionFactory = null;
    @Override
    public void start(Stage stage) throws Exception {

        // Tässä lisätään fxml-tiedoston asiat projektiin ja ohjelma sitten näyttää sen mitä screen builderissa on tehty
        Parent root = FXMLLoader.load(getClass().getResource("/startScreen2.fxml"));
        Scene scene = new Scene(root, 600, 400);

        stage.setMaximized(true);
        stage.setScene(scene);
        stage.setTitle("FXML Example");
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                if (sessionFactory != null && sessionFactory.isOpen()) {
                    sessionFactory.close();
                }
            }
        });
    }
    public static void main(String[] args) {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        /*Alue turku = new Alue("Turku");
        turku.lisaaAlue(turku, sessionFactory);
        Posti posti = new Posti("04300", "Uusimaa");
        posti.lisaaPosti(posti, sessionFactory);
        Palvelu palvelu = new Palvelu(Alue.etsiAlue("Turku", sessionFactory), "palvelu2", "kuvaus", 123.456, 24);
        palvelu.lisaaPalvelu(palvelu, sessionFactory);*/
        launch(args);
    }
}
