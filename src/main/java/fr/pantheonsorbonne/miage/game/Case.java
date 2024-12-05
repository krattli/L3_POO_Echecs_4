package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Colonne;
import fr.pantheonsorbonne.miage.enums.Ligne;
import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;

public class Case {
    private final Colonne x;
    private final Ligne y;

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

    Case(Colonne x, Ligne y) {
        this.x = x;
        this.y = y;
    }

    Case(Colonne x, int y) {
        this.x = x;
        this.y = Ligne.values()[y-1];
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

    public Case getCorespondingCoordRotatedBy90(int n){
        int[] coordInt = getCoordInt();
        for (int i = 0; i < n; i++) {
            int tempVar = coordInt[0];
            coordInt[0] = 13 - coordInt[1];
            coordInt[1] = tempVar;
        }
        return new Case(coordInt[0], coordInt[1]);
    }

    public Case getCorespondingCoordRotatedBy90(){
        return getCorespondingCoordRotatedBy90(1);
    }
}
