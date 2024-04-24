package oma.grafiikka.ot1;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

@Entity
@Table (name = "asiakas")
public class Asiakas {
    /**
     * kenttä asiakkaan id:lle
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asiakas_id")
    private int asiakas_id;
    /**
     * kenttä asiakkaan postinumerolle
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "postinro")
    private Posti posti;
    /**
     * Kenttä etunimelle
     */
    @Column(name = "etunimi")
    private String etunimi;
    /**
     * Kenttä sukunimelle
     */
    @Column(name = "sukunimi")
    private String sukunimi;
    /**
     * Kenttä kotiosoitteelle
     */
    @Column(name = "lahiosoite")
    private String lahiosoite;
    /**
     * Kenttä sähköpostille
     */
    @Column(name = "email")
    private String email;
    /**
     * Kenttä puhelinnumerolle
     */
    @Column(name = "puhelinnro")
    private String puhelinnro;
    @OneToMany(mappedBy = "asiakas", fetch = FetchType.EAGER)
    private List<Varaus> varaukset;

    /**
     * alustaja asiakas oliolle
     * @param posti eli postinumero
     * @param etunimi eli etunimi
     * @param sukunimi eli sukunimi
     * @param lahiosoite eli kotiosoite
     * @param email eli sähköposti
     * @param puhelinnro eli puhelinnumero
     */
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
    //Tämä taitaa olla turha?
    public Posti getPosti(){
        return posti;
    }

    /**
     * Get-metodi asiakkaan postinumerolle
     * @return nro eli asiakkaan postinumero
     */
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

    /**
     * Get-metodi asiakas id:lle
     * @return asiakas_id eli asiakas id
     */
    public int getAsiakas_id() {
        return asiakas_id;
    }

    /**
     * Set-metodi asiakas_id:lle
     * @param asiakas_id eli asiakas id
     */
    public void setAsiakas_id(int asiakas_id) {
        this.asiakas_id = asiakas_id;
    }

    /**
     * Get-metodi asiakkaan etunimelle
     * @return etunimi eli asiakkaan etunimi
     */
    public String getEtunimi() {
        return etunimi;
    }

    /**
     * Set-metodi asiakkaan etunimelle
     * @param etunimi eli asiakkaan etunimi
     */
    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    /**
     * Get-metodi asiakkaan sukunimelle
     * @return sukunimi eli asiakkaan sukunimi
     */
    public String getSukunimi() {
        return sukunimi;
    }

    /**
     * Set-metodi asiakkaan sukunimelle
     * @param sukunimi eli asiakkaan sukunimi
     */
    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    /**
     * get-metodi asiakkaan lähiosoitteelle
     * @return lahiosoite eli asiakkaan kotiosoite
     */
    public String getLahiosoite() {
        return lahiosoite;
    }

    /**
     * Set-metodi asiakkaan lähiosoitteelle
     * @param lahiosoite eli asiakkaan lähiosoite
     */
    public void setLahiosoite(String lahiosoite) {
        this.lahiosoite = lahiosoite;
    }

    /**
     * Get-metodi asiakkaan sähköpostille
     * @return email eli sähköposti
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set-metodi asiakkaan sähköpostille
     * @param email eli sähköposti
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get-metodi asiakkaan puhelinnumerolle
     * @return puhelinnumero
     */
    public String getPuhelinnro() {
        return puhelinnro;
    }

    /**
     * Set-metodi asiakkaan puhelinnumerolle
     * @param puhelinnro eli asiakkaan puhelinnumero
     */
    public void setPuhelinnro(String puhelinnro) {
        this.puhelinnro = puhelinnro;
    }

    public List<Varaus> getVaraukset() {
        return varaukset;
    }

    public void setVaraukset(List<Varaus> varaukset) {
        this.varaukset = varaukset;
    }

    /**
     * metodi asiakkaan lisäämiselle
     * @param asiakas eli asiakas olio
     * @param sessionFactory
     */
    public static void lisaaAsiakas(Asiakas asiakas, SessionFactory sessionFactory){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(asiakas);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * metodi asiakkaan poistamiselle
     * @param asiakas eli asiakas olio
     */
    public static void poistaAsiakas(Asiakas asiakas){
        try(Session session = Main.sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(asiakas);
            transaction.commit();
        }
    }
    /**
     * metodi jonka avulla päästään näkemään lista kaikista asiakkaista
     * @return listan asiakkaista
     */
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

    /**
     *
     * @param asiakas
     * @param enimi
     * @param snimi
     * @param lahiosoite
     * @param posti
     * @param sposti
     * @param puhnro
     */
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

    /**
     * metodi asiakkaiden hakemiselle
     * @param enimi eli asiakkaan etunimi
     * @param snimi eli asiakkaan sukunimi
     * @param puhnro eli asiakkaan puhelinnumero
     * @return
     */
    public static Asiakas haeAsiakas(String enimi, String snimi, String puhnro){
        try(Session session = Main.sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Asiakas> cq = cb.createQuery(Asiakas.class);
            Root<Asiakas> root = cq.from(Asiakas.class);

            cq.select(root).where(
                    cb.equal(root.get("etunimi"), enimi),
                    cb.equal(root.get("sukunimi"), snimi),
                    cb.equal(root.get("puhelinnro"), puhnro)
            );
            Query<Asiakas> query = session.createQuery(cq);
            Asiakas asiakas = query.uniqueResult();
            tx.commit();
            return asiakas;
        }
        catch (Exception e){
            return null;
        }
    }
}
