package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.game.pieces.PieceSimple;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;

import java.util.ArrayList;

public class Fou extends PieceSimple {
    private static final int VALUE_PIECE = 5;

    public Fou(Player owner, Case position) {
        super(owner, position);
    }
    public Fou(Player owner, String position) {
        super(owner, position);
    }

    @Override
    public int getValuePiece() {
        return VALUE_PIECE;
    }

    public ArrayList<Coup> getAllPossibleMoves() {
        return this.computeLinesOfMoves(this.getDirections());
    }

    public int[][] getDirections() {
        return new int[][]{{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    }
}
