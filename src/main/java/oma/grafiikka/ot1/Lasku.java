package oma.grafiikka.ot1;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Luokka missä käsitellään SQL:n lasku taulua, sekä sille tarvittavat kentät ja metodit
 */
@Entity
@Table(name = "lasku")
public class Lasku {
    /**
     * Kenttä laskun id:lle
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lasku_id")
    private int lasku_id;
    /**
     * Kenttä varauksen id_lle
     */
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "varaus_id")
    private Varaus varaus;
    /**
     * Kenttä summalle
     */
    @Column(name = "summa")
    private double summa;
    /**
     * kenttä alville
     */
    @Column(name = "alv")
    private double alv;
    /**
     * kenttä maksun tiedosta
     */
    @Column(name = "maksettu")
    private char maksettu;

    /**
     * Laskun alustajja
     * @param lasku_id eli laskun id
     * @param varaus eli varauksen id
     * @param summa eli laskun hinta
     * @param alv eli alvi
     * @param maksettu tieto maksun tilanteesta
     */
    public Lasku(int lasku_id, Varaus varaus, double summa, double alv, char maksettu) {
        this.lasku_id = lasku_id;
        this.varaus = varaus;
        this.summa = summa;
        this.alv = alv;
        this.maksettu = maksettu;
    }
    public Lasku(){}

    /**
     * Get-metodi laskun id:lle
     * @return laskun id
     */
    public int getLasku_id() {
        return lasku_id;
    }

    /**
     * Set-metodi laskun id:lle
     * @param lasku_id eli laskun id
     */
    public void setLasku_id(int lasku_id) {
        this.lasku_id = lasku_id;
    }

    /**
     * Get-metodi varauksen id:lle
     * @return varaus
     */
    public Varaus getVaraus_id() {
        return varaus;
    }

    /**
     * Set-metodi varauksen id:lle
     * @param varaus eli varauksen id
     */
    public void setVaraus_id(Varaus varaus) {
        this.varaus = varaus;
    }

    /**
     * Get-metodi summalle
     * @return summa
     */
    public double getSumma() {
        return summa;
    }

    /**
     * Set-metodi summalle eli laskun hinnalle
     * @param summa eli laskun hinta
     */
    public void setSumma(double summa) {
        this.summa = summa;
    }

    /**
     * Get-metodi Alv:lle
     * @return alv
     */
    public double getAlv() {
        return alv;
    }

    /**
     * Set-metodi alville
     * @param alv eli alvi
     */
    public void setAlv(double alv) {
        this.alv = alv;
    }

    /**
     * Get-metodi maksun tilanteelle
     * @return maksettu tila eli onko maksettu vai ei
     */
    public char getMaksettu() {
        return maksettu;
    }

    /**
     * Set-metodi maksun tilanteelle
     * @param maksettu eli onko maksettu vai ei
     */
    public void setMaksettu(char maksettu) {
        this.maksettu = maksettu;
    }


    public void lisaaLasku(Lasku lasku, SessionFactory sessionFactory){
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(lasku);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
