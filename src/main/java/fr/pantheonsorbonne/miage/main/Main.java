package fr.pantheonsorbonne.miage.main;

import fr.pantheonsorbonne.miage.engine.local.PartieLocal;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerSmarter;

public class Main {
    public static void main(String[] args) {

        PlayerSmarter j1 = new PlayerSmarter("Gary Kasparov");
        PlayerSmarter j2 = new PlayerSmarter("Hikaru Nakamura");
        PlayerBot j3 = new PlayerBot("Alireza Firouzja");
        PlayerBot j4 = new PlayerBot("Magnus Carlsen");

//        PartieLocal game = new PartieLocal(new Player[]{j1, j2, j3, j4});
//        game.initPlateau();
//
//        game.play();
//
//        game.printWinners();

        float[] stats = getStatsAbout(new Player[] {j1,j2,j3,j4}, 100);
        for (float stat : stats) {
            System.out.println(stat);
        }
    }

    public static float[] getStatsAbout(Player[] players, int nbSimulations) {
        float[] stats = new float[players.length];
        int[] countVictories = new int[players.length];
        for (int i = 0; i < nbSimulations; i++) {
            PartieLocal game = new PartieLocal(players);
            game.initPlateau();
            Player winner = game.play();
            int winnerIndex = -1;
            for (int j = 0; j < players.length; j++) {
                if (players[j].equals(winner)) {
                    winnerIndex = j;
                }
            }
            countVictories[winnerIndex]++;
        }
        for (int i = 0; i < players.length; i++) {
            stats[i] = (float) countVictories[i] / nbSimulations;
        }
        return stats;
    }
}

