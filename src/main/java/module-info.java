module oma.grafiikka.ot1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires javafx.graphics;
    requires kernel;
    requires layout;


    opens oma.grafiikka.ot1 to javafx.fxml, org.hibernate.orm.core;
    exports oma.grafiikka.ot1;
}