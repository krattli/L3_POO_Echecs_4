package fr.pantheonsorbonne.miage.main;

import fr.pantheonsorbonne.miage.engine.local.PartieLocal;
import fr.pantheonsorbonne.miage.game.playersAI.Player;
import fr.pantheonsorbonne.miage.game.playersAI.PlayerBot;
import fr.pantheonsorbonne.miage.game.playersAI.PlayerSmarter;

public class Main {
    public static void main(String[] args) {

        PlayerBot j1 = new PlayerBot("Gary Kasparov");
        PlayerSmarter j2 = new PlayerSmarter("Hikaru Nakamura");
        PlayerBot j3 = new PlayerBot("Alireza Firouzja");
        PlayerSmarter j4 = new PlayerSmarter("Magnus Carlsen");

        float[] stats = getStatsAbout(new Player[] {j1,j2,j3,j4}, 100);
        for (float stat : stats) {
            System.out.println(stat);
        }
    }

    public static float[] getStatsAbout(Player[] players, int nbSimulations) {
        float[] stats = new float[players.length];
        int[] countVictories = new int[players.length];
        for (int i = 0; i < nbSimulations; i++) {
            if (i%10 == 0) {
                System.out.println("Simulation " + i);
            }
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

