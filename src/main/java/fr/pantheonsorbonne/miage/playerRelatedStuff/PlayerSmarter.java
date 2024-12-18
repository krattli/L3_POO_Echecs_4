package fr.pantheonsorbonne.miage.playerRelatedStuff;

import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.pieces.simple.Roi;
import fr.pantheonsorbonne.miage.game.typeCoup.Deplacement;
import fr.pantheonsorbonne.miage.game.typeCoup.Prise;
import fr.pantheonsorbonne.miage.game.typeCoup.Promotion;
import fr.pantheonsorbonne.miage.game.typeCoup.Roque;

import java.util.ArrayList;
import java.util.Random;

public class PlayerSmarter extends Player{
    private static final int RECOMPENSE_GRAND_ROQUE = 8;
    private static final int RECOMPENSE_PETIT_ROQUE = 6;
    private static final int RECOMPENSE_PROMOTION = 10;
    private static final int RECOMPENSE_PRISE = 3;
    private static final int RECOMPENSE_COUP_PROTEGE = 8;
    private static final int RECOMPENSE_COUP_SUICIDAIRE = -7;
    private static final int RECOMPENSE_MATTER_AUTRE_JOUEUR = 12;

    public PlayerSmarter(String nom) {
        super(nom);
    }

    @Override
    public Coup getNextCoupKingChecked() {
        Roi myKing = this.getHisKing();
        return getRandomMove(myKing.getAllPossibleMoves());
    }

    @Override
    public Coup getNextCoupNormal() {
        ArrayList<Coup> allMoves = this.getAllPossibleMoves();
        if (allMoves.isEmpty()) {
            return null;
        }
        int[] ratingCoups = new int[allMoves.size()];
        for (int i = 0; i < allMoves.size(); i++) {
            ratingCoups[i] = rateThisCoup(allMoves.get(i));
        }
        double[] probabilities = returnLogSoftMaxedTab(ratingCoups);

        return allMoves.get(getRandomIndexSoftMaxDistribution(probabilities));
    }

    private static int rateThisCoup(Coup coup) {

        int valeurCoup = 0;

        valeurCoup += handleMenaces(coup);
        if (coup.getClass() == Prise.class) {
            if (((Prise) coup).getPiecePrise().getClass() == Roi.class) {
                valeurCoup += RECOMPENSE_MATTER_AUTRE_JOUEUR;
            }
            else {
                valeurCoup += coup.getPiece().getValuePiece() + RECOMPENSE_PRISE;
            }
        }
        else if (coup.getClass() != Deplacement.class) {
            valeurCoup += handleOtherTypesOfCoup(coup);
        }
        return valeurCoup;
    }

    private static int handleMenaces(Coup coup) {
        if (coup.getClass() == Roque.class) {return 0;}
        int[] arrivee = coup.getArrivee().getCoordInt();
        int delta = 0;
        boolean[][][] menaces = coup.getPiece().getOwner().getEchiquier().getCasesMenacees();
        int hisPlayerOrdinal = coup.getPiece().getOwner().getColor().ordinal();

        for (int i = 0 ; i < menaces.length ; i++) {
            boolean isArriveeMenaced = menaces[i][arrivee[1]][arrivee[0]];
            if (i != hisPlayerOrdinal) {
                if (isArriveeMenaced) {
                    delta += RECOMPENSE_COUP_SUICIDAIRE;
                }
            }
            else {
                if (isArriveeMenaced) {
                    delta += RECOMPENSE_COUP_PROTEGE;
                }
            }
        }
        return delta;
    }

    private static int handleOtherTypesOfCoup(Coup coup) {
        int delta = 0;
        if (coup.getClass() == Roque.class) {
            if (((Roque) coup).isGrandRoque()) {
                delta += RECOMPENSE_GRAND_ROQUE;
            }
            else {
                delta += RECOMPENSE_PETIT_ROQUE;
            }
        }
        else if (coup.getClass() == Promotion.class) {
            delta += RECOMPENSE_PROMOTION;
        }
        return delta;
    }

    private static double[] returnLogSoftMaxedTab(int[] input) {
        double sum = 0.0;
        double[] logTransformed = new double[input.length];
        double[] logSoftMaxedTab = new double[input.length];

        for (int i = 0; i < input.length; i++) {
            if (input[i] > 0) {
                logTransformed[i] = Math.log(input[i]);
            } else {
                logTransformed[i] = 0;
            }
        }

        for (double value : logTransformed) {
            sum += Math.exp(value);
        }

        for (int i = 0; i < logTransformed.length; i++) {
            logSoftMaxedTab[i] = Math.exp(logTransformed[i]) / sum;
        }

        return logSoftMaxedTab;
    }

    private static int getRandomIndexSoftMaxDistribution(double[] probabilities) {
        Random random = new Random();
        double randomValue = random.nextDouble();
        double cumulativeSum = 0.0;

        for (int i = 0; i < probabilities.length; i++) {
            cumulativeSum += probabilities[i];
            if (randomValue <= cumulativeSum) {
                return i;
            }
        }
        return probabilities.length - 1;
    }
}
