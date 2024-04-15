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
 * This class contains methods that give functionality to the elements in the ui. HUOM!!! Ensimmäiset 10 metodia ovat
 * järjestyksessä Buttonit aloitusnäytöltä. Tämän seikan huomioiminen saattaa paranttaa luettavuutta. Muut metodit ovat
 * Aukeavien ikkunoiden sisällä olevia metodeja. Metodeja niimmaan perkeleesti.
 */
public class Controller {

    public TextField addArea;

    /**
     * Tällä metodilla voidaan avata uusi ikkunta "Poista varaus" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void deleteReservation(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/poistaVaraus.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Poista varaus");
        stage.show();
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Etsi mökkejä" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void findCabins(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/etsiMokkeja.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("FXML Example");
        stage.show();
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Uusi varaus" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
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
     * Tällä metodilla voidaan avata uusi ikkuna "Laskujen hallinta" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void manageInvoice(ActionEvent actionEvent) throws IOException{
        System.out.println("Laskuja hallittu");
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Laskujen seuranta" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void trackInvoice(ActionEvent actionEvent) throws IOException{
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Majoitusten raportit" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void seeAccommodationReports(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/majoitustenRaportit.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("FXML Example");
        stage.show();
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Palveluiden raportit" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void seeServiceReports(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/palveluidenRaportit.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("FXML Example");
        stage.show();
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Alueiden hallinta" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void manageAreas(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/alueidenHallinta.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("FXML Example");
        stage.show();
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Asiakashallinta" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void manageCustomers(ActionEvent actionEvent) throws IOException {
        System.out.println("Asiakkaita hallittu");
    }


    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Asiakashallinta" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void manageServices(ActionEvent actionEvent) throws IOException {
    }

    /**
     * Metodi, jolla voi lisätä uuden alueen
     * @param actionEvent
     */
    public void addNewArea(ActionEvent actionEvent) {
        Alue alue = new Alue(addArea.getText());
        alue.lisaaAlue(alue);
    }

    public void deleteArea(ActionEvent actionEvent) {
    }

    public void addArea(ActionEvent actionEvent) {
    }

    public void deleteCustomer(ActionEvent actionEvent) {
    }

    public void changeCustomerInformation(ActionEvent actionEvent) {
    }

    public void checkReportOnAccommodation(ActionEvent actionEvent) {
    }

    public void findReservations(ActionEvent actionEvent) {
    }

    public void deleteThisReservation(ActionEvent actionEvent) {
    }

    public void findThisCabin(ActionEvent actionEvent) {
    }

    public void makeReservation(ActionEvent actionEvent) {
    }

    public void serviceReportSearch(ActionEvent actionEvent) {
    }
}
