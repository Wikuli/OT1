package oma.grafiikka.ot1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.io.IOException;
import java.lang.reflect.Method;
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
    public AnchorPane aluePane;
    public TextField uusiMokinNimi;
    public TextField uusiHenkiloMaara;
    public TextField uusiHinta;
    public TextField uusiVarustelu;
    public TextField uusiKatuOsoite;
    public TextField uusiKuvaus;
    public TextField AlueTextField;
    public TextField postiNumeroTextField;
    @FXML
    public ListView jarjestelmanMokit;

    public void popUpIkkunanLuoja(String fxmlTiedosto, String ikkunanNimi) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlTiedosto));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle(ikkunanNimi);
        stage.setResizable(false);
        stage.show();
    }

    public void naytaAlueListView(){
        List <Alue> alueet = kaikkiAlueet();
        ArrayList <String> nimet = new ArrayList<>();

        for (Alue alue:alueet){
            System.out.println(alue.getNimi());
            nimet.add(alue.getNimi());
        }

        areaListViewNew.setItems(FXCollections.observableArrayList(nimet));
        areaListViewNew.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void naytaMokkiListView(){
        List <Mokki> mokit = kaikkiMokit();
        ArrayList <String> nimet = new ArrayList<>();

        for (Mokki mokki:mokit){
            nimet.add(mokki.getMokkinimi());
        }

        jarjestelmanMokit.setItems(FXCollections.observableArrayList(nimet));
        jarjestelmanMokit.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkunta "Poista varaus" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void manageCabins(ActionEvent actionEvent) throws IOException {
        popUpIkkunanLuoja("/mokkienHallinta.fxml", "Mökkien hallinta");
    }
    /**
     * Tällä metodilla voidaan avata uusi ikkunta "Poista varaus" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void deleteReservation(ActionEvent actionEvent) throws IOException {
        popUpIkkunanLuoja("/poistaVaraus.fxml", "Poista varaus");
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Etsi mökkejä" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void findCabins(ActionEvent actionEvent) throws IOException {
        popUpIkkunanLuoja("/etsiMokkeja.fxml", "Etsi mökkejä");
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Uusi varaus" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void newReservation(ActionEvent actionEvent) throws IOException {
        popUpIkkunanLuoja("/uusiVaraus.fxml", "Uusi varaus");
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Laskujen hallinta" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void manageInvoice(ActionEvent actionEvent) throws IOException{
        popUpIkkunanLuoja("/laskujenHallinta.fxml", "Laskujen hallinta");
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Laskujen seuranta" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void trackInvoice(ActionEvent actionEvent) throws IOException{
        popUpIkkunanLuoja("/laskujenSeuranta.fxml", "Laskujen seuranta");
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Majoitusten raportit" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void seeAccommodationReports(ActionEvent actionEvent) throws IOException {
        popUpIkkunanLuoja("/majoitustenRaportit.fxml", "Majoitusten raportit");
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Palveluiden raportit" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void seeServiceReports(ActionEvent actionEvent) throws IOException {
        popUpIkkunanLuoja("/palveluidenRaportit.fxml", "Palveluiden raportit");
    }

    public void haeAlueet(ActionEvent actionEvent) {
        naytaAlueListView();
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Alueiden hallinta" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    @FXML
    public void manageAreas(ActionEvent actionEvent) throws IOException {
        popUpIkkunanLuoja("/alueidenHallinta.fxml", "Alueiden hallinta");
        naytaAlueListView();
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
        popUpIkkunanLuoja("/asiakkaidenHallinta.fxml", "Asiakkaiden hallinta");
    }

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Asiakashallinta" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    public void manageServices(ActionEvent actionEvent) throws IOException {
        popUpIkkunanLuoja("/palveluidenHallinta.fxml", "Palveluiden hallinta");
    }

    /**
     * Metodi, jolla voi lisätä uuden alueen
     * @param actionEvent napin klikkaus
     */
    public void addNewArea(ActionEvent actionEvent) {
        Alue alue = new Alue(addAreaTextField.getText());
        Alue etsittyAlue = Alue.etsiAlue(alue.getNimi(), Main.sessionFactory);

        if (etsittyAlue == null){
            alue.lisaaAlue(alue, Main.sessionFactory);
        }
        else {
            System.out.println("Elä perkele");
        }
        naytaAlueListView();
    }

    public void deleteArea(ActionEvent actionEvent) {
        List<String> alueet = areaListViewNew.getSelectionModel().getSelectedItems();
        for (String i: alueet){
            Alue alue = Alue.etsiAlue(i, Main.sessionFactory);
            Alue.poistaAlue(alue, Main.sessionFactory);
        }
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


    public void addNewCabin(ActionEvent actionEvent) throws IOException {
        String mokinNimi = uusiMokinNimi.getText();
        String katuOsoite = uusiKatuOsoite.getText();
        Double hinta = 0.0;
        try {
            hinta = Double.parseDouble(uusiHinta.getText());
        }
        catch (Exception e){
            uusiHinta.setText("Anna desimaalilukuna!");
            return;
        }
        String kuvaus = uusiKuvaus.getText();
        int henkiloMaara = 0;
        try {
            henkiloMaara = Integer.parseInt(uusiHenkiloMaara.getText());
        }
        catch (Exception e) {
            uusiHenkiloMaara.setText("Anna kokonaislukuna!");
            return;
        }
        String varustelu = uusiVarustelu.getText();
        String alue = AlueTextField.getText();
        Alue haettuAlue = Alue.etsiAlue(alue, Main.sessionFactory);
        if (haettuAlue == null) {
            Alue.lisaaAlue(new Alue(alue), Main.sessionFactory);
        }
        haettuAlue = Alue.etsiAlue(alue, Main.sessionFactory);
        String postiNumero = postiNumeroTextField.getText();
        Posti etsittyPosti = Posti.etsiPosti(postiNumero, Main.sessionFactory);
        if (etsittyPosti == null) {
            etsittyPosti = new Posti(postiNumero, postiNumero);
            Posti.lisaaPosti(etsittyPosti, Main.sessionFactory);
        }
        Mokki uusiMokki = new Mokki(haettuAlue, etsittyPosti, mokinNimi, katuOsoite, hinta, kuvaus, henkiloMaara, varustelu);
        uusiMokki.lisaaMokki(uusiMokki, Main.sessionFactory);

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/mokkiLisatty.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Palveluiden hallinta");
        stage.setResizable(false);
        stage.show();

        AlueTextField.clear();
        postiNumeroTextField.clear();
        uusiHinta.clear();
        uusiVarustelu.clear();
        uusiHenkiloMaara.clear();
        uusiKuvaus.clear();
        uusiKatuOsoite.clear();
        uusiMokinNimi.clear();

        naytaAlueListView();
    }

    public void deleteCabin(ActionEvent actionEvent) {
    }

    public List <Mokki> kaikkiMokit() {
        try (Session session = Main.sessionFactory.openSession()) {
            Query<Mokki> alueQuery = session.createQuery("from Mokki", Mokki.class);
            return alueQuery.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void alterCabinInfo(ActionEvent actionEvent) {
    }


    public void haeMokit(ActionEvent actionEvent) {
        naytaMokkiListView();
    }
}
