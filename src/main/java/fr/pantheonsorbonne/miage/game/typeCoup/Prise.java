package fr.pantheonsorbonne.miage.game.typeCoup;

import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Piece;

public class Prise extends Coup {
    Piece prise;

    public Prise(Piece piece, Case arrivee) {
        super(piece, arrivee);
    }

    public Piece getPiecePrise() {
        return prise;
    }

    @Override
    public String toString() {
        return this.getPiece().getClass().getSimpleName().charAt(0) + this.getDepart().toString() + "x" + this.getArrivee().toString();
    }

    public Prise(Piece piece, Case arrivee, Piece prise) {
        super(piece, arrivee);
        this.prise = prise;
    }


}
