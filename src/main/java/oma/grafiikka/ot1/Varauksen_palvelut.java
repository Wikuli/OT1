package oma.grafiikka.ot1;

import jakarta.persistence.*;

@Entity
@Table(name = "varauksen_palvelut" )
public class Varauksen_palvelut {
    @ManyToOne (fetch = FetchType.LAZY)
    private Varaus varaus;
    @ManyToOne (fetch = FetchType.LAZY)
    private Palvelu palvelu;
    @Column (name = "lkm")
    private int lkm;

    public Varauksen_palvelut(Varaus varaus, Palvelu palvelu, int lkm) {
        this.varaus = varaus;
        this.palvelu = palvelu;
        this.lkm = lkm;
    }

    public Varaus getVaraus() {
        return varaus;
    }

    public void setVaraus(Varaus varaus) {
        this.varaus = varaus;
    }

    public Palvelu getPalvelu() {
        return palvelu;
    }

    public void setPalvelu(Palvelu palvelu) {
        this.palvelu = palvelu;
    }

    public int getLkm() {
        return lkm;
    }

    public void setLkm(int lkm) {
        this.lkm = lkm;
    }
}
