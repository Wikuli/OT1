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
    /**
     *kenttä varauksen id:lle
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "varaus_id")
    private int varaus_id;
    /**
     * kenttä asiakas oliolle
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "asiakas_id")
    private Asiakas asiakas;
    /**
     * Kenttä mökki oliolle
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mokki_id")
    private Mokki mokki;
    /**
     * kenttä varatuille päiville
     */
    @Column(name = "varattu_pvm", columnDefinition = "DATETIME")
    private Date varattu_pvm;
    /**
     *kenttä vahvistus päivälle
     */
    @Column(name = "vahvistus_pvm", columnDefinition = "DATETIME")
    private Date vahvistus_pvm;
    /**
     * kenttä varauksen aloituspäivälle
     */
    @Column(name = "varattu_alkupvm", columnDefinition = "DATETIME")
    private Date varattu_alkupvm;
    /**
     * kenttä varauksen loppumis päivälle
     */
    @Column(name = "varattu_loppupvm", columnDefinition = "DATETIME")
    private Date varattu_loppupvm;
    //@OneToOne (mappedBy = "varaus")
    //private List<Varauksen_palvelut> varauksenPalveluList;
    @OneToMany(mappedBy = "varaus", fetch = FetchType.EAGER)
    private List<Lasku> laskut;

    /**
     * varausten alustaja
     * @param asiakas eli asiakas olio
     * @param mokki eli mökki olio
     * @param varattu_pvm eli mökin päivät jolloin jo varattu
     * @param vahvistus_pvm eli varauksen vahvistus päivä
     * @param varattu_alkupvm varauksen aloitus päivä
     * @param varattu_loppupvm varauksen loppu päivä
     */
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

    public List<Lasku> getLaskut(){
        return laskut;
    }

    /**
     * Get-metodi varaus_id:lle
     * @return varaus_id eli varauksen id
     */
    public int getVaraus_id() {
        return varaus_id;
    }

    /**
     * get-metodi asiakkaalle
     * @return asiakas eli asiakas olio
     */
    public Asiakas getAsiakas() {
        return asiakas;
    }

    /**
     * Set-metodi asiakkaalle
     * @param asiakas eli asiakas olio
     */
    public void setAsiakas(Asiakas asiakas) {
        this.asiakas = asiakas;
    }

    /**
     * Get-metodi mökille
     * @return mokki eli mökki lio
     */
    public Mokki getMokki() {
        return mokki;
    }

    /**
     * set-metodi mökille
     * @param mokki eli mökki olio
     */
    public void setMokki(Mokki mokki) {
        this.mokki = mokki;
    }

    /**
     * Get-metodi varatuille päiville
     * @return varattu_pvm eli varatut päivät
     */
    public Date getVarattu_pvm() {
        return varattu_pvm;
    }

    /**
     * Set-metodi varatuille päiville
     * @param varattu_pvm eli varatut päivät
     */
    public void setVarattu_pvm(Date varattu_pvm) {
        this.varattu_pvm = varattu_pvm;
    }

    /**
     * Get-metodi vahvistus päivämäärälle
     * @return vahvistus_pvm eli vahvistus päiivämäärä
     */
    public Date getVahvistus_pvm() {
        return vahvistus_pvm;
    }

    /**
     * Set-metodi vahvistus päivämäärälle
     * @param vahvistus_pvm eli vahvistus päivämäärä
     */
    public void setVahvistus_pvm(Date vahvistus_pvm) {
        this.vahvistus_pvm = vahvistus_pvm;
    }

    /**
     * Get-metodi varauksen alkupäivämäärälle
     * @return varattu_alkupvm eli varauksen aloitus pvm
     */
    public Date getVarattu_alkupvm() {
        return varattu_alkupvm;
    }

    /**
     * Set-metodi varauksen alkupäivämäärälle
     * @param varattu_alkupvm eli varauksen aloitus päivä
     */
    public void setVarattu_alkupvm(Date varattu_alkupvm) {
        this.varattu_alkupvm = varattu_alkupvm;
    }

    /**
     * Get-metodi varauksen loppupäivämäärälle
     * @return varattu_loppupvm
     */
    public Date getVarattu_loppupvm() {
        return varattu_loppupvm;
    }

    /**
     * set-metodi varauksen loppupäivämäärälle
     * @param varattu_loppupvm eli varauksen loppu päivämäärä
     */
    public void setVarattu_loppupvm(Date varattu_loppupvm) {
        this.varattu_loppupvm = varattu_loppupvm;
    }

    /**
     * Metodi varauksen lisäykseen
     * @param varaus parametrina toimii varaus olio
     */
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

    /**
     * metodi varauksien etsimiseen.
     * @param asiakas Varauksien etsimiseen hyödynnetään asiakasta
     * @param sessionFactory
     * @return
     */
    public static List<Varaus> etsiVaraus(Asiakas asiakas, SessionFactory sessionFactory) {
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

    /**
     * metodi varauksen poistoon
     * @param varaus eli varaus olio
     */
    public static void poistaVaraus(Varaus varaus){
        try(Session session = Main.sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(varaus);
            transaction.commit();
        }
    }
}
