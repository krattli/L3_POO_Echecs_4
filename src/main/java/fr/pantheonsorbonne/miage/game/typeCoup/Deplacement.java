package fr.pantheonsorbonne.miage.game.typeCoup;

import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Piece;

public class Deplacement extends Coup {
    public Deplacement(Piece piece, Case arrivee) {
        super(piece, arrivee);
    }
    public Deplacement(Piece piece, String arrivee) {
        super(piece, arrivee);
    }
    @Override
    public String toString() {
        return this.getPiece().getClass().getSimpleName().charAt(0) + this.getCaseDepart().toString() + "-" + this.getCaseArrivee().toString();
    }
}
