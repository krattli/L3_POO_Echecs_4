package fr.pantheonsorbonne.miage.game.pieces;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.game.typeCoup.Deplacement;
import fr.pantheonsorbonne.miage.game.typeCoup.Prise;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import java.util.ArrayList;

public abstract class PieceSimple extends Piece {

    protected static int PORTEE_MAX = 14;

    public PieceSimple(Player owner, Case position) {
        super(owner, position);
    }
    public PieceSimple(Player owner, String position) {
        super(owner, position);
    }

    protected ArrayList<Coup> computeLinesOfMoves(int[][] directions, int porteePiece) {
        ArrayList<Coup> ALlPossibleMoves = new ArrayList<>();
        for (int[] direction : directions) {
            ALlPossibleMoves.addAll(this.getTraceLine(direction, porteePiece));
        }
        return ALlPossibleMoves;
    }

    protected ArrayList<Coup> computeLinesOfMoves(int[][] directions) {return computeLinesOfMoves(directions, PORTEE_MAX);}

    protected ArrayList<Coup> getTraceLine(int[] direction, int porteePiece) {
        ArrayList<Coup> traceLine = new ArrayList<>();
        for (int i = 1; i <= porteePiece; i++) {
            Case caseStep = this.position.getValidTranslatedCase(i*direction[0],i*direction[1]);
            if (caseStep != null) {
                Piece piecePrise = this.getOwner().getEchiquier().getPieceAt(caseStep);
                if (piecePrise == null) {
                    traceLine.add(new Deplacement(this,caseStep));
                }
                else if (piecePrise.getOwner().equals(this.getOwner())) {
                    return traceLine;
                }
                else {
                    traceLine.add( new Prise(this,caseStep,piecePrise) );
                    return traceLine;
                }
            }
            else {
                return traceLine;
            }
        }
        return traceLine;
    }

    @Override
    public abstract ArrayList<Coup> getAllPossibleMoves();

}