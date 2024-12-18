package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.enums.Colonne;
import fr.pantheonsorbonne.miage.enums.Ligne;
import fr.pantheonsorbonne.miage.game.pieces.PieceSimple;
import fr.pantheonsorbonne.miage.game.typeCoup.Deplacement;
import fr.pantheonsorbonne.miage.game.typeCoup.Prise;
import fr.pantheonsorbonne.miage.game.typeCoup.Promotion;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;

import java.util.ArrayList;

public class Pion extends PieceSimple {
    private static final int VALUE_PIECE = 1;

    public Pion(Player owner, Case position) {
        super(owner, position);
    }
    public Pion(Player owner, String position) {
        super(owner, position);
    }

    @Override
    public int getValuePiece() {
        return VALUE_PIECE;
    }

    @Override
    public ArrayList<Coup> getAllPossibleMoves() {
        ArrayList<Coup> coups = new ArrayList<>();

        int nbCoupsDevant = 1;
        if(this.isFirstTime()){nbCoupsDevant = 2;}
        ArrayList<Coup> enAvant = this.computeLinesOfMoves(new int[][] {getDirections()[0]},nbCoupsDevant);

        for(Coup coup : enAvant){
            if(coup.getClass() == Prise.class){break;}
            else{
                if (isPromoted()){
                    coups.add( new Promotion(this, coup.getCaseArrivee()));
                }
                else {coups.add(coup);}
            }
        }

        ArrayList<Coup> prisesDiagonales = this.computeLinesOfMoves(new int[][] {getDirections()[1],getDirections()[2]},1);
        for(Coup coup : prisesDiagonales){
            if(coup.getClass() != Deplacement.class){coups.add(coup);}
        }
        return coups;
    }

    private boolean isFirstTime(){
        switch (this.getOwner().getColor()) {
            case RED:
                if(this.getPosition().getLigne() == Ligne.TWO){return true;}
                break;
            case GREEN:
                if (this.getPosition().getColonne() == Colonne.M){return true;}
                break;
            case YELLOW:
                if (this.getPosition().getLigne() == Ligne.THIRTEEN){return true;}
                break;
            case BLUE:
                if (this.getPosition().getColonne() == Colonne.B) {return true;}
                break;
            default:
                return false;
        }
        return false;
    }

    public int[][] getDirections() {
        return switch (this.getOwner().getColor()) {
            case RED -> new int[][]{{0, 1}, {-1, 1}, {1, 1}};
            case GREEN -> new int[][]{{-1, 0}, {-1, -1}, {-1, 1}};
            case YELLOW -> new int[][]{{0, -1}, {-1, -1}, {1, -1}};
            case BLUE -> new int[][]{{1, 0}, {1, -1}, {1, 1}};
        };
    }

    public boolean isPromoted() {
        boolean isPromu = false;
        switch (this.getOwner().getColor()) {
            case RED:
                if (this.getPosition().getLigne() == Ligne.THIRTEEN) {
                    isPromu = true;
                }
                break;
            case YELLOW:
                if (this.getPosition().getLigne() == Ligne.TWO) {
                    isPromu = true;
                }
                break;
            case GREEN:
                if (this.getPosition().getColonne() == Colonne.B) {
                    isPromu = true;
                }
                break;
            case BLUE:
                if (this.getPosition().getColonne() == Colonne.M) {
                    isPromu = true;
                }
                break;
        }
        return isPromu;
    }

    public void promouvoirPionToQueen() {

    }
}
