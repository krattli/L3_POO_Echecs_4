package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.game.pieces.PieceSimple;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;

import java.util.ArrayList;

public class Tour extends PieceSimple {
    private boolean hasntMooved = true;

    public Tour(Player owner, Case position) {
        super(owner, position);
    }

    public Tour(Player owner, String position) {
        super(owner, position);
    }

    public ArrayList<Coup> getAllPossibleMoves() {
        return this.computeLinesOfMoves(this.getDirections());
    }

    public int[][] getDirections() {
        return new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    }

    public boolean hasntMooved() {
        return this.hasntMooved;
    }

}
