package oma.grafiikka.ot1;

import jakarta.persistence.*;

@Entity
@Table(name = "mokki")
public class Mokki {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "mokki_id")
    private int mokki_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alue_id")
    private Alue alue;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postinro")
    private Posti posti;
    @Column(name = "mokkinimi")
    private String mokkinimi;
    @Column(name = "katuosoite")
    private String katuosoite;
    @Column(name = "hinta")
    private double hinta;
    @Column(name = "kuvaus")
    private String kuvaus;
    @Column(name = "henkilomaara")
    private int henkilomaara;
    @Column(name = "varustelu")
    private String varustelu;
}
