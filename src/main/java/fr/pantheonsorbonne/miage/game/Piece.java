package fr.pantheonsorbonne.miage.game;
import fr.pantheonsorbonne.miage.game.typeCoup.Deplacement;
import fr.pantheonsorbonne.miage.game.playersAI.Player;
import java.util.ArrayList;

public abstract class Piece {
    private final Player owner;
    protected Case position;

    public Piece(Player owner, Case position) {
        this.owner = owner;
        this.position = position;
        owner.getEchiquier().setPieceToPosition(this, position);
    }

    public Piece(Player owner, String position) {
        this(owner, new Case(position));
    }

    public Case getPosition() {return position;}
    public void setPosition(Case position) {this.position = position;}
    public Player getOwner() {
        return owner;
    }
    public abstract int getValuePiece();

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