package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.game.playersAI.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;

import java.util.ArrayList;

public class Tour extends FirstMovePiece {
    private static final int VALUE_PIECE = 5;

    public Tour(Player owner, Case position) {
        super(owner, position);
    }
    public Tour(Player owner, String position) {
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
        return new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    }
}
