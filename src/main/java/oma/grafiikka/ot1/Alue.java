package oma.grafiikka.ot1;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

@Entity
@Table(name = "alue")
public class Alue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alue_id")
    private int alue_id;
    @Column(name = "nimi")
    private String nimi;
    @OneToMany(mappedBy = "alue")
    private List<Mokki> mokit;

    public Alue(String nimi){
        this.nimi = nimi;
    }

    public Alue() {

    }


    public int getAlue_id() {
        return alue_id;
    }

    public void setAlue_id(int alue_id) {
        this.alue_id = alue_id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    public void lisaaAlue(Alue alue){
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();


        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(alue);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

    public Alue etsiAlue(String alueNimi) {
        SessionFactory sesFac = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Alue alue = null;
        try (Session session = sesFac.openSession()) {
            TypedQuery<Alue> query = session.createQuery("FROM Alue WHERE nimi = :nimi", Alue.class);
            query.setParameter("nimi", alueNimi);
            alue = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sesFac.close();
        }
        return alue;
    }

    public void poistaAlue(Alue alue){
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        String aluenimi = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            etsiAlue(aluenimi);
            session.delete(alue);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

}
