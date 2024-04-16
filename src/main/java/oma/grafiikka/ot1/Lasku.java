package oma.grafiikka.ot1;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name = "lasku")
public class Lasku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lasku_id")
    private int lasku_id;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "varaus_id")
    private Varaus varaus;
    @Column(name = "summa")
    private double summa;
    @Column(name = "alv")
    private double alv;
    @Column(name = "maksettu")
    private char maksettu;

    public Lasku(int lasku_id, Varaus varaus, double summa, double alv, char maksettu) {
        this.lasku_id = lasku_id;
        this.varaus = varaus;
        this.summa = summa;
        this.alv = alv;
        this.maksettu = maksettu;
    }

    public int getLasku_id() {
        return lasku_id;
    }

    public void setLasku_id(int lasku_id) {
        this.lasku_id = lasku_id;
    }

    public Varaus getVaraus_id() {
        return varaus;
    }

    public void setVaraus_id(Varaus varaus) {
        this.varaus = varaus;
    }

    public double getSumma() {
        return summa;
    }

    public void setSumma(double summa) {
        this.summa = summa;
    }

    public double getAlv() {
        return alv;
    }

    public void setAlv(double alv) {
        this.alv = alv;
    }

    public char getMaksettu() {
        return maksettu;
    }

    public void setMaksettu(char maksettu) {
        this.maksettu = maksettu;
    }


    public void lisaaLasku(Lasku lasku){
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();


        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(lasku);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
