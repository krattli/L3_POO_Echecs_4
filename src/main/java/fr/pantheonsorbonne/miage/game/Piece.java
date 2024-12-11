package fr.pantheonsorbonne.miage.game;
import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Piece {
    private final Player owner;
    protected Case position;

    public Piece(Player owner, Case position) {
        this.owner = owner;
        this.position = position;
        Echiquier.setPieceToPosition(this, position);
    }

    public Piece(Player owner, String position) throws WrongCaseFormatException {
        this.owner = owner;
        this.position = new Case(position);
    }

    public Case getPosition() {return position;}
    public void setPosition(Case position) {this.position = position;}
    public Player getOwner() {
        return owner;
    }

    public abstract ArrayList<Coup> getAllPossibleMoves();

    protected ArrayList<Coup> computeLinesOfMoves(int[][] directions, int porteePiece) {
        ArrayList<Coup> ALlPossibleMoves = new ArrayList<>();
        for (int[] direction : directions) {
            ALlPossibleMoves.addAll(getTraceLine(direction, porteePiece));
        }
        return ALlPossibleMoves;
    }

    protected ArrayList<Coup> computeLinesOfMoves(int[][] directions) {
        return computeLinesOfMoves(directions, 14);
    }

    private ArrayList<Coup> getTraceLine(int[] direction, int porteePiece) {
        ArrayList<Coup> traceLine = new ArrayList<>();
        for (int i = 1; i <= porteePiece; i++) {
            Case caseStep = this.position.getValidTranslatedCase(i*direction[0],i*direction[1]);
            if (caseStep != null) {
                Piece piecePrise = Echiquier.getPieceAt(caseStep);
                if (piecePrise == null) {
                    traceLine.add(new Coup(this,caseStep));
                }
                else if (piecePrise.getOwner().equals(this.owner)) {
                    return traceLine;
                }
                else {
                    traceLine.add( new Coup(this,caseStep,piecePrise) );
                }
            }
            else {
                return traceLine;
            }
        }
        return traceLine;
    }
    public abstract boolean isTheMoveLegal(Coup coup);

    public void kill(){
        Echiquier.setPieceToPosition(this, null);
    }
}