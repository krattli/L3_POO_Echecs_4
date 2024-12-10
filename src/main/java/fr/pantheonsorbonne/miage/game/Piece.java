package fr.pantheonsorbonne.miage.game;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import java.util.ArrayList;

public abstract class Piece {
    private final Player owner;
    private Case position;

    public Piece(Player owner, Case position) {
        this.owner = owner;
        this.position = position;
        Echiquier.setPieceToPosition(this, position);
    }

    public Case getPosition() {return position;}
    public void setPosition(Case position) {this.position = position;}
    public Player getOwner() {
        return owner;
    }

    public abstract ArrayList<Coup> getAllPossibleMoves();
    public abstract boolean isTheMoveLegal(Coup coup);

    public void kill(){
        Case caseLocation = this.getPosition();
        Echiquier.setPieceToPosition(this, null);
    }
}