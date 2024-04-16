package oma.grafiikka.ot1;

import jakarta.persistence.*;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name = "palvelu")
public class Palvelu {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "palvelu_id")
    private int palvelu_id;
    @ManyToOne(fetch = FetchType.LAZY) //Vai pitääkö olla OneToMany?
    @JoinColumn(name = "alue_id")
    private Alue alue;    // vai pitääkö olla private int alue_id ?
    @Column(name = "nimi")
    private String nimi;
    @Column(name = "kuvaus")
    private String kuvaus;
    @Column (name = "hinta")
    private double hinta;
    @Column (name = "alv")
    private double alv;

    public Palvelu(Alue alue, String nimi, String kuvaus, double hinta, double alv){
        this.alue = alue;
        this.nimi = nimi;
        this.kuvaus = kuvaus;
        this.hinta = hinta;
        this.alv = alv;
    }

    public int getPalvelu_id() {
        return palvelu_id;
    }

    public void setPalvelu_id(int palvelu_id) {
        this.palvelu_id = palvelu_id;
    }

    public Alue getAlue() {
        return alue;
    }

    public void setAlue(Alue alue) {
        this.alue = alue;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    public double getAlv() {
        return alv;
    }

    public void setAlv(double alv) {
        this.alv = alv;
    }


    public void lisaaPalvelu(Palvelu palvelu){
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();


        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(palvelu);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}

