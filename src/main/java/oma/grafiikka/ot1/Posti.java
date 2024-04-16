package oma.grafiikka.ot1;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

@Entity
@Table(name = "posti")
public class Posti {
    @Id
    @Column(name = "postinro")
    private String posti;
    @Column(name = "toimipaikka")
    private String toimipaikka;

    @OneToMany(mappedBy = "posti")
    private List<Asiakas> asiakkaat;

    @OneToMany(mappedBy = "posti")
    private List<Mokki> mokit;

    public Posti(String posti, String toimipaikka) {
        this.posti = posti;
        this.toimipaikka = toimipaikka;
    }

    public String getPosti() {
        return posti;
    }

    public void setPosti(String posti) {
        this.posti = posti;
    }

    public String getToimipaikka() {
        return toimipaikka;
    }

    public void setToimipaikka(String toimipaikka) {
        this.toimipaikka = toimipaikka;
    }

    public void lisaaPosti(Posti posti){
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();


        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(posti);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
