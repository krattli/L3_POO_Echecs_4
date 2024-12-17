package fr.pantheonsorbonne.miage.game.typeCoup;

import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.game.pieces.simple.Roi;
import fr.pantheonsorbonne.miage.game.pieces.simple.Tour;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

import java.util.ArrayList;

public class Roque extends Coup {

    private static final String[] CASES_TO_CHECK_PETIT_ROQUE = { "J1", "I1" };
    private static final String[] CASES_TO_CHECK_GRAND_ROQUE = { "E1", "F1", "G1" };

    private static final String[] POS_TOUR_PETIT_ROQUE = { "K1", "N11", "D14","A4" };
    private static final String[] POS_TOUR_GRAND_ROQUE = { "D1", "N4", "K14", "A11" };

    public static final String[][] POS_PETIT_ROQUE = {{"J1","I1"},{"N11","N10"},{"E14","F14"},{"A5","A6"}};
    public static final String[][] POS_GRAND_ROQUE = {{"J1","I1"},{"N11","N10"},{"E14","F14"},{"A5","A6"}};


    private final boolean isGrandRoque;
    private final Tour tourARoquer;

    public Roque (Roi roi, boolean isGrandRoque) {
        super( roi, (Case) null);
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

        Roi roiARoquer = player.getHisKing();
        Tour tourPetitRoque = getTourARoquer(roiARoquer, false);
        Tour tourGrandRoque = getTourARoquer(roiARoquer, true);

        if (roiARoquer != null && roiARoquer.hasntMooved()) {
            if (tourPetitRoque != null && tourPetitRoque.hasntMooved()){
                if (checkIntermediateCasesRoque(CASES_TO_CHECK_PETIT_ROQUE, roiARoquer)){
                    roques.add(new Roque(roiARoquer, false));
                }
            }
            if (tourGrandRoque != null && tourGrandRoque.hasntMooved()){
                if (checkIntermediateCasesRoque(CASES_TO_CHECK_GRAND_ROQUE, roiARoquer)){
                    roques.add(new Roque(roiARoquer, true));
                }
            }
        }
        return roques;
    }

    private static boolean checkIntermediateCasesRoque(String[] cases, Piece roiARoquer) {
        Player p = roiARoquer.getOwner();
        int ordinalPlayer = p.getColor().ordinal();
        boolean[][][] menaces = roiARoquer.getOwner().getEchiquier().getCasesMenacees();
        for (String strCase : cases) {
            Case c = new Case(strCase);
            Case caseToCheck = c.getCoordRotatedBy90(ordinalPlayer);
            if (p.getEchiquier().getPieceAt(caseToCheck) != null) {
                return false;
            }
            int[] coordCase = caseToCheck.getCoordInt();
            for (int i = 0 ; i < menaces.length ; i++) {
                if (i != ordinalPlayer && menaces[i][coordCase[1]][coordCase[0]]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static Tour getTourARoquer(Roi roiARoquer, boolean isGrandRoque) {
        String casePosition = "";
        switch (roiARoquer.getOwner().getColor()){
            case RED :
                casePosition = isGrandRoque ? POS_TOUR_GRAND_ROQUE[0] : POS_TOUR_PETIT_ROQUE[0];
                break;
            case GREEN:
                casePosition = isGrandRoque ? POS_TOUR_GRAND_ROQUE[1] : POS_TOUR_PETIT_ROQUE[1];
                break;
            case YELLOW:
                casePosition = isGrandRoque ? POS_TOUR_GRAND_ROQUE[2] : POS_TOUR_PETIT_ROQUE[2];
                break;
            case BLUE:
                casePosition = isGrandRoque ? POS_TOUR_GRAND_ROQUE[3] : POS_TOUR_PETIT_ROQUE[3];
                break;
        }
        Piece tourARoquer= roiARoquer.getOwner().getEchiquier().getPieceAt(casePosition);
        if (tourARoquer != null && tourARoquer.getClass() == Tour.class) {
            return (Tour) tourARoquer;
        }
        return null;
    }

    public int[] getSensDuRoqueRoi() {
        boolean isGrandRoque = this.isGrandRoque;
        switch (this.getPiece().getOwner().getColor()){
            case RED :
                return isGrandRoque? new int[]{-1,0} : new int[]{1,0};
            case GREEN:
                return isGrandRoque? new int[]{0,-1} : new int[]{0,1};
            case YELLOW:
                return isGrandRoque? new int[]{1,0} : new int[]{-1,0};
            case BLUE:
                return isGrandRoque? new int[]{0,1} : new int[]{0,-1};
        }
        return null;
    }
}
