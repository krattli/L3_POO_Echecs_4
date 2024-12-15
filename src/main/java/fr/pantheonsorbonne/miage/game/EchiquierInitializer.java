package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Colonne;
import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.pieces.simple.*;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

public class EchiquierInitializer {

    public static final String H_1 = "H1";
    public static final String G_1 = "G1";
    public static final String F_1 = "F1";
    public static final String I_1 = "I1";
    public static final String E_1 = "E1";
    public static final String J_1 = "J1";
    public static final String D_1 = "D1";
    public static final String K_1 = "K1";

    private EchiquierInitializer() {
    }

    public static void initialiser(Echiquier echiquier) {
        echiquier.emptyPlateau();
        Player[] players = echiquier.getPlayers();
        for (int i = 0; i < Echiquier.getInfosAboutThat()[1]; i++) {
            try {
                initialiserPlayer(players[i]);
            } catch (WrongCaseFormatException ignored) {}
        }
    }

    private static void initialiserPlayer(Player player) throws WrongCaseFormatException {

        int numberOfRotation = 4 - player.getColor().ordinal();

        Case posRoiInit = new Case(H_1).getCoordRotatedBy90(numberOfRotation);
        Roi roi = new Roi(player, posRoiInit);

        Case posDameInit = new Case(G_1).getCoordRotatedBy90(numberOfRotation);
        Dame dame = new Dame(player, posDameInit);

        Case posFou1Init = new Case(F_1).getCoordRotatedBy90(numberOfRotation);
        Case posFou2Init = new Case(I_1).getCoordRotatedBy90(numberOfRotation);
        Fou fou1 = new Fou(player, posFou1Init);
        Fou fou2 = new Fou(player, posFou2Init);

        Case posCavalier1Init = new Case(E_1).getCoordRotatedBy90(numberOfRotation);
        Case posCavalier2Init = new Case(J_1).getCoordRotatedBy90(numberOfRotation);
        Cavalier cavalier1 = new Cavalier(player, posCavalier1Init);
        Cavalier cavalier2 = new Cavalier(player, posCavalier2Init);

        Case posTour1Init = new Case(D_1).getCoordRotatedBy90(numberOfRotation);
        Case posTour2Init = new Case(K_1).getCoordRotatedBy90(numberOfRotation);
        Tour tour1 = new Tour(player, posTour1Init);
        Tour tour2 = new Tour(player, posTour2Init);

        for (int i = 3; i <= 10; i++) {
            Case posPionInit = new Case(Colonne.values()[i].toString() + "2").getCoordRotatedBy90(numberOfRotation);
            Pion pion = new Pion(player, posPionInit);
        }
    }
}