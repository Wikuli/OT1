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
    private String postinro;
    @Column(name = "toimipaikka")
    private String toimipaikka;

    @OneToMany(mappedBy = "posti")
    private List<Asiakas> asiakkaat;

    @OneToMany(mappedBy = "posti")
    private List<Mokki> mokit;

    public Posti(String posti, String toimipaikka) {
        this.postinro = posti;
        this.toimipaikka = toimipaikka;
    }

    public String getPosti() {
        return postinro;
    }

    public void setPosti(String posti) {
        this.postinro = posti;
    }

    public String getToimipaikka() {
        return toimipaikka;
    }

    public void setToimipaikka(String toimipaikka) {
        this.toimipaikka = toimipaikka;
    }

    public static void lisaaPosti(Posti posti, SessionFactory sessionFactory){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(posti);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Posti etsiPosti(String postinro, SessionFactory sesFac) {
        Posti posti = null;
        try (Session session = sesFac.openSession()) {
            TypedQuery<Posti> query = session.createQuery("FROM Posti WHERE postinro = :nimi", Posti.class);
            query.setParameter("nimi", postinro);
            posti = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posti;
    }
}
