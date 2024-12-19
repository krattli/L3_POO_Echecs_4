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
        PlayerBot j4 = new PlayerBot("Magnus Carlsen");

        //PartieLocal game = new PartieLocal(new Player[] {j1,j2,j3,j4}, true);
        //game.play();

        //getStatsAbout(new Player[] {j1,j2,j3,j4}, 100);
    }

    public static void getStatsAbout(Player[] players, int nbSimulations) {
        float[] stats = new float[players.length];
        int[] countVictories = new int[players.length];

        for (int i = 0; i < nbSimulations; i++) {
            if (i%10 == 0) {
                System.out.println("Simulation " + i);
            }
            PartieLocal game = new PartieLocal(players);
            Player winner = game.play();
            for (int j = 0; j < players.length; j++) {
                if (players[j].equals(winner)) {
                    countVictories[j]++;
                }
            }
        }
        for (int i = 0; i < players.length; i++) {
            stats[i] = (float) countVictories[i] / nbSimulations;
        }
        for (int i = 0; i < players.length; i++) {
            System.out.println("Joueur de type " + players[i].getClass().toString().split("\\.")[5] +" gagne dans "+ stats[i]*100 +"% des cas");
        }
    }
}

