package oma.grafiikka.ot1;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;

@Entity
@Table(name = "varaus")
public class Varaus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "varaus_id")
    private int varaus_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asiakas_id")
    private Asiakas asiakas;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mokki_id")
    private Mokki mokki;
    @Column(name = "varattu_pvm", columnDefinition = "DATETIME")
    private Date varattu_pvm;
    @Column(name = "vahvistus_pvm", columnDefinition = "DATETIME")
    private Date vahvistus_pvm;
    @Column(name = "varattu_alkupvm", columnDefinition = "DATETIME")
    private Date varattu_alkupvm;
    @Column(name = "varattu_loppupvm", columnDefinition = "DATETIME")
    private Date varattu_loppupvm;

    public Varaus(Asiakas asiakas, Mokki mokki, Date varattu_pvm, Date vahvistus_pvm, Date varattu_alkupvm, Date varattu_loppupvm) {
        this.asiakas = asiakas;
        this.mokki = mokki;
        this.varattu_pvm = varattu_pvm;
        this.vahvistus_pvm = vahvistus_pvm;
        this.varattu_alkupvm = varattu_alkupvm;
        this.varattu_loppupvm = varattu_loppupvm;
    }

    public int getVaraus_id() {
        return varaus_id;
    }

    public Asiakas getAsiakas() {
        return asiakas;
    }

    public void setAsiakas(Asiakas asiakas) {
        this.asiakas = asiakas;
    }

    public Mokki getMokki() {
        return mokki;
    }

    public void setMokki(Mokki mokki) {
        this.mokki = mokki;
    }

    public Date getVarattu_pvm() {
        return varattu_pvm;
    }

    public void setVarattu_pvm(Date varattu_pvm) {
        this.varattu_pvm = varattu_pvm;
    }

    public Date getVahvistus_pvm() {
        return vahvistus_pvm;
    }

    public void setVahvistus_pvm(Date vahvistus_pvm) {
        this.vahvistus_pvm = vahvistus_pvm;
    }

    public Date getVarattu_alkupvm() {
        return varattu_alkupvm;
    }

    public void setVarattu_alkupvm(Date varattu_alkupvm) {
        this.varattu_alkupvm = varattu_alkupvm;
    }

    public Date getVarattu_loppupvm() {
        return varattu_loppupvm;
    }

    public void setVarattu_loppupvm(Date varattu_loppupvm) {
        this.varattu_loppupvm = varattu_loppupvm;
    }


    public void lisaaVaraus(Varaus varaus, SessionFactory sessionFactory){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(varaus);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
