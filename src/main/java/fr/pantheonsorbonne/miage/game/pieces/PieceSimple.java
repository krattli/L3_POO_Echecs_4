package fr.pantheonsorbonne.miage.game.pieces;
import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.game.typeCoup.Deplacement;
import fr.pantheonsorbonne.miage.game.typeCoup.Prise;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import java.util.ArrayList;

public abstract class PieceSimple extends Piece {

    public PieceSimple(Player owner, Case position) {
        super(owner, position);
    }
    public PieceSimple(Player owner, String position) throws WrongCaseFormatException {
        super(owner, new Case(position));
    }

    protected ArrayList<Coup> computeLinesOfMoves(int[][] directions, int porteePiece) {
        ArrayList<Coup> ALlPossibleMoves = new ArrayList<>();
        for (int[] direction : directions) {
            ALlPossibleMoves.addAll(this.getTraceLine(direction, porteePiece));
        }
        return ALlPossibleMoves;
    }

    protected ArrayList<Coup> computeLinesOfMoves(int[][] directions) {return computeLinesOfMoves(directions, 14);}

    private ArrayList<Coup> getTraceLine(int[] direction, int porteePiece) {
        ArrayList<Coup> traceLine = new ArrayList<>();
        for (int i = 1; i <= porteePiece; i++) {
            Case caseStep = this.position.getValidTranslatedCase(i*direction[0],i*direction[1]);
            if (caseStep != null) {
                Piece piecePrise = Echiquier.getPieceAt(caseStep);
                if (piecePrise == null) {
                    traceLine.add(new Deplacement(this,caseStep));
                }
                else if (piecePrise.getOwner().equals(this.getOwner())) {
                    return traceLine;
                }
                else {
                    traceLine.add( new Prise(this,caseStep,piecePrise) );
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