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
}

