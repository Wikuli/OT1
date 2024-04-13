package oma.grafiikka.ot1;

import jakarta.persistence.*;

@Entity
@Table(name = "mokki")
public class Mokki {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "mokki_id")
    private int mokki_id;
}
