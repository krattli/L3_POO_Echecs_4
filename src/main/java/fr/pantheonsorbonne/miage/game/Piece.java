package fr.pantheonsorbonne.miage.game;
import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.typeCoup.Deplacement;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import java.util.ArrayList;

public abstract class Piece {
    private final Player owner;
    protected Case position;

    public Piece(Player owner, Case position) {
        this.owner = owner;
        this.position = position;
        owner.getEchiquier().setPieceToPosition(this, position);
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
    public abstract int[][] getDirections();

    //never used, donc faire attention à bien supp
    public Coup getNewCoup(Case destination) {
        return new Deplacement(this, destination);
    }

    public void kill(){
        owner.getEchiquier().setPieceToPosition(this, null);
    }
}