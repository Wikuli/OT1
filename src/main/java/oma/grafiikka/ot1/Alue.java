package oma.grafiikka.ot1;

import jakarta.persistence.*;
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
    public Alue(){

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
    public void lisaaAlue(Alue alue, SessionFactory sessionFactory){
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

    // Tänne pitäisi varmaankin saada metodi, joka hakee kaikki alueet tietokannasta? Saisi sitten tuohon ui:hin ListViewn
    // alueista ja sitten siitä valitsemalla voisi poistaa alueen tietokannasta. Vai onko helpompaa, että järjestelmään
    // kirjoitetaan alueen nimi, joka halutaan poistaa?

}
