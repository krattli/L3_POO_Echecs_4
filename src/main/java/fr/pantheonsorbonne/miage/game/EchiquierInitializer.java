package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Colonne;
import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.Pieces.pieces_simple.*;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

public class EchiquierInitializer {
    public static void initialiser(){
        Player[] players = Echiquier.getPlayers();
        for (Piece[] pieces : Echiquier.getPlateau()) {
            for (Piece piece : pieces) {
                if(piece != null) {
                    piece.kill();
                }
            }
        }
        try {
            for (Player player : players) initialiserPlayer(player);
        }
        catch (WrongCaseFormatException e) {}
    }

    private static void initialiserPlayer(Player player) throws WrongCaseFormatException {

        int numberOfRotation = (player.getColor().ordinal() + 3 ) % 4;

        Case posRoiInit = new Case("H1").getCoordRotatedBy90(numberOfRotation);
        Roi roi = new Roi(player, posRoiInit);

        Case posDameInit = new Case("G1").getCoordRotatedBy90(numberOfRotation);
        Dame dame = new Dame(player, posDameInit);

        Case posFou1Init = new Case("F1").getCoordRotatedBy90(numberOfRotation);
        Case posFou2Init = new Case("I1").getCoordRotatedBy90(numberOfRotation);
        Fou fou1 = new Fou(player, posFou1Init);
        Fou fou2 = new Fou(player, posFou2Init);

        Case posCavalier1Init = new Case("E1").getCoordRotatedBy90(numberOfRotation);
        Case posCavalier2Init = new Case("J1").getCoordRotatedBy90(numberOfRotation);
        Cavalier cavalier1 = new Cavalier(player, posCavalier1Init);
        Cavalier cavalier2 = new Cavalier(player, posCavalier2Init);

        Case posTour1Init = new Case("D1").getCoordRotatedBy90(numberOfRotation);
        Case posTour2Init = new Case("K1").getCoordRotatedBy90(numberOfRotation);
        Tour tour1 = new Tour(player, posTour1Init);
        Tour tour2 = new Tour(player, posTour2Init);

        for (int i = 3 ; i <= 10 ; i++){
            Case posPionInit = new Case(Colonne.values()[i].toString()+"2").getCoordRotatedBy90(numberOfRotation);
            Pion pion = new Pion(player, posPionInit);
        }
    }
}