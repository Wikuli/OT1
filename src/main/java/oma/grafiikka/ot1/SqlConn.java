package oma.grafiikka.ot1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class SqlConn {

    public void startSession() {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory sesFactory = config.buildSessionFactory();
        Session session = sesFactory.openSession();
    }

}
