package fr.pantheonsorbonne.miage.game.Pieces.pieces_simple;

import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import java.util.ArrayList;

public class Fou extends Piece {
    private static final int[][] directions = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    public Fou(Player owner, Case position) {
        super(owner, position);
    }

    public ArrayList<Coup> getAllPossibleMoves() {
        return this.computeLinesOfMoves(Fou.directions);
    }

    public boolean isTheMoveLegal(Coup coup) {
        return false;
    }
}
