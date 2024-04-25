package oma.grafiikka.ot1;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

/**
 * This class contains methods that give functionality to the elements in the ui.
 */
public class Controller implements Initializable {
    public static Date alku;
    public static Date loppu;
    @FXML
    public Button varausHaeAs;
    public AnchorPane toimintoOnnistuiAnchorPane = new AnchorPane();
    public ListView<Mokki> valitseMokkiListaltaListView;
    public TextArea mokinTiedotTextAres;
    @FXML
    public TextArea valittuMokkiTextArea;
    public TextArea varauksenTiedotTextArea;
    public static Mokki valittuMokki;
    public TextField addAreaTextField;
    @FXML
    public ListView areaListView;
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
    public ListView areaListViewService;
    public ListView palvelutListView;
    public ListView<Varaus> varauksetListView;
    public TextField haeAlueTextField;
    public TextArea palvelunTiedotTextArea;
    public TextField uudenPalvelunNimiTextField;
    public TextField uudenPalvelunKuvausTextField;
    public TextField uudenPalvelunHintaTextField;
    public TextField uudenPalvelunAlvTextField;
    public Button addNewServiceButton;
    public TextField muokattuPalvelunNimiTextField;
    public TextField muokattuPalvelunKuvausTextField;
    public TextField muokattuPalvelunHintaTextField;
    public TextField muokattuPalvelunAlvTextField;
    @FXML
    public TextField haeVarausAsEnimi;
    @FXML
    public TextField haeVarausAsSnimi;
    @FXML
    public TextField haeVarausAsPuhnro;
    @FXML
    public TextArea asiakastiedotVaraus;
    public TextField laskujenSeurantaEtuNimiTF;
    public TextField laskujenSeurantaSukuNimiTF;
    public TextField laskujenSeurantaPuhTF;
    public TextField haeVarauspoistoEnimi;
    public TextField haeVVarauspoistoSnimi;
    public TextField haeVarauspoistoPuhnro;
    @FXML
    public ListView<Varaus> varausPoistoLV;
    public TextArea varausPoistoTiedotTA;

