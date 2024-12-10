package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Colonne;
import fr.pantheonsorbonne.miage.enums.Ligne;
import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;

public class Case {
    private final Colonne X;
    private final Ligne Y;

    //Constructeur renvoie des valeurs incompréhensible
    //à débeuger ou supprimer vu que de toute manière il est innutile
    public Case(int Y, int X) {
        this.X = Colonne.values()[13 - Y];
        this.Y = Ligne.values()[X];
    }

    public Case(String notation) throws WrongCaseFormatException {
        if (notation == null || notation.length() < 2) {
            throw new WrongCaseFormatException("La notation doit être au format algébrique classique");
        }
        String colonneStr = notation.substring(0, 1).toUpperCase();
        String ligneStr = notation.substring(1);

        try {
            this.X = Colonne.valueOf(colonneStr);

            int numeroLigne = Integer.parseInt(ligneStr);
            this.Y = Ligne.values()[numeroLigne - 1];

        } catch (IllegalArgumentException e) {
            throw new WrongCaseFormatException("Notation invalide : " + notation);
        }
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

    public Colonne getColonne() {return this.X;}
    public Ligne getLigne() {return this.Y;}

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
        int newCaseY = coordInt[1] + Y;
        if (isValidCoord(newCaseX, newCaseY)) {
            return new Case(newCaseY, newCaseX);
        } else {
            return null;
        }
    }

    public Case getCorrespondingCoordRotatedBy90(int n) {
        int[] coordInt = getCoordInt();
        for (int i = 0; i < n; i++) {
            int tempVar = coordInt[0];
            coordInt[0] = 13 - coordInt[1];
            coordInt[1] = tempVar;
        }
        return new Case(coordInt[0], coordInt[1]);
    }

    public Case getCorrespondingCoordRotatedBy90() {
        return getCorrespondingCoordRotatedBy90(1);
    }
}
