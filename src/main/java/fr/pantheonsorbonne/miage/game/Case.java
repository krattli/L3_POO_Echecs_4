package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Colonne;
import fr.pantheonsorbonne.miage.enums.Ligne;
import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;

public class Case {
    private final Colonne X;
    private final Ligne Y;

    public Case(int X, int Y) {
        this.X = Colonne.values()[X];
        this.Y = Ligne.values()[13 - Y];
    }

    public Case(String notation) {
        if (notation == null || notation.length() < 2) {
            System.out.println("mauvais format de caes : " + notation);
        }
        String colonneStr = notation.substring(0, 1).toUpperCase();
        String ligneStr = notation.substring(1);

        this.X = Colonne.valueOf(colonneStr);
        int numeroLigne = Integer.parseInt(ligneStr);
        this.Y = Ligne.values()[numeroLigne - 1];
    }

    //Ce constructeur est un peu useless en vrai (et flemme de débug le this.X=Y;)
    public Case(Colonne Y, Ligne X) {
        this.X = Y;
        this.Y = X;
    }

    //Lui aussi il est super useless
    public Case(Colonne X, int Y) {
        this.X = X;
        this.Y = Ligne.values()[Y - 1];
    }

    public String toString() {
        return this.X.toString() + (this.Y.ordinal() + 1);
    }

    public Colonne getColonne() {
        return this.X;
    }

    public Ligne getLigne() {
        return this.Y;
    }

    public int[] getCoordInt() {
        int[] coordinates = new int[2];
        coordinates[0] = X.ordinal();
        coordinates[1] = 13 - Y.ordinal();
        return coordinates;
    }

    public boolean isValid() {
        boolean valid = true;
        int[] coordInt = this.getCoordInt();
        int Y = coordInt[0];
        int X = coordInt[1];
        if ((Y < 3 && X < 3) || (Y > 10 && X > 10) || (Y > 10 && X < 3) || (Y < 3 && X > 10)) {
            valid = false;
        }
        return valid;
    }

    public static boolean isValidCoord(int Y, int X) {
        if (Y < 14 && X < 14 && Y >= 0 && X >= 0) {
            return new Case(Y, X).isValid();
        } else {
            return false;
        }
    }

    public Case getValidTranslatedCase(int X, int Y) {
        int[] coordInt = this.getCoordInt();
        int newCaseX = coordInt[0] + X;
        int newCaseY = coordInt[1] - Y;
        if (isValidCoord(newCaseX, newCaseY)) {
            return new Case(newCaseX, newCaseY);
        } else {
            return null;
        }
    }

    public Case getCoordRotatedBy90(int n) {
        int[] coordInt = getCoordInt();
        for (int i = 0; i < n; i++) {
            int tempVar = coordInt[0];
            coordInt[0] = 13 - coordInt[1];
            coordInt[1] = tempVar;
        }
        return new Case(coordInt[0], coordInt[1]);
    }

    public Case getCoordRotatedBy90() {
        return getCoordRotatedBy90(1);
    }
}
