package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Colonne;

public class Case {
    private Colonne x;
    private int y;

    Case(Colonne x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return this.x.toString()+this.y;
    }

    public Colonne getColonne() {
        return x;
    }

    public int getLigne() {
        return y;
    }
}
