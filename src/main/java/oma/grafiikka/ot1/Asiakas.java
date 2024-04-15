package oma.grafiikka.ot1;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "asiakas")
public class Asiakas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asiakas_id")
    private int asiakas_id;
    @ManyToOne(fetch = FetchType.LAZY)
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
    /*@OneToMany(mappedBy = "asiakas")
    private List<Varaus> varaukset;
*/
    public Asiakas(Posti posti, String etunimi, String sukunimi, String lahiosoite, String email, String puhelinnro){
        //this.posti = posti;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.lahiosoite = lahiosoite;
        this.email = email;
        this.puhelinnro = puhelinnro;
    }
    public void setPosti(Posti posti){
        //this.posti = posti;
    }
    public int getPosti(){
        //return posti;
        return 1;
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
}
