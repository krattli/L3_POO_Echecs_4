package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.pieces.PieceSimple;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import java.util.ArrayList;

public class Roi extends PieceSimple {
    public Roi(Player owner, Case position) {
        super(owner, position);
    }
    public Roi(Player owner, String position) throws WrongCaseFormatException {
        super(owner, position);
    }

    protected int[][] getDirections() {return new int[][] {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};}

    public ArrayList<Coup> getAllPossibleMoves() {
        ArrayList<Coup> coupsPossibles =  this.computeLinesOfMoves(this.getDirections(),1);
        int thisPlayerOrdinal = this.getOwner().getColor().ordinal();
        boolean[][][] casesMenacees = this.getOwner().getEchiquier().getCasesMenacees();
        ArrayList<Coup> coupsReels = new ArrayList<Coup>();
        parcoursLesCoups:
        for (Coup coup : coupsPossibles) {
            int coord[] = coup.getArrivee().getCoordInt();
            for (int i = 0 ; i < casesMenacees.length ; i++) {
                if ( i != thisPlayerOrdinal){
                    if (casesMenacees[i][coord[1]][coord[0]]) {
                        continue parcoursLesCoups;
                    }
                }
            }
            coupsReels.add(coup);
        }
        return coupsReels;
    }
}
