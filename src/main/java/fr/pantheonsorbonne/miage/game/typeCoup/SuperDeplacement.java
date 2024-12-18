package fr.pantheonsorbonne.miage.game.typeCoup;

import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Piece;

import java.util.ArrayList;

public class SuperDeplacement extends Coup {
    private ArrayList<Piece> piecesMangees;

    public SuperDeplacement(Piece piece, Case arrivee) {
        super(piece, arrivee);
    }

    @Override
    public String toString() {
        String nombreDePrises;
        if (this.piecesMangees == null || this.piecesMangees.isEmpty()) {
            nombreDePrises = "-";
        } else {
            nombreDePrises = "x".repeat(this.piecesMangees.size());
        }
        return "S"
                + this.getPiece().getClass().getSimpleName().charAt(5)
                + this.getDepart().toString()
                + nombreDePrises
                + this.getArrivee().toString();
    }
}
