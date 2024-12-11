package fr.pantheonsorbonne.miage.game.Pieces.pieces_simple;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import java.util.ArrayList;

public class Cavalier extends Piece {
    private static final int[][] directions = {{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1},{-2,1},{-1,2}};
    public Cavalier(Player owner, Case position) {
        super(owner, position);
    }
    public Cavalier(Player owner, String position) throws WrongCaseFormatException {
        super(owner, position);
    }

    public ArrayList<Coup> getAllPossibleMoves() {
        return this.computeLinesOfMoves(Cavalier.directions,1);
    }

    public boolean isTheMoveLegal(Coup coup) {
        return false;
    }

}