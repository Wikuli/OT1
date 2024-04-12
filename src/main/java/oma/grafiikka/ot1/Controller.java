package oma.grafiikka.ot1;

import javafx.event.ActionEvent;

/**
 * This class contains methods that give functionality to the elements in the ui.
 */
public class Controller {

    /**
     * Method for the "Uusi varaus" button functionality
     * @param actionEvent Equals a click of the button from the user
     */
    public void newReservation(ActionEvent actionEvent) {
        System.out.println("Uusi varaus tehty");
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
}
