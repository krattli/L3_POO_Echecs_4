package fr.pantheonsorbonne.miage.game.Pieces.pieces_simple;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.Pieces.PieceSimple;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import java.util.ArrayList;

public class Roi extends PieceSimple {
    private static final int[][] directions = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    public Roi(Player owner, Case position) {
        super(owner, position);
    }
    public Roi(Player owner, String position) throws WrongCaseFormatException {
        super(owner, position);
    }

    public ArrayList<Coup> getAllPossibleMoves() {
        return this.computeLinesOfMoves(Roi.directions,1);
    }

    public boolean isTheMoveLegal(Coup coup) {
        return false;
    }
}
