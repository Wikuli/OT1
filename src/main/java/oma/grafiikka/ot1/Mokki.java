package oma.grafiikka.ot1;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name = "mokki")
public class Mokki {
    /**
     * Kenttä mökin id:lle.
     * Yhdistetään SQL:n taulun mokki_id riviin
     */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "mokki_id")
    private int mokki_id;
    /**
     * Kenttä mökin alue ID:lle
     * Yhdistetään SQL:n mokki taulun alue_id riviin
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alue_id")
    private Alue alue;
    /**
     * Kenttä mökin postinumerolle
     * Yhdistetään SQL:n mokki taulun postinro riviin
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postinro")
    private Posti posti;
    /**
     * Kenttä mökin nimelle
     * Yhdistetään SQL:n mokki taulun mokkinimi riviin
     */
    @Column(name = "mokkinimi")
    private String mokkinimi;
    /**
     * Kenttä mökin katuosoitteelle
     * Yhdistetään SQL:n mokki taulun katuosoite riviin
     */
    @Column(name = "katuosoite")
    private String katuosoite;
    /**
     * Kenttä mökin hinnalle
     * Yhdistetään SQL:n mokki taulun hinta riviin
     */
    @Column(name = "hinta")
    private double hinta;
    /**
     * Kenttä mökin kuvaukselle
     * Yhdistetään SQL:n mokki taulun kuvaus riviin
     */
    @Column(name = "kuvaus")
    private String kuvaus;
    /**
     * Kenttä mökin henkilömäärälle
     * yhdistetään SQL:n mokki taulun henkilomaara riviin
     */
    @Column(name = "henkilomaara")
    private int henkilomaara;
    /**
     * Kenttä mökin varusteluille
     * yhdistetään SQL:n mokki taulun varustelu riviin
     */
    @Column(name = "varustelu")
    private String varustelu;

    /**
     * Alustaja mökki oliolle
     * @param alue eli mökin alue
     * @param posti eli mökin postinumero
     * @param mokkinimi eli mökin nimi
     * @param katuosoite eli mökin katuosoite
     * @param hinta eli mökin hinta
     * @param kuvaus eli mökin kuvaus
     * @param henkilomaara eli mökin henkilömäärä
     * @param varustelu eli mökin varustelu
     */
    public Mokki(Alue alue, Posti posti, String mokkinimi, String katuosoite, double hinta, String kuvaus, int henkilomaara, String varustelu) {
        this.alue = alue;
        this.posti = posti;
        this.mokkinimi = mokkinimi;
        this.katuosoite = katuosoite;
        this.hinta = hinta;
        this.kuvaus = kuvaus;
        this.henkilomaara = henkilomaara;
        this.varustelu = varustelu;
    }

    public Mokki(){

    }
    /**
     * Get-metodi mökin id_lle
     * @return mokki_id eli mökin id
     */
    public int getMokki_id() {
        return mokki_id;
    }
    /**
     * Get-metodi mökin alueelle
     * @return alue eli mökin alueen
     */
    public Alue getAlue() {
        return alue;
    }
    /**
     * Set-metodi mökin alueelle
     * @param alue eli parametrinä toimii mökin alue
     */
    public void setAlue(Alue alue) {
        this.alue = alue;
    }
    /**
     * Get-metodi mökin postinumerolle
     * @return posti eli mökin postinumero
     */
    public Posti getPosti() {
        return posti;
    }
    /**
     * Set-metodi mökin postinumerolle
     * @param posti eli mökin postinumero
     */
    public void setPosti(Posti posti) {
        this.posti = posti;
    }
    /**
     *Get-metodi mökin nimelle
     * @return mokkinimi eli palauttaa mökin nimen
     */
    public String getMokkinimi() {
        return mokkinimi;
    }
    /**
     * Set-metodi mökin nimelle
     * @param mokkinimi eli mökin nimi
     */
    public void setMokkinimi(String mokkinimi) {
        this.mokkinimi = mokkinimi;
    }
    /**
     * Get-metodi katuosoitteelle
     * @return katuosoite eli palauttaa mökin katusoitteen
     */
    public String getKatuosoite() {
        return katuosoite;
    }
    /**
     * Set-metodi mökin katuosoiteelle
     * @param katuosoite eli mökin katuosoite.
     */
    public void setKatuosoite(String katuosoite) {
        this.katuosoite = katuosoite;
    }
    /**
     * Get-metodi hinnalle
     * @return hinta eli palauttaa hinnan
     */
    public double getHinta() {
        return hinta;
    }

    /**
     * Set-metodi mökin hinnalle
     * @param hinta eli mökin hinta
     */
    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    /**
     * Get-metodi mökin kuvaukselle
     * @return kuvaus eli palauttaa mökin kuvauksen
     */
    public String getKuvaus() {
        return kuvaus;
    }

    /**
     * Set-metodi kuvauselle
     * @param kuvaus eli mökin kuvaus
     */
    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    /**
     * Get-metodi henkilömäärälle
     * @return henkilomaara eli moökin henkilömäärä
     */
    public int getHenkilomaara() {
        return henkilomaara;
    }

    /**
     * Set-metodi mökin henkilömöörölle
     * @param henkilomaara eli mökin henkilömäärä
     */
    public void setHenkilomaara(int henkilomaara) {
        this.henkilomaara = henkilomaara;
    }

    /**
     * Get-metodi mökin varustelulle
     * @return varustelu eli palauttaa mökin varustelun
     */
    public String getVarustelu() {
        return varustelu;
    }

    /**
     * Set-metodi mökin varustelulle
     * @param varustelu eli mökin varustelu
     */
    public void setVarustelu(String varustelu) {
        this.varustelu = varustelu;
    }

    public void lisaaMokki(Mokki mokki, SessionFactory sessionFactory){

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(mokki);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
