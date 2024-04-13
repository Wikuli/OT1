package oma.grafiikka.ot1;

import jakarta.persistence.*;

@Entity
@Table(name = "lasku")
public class Lasku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lasku_id")
    private int lasku_id;
    @Column(name = "varaus_id")
    private int varaus_id;
    @Column(name = "summa")
    private double summa;
    @Column(name = "alv")
    private double alv;
    @Column(name = "maksettu")
    private char maksettu;

    public Lasku(int lasku_id, int varaus_id, double summa, double alv, char maksettu) {
        this.lasku_id = lasku_id;
        this.varaus_id = varaus_id;
        this.summa = summa;
        this.alv = alv;
        this.maksettu = maksettu;
    }

    public int getLasku_id() {
        return lasku_id;
    }

    public void setLasku_id(int lasku_id) {
        this.lasku_id = lasku_id;
    }

    public int getVaraus_id() {
        return varaus_id;
    }

    public void setVaraus_id(int varaus_id) {
        this.varaus_id = varaus_id;
    }

    public double getSumma() {
        return summa;
    }

    public void setSumma(double summa) {
        this.summa = summa;
    }

    public double getAlv() {
        return alv;
    }

    public void setAlv(double alv) {
        this.alv = alv;
    }

    public char getMaksettu() {
        return maksettu;
    }

    public void setMaksettu(char maksettu) {
        this.maksettu = maksettu;
    }
}
