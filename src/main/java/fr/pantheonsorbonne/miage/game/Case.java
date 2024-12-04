package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Colonne;
import fr.pantheonsorbonne.miage.enums.Ligne;

public class Case {
    public final Colonne x;
    public final Ligne y;

    Case(Colonne x, Ligne y) {
        this.x = x;
        this.y = y;
    }

    Case(Colonne x, int y) {
        this.x = x;
        this.y = Ligne.values()[y-1];
    }

    Case(int x, int y) {
        this.x = Colonne.values()[x];
        this.y = Ligne.values()[13 - y];
    }

    public String toString() {
        return this.x.toString() + (this.y.ordinal() + 1);
    }

    public Colonne getColonne() {
        return x;
    }

    public Ligne getLigne() {
        return y;
    }

    public int[] getCoordInt() {
        int[] coordinates = new int[2];
        coordinates[0] = x.ordinal();
        coordinates[1] = 13 - y.ordinal();
        return coordinates;
    }

    public static void main(String[] args) {
        Case c = new Case(Colonne.A, 1);
        System.out.println(c.x + "  " + c.y);
        System.out.println(c.toString());
        System.out.println(c.getCoordInt()[0] + " " + c.getCoordInt()[1]);
    }
}
