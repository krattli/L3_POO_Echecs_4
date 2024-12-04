package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Colonne;
import fr.pantheonsorbonne.miage.enums.Ligne;
import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;

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
        this.x = Colonne.values()[13 - x];
        this.y = Ligne.values()[y];
    }

    public Case(String notation) throws WrongCaseFormatException {
        if (notation == null || notation.length() < 2) {
            throw new WrongCaseFormatException("La notation doit être au format algébrique classique");
        }
        String colonneStr = notation.substring(0, 1).toUpperCase();
        String ligneStr = notation.substring(1);

        try {
            this.x = Colonne.valueOf(colonneStr);

            int numeroLigne = Integer.parseInt(ligneStr);
            this.y = Ligne.values()[numeroLigne - 1];

        } catch (IllegalArgumentException e) {
            throw new WrongCaseFormatException("Notation invalide : " + notation);
        }
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
        coordinates[0] = 13 - x.ordinal();
        coordinates[1] = y.ordinal();
        return coordinates;
    }

    public static void main(String[] args) throws WrongCaseFormatException {
        Case c = new Case(13,0);
        System.out.println(c.x + "  " + c.y);
        System.out.println(c.toString());
        System.out.println(c.getCoordInt()[0] + " " + c.getCoordInt()[1]);
    }
}
