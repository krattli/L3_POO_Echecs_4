package fr.pantheonsorbonne.miage.game.typeCoup;

import fr.pantheonsorbonne.miage.enums.Color;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.game.pieces.simple.Roi;
import fr.pantheonsorbonne.miage.game.pieces.simple.Tour;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

import java.util.ArrayList;

public class Roque extends Coup {
    private static final String[] CASES_TO_CHECK_PETIT_ROQUE = {"J1", "I1"};
    private static final String[] CASES_TO_CHECK_GRAND_ROQUE = {"E1", "F1", "G1"};
    private static final String[] POS_TOUR_PETIT_ROQUE = {"K1", "N11", "D14", "A4"};
    private static final String[] POS_TOUR_GRAND_ROQUE = {"D1", "N4", "K14", "A11"};
    private static final int[][] DIRECTIONS_GRAND_ROQUE = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private static final int[][] DIRECTIONS_PETIT_ROQUE = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private final boolean isGrandRoque;
    private final Tour tourARoquer;

    public Roque(Roi roi, boolean isGrandRoque) {
        super(roi, (Case) null);
        this.isGrandRoque = isGrandRoque;
        this.tourARoquer = getTourARoquer(roi, this.isGrandRoque);
    }

    @Override
    public Roi getPiece() {
        return (Roi) super.getPiece();
    }

    public Tour getTourARoquer() {
        return tourARoquer;
    }

    @Override
    public String toString() {
        return isGrandRoque ? "O-O-O" : "O-O";
    }

    public boolean isGrandRoque() {
        return isGrandRoque;
    }

    public static Roque[] getRoques(Player player) {
        Roque[] roques = new Roque[2];
        Roi roiARoquer = player.getHisKing();

        if (roiARoquer != null && roiARoquer.hasntMooved()) {
            roques[0] = addRoqueIfValid(roiARoquer, false, CASES_TO_CHECK_PETIT_ROQUE);
            roques[1] = addRoqueIfValid(roiARoquer, true, CASES_TO_CHECK_GRAND_ROQUE);
        }
        return roques;
    }

    private static Roque addRoqueIfValid(Roi roiARoquer, boolean isGrandRoque, String[] casesToCheck) {
        Tour tour = getTourARoquer(roiARoquer, isGrandRoque);
        if (tour != null && tour.hasntMooved() && areIntermediateCasesSafe(casesToCheck, roiARoquer)) {
            return new Roque(roiARoquer, isGrandRoque);
        }
        return null;
    }

    private static boolean areIntermediateCasesSafe(String[] cases, Piece roiARoquer) {
        Player player = roiARoquer.getOwner();
        int ordinalPlayer = player.getOrderInGame();
        boolean[][][] threats = player.getEchiquier().getCasesMenacees();

        for (String caseName : cases) {
            Case caseToCheck = new Case(caseName).getCoordRotatedBy90(ordinalPlayer);

            if (player.getEchiquier().getPieceAt(caseToCheck) != null) return false;

            int[] coord = caseToCheck.getCoordInt();
            for (int i = 0; i < threats.length; i++) {
                if (i != ordinalPlayer && threats[i][coord[1]][coord[0]]) return false;
            }
        }
        return true;
    }

    private static Tour getTourARoquer(Roi roiARoquer, boolean isGrandRoque) {
        String casePosition = getTourPositionForColor(roiARoquer.getOwner().getColor(), isGrandRoque);
        Piece piece = roiARoquer.getOwner().getEchiquier().getPieceAt(casePosition);

        return (piece instanceof Tour) ? (Tour) piece : null;
    }

    private static String getTourPositionForColor(Color color, boolean isGrandRoque) {
        return isGrandRoque ? POS_TOUR_GRAND_ROQUE[color.ordinal()] : POS_TOUR_PETIT_ROQUE[color.ordinal()];
    }

    public int[] getSensDuRoqueRoi() {
        int colorIndex = this.getPiece().getOwner().getOrderInGame();
        return isGrandRoque ? DIRECTIONS_GRAND_ROQUE[colorIndex] : DIRECTIONS_PETIT_ROQUE[colorIndex];
    }
}
