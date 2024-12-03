package fr.pantheonsorbonne.miage.game.Pieces;

import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import java.util.ArrayList;

public class Pion extends Piece {
    public Pion(Player owner, Case position) {
        super(owner, position);
    }

    public ArrayList<Coup> getAllPossibleMoves() {
        return null;
    }

    public Boolean isTheMoveLegal() {
        return null;
    }
}
