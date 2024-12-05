package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Colonne;
import fr.pantheonsorbonne.miage.enums.Color;
import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.Pieces.pieces_simple.*;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

import java.util.Arrays;

public class EchiquierInitializer {
    public static void initialiser() throws WrongCaseFormatException {
        Player[] players = Echiquier.getPlayers();
        for (Piece[] pieces : Echiquier.getPlateau()) {
            for (Piece piece : pieces) {
                if(piece != null) {
                    piece.kill();
                }
            }
        }
        for(Player player : players) initialiserPlayer(player);
    }

    private static void initialiserPlayer(Player player) throws WrongCaseFormatException {

        Case posRoiInit = new Case("H1").getCorespondingCoordRotatedBy90(player.getColor().ordinal());
        Roi roi = new Roi(player, posRoiInit);

        Case posDameInit = new Case("G1").getCorespondingCoordRotatedBy90(player.getColor().ordinal());
        Dame dame = new Dame(player, posDameInit);

        Case posFou1Init = new Case("F1").getCorespondingCoordRotatedBy90(player.getColor().ordinal());
        Case posFou2Init = new Case("I1").getCorespondingCoordRotatedBy90(player.getColor().ordinal());
        Fou fou1 = new Fou(player, posFou1Init);
        Fou fou2 = new Fou(player, posFou2Init);

        Case posCavalier1Init = new Case("E1").getCorespondingCoordRotatedBy90(player.getColor().ordinal());
        Case posCavalier2Init = new Case("J1").getCorespondingCoordRotatedBy90(player.getColor().ordinal());
        Cavalier cavalier1 = new Cavalier(player, posCavalier1Init);
        Cavalier cavalier2 = new Cavalier(player, posCavalier2Init);

        Case posTour1Init = new Case("D1").getCorespondingCoordRotatedBy90(player.getColor().ordinal());
        Case posTour2Init = new Case("K1").getCorespondingCoordRotatedBy90(player.getColor().ordinal());
        Tour tour1 = new Tour(player, posTour1Init);
        Tour tour2 = new Tour(player, posTour2Init);

        for (int i = 3 ; i <= 10 ; i++){
            Case posPionInit = new Case(Colonne.values()[i].toString()+"2").getCorespondingCoordRotatedBy90(player.getColor().ordinal());
            Pion pion = new Pion(player, posPionInit);
        }
    }
}