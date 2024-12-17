package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.game.typeCoup.Deplacement;
import fr.pantheonsorbonne.miage.game.typeCoup.Prise;
import fr.pantheonsorbonne.miage.game.typeCoup.Roque;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import java.util.ArrayList;

public class Roi extends FirstMovePiece {

    public Roi(Player owner, Case position) {
        super(owner, position);
    }
    public Roi(Player owner, String position){
        super(owner, position);
    }

    public int[][] getDirections() {return new int[][] {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};}


    public ArrayList<Coup> getAllPossibleMoves() {

        ArrayList<Coup> coupsPossiblesWithoutCheckChecking =  this.computeLinesOfMoves(this.getDirections(),1);
        int thisPlayerOrdinal = this.getOwner().getColor().ordinal();

        boolean[][][] casesMenacees = this.getOwner().getEchiquier().getCasesMenacees();
        ArrayList<Coup> coupsReels = new ArrayList<>();
        parcourirLesCoups:
        for (Coup coup : coupsPossiblesWithoutCheckChecking) {
            int[] coord = coup.getArrivee().getCoordInt();
            for (int i = 0 ; i < casesMenacees.length ; i++) {
                if ( i != thisPlayerOrdinal){
                    if (casesMenacees[i][coord[1]][coord[0]]) {
                        continue parcourirLesCoups;
                    }
                }
            }
            coupsReels.add(coup);
        }

        ArrayList<Coup> illegalCoups = this.getIllegalCoups();

        for (int i = 0; i < coupsReels.size(); i++) {
            for (Coup coupIll : illegalCoups) {
                if (coupsReels.get(i).getArrivee().equals(coupIll.getArrivee())) {
                    coupsReels.remove(i);
                    i--;
                    break;
                }
            }
        }

        coupsReels.addAll(Roque.getRoques(this.getOwner()));

        return coupsReels;
    }

    private ArrayList<Coup> getIllegalCoups() {
        ArrayList<Coup> coups = new ArrayList<>();
        int[][] directions = getDirections();
        for (int i = 0; i < directions.length; i++) {
            int[] direction = directions[i];
            ArrayList<Coup> tempStockageCoups = this.getTraceLine(direction , PORTEE_MAX);
            for (Coup coup : tempStockageCoups) {
                if (coup instanceof Prise) {
                    Piece prise = ((Prise) coup).getPiecePrise();
                    if (prise.getOwner() != this.getOwner()) {
                        boolean isRightTypePiece = isIsRightTypePiece((Prise) coup, prise, i);
                        if (isRightTypePiece) {
                            int[] oppositeDirection = new int[] {-direction[0], -direction[1]};
                            Coup opposite = this.getTraceLine(oppositeDirection,1).getFirst();
                            if (opposite instanceof Deplacement) {
                                coups.add(opposite);
                            }
                        }
                    }
                }
            }
        }
        return coups;
    }

    private static boolean isIsRightTypePiece(Prise coup, Piece prise, int i) {
        boolean isRightTypePiece = false;
        if (prise.getClass() == Dame.class) {
            isRightTypePiece = true;
        }
        else if (i % 2 == 0 && prise.getClass() == Tour.class) {
            isRightTypePiece = true;
        }
        else if (i % 2 == 1 && coup.getPiecePrise().getClass() == Tour.class) {
            isRightTypePiece = true;
        }
        return isRightTypePiece;
    }
}
