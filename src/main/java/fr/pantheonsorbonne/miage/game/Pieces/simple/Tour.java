package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.pieces.PieceSimple;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import java.util.ArrayList;

public class Tour extends PieceSimple {
    public Tour(Player owner, Case position) {
        super(owner, position);
    }
    public Tour(Player owner, String position) throws WrongCaseFormatException {
        super(owner, position);
    }

    public ArrayList<Coup> getAllPossibleMoves() {
        return this.computeLinesOfMoves(this.getDirections());
    }
    protected int[][] getDirections() {
        return new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    }

    public boolean isTheMoveLegal(Coup coup) {
        return false;
    }
}
