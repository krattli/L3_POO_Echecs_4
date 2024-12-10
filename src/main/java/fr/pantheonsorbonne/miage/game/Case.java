package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Colonne;
import fr.pantheonsorbonne.miage.enums.Ligne;
import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;

public class Case {
    private final Colonne x;
    private final Ligne y;

    public Case(int x, int y) {
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

    public Case(Colonne x, Ligne y) {
        this.x = x;
        this.y = y;
    }

    public Case(Colonne x, int y) {
        //Case(x,Ligne.values()[y-1]);
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

    public boolean isValid() {
        boolean valid = true;
        int[] coordInt = this.getCoordInt();
        int x = coordInt[0];
        int y = coordInt[1];
        if ((x < 3 && y < 3) || (x > 10 && y > 10) || (x > 10 && y < 3) || (x < 3 && y > 10)) {
            valid = false;
        }
        return valid;
    }

    public static boolean isValidCoord(int x, int y) {
        if (x < 14 && y < 14 && x >= 0 && y >= 0) {
            return new Case(x, y).isValid();
        }
        else{
            return false;
        }
    }

    public Case getValidTranslatedCase(int x, int y ){
        int[] coordInt = this.getCoordInt();
        int newCaseX = coordInt[0] + x;
        int newCaseY = coordInt[1] + y;
        if (isValidCoord(newCaseX,newCaseY)){
            return new Case(newCaseX,newCaseY);
        }
        else{
            return null;
        }
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