    @FXML
    private TextField muokattuMokinNimi;
    @FXML
    private TextField muokattuHenkiloMaara;
    @FXML
    private TextField muokattuHinta;
    @FXML
    private TextField muokattuVarustelu;
    @FXML
    private TextField muokattuKatuOsoite;
    @FXML
    private TextField muokattuKuvaus;
    @FXML
    public DatePicker AlkuPvm;
    @FXML
    public DatePicker LoppuPvm;
    @FXML
    public TextField alueTF;
    @FXML
    public TextField hintaMinTF;
    @FXML
    public TextField hintaMaxTF;
    @FXML
    public ListView palveluLV;
    @FXML
    public TextField enimiTF;
    @FXML
    public TextField snimiTF;
    @FXML
    public TextField lahiosoiteTF;
    @FXML
    public TextField postinroTF;
    @FXML
    public TextField spostiTF;
    @FXML
    public TextField puhnroTF;
    @FXML
    public ListView<Asiakas> asiakasLV;
    @FXML
    public TextField editEnimiTF;
    @FXML
    public TextField editSnimiTF;
    @FXML
    public TextField editPuhNroTF;
    @FXML
    public TextField editSpostiTF;
    @FXML
    public TextField editPostiNroTF;
    @FXML
    public TextField editLahiosTF;


    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        if (asiakasLV != null) {
            initializeUIComponent();
        }
        if (valitseMokkiListaltaListView != null) {
            initializeLV();
        }
        if (palveluLV != null) {
            initializePalveluLV();
        }
        if (varauksetListView != null) {
            initializeVarausLV();
        }
        if (varausPoistoLV != null) {
            initializevarausPoistoLV();
        }
    }

    private void initializeUIComponent() {
        asiakasLV.getSelectionModel().selectedItemProperty().addListener((observable, oldAsiakas, newAsiakas) -> {
            if (newAsiakas == null) {
                editLahiosTF.clear();
                editPostiNroTF.clear();
                editEnimiTF.clear();
                editSnimiTF.clear();
                editSpostiTF.clear();
                editPuhNroTF.clear();
            } else {
                editPuhNroTF.setText(newAsiakas.getPuhelinnro());
                editEnimiTF.setText(newAsiakas.getEtunimi());
                editLahiosTF.setText(newAsiakas.getLahiosoite());
                editPostiNroTF.setText(newAsiakas.getPostiNro());
                editSpostiTF.setText(newAsiakas.getEmail());
                editSnimiTF.setText(newAsiakas.getSukunimi());
            }
        });
        asiakasLV.setCellFactory(new Callback<ListView<Asiakas>, ListCell<Asiakas>>() {
            @Override
            public ListCell<Asiakas> call(ListView<Asiakas> param) {
                return new ListCell<Asiakas>() {
                    @Override
                    protected void updateItem(Asiakas asiakas, boolean empty) {
                        super.updateItem(asiakas, empty);
                        if (asiakas != null) {
                            setText(asiakas.getEtunimi() + " " + asiakas.getSukunimi());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    public void initializeLV() {
        valitseMokkiListaltaListView.setCellFactory(new Callback<ListView<Mokki>, ListCell<Mokki>>() {
            @Override
            public ListCell<Mokki> call(ListView<Mokki> param) {
                return new ListCell<Mokki>() {
                    @Override
                    protected void updateItem(Mokki mokki, boolean empty) {
                        super.updateItem(mokki, empty);
                        if (mokki != null) {
                            setText(mokki.getMokkinimi());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        valitseMokkiListaltaListView.getSelectionModel().selectedItemProperty().addListener((observable, oldMokki, newMokki) -> {
            if (newMokki == null) {
                mokinTiedotTextAres.clear();
            } else {
                mokinTiedotTextAres.setText("Mökin nimi: " + newMokki.getMokkinimi() + "\n" +
                        "Kuvaus: " + newMokki.getKuvaus() + "\n" +
                        "Hinta: " + newMokki.getHinta() + "\n" +
                        "Varustelu: " + newMokki.getVarustelu() + "\n" +
                        "Henkilömäärä: " + newMokki.getHenkilomaara() + "\n" +
                        "Osoite: " + newMokki.getKatuosoite());
            }
        });
    }

    public void initializePalveluLV() {
        palveluLV.setCellFactory(new Callback<ListView<Palvelu>, ListCell<Palvelu>>() {
            @Override
            public ListCell<Palvelu> call(ListView<Palvelu> param) {
                return new ListCell<Palvelu>() {
                    @Override
                    protected void updateItem(Palvelu palvelu, boolean empty) {
                        super.updateItem(palvelu, empty);
                        if (palvelu != null) {
                            setText(palvelu.getNimi());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
        palveluLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void initializevarausPoistoLV() {
        varausPoistoLV.setCellFactory(new Callback<ListView<Varaus>, ListCell<Varaus>>() {
            @Override
            public ListCell<Varaus> call(ListView<Varaus> param) {
                return new ListCell<Varaus>() {
                    @Override
                    protected void updateItem(Varaus varaus, boolean empty) {
                        super.updateItem(varaus, empty);
                        if (varaus != null) {
                            setText(varaus.getMokki().getMokkinimi());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
        varausPoistoLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        varausPoistoLV.getSelectionModel().selectedItemProperty().addListener((observable, oldVaraus, newVaraus) -> {
            if(newVaraus == null){
                varausPoistoTiedotTA.clear();
            }
            else{
                varausPoistoTiedotTA.setText("Mökin nimi: " + newVaraus.getMokki().getMokkinimi() + "\n" +
                        "Varauksen alkamis päivämäärä: " + newVaraus.getVarattu_alkupvm() + "\n" +
                        "Varauksen loppumis päivämäärä: " + newVaraus.getVarattu_loppupvm() + "\n" +
                        "Varauksen hinta per päivä: " + newVaraus.getMokki().getHinta());
            }
        });
    }

    public void initializeVarausLV(){
        varauksetListView.setCellFactory(new Callback<ListView<Varaus>, ListCell<Varaus>>() {
            @Override
            public ListCell<Varaus> call(ListView<Varaus> param) {
                return new ListCell<Varaus>(){
                    @Override
                    protected void updateItem(Varaus varaus, boolean empty){
                        super.updateItem(varaus, empty);
                        if(varaus != null){
                            setText(varaus.getVarattu_alkupvm() + " - " + varaus.getVarattu_loppupvm());
                        }
                        else {
                            setText(null);
                        }
                    }
                };
            }
        });

        varauksetListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        varauksetListView.getSelectionModel().selectedItemProperty().addListener((observable, oldVaraus, newVaraus) -> {
            Double millis1 = (double) newVaraus.getVarattu_alkupvm().getTime();
            Double millis2 = (double) newVaraus.getVarattu_loppupvm().getTime();
            Double erotus = millis2 - millis1;
            Double paivina = (erotus / (1000 * 60 * 60 * 24));
            Calendar kalenteri = Calendar.getInstance();
            kalenteri.setTime(newVaraus.getVarattu_loppupvm());
            kalenteri.add(Calendar.DATE, 30);
            Date erapaiva = new Date(kalenteri.getTimeInMillis());
            if (newVaraus == null) {
                varauksenTiedotTextArea.clear();
            }
            else {
                varauksenTiedotTextArea.setText("Asiakkaan nimi: " + newVaraus.getAsiakas().getEtunimi() + " " +
                        "" + newVaraus.getAsiakas().getSukunimi() + "\n" +
                        "Asiakkaan puhelinnumero: " + newVaraus.getAsiakas().getPuhelinnro() + "\n" +
                        "Asiakkaan sposti: " + newVaraus.getAsiakas().getEmail() + "\n" +
                        "Asiakkaan osoite: " + newVaraus.getAsiakas().getLahiosoite() + "\n" +
                        "Asiakkaan postinumero: " + newVaraus.getAsiakas().getPostiNro() + "\n\n" +
                        "Varauksen alku: " + newVaraus.getVarattu_alkupvm() + "\n" +
                        "Varauksen loppu: " + newVaraus.getVarattu_loppupvm() + "\n" +
                        "Vahvistus: " + newVaraus.getVahvistus_pvm() + "\n" +
                        "Varauksen pvm: " + newVaraus.getVarattu_pvm() + "\n\n" +
                        "Mökin nimi: " + newVaraus.getMokki().getMokkinimi() + "\n" +
                        "Majoituspäivien määrä: " + Math.round(paivina) + "\n" +
                        "Mökin hinta per vrk: " + newVaraus.getMokki().getHinta() + "\n\n" +
                        "Mökin hinta kokonaisuudessaan: " + paivina * newVaraus.getMokki().getHinta() + "\n" +
                        "Eräpäivä: " + erapaiva);
            }
        });
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Apukoodit
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Tällä metodilla voi avata uuden pop-up ikkunan ohjelmassa
     * @param fxmlTiedosto Tämä on merkkijono, joka määrittää, mikä fmxl-tiedosto avataan
     * @param ikkunanNimi Merkkijono, joka määrittää ikkunan vasempaan ylälaitaan tulevan otsikon
     * @throws IOException Heittää exceptionin, jolla varmistetaan että on oikea tiedostonimi
     */
    public void popUpIkkunanLuoja(String fxmlTiedosto, String ikkunanNimi) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlTiedosto));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle(ikkunanNimi);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Tällä metodilla saa ListViewin näyttämään tietokantaan tallennetut alueet
     * @param listView se ListView joka halutaan näyttää
     */
    public void naytaAlueListView(ListView listView){
        List <Alue> alueet = kaikkiAlueet();
        ArrayList <String> nimet = new ArrayList<>();

        for (Alue alue:alueet){
            System.out.println(alue.getNimi());
            nimet.add(alue.getNimi());
        }

        listView.setItems(FXCollections.observableArrayList(nimet));
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void haeVarauksetLaskutus(ActionEvent actionEvent) {
        varauksetListView.setItems(FXCollections.observableArrayList(kaikkiVaraukset()));
    }

    /**
     * Tällä saa näkyviin tietokantaan tallennetut mökit ListViewiin
     */
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
     * Tällä saa näkyviin tietokantaan tallennetut palvelut ListViewiin
     */
    public void naytaPalveluListView(){
        List <Palvelu> palvelut = kaikkiPalvelut();
        ArrayList <String> nimet = new ArrayList<>();

        for (Palvelu palvelu:palvelut){
            nimet.add(palvelu.getNimi());
        }

        palvelutListView.setItems(FXCollections.observableArrayList(nimet));
        palvelutListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void naytaAlueenPalvelutListView(){
        List <Palvelu> palvelut = kaikkiPalvelut();
        ArrayList <String> nimet = new ArrayList<>();

        Alue haettavaAlue = Alue.etsiAlue(haeAlueTextField.getText(), Main.sessionFactory);

        for (Palvelu palvelu:palvelut){
            if (haettavaAlue.getAlue_id() == palvelu.getAlue().getAlue_id()) {
                nimet.add(palvelu.getNimi());
            }
        }

        palvelutListView.setItems(FXCollections.observableArrayList(nimet));
        palvelutListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    /**
     * Palauttaa listan, jossa on kaikki alueet
     * @return palauttaa listan
     */
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

    public List <Varaus> kaikkiVaraukset() {
        try (Session session = Main.sessionFactory.openSession()) {
            Query<Varaus> varausQuery = session.createQuery("from Varaus", Varaus.class);
            return varausQuery.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Palauttaa listan, jossa on kaikki mökit
     * @return palauttaa listan
     */
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

    /**
     * Palauttaa listan, jossa on kaikki palvelut
     * @return palauttaa listan
     */
    public List <Palvelu> kaikkiPalvelut() {
        try (Session session = Main.sessionFactory.openSession()) {
            Query<Palvelu> palveluQuery = session.createQuery("from Palvelu ", Palvelu.class);
            return palveluQuery.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void naytaAlueenPalvelut() {
        List<String> palvelut = palvelutListView.getSelectionModel().getSelectedItems();
        for (String i: palvelut){
            Palvelu haettuPalvelu = Palvelu.etsiPalvelu(i, Main.sessionFactory);
            palvelunTiedotTextArea.setText("Palvelun tiedot:\nNimi: " + haettuPalvelu.getNimi() + "\n" +
                    "Kuvaus: " + haettuPalvelu.getKuvaus() + "\nHinta: " + haettuPalvelu.getHinta() + "\n" +
                    "Alv: " + haettuPalvelu.getAlv());
        }
    }

    public void naytaViestiToiminnonOnnistumisesta(String otsikko) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/toimintoOnnistui.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle(otsikko);
        stage.setResizable(false);
        stage.show();
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Aloitusnäytön koodit
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
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

    /**
     * Tällä metodilla voidaan avata uusi ikkuna "Alueiden hallinta" nappia painamalla
     * @param actionEvent Napin painallus
     * @throws IOException Heitetään exception, jos haluttua fxml-tiedostoa ei ole.
     */
    @FXML
    public void manageAreas(ActionEvent actionEvent) throws IOException {
        popUpIkkunanLuoja("/alueidenHallinta.fxml", "Alueiden hallinta");
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
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------


    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // PopUp Ikkunoiden koodit
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Tällä metodilla saa nappia painamalla kutsuttua naytaALueListView, joka näyttää alueet ListViewissä
     * @param actionEvent Napin painallus
     */
    public void haeAlueet(ActionEvent actionEvent) {
        naytaAlueListView(areaListView);
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
        naytaAlueListView(areaListView);
    }

    /**
     * Kutsuu Alue-luokan metodeja, jotka poistavat halutun alueen tietokannasta
     * @param actionEvent napin painallus
     */
    public void deleteArea(ActionEvent actionEvent) {
        List<String> alueet = areaListView.getSelectionModel().getSelectedItems();
        for (String i: alueet){
            Alue alue = Alue.etsiAlue(i, Main.sessionFactory);
            Alue.poistaAlue(alue, Main.sessionFactory);
        }
    }

    public void deleteCustomer(ActionEvent actionEvent) {
        if(asiakasLV == null || asiakasLV.getItems().isEmpty()){
            return;
        }
        if (asiakasLV.getSelectionModel().getSelectedItem() == null){
            return;
        }
        Asiakas asiakas = (Asiakas) asiakasLV.getSelectionModel().getSelectedItem();
        asiakasLV.getItems().remove(asiakas);
        Asiakas.poistaAsiakas(asiakas);
    }

    public void changeCustomerInformation(ActionEvent actionEvent) {
        String enimi = editEnimiTF.getText();
        String snimi = editSnimiTF.getText();
        String lahiosoite = editLahiosTF.getText();
        String postinro = editPostiNroTF.getText();
        String sposti = editSpostiTF.getText();
        String puhnro = editPuhNroTF.getText();
        if(postinro.length() != 5){
            return;
        }
        Posti posti = Posti.etsiPosti(postinro, Main.sessionFactory);
        if(posti == null){
            posti = new Posti(postinro, "posti");
            Posti.lisaaPosti(posti, Main.sessionFactory);
        }
        Asiakas asiakas = asiakasLV.getSelectionModel().getSelectedItem();
        Asiakas.paivitaAsiakas(asiakas, enimi, snimi, lahiosoite, posti, sposti, puhnro);
    }

    public void checkReportOnAccommodation(ActionEvent actionEvent) {
    }

    public void findReservations(ActionEvent actionEvent) {
        String enimi = haeVarauspoistoEnimi.getText();
        String snimi = haeVVarauspoistoSnimi.getText();
        String puhnro = haeVarauspoistoPuhnro.getText();
//        Asiakas asiakas = Asiakas.haeAsiakas(enimi, snimi, puhnro);
//        System.out.println(Varaus.etsiVaraus(asiakas, Main.sessionFactory));
        try {
            Asiakas asiakas = Asiakas.haeAsiakas(enimi, snimi, puhnro);
            System.out.println(Varaus.etsiVaraus(asiakas, Main.sessionFactory));
            List<Varaus> varaukset = Varaus.etsiVaraus(asiakas, Main.sessionFactory);
            varausPoistoLV.setItems(FXCollections.observableList(varaukset));

        }
        catch (Exception e){
            return;
        }
//        Asiakas asiakas = Asiakas.haeAsiakas("Urho", "Kekkonen", "112");
//        System.out.println(Varaus.etsiVaraus(asiakas, Main.sessionFactory));
    }

    public void deleteThisReservation(ActionEvent actionEvent) {
        Varaus varaus = varausPoistoLV.getSelectionModel().getSelectedItem();
        if(varaus != null){
            Varaus.poistaVaraus(varaus);
        }
    }

    public void findThisCabin(ActionEvent actionEvent) {
        try {
            this.alku = Date.valueOf(AlkuPvm.getValue());
            System.out.println(alku);
        }
        catch (Exception e){
            return;
        }
        try {
            this.loppu = Date.valueOf(LoppuPvm.getValue());
            System.out.println(loppu);
        }
        catch (Exception e){
            return;
        }
        String alue = alueTF.getText();
        List<Palvelu> palvelut = palveluLV.getSelectionModel().getSelectedItems();

        double hintaMin = 0;
        try {
            if(!hintaMinTF.getText().isBlank()) {
                hintaMin = Double.parseDouble(hintaMinTF.getText());
            }
        }
        catch (Exception e){
            return;
        }
        double hintaMax = 0;
        try {
            if(!hintaMaxTF.getText().isBlank()){
                hintaMax = Double.parseDouble(hintaMaxTF.getText());
            }
        }
        catch (Exception e){
            return;
        }

        Alue kohdeAlue = Alue.etsiAlue(alue, Main.sessionFactory);
        if(kohdeAlue == null){
            return;
        }

        for (Palvelu palvelu : palvelut){
            if(palvelu.getAlue() != kohdeAlue){
                return;
            }
        }


        ArrayList<Mokki> retMokit = new ArrayList<>();
        try(Session session = Main.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            System.out.println(kohdeAlue.getMokit());
            for (Mokki mokki : kohdeAlue.getMokit()) {
                System.out.println(mokki.getMokkinimi());
                if (mokki.getHinta() < hintaMax && mokki.getHinta() > hintaMin) {
                    if (!Varaus.onVarattu(alku, loppu, mokki.getMokki_id(), Main.sessionFactory)) {
                        retMokit.add(mokki);
                    }
                }
            }
            transaction.commit();
        }
        catch (HibernateException e){

        }
        valitseMokkiListaltaListView.setItems(FXCollections.observableArrayList(retMokit));
    }

    Asiakas valittuAs;
    public void haeAs(ActionEvent actionEvent) {
        String varausEnimi = haeVarausAsEnimi.getText();
        String varausSnimi = haeVarausAsSnimi.getText();
        String varausPuhnro = haeVarausAsPuhnro.getText();
        if(varausSnimi.isBlank() || varausEnimi.isEmpty() || varausPuhnro.isEmpty()){
            return;
        }
        valittuAs = Asiakas.haeAsiakas(varausEnimi, varausSnimi, varausPuhnro);
        if(valittuAs == null){
            return;
        }

    }

    public void makeReservation(ActionEvent actionEvent) {
        System.out.println("makeReservation");
        System.out.println("valittuas " + valittuAs);
        System.out.println("valittumokk " + valittuMokki);
        System.out.println("alku " + alku);
        System.out.println("loppu " + loppu);
        if (valittuAs == null || valittuMokki == null || alku == null || loppu == null){
            return;
        }
        LocalDate lDate = LocalDate.now();
        Date date = Date.valueOf(lDate);
        Varaus varaus = new Varaus(valittuAs, valittuMokki, date, null, alku, loppu);
        System.out.println(varaus);
        Varaus.lisaaVaraus(varaus);
    }

    public void serviceReportSearch(ActionEvent actionEvent) {
    }

    public void areaServiceFetch(ActionEvent actionEvent) {
        naytaAlueenPalvelutListView();
    }

    public void deleteServiceFromArea(ActionEvent actionEvent) throws IOException {
        List<String> palvelut = palvelutListView.getSelectionModel().getSelectedItems();
        for (String i: palvelut){
            Palvelu palvelu = Palvelu.etsiPalvelu(i, Main.sessionFactory);
            Palvelu.poistaPalvelu(palvelu, Main.sessionFactory);
            naytaViestiToiminnonOnnistumisesta("Palvelu poistettu!");
            areaServiceFetch(actionEvent);
            palvelunTiedotTextArea.clear();
        }
    }


    public void addNewService(ActionEvent actionEvent) throws IOException {
        String palvelunNimi = uudenPalvelunNimiTextField.getText();
        String palvelunKuvaus = uudenPalvelunKuvausTextField.getText();
        Double palvelunHinta;
        Double palvelunAlv;

        try {
            palvelunHinta = Double.parseDouble(uudenPalvelunHintaTextField.getText());
        }
        catch (Exception e){
            uudenPalvelunHintaTextField.setText("Anna desimaalilukuna!");
            return;
        }

        try {
            palvelunAlv = Double.parseDouble(uudenPalvelunAlvTextField.getText());
        }
        catch (Exception e) {
            uudenPalvelunAlvTextField.setText("Anna desimaalilukuna!");
            return;
        }

        areaListViewService.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        List<String> alue = areaListViewService.getSelectionModel().getSelectedItems();
        Alue haettuAlue = Alue.etsiAlue(alue.getFirst(), Main.sessionFactory);

        Palvelu uusiPalvelu = new Palvelu(haettuAlue, palvelunNimi, palvelunKuvaus, palvelunHinta, palvelunAlv);
        Palvelu.lisaaPalvelu(uusiPalvelu, Main.sessionFactory);

        naytaViestiToiminnonOnnistumisesta("Palvelu lisätty!");

        uudenPalvelunNimiTextField.clear();
        uudenPalvelunKuvausTextField.clear();
        uudenPalvelunHintaTextField.clear();
        uudenPalvelunAlvTextField.clear();
    }

    public void alterServiceInfo(ActionEvent actionEvent) throws IOException {
        String valittuPalvelu = (String) palvelutListView.getSelectionModel().getSelectedItem();
        if (valittuPalvelu != null) {
            try(Session session = Main.sessionFactory.openSession()) {
                Query<Palvelu> query = session.createQuery("FROM Palvelu WHERE nimi = :nimi");
                query.setParameter("nimi", valittuPalvelu);
                Palvelu palvelu = query.uniqueResult();
                if (palvelu != null) {
                    palvelu.setNimi(muokattuPalvelunNimiTextField.getText());
                    palvelu.setKuvaus(muokattuPalvelunKuvausTextField.getText());
                    palvelu.setHinta(Double.parseDouble(muokattuPalvelunHintaTextField.getText()));
                    palvelu.setAlv(Double.parseDouble(muokattuPalvelunAlvTextField.getText()));


                    Transaction transaction = session.beginTransaction();
                    session.update(palvelu);
                    transaction.commit();

                }
            }
        }
        naytaViestiToiminnonOnnistumisesta("Palvelun tietoja muokattu!");
    }
    // ================================================================================================================================================
    public void createEmailInvoice(ActionEvent actionEvent) throws IOException {
        luoPdf.luoPdfDoc("Lasku, " +
                varauksetListView.getSelectionModel().getSelectedItem().getAsiakas().getSukunimi() + " " +
                varauksetListView.getSelectionModel().getSelectedItem().getAsiakas().getEtunimi() + " (" +
                varauksetListView.getSelectionModel().getSelectedItem().getVaraus_id() + ").pdf",
                varauksenTiedotTextArea.getText() + "\n" +
                "Tilinumero: FI12 3456 7891 0111 21\nSaaja: Village Newbies Oy\nViite: " +
                        varauksetListView.getSelectionModel().getSelectedItem().getVaraus_id() + "0000000" );
        naytaViestiToiminnonOnnistumisesta("Lasku luotu!");
    }

    public void findInvoice(ActionEvent actionEvent) {
        String varausEnimi = laskujenSeurantaEtuNimiTF.getText();
        String varausSnimi = laskujenSeurantaSukuNimiTF.getText();
        String varausPuhnro = laskujenSeurantaPuhTF.getText();
        if(varausSnimi.isBlank() || varausEnimi.isEmpty() || varausPuhnro.isEmpty()){
            return;
        }
        valittuAs = Asiakas.haeAsiakas(varausEnimi, varausSnimi, varausPuhnro);
        if(valittuAs == null){
            return;
        }
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
        naytaViestiToiminnonOnnistumisesta("Mökki lisätty!");

        AlueTextField.clear();
        postiNumeroTextField.clear();
        uusiHinta.clear();
        uusiVarustelu.clear();
        uusiHenkiloMaara.clear();
        uusiKuvaus.clear();
        uusiKatuOsoite.clear();
        uusiMokinNimi.clear();

        naytaAlueListView(areaListView);
    }

    public void deleteCabin(ActionEvent actionEvent) {
        List<String> mokit = jarjestelmanMokit.getSelectionModel().getSelectedItems();
        for (String i: mokit){
            Mokki mokki = Mokki.etsiMokki(i, Main.sessionFactory);
            Mokki.poistaMokki(mokki, Main.sessionFactory);
        }
    }

    public void alterCabinInfo(ActionEvent actionEvent) {   //same as muokkaaMokki
        String valittuMokki = (String) jarjestelmanMokit.getSelectionModel().getSelectedItem();
        if (valittuMokki != null) {
            try(Session session = Main.sessionFactory.openSession()) {
                Query<Mokki> query = session.createQuery("FROM Mokki WHERE mokkinimi = :mokkinimi");
                query.setParameter("mokkinimi", valittuMokki);
                Mokki mokki = query.uniqueResult();
                if (mokki != null) {
                    mokki.setMokkinimi(muokattuMokinNimi.getText());
                    mokki.setHenkilomaara(Integer.parseInt(muokattuHenkiloMaara.getText()));
                    mokki.setHinta(Double.parseDouble(muokattuHinta.getText()));
                    mokki.setVarustelu(muokattuVarustelu.getText());
                    mokki.setKatuosoite(muokattuKatuOsoite.getText());
                    mokki.setKuvaus(muokattuKuvaus.getText());

                    Transaction transaction = session.beginTransaction();
                    session.update(mokki);
                    transaction.commit();
                }
            }
        }
    }

    public void mokinTiedotTextFieldiin(MouseEvent event) {
        if (event.getClickCount() == 2) {
            String valittuMokki = (String) jarjestelmanMokit.getSelectionModel().getSelectedItem();
            if (valittuMokki != null) {
                try (Session session = Main.sessionFactory.openSession()) {
                Query<Mokki> query = session.createQuery("FROM Mokki WHERE mokkinimi = :mokkinimi",Mokki.class);
                query.setParameter("mokkinimi", valittuMokki);
                Mokki mokki = query.uniqueResult();
                if (mokki != null) {
                    muokattuMokinNimi.setText(mokki.getMokkinimi());
                    muokattuHenkiloMaara.setText(String.valueOf(mokki.getHenkilomaara()));
                    muokattuHinta.setText(String.valueOf(mokki.getHinta()));
                    muokattuVarustelu.setText(mokki.getVarustelu());
                    muokattuKatuOsoite.setText(mokki.getKatuosoite());
                    muokattuKuvaus.setText(mokki.getKuvaus());

                }
            }
        }
    }
    }

    public void palvelunTiedotTextFieldeihin() {
        String valittuPalvelu = (String) palvelutListView.getSelectionModel().getSelectedItem();
        if (valittuPalvelu != null) {
            try (Session session = Main.sessionFactory.openSession()) {
                Query<Palvelu> query = session.createQuery("FROM Palvelu WHERE nimi = :nimi", Palvelu.class);
                query.setParameter("nimi", valittuPalvelu);
                Palvelu palvelu = query.uniqueResult();
                if (palvelu != null) {
                    muokattuPalvelunNimiTextField.setText(palvelu.getNimi());
                    muokattuPalvelunKuvausTextField.setText(palvelu.getKuvaus());
                    muokattuPalvelunHintaTextField.setText(String.valueOf(palvelu.getHinta()));
                    muokattuPalvelunAlvTextField.setText(String.valueOf(palvelu.getAlv()));
                }
            }
        }

    }

    public void haeMokit(ActionEvent actionEvent) {
        naytaMokkiListView();
    }

    public void haeAlueetPalveluidenHallinnassa(ActionEvent actionEvent) {
        naytaAlueListView(areaListViewService);
    }

    public void findCustomers(ActionEvent actionEvent) {
        List<Asiakas> asiakkaat = Asiakas.kaikkiAsiakkaat();
        if (asiakkaat == null){
            return;
        }
        asiakasLV.setItems(FXCollections.observableList(asiakkaat));
    }

    public void addCustomer(ActionEvent actionEvent) {
        String enimi = enimiTF.getText();
        String snimi = snimiTF.getText();
        String lahiosoite = lahiosoiteTF.getText();
        String postinro = postinroTF.getText();
        if (postinro.length() != 5){
            return;
        }
        String sposti = spostiTF.getText();
        String puhnro = puhnroTF.getText();
        List<String> lista = Arrays.asList(enimi, snimi, lahiosoite, postinro, sposti, puhnro);

        for(String string : lista){
            if (string.isBlank()){
                return;
            }
        }

        Posti posti = Posti.etsiPosti(postinro, Main.sessionFactory);
        if(posti == null){
            posti = new Posti(postinro, "tp");
            Posti.lisaaPosti(posti, Main.sessionFactory);
        }
        Asiakas asiakas = new Asiakas(posti, enimi, snimi, lahiosoite, sposti, puhnro);
        asiakas.lisaaAsiakas(asiakas, Main.sessionFactory);
    }

    public void naytaPalvelunTiedotTextAreassa(MouseEvent mouseEvent) {
        naytaAlueenPalvelut();
        palvelunTiedotTextFieldeihin();
    }

    public void valitseMokki(ActionEvent actionEvent) throws IOException {
        this.valittuMokki = valitseMokkiListaltaListView.getSelectionModel().getSelectedItem();
        popUpIkkunanLuoja("/uusiVaraus.fxml", "Uusi varaus");

        /*valittuMokkiTextArea.setText("Mökin nimi: " + valittuMokki.getMokkinimi() + "\n" +
                "Kuvaus: " + valittuMokki.getKuvaus() + "\n" +
                "Hinta: " + valittuMokki.getHinta() + "\n" +
                "Varustelu: " + valittuMokki.getVarustelu() + "\n" +
                "Henkilömäärä: " + valittuMokki.getHenkilomaara() + "\n" +
                "Osoite: " + valittuMokki.getKatuosoite());*/
    }

    public void haePalvelutEtsiMokki(ActionEvent actionEvent) {
        try (Session session = Main.sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query<Palvelu> query = session.createQuery("FROM Palvelu", Palvelu.class);
            List<Palvelu> palvelut = query.getResultList();
            tx.commit();
            if (palvelut != null) {
                palveluLV.setItems(FXCollections.observableList(palvelut));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}