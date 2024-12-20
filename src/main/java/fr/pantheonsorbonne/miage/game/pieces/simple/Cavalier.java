package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.game.pieces.PieceSimple;
import fr.pantheonsorbonne.miage.game.playersAI.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;

import java.util.ArrayList;

public class Cavalier extends PieceSimple {
    private static final int VALUE_PIECE = 3;

    public Cavalier(Player owner, Case position) {
        super(owner, position);
    }
    public Cavalier(Player owner, String position) {
        super(owner, position);
    }

    public int getValuePiece() {
        return VALUE_PIECE;
    }

    public ArrayList<Coup> getAllPossibleMoves() {
        return this.computeLinesOfMoves(getDirections(), 1);
    }

    public int[][] getDirections() {
        return new int[][]{{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
    }

}