package oma.grafiikka.ot1;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

@Entity
@Table (name = "asiakas")
public class Asiakas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asiakas_id")
    private int asiakas_id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postinro")
    private Posti posti;
    @Column(name = "etunimi")
    private String etunimi;
    @Column(name = "sukunimi")
    private String sukunimi;
    @Column(name = "lahiosoite")
    private String lahiosoite;
    @Column(name = "email")
    private String email;
    @Column(name = "puhelinnro")
    private String puhelinnro;
    @OneToMany(mappedBy = "asiakas")
    private List<Varaus> varaukset;

    public Asiakas(Posti posti, String etunimi, String sukunimi, String lahiosoite, String email, String puhelinnro){
        this.posti = posti;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.lahiosoite = lahiosoite;
        this.email = email;
        this.puhelinnro = puhelinnro;
    }
    public Asiakas(){

    }
    public void setPosti(Posti posti){
        this.posti = posti;
    }
    public Posti getPosti(){
        return posti;
    }
    public String getPostiNro(){
        try(Session session = Main.sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            String nro = posti.getPosti();
            transaction.commit();
            return nro;
        }
        catch (Exception e) {
            return null;
        }
    }

    public int getAsiakas_id() {
        return asiakas_id;
    }

    public void setAsiakas_id(int asiakas_id) {
        this.asiakas_id = asiakas_id;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public String getLahiosoite() {
        return lahiosoite;
    }

    public void setLahiosoite(String lahiosoite) {
        this.lahiosoite = lahiosoite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPuhelinnro() {
        return puhelinnro;
    }

    public void setPuhelinnro(String puhelinnro) {
        this.puhelinnro = puhelinnro;
    }


    public static void lisaaAsiakas(Asiakas asiakas, SessionFactory sessionFactory){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(asiakas);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void poistaAsiakas(Asiakas asiakas){
        try(Session session = Main.sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(asiakas);
            transaction.commit();
        }
    }

    public static List<Asiakas> kaikkiAsiakkaat(){
        try (Session session = Main.sessionFactory.openSession();){
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Asiakas> cq = cb.createQuery(Asiakas.class);
            Root<Asiakas> root = cq.from(Asiakas.class);
            cq.select(root);
            List<Asiakas> asiakkaat = session.createQuery(cq).getResultList();
            return asiakkaat;
        }
        catch (Exception e){
            return null;
        }
    }
    public static void paivitaAsiakas(Asiakas asiakas, String enimi, String snimi, String lahiosoite, Posti posti, String sposti, String puhnro){
        try(Session session = Main.sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            asiakas.setEtunimi(enimi);
            asiakas.setSukunimi(snimi);
            asiakas.setLahiosoite(lahiosoite);
            asiakas.setPosti(posti);
            asiakas.setEmail(sposti);
            asiakas.setPuhelinnro(puhnro);
            session.merge(asiakas);
            tx.commit();
        }
        catch (Exception e){

        }
    }
}
