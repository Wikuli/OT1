package oma.grafiikka.ot1;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Fetch;
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
    @OneToMany(mappedBy = "alue", fetch = FetchType.EAGER)
    private List<Mokki> mokit;

    @OneToMany(mappedBy = "alue")
    private List<Palvelu> palvelut;

    public Alue(String nimi){
        this.nimi = nimi;
    }
    public Alue(){

    }

    public List<Mokki> getMokit(){
        return this.mokit;
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
    public static void lisaaAlue(Alue alue, SessionFactory sessionFactory){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(alue);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Alue etsiAlue(String alueNimi, SessionFactory sesFac) {
        Alue alue = null;
        try (Session session = sesFac.openSession()) {
            TypedQuery<Alue> query = session.createQuery("FROM Alue WHERE nimi = :nimi", Alue.class);
            query.setParameter("nimi", alueNimi);
            alue = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alue;
    }

    public static void poistaAlue(Alue alue, SessionFactory sessionFactory){
        String aluenimi = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            etsiAlue(aluenimi, sessionFactory);
            session.delete(alue);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
