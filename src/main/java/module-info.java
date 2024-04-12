module oma.grafiikka.ot1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens oma.grafiikka.ot1 to javafx.fxml;
    exports oma.grafiikka.ot1;
}