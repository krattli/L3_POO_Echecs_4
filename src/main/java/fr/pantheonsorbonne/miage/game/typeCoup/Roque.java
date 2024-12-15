package fr.pantheonsorbonne.miage.game.typeCoup;

import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Piece;

public class Roque extends Coup {
    public Roque(Piece piece, Case arrivee) {
        super(piece, arrivee);
    }

    @Override
    public String toString() {
        return "";
    }
}
