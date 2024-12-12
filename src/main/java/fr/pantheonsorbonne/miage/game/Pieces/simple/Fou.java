package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.pieces.PieceSimple;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import java.util.ArrayList;

public class Fou extends PieceSimple {
    private static final int[][] directions = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    public Fou(Player owner, Case position) {
        super(owner, position);
    }
    public Fou(Player owner, String position) throws WrongCaseFormatException {
        super(owner, position);
    }

    public ArrayList<Coup> getAllPossibleMoves() {
        return this.computeLinesOfMoves(Fou.directions);
    }

    public boolean isTheMoveLegal(Coup coup) {
        return false;
    }
}
