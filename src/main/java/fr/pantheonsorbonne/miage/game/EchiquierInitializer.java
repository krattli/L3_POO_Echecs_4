package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

public class EchiquierInitializer {
    public static void initialiser(Echiquier echiquier) {
        Player[] players = echiquier.getPlayers();
        Piece[][] plateau = echiquier.getPlateau();
        for(Player player : players) initialiserPlayer(player);
    }

    private static void initialiserPlayer(Player player) {
        //Prendre la couleur du joueur et placer les pions dans le coin associé...
    }
}