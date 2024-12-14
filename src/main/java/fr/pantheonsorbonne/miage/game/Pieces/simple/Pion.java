package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.enums.Colonne;
import fr.pantheonsorbonne.miage.enums.Ligne;
import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.pieces.PieceSimple;
import fr.pantheonsorbonne.miage.game.typeCoup.Deplacement;
import fr.pantheonsorbonne.miage.game.typeCoup.Prise;
import fr.pantheonsorbonne.miage.game.typeCoup.Promotion;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;

import java.util.ArrayList;

public class Pion extends PieceSimple {

    public Pion(Player owner, Case position) {
        super(owner, position);
    }

    public Pion(Player owner, String position) throws WrongCaseFormatException {
        super(owner, position);
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
                    coups.add(new Promotion(this, coup.getDepart()));
                }
                else {coups.add(coup);}
            }
        }

        ArrayList<Coup> prisesDiagonales = this.computeLinesOfMoves(new int[][] {getDirections()[1],getDirections()[2]},1);
        for(Coup coup : prisesDiagonales){
            if(coup.getClass() == Deplacement.class){continue;}
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

    private int[][] getDirections() {
        int[][] directions = new int[3][2];
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
                directions = new int[][]{{1, 0},{1, -1}, {1, 1}};
                break;
        }
        return directions;
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