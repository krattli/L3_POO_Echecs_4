package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.pieces.PieceSimple;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

import java.util.ArrayList;

public abstract class firstMovePiece extends PieceSimple {
    private boolean hasntMooved = true;

    public firstMovePiece(Player owner, Case position) {
        super(owner, position);
    }

    public firstMovePiece(Player owner, String position) {
        super(owner, position);
    }

    public boolean hasntMooved() {return this.hasntMooved;}
    public void hasMooved() {hasntMooved = true;}

    @Override
    public abstract  ArrayList<Coup> getAllPossibleMoves();

    @Override
    public abstract int[][] getDirections();
}
