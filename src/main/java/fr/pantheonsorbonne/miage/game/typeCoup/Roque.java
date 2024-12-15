package fr.pantheonsorbonne.miage.game.typeCoup;

import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.game.pieces.simple.Roi;
import fr.pantheonsorbonne.miage.game.pieces.simple.Tour;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

import java.util.ArrayList;

public class Roque extends Coup {
    private final boolean isGrandRoque;

    public Roque(Piece piece, Case arrivee, boolean isGrandRoque) {
        super(piece, arrivee);
        this.isGrandRoque = isGrandRoque;
    }

    @Override
    public String toString() {
        if (isGrandRoque) {
            return "O-O-O";
        }
        return "O-O";
    }
    public boolean isGrandRoque() {
        return isGrandRoque;
    }

    public static ArrayList<Roque> getRoques(Player player) {
        ArrayList<Roque> roques = new ArrayList<>();

        int playerNumberRotation = player.getColor().ordinal();

        Piece roiARoquer = player.getEchiquier().getPieceAt(new Case("H1").getCoordRotatedBy90(playerNumberRotation));
        Piece tourPetitRoque = player.getEchiquier().getPieceAt( new Case("K1").getCoordRotatedBy90(playerNumberRotation));
        Piece tourGrandRoque = player.getEchiquier().getPieceAt(new Case("D1").getCoordRotatedBy90(playerNumberRotation));

        if (roiARoquer != null && roiARoquer.getClass() == Roi.class && ((Roi) roiARoquer).hasntMooved()) {
            if (tourPetitRoque != null && tourPetitRoque.getClass() == Tour.class && ((Tour) tourPetitRoque).hasntMooved()){
                roques.add(new Roque(roiARoquer, tourPetitRoque.getPosition(), false));
            }
            if (tourGrandRoque != null && tourGrandRoque.getClass() == Tour.class && ((Tour) tourGrandRoque).hasntMooved()){
                roques.add(new Roque(roiARoquer, tourGrandRoque.getPosition(), true));
            }
        }
        return roques;
    }
}
