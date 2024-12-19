package fr.pantheonsorbonne.miage.game.typeCoup;

import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Piece;

public class Promotion extends Coup {

    public Promotion(Piece piece, Case arrivee) {
        super(piece, arrivee);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName().charAt(0) + this.getCaseDepart().toString() + "-" + this.getCaseArrivee().toString() + "D";
    }

    public Promotion(Piece piece, Case arrivee, Piece prise) {
        super(piece, arrivee);
    }


}
