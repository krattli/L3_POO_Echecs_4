package fr.pantheonsorbonne.miage.game.typeCoup;

import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Piece;

public class Rock extends Coup {
    public Rock(Piece piece, Case arrivee) {
        super(piece, arrivee);
    }

    @Override
    public String toString() {
        return "";
    }
}
