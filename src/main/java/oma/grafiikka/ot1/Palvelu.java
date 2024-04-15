package oma.grafiikka.ot1;

import jakarta.persistence.*;
import javafx.scene.control.Alert;

@Entity
@Table(name = "palvelu")
public class Palvelu {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "palvelu_id")
    private int palvelu_id;
    @ManyToOne(fetch = FetchType.LAZY) //Vai pitääkö olla OneToMany?
    @JoinColumn(name = "alue_id")
    private Alue alue;    // vai pitääkö olla private int alue_id ?
    @Column(name = "nimi")
    private String nimi;
    @Column(name = "kuvaus")
    private String kuvaus;
    @Column (name = "hinta")
    private double hinta;
    @Column (name = "alv")
    private double alv;

    public int getPalvelu_id() {
        return palvelu_id;
    }

    public void setPalvelu_id(int palvelu_id) {
        this.palvelu_id = palvelu_id;
    }

    public Alue getAlue() {
        return alue;
    }

    public void setAlue(Alue alue) {
        this.alue = alue;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    public double getAlv() {
        return alv;
    }

    public void setAlv(double alv) {
        this.alv = alv;
    }
}

