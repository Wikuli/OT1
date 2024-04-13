package oma.grafiikka.ot1;

import jakarta.persistence.*;

@Entity
@Table (name = "asiakas")
public class Asiakas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asiakas_id")
    private int asiakas_id;
    @Column(name = "postinro")
    private String postinro;
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

    public Asiakas(String postinro, String etunimi, String sukunimi, String lahiosoite, String email, String puhelinnro){
        this.postinro = postinro;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.lahiosoite = lahiosoite;
        this.email = email;
        this.puhelinnro = puhelinnro;
    }

    public int getAsiakas_id() {
        return asiakas_id;
    }

    public void setAsiakas_id(int asiakas_id) {
        this.asiakas_id = asiakas_id;
    }

    public String getPostinro() {
        return postinro;
    }

    public void setPostinro(String postinro) {
        this.postinro = postinro;
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