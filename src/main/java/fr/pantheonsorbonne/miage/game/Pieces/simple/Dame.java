package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.game.pieces.PieceSimple;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;

import java.util.ArrayList;

public class Dame extends PieceSimple {
    private static final int VALUE_PIECE_NORMAL = 9;
    private static final int VALUE_PIECE_PROMUE = 1;

    private int value_Piece = VALUE_PIECE_NORMAL;

    public Dame(Player owner, Case position) {
        super(owner, position);
    }
    public Dame(Player owner, String position) {
        super(owner, position);
    }

    public void setValuePiecePromotion() {
        this.value_Piece = VALUE_PIECE_PROMUE;
    }
    public int getValuePiece() {
        return value_Piece;
    }

    public ArrayList<Coup> getAllPossibleMoves() {
        return this.computeLinesOfMoves(this.getDirections());
    }

    public int[][] getDirections() {
        return new int[][]{{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    }

}
