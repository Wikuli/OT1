package oma.grafiikka.ot1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * This class contains methods that give functionality to the elements in the ui.
 */
public class Controller {

    public TextField addArea;

    /**
     * Method for the "Uusi varaus" button functionality. Opens a new window when clicked.
     * @param actionEvent Equals a click of the button from the user
     */
    public void newReservation(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/uusiVaraus.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("FXML Example");
        stage.show();

    }

    /**
     * Method for the "Asiakkaiden hallinta" button functionality
     * @param actionEvent Equals a click of the button from the user
     */
    public void manageCustomers(ActionEvent actionEvent) {
        System.out.println("Asiakkaita hallittu");
    }

    /**
     * Method for the "Laskutus" button functionality
     * @param actionEvent Equals a click of the button from the user
     */
    public void manageInvoice(ActionEvent actionEvent) {
        System.out.println("Laskuja hallittu");
    }

    /**
     * Method for the "Raportit" button functionality
     * @param actionEvent Equals a click of the button from the user
     */
    public void seeReports(ActionEvent actionEvent) {
        System.out.println("Raportteja katseltu");
    }

    /**
     * Method for the "Alueiden hallinta" button functionality
     * @param actionEvent Equals a click of the button from the user
     */
    public void manageAreas(ActionEvent actionEvent) {
        System.out.println("Alueita hallittu");
    }

    /**
     * Method for the "Palveluiden hallinta" button functionality
     * @param actionEvent Equals a click of the button from the user
     */
    public void manageServices(ActionEvent actionEvent) {
        System.out.println("Palveluita hallittu");
    }

    /**
     * Metodi, jolla voi lisätä uuden alueen
     * @param actionEvent
     */
    public void addNewArea(ActionEvent actionEvent) {
        Alue alue = new Alue(addArea.getText());
        alue.lisaaAlue(alue);
    }

    public void findCabins(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/etsiMokkeja.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("FXML Example");
        stage.show();
    }

    public void trackInvoice(ActionEvent actionEvent) {
    }

    public void deleteReservation(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/poistaVaraus.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("FXML Example");
        stage.show();
    }

    public void seeAccommodationReports(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/majoitustenRaportit.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("FXML Example");
        stage.show();
    }

    public void seeServiceReports(ActionEvent actionEvent) {
    }
}
