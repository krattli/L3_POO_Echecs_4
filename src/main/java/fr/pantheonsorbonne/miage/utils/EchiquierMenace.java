package fr.pantheonsorbonne.miage.utils;

import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

import java.util.ArrayList;


public class EchiquierMenace {
    private static int[] stats = Echiquier.getInfosAboutThat();
    private EchiquierMenace() {}

    public static boolean[][][] computeMenace(Echiquier echiquier) {
        boolean[][][] menaces = new boolean[stats[1]][stats[0]][stats[0]];
        Player[] players = echiquier.getPlayers();
        for (int i = 0; i < players.length; i++) {
            menaces[i] = menacedByPlayer(players[i]);
         }
        return menaces;
    }

    private static boolean[][] menacedByPlayer(Player player) {
        boolean[][] menaces = new boolean[stats[0]][stats[0]];
        ArrayList<Coup> coups = player.getAllPossibleMoves();
        for (Coup coup : coups) {
            Case c = coup.getArrivee();
            int[] coords = c.getCoordInt();
            menaces[coords[0]][coords[1]] = true;
        }
        return menaces;
    }
}
