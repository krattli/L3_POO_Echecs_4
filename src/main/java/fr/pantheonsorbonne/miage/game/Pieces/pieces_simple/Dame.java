package fr.pantheonsorbonne.miage.game.Pieces.pieces_simple;

import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import java.util.ArrayList;

public class Dame extends Piece {
    public Dame(Player owner, Case position) {
        super(owner, position);
    }

    public ArrayList<Coup> getAllPossibleMoves() {
        return null;
    }

    public Boolean isTheMoveLegal() {
        return null;
    }
}
