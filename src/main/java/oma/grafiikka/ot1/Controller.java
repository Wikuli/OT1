package oma.grafiikka.ot1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods that give functionality to the elements in the ui. HUOM!!! Ensimmäiset 10 metodia ovat
 * järjestyksessä Buttonit aloitusnäytöltä. Tämän seikan huomioiminen saattaa paranttaa luettavuutta. Muut metodit ovat
 * Aukeavien ikkunoiden sisällä olevia metodeja. Metodeja niimmaan perkeleesti.
 */
public class Controller {

    public TextField addAreaTextField;
    @FXML
    public ListView areaListViewNew;

    /**
     * Tällä metodilla voidaan avata uusi ikkunta "Poista varaus" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void manageCabins(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/mokkienHallinta.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Mökkien hallinta");
        stage.setResizable(false);
        stage.show();
    }

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
        stage.setResizable(false);
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
        stage.setTitle("Etsi mökkejä");
        stage.setResizable(false);
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
        stage.setTitle("Uusi varaus");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Laskujen hallinta" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void manageInvoice(ActionEvent actionEvent) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/laskujenHallinta.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Laskujen hallinta");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Laskujen seuranta" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void trackInvoice(ActionEvent actionEvent) throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/laskujenSeuranta.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Laskujen seuranta");
        stage.setResizable(false);
        stage.show();
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
        stage.setTitle("Majoitusten raportit");
        stage.setResizable(false);
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
        stage.setTitle("Palveluiden raportit");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Alueiden hallinta" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    @FXML
    public void manageAreas(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/alueidenHallinta.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Alueiden hallinta");
        stage.setResizable(false);
        stage.show();

        List <Alue> alueet = kaikkiAlueet();
        ArrayList <String> nimet = new ArrayList<>();
        System.out.println(alueet);

        for (Alue alue:alueet){
            System.out.println(alue.getNimi());
            nimet.add(alue.getNimi());
        }
        System.out.println(nimet);
        //areaListViewNew.setItems(FXCollections.observableArrayList(nimet));
        // areaListViewNew.getItems().addAll("Teppo", "MAtti");
        System.out.println(areaListViewNew.getItems());
    }

    public List <Alue> kaikkiAlueet() {
        try (Session session = Main.sessionFactory.openSession()) {
            Query<Alue> alueQuery = session.createQuery("from Alue", Alue.class);
            return alueQuery.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Asiakashallinta" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void manageCustomers(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/asiakkaidenHallinta.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Asiakashallinta");
        stage.setResizable(false);
        stage.show();
    }


    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Asiakashallinta" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void manageServices(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/palveluidenHallinta.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Palveluiden hallinta");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Metodi, jolla voi lisätä uuden alueen
     * @param actionEvent napin klikkaus
     */
    public void addNewArea(ActionEvent actionEvent) {
        Alue alue = new Alue(addAreaTextField.getText());
        alue.lisaaAlue(alue, Main.sessionFactory);
    }


    public void deleteArea(ActionEvent actionEvent) {
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

    public void areaServiceFetch(ActionEvent actionEvent) {
    }

    public void deleteServiceFromArea(ActionEvent actionEvent) {
    }

    public void addNewService(ActionEvent actionEvent) {
    }

    public void deleteEntireService(ActionEvent actionEvent) {
    }

    public void alterServiceInfo(ActionEvent actionEvent) {
    }

    public void createPaperInvoice(ActionEvent actionEvent) {
    }

    public void createEmailInvoice(ActionEvent actionEvent) {
    }

    public void findInvoice(ActionEvent actionEvent) {
    }

    public void invoicePayed(ActionEvent actionEvent) {
    }


    public void addNewCabin(ActionEvent actionEvent) {
    }

    public void deleteCabin(ActionEvent actionEvent) {
    }

    public void alterCabinInfo(ActionEvent actionEvent) {
    }
}
