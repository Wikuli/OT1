package oma.grafiikka.ot1;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
    //@OneToMany (mappedBy = "varaus")
    //private List<Varauksen_palvelut> varauksenPalveluList;

    public Varaus(Asiakas asiakas, Mokki mokki, Date varattu_pvm, Date vahvistus_pvm, Date varattu_alkupvm, Date varattu_loppupvm) {
        this.asiakas = asiakas;
        this.mokki = mokki;
        this.varattu_pvm = varattu_pvm;
        this.vahvistus_pvm = vahvistus_pvm;
        this.varattu_alkupvm = varattu_alkupvm;
        this.varattu_loppupvm = varattu_loppupvm;
    }

    public Varaus(){

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


    public static void lisaaVaraus(Varaus varaus){
        try (Session session = Main.sessionFactory.openSession()) {
            System.out.println("lisaa varauksen sisällä");
            System.out.println(varaus);
            Transaction transaction = session.beginTransaction();
            session.persist(varaus);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean onVarattu(Date alkuPvm, Date loppuPvm, int id, SessionFactory sessionFactory){
        try{
            Session session = sessionFactory.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = cb.createQuery(Long.class);
            Root<Varaus> root = query.from(Varaus.class);
            query.select(cb.count(root));

            query.where(
                    cb.and(
                            cb.equal(root.get("mokki").get("mokki_id"), id),
                            cb.or(
                                    cb.between(root.get("varattu_alkupvm"), alkuPvm, loppuPvm),
                                    cb.between(root.get("varattu_loppupvm"), alkuPvm, loppuPvm)
                            )
                    )
            );
            Long maara = session.createQuery(query).getSingleResult();

            return maara != 0;
        }
        catch (Exception e){
            return true;
        }
    }
    public static List<Varaus> etsiVaraus(Asiakas asiakas) {
        Date date = Date.valueOf(LocalDate.now());
        int id = asiakas.getAsiakas_id();
        try (Session session = Main.sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Varaus> query = cb.createQuery(Varaus.class);
            Root<Varaus> root = query.from(Varaus.class);
            query.select(root);
            query.where(
                    cb.greaterThan(
                            root.get("varattu_alkupvm"), date
                    )
            );
            List<Varaus> varaukset = session.createQuery(query).getResultList();
            return varaukset;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static void poistaVaraus(Varaus varaus){
        try(Session session = Main.sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(varaus);
            transaction.commit();
        }
    }
}
