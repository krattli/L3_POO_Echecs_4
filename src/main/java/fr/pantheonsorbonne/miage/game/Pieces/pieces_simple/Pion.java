package fr.pantheonsorbonne.miage.game.Pieces.pieces_simple;

import fr.pantheonsorbonne.miage.enums.Colonne;
import fr.pantheonsorbonne.miage.enums.Ligne;
import fr.pantheonsorbonne.miage.game.Pieces.PieceSimple;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;

import java.util.ArrayList;

public class Pion extends PieceSimple {
    private static int[][] directions;

    public Pion(Player owner, Case position) {
        super(owner, position);
        switch (this.getOwner().getColor()) {
            case RED:
                directions = new int[][]{{0, 1},{-1, 1}, {1, 1}};
                break;
            case GREEN:
                directions = new int[][]{{-1, 0},{-1, -1}, {-1, 1}};
                break;
            case YELLOW:
                directions = new int[][]{{0, -1},{-1, -1}, {1, -1}};
                break;
            case BLUE:
                directions = new int[][]{{1, 1},{1, -1}, {1, 1}};
                break;
        }
    }

    @Override
    public ArrayList<Coup> getAllPossibleMoves() {
        ArrayList<Coup> coups = new ArrayList<>();

        int nbCoupsDevant = 1;
        if(this.isFirstTime()){nbCoupsDevant = 2;}
        ArrayList<Coup> enAvant = this.computeLinesOfMoves(new int[][] {Pion.directions[0]},nbCoupsDevant);

        for(Coup coup : enAvant){
            if(coup.getPiecePrise() != null){break;}
            else{coups.add(coup);}
        }

        ArrayList<Coup> prisesDiagonales = this.computeLinesOfMoves(new int[][] {Pion.directions[1],Pion.directions[2]},1);
        for(Coup coup : prisesDiagonales){
            if(coup.getPiecePrise() == null){break;}
            else{coups.add(coup);}
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

    public boolean isTheMoveLegal(Coup coup) {
        return false;
    }

    public boolean isPromuToQueen() {
        boolean isPromu = false;
        switch (this.getOwner().getColor()) {
            case RED:
                if (this.getPosition().getLigne() == Ligne.FOURTEEN) {
                    isPromu = true;
                }
                break;
            case YELLOW:
                if (this.getPosition().getColonne() == Colonne.A) {
                    isPromu = true;
                }
                break;
            case GREEN:
                //finir de coder ça lmao

        }
        return isPromu;
    }

    public void promouvoirPionToQueen() {

    }
}
