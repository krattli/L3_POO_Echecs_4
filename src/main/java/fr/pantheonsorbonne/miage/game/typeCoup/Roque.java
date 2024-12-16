package fr.pantheonsorbonne.miage.game.typeCoup;

import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.game.pieces.simple.Roi;
import fr.pantheonsorbonne.miage.game.pieces.simple.Tour;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

import java.util.ArrayList;

public class Roque extends Coup {
    private static final String posRoi = "H1";
    private static final String posTourPetitRoque = "K1";
    private static final String posTourGrandRoque = "D1";
    private static final String[] casesToCheckPetitRoque = { "J1", "I1" };
    private static final String[] casesToCheckGrandRoque = { "E1", "F1", "G1" };


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

        Piece roiARoquer = player.getEchiquier().getPieceAt(new Case(posRoi).getCoordRotatedBy90(playerNumberRotation));
        Piece tourPetitRoque = player.getEchiquier().getPieceAt( new Case(posTourPetitRoque).getCoordRotatedBy90(playerNumberRotation));
        Piece tourGrandRoque = player.getEchiquier().getPieceAt(new Case(posTourGrandRoque).getCoordRotatedBy90(playerNumberRotation));

        if (roiARoquer != null && roiARoquer.getClass() == Roi.class && ((Roi) roiARoquer).hasntMooved()) {
            if (tourPetitRoque != null && tourPetitRoque.getClass() == Tour.class && ((Tour) tourPetitRoque).hasntMooved()){
                if (checkIntermediateCasesRoque(casesToCheckPetitRoque, roiARoquer)){
                    roques.add(new Roque(roiARoquer, tourPetitRoque.getPosition(), false));
                }
            }
            if (tourGrandRoque != null && tourGrandRoque.getClass() == Tour.class && ((Tour) tourGrandRoque).hasntMooved()){
                if (checkIntermediateCasesRoque(casesToCheckGrandRoque, roiARoquer)){
                    roques.add(new Roque(roiARoquer, tourGrandRoque.getPosition(), true));
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
}
