package fr.pantheonsorbonne.miage.game.pieces;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import java.util.ArrayList;

public abstract class SuperPiece extends Piece {

    public SuperPiece(Player owner, Case position) {
        super(owner, position);
    }

    public abstract boolean isOccupiedByThisPiece(Case position);

    public abstract ArrayList<Case> getCasesOccupiedByThisPiece();

}