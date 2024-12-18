package fr.pantheonsorbonne.miage.main;

import fr.pantheonsorbonne.miage.engine.local.PartieLocal;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;

public class Main {
    public static void main(String[] args) {

        PlayerBot j1 = new PlayerBot("Gary Kasparov");
        PlayerBot j2 = new PlayerBot("Hikaru Nakamura");
        PlayerBot j3 = new PlayerBot("Alireza Firouzja");
        PlayerBot j4 = new PlayerBot("Magnus Carlsen");

//        PartieLocal game = new PartieLocal(new Player[]{j1, j2, j3, j4});
//        game.initPlateau();
//
//        game.play();
//
//        game.printWinners();

        float[] stats = getStatsAbout(new Player[] {j1,j2,j3,j4}, 1000);
        for (float stat : stats) {
            System.out.println(stat);
        }
    }

    public static float[] getStatsAbout(Player[] players, int nbSimulations) {
        float[] stats = new float[players.length];
        for (int i = 0; i < nbSimulations; i++) {
            PartieLocal game = new PartieLocal(players);
            game.initPlateau();
            game.play();
            int[] winners = game.getUpdatedScoreBoard();
            for (int j = 0; j < 4; j++) {
                players[j].resetPoints();
                System.out.print(winners[j] + "  ");
                stats[j] += winners[j];
            }
            System.out.println();
        }
        for (int i = 0; i < players.length; i++) {
            stats[i] /= nbSimulations;
        }
        return stats;
    }
}

