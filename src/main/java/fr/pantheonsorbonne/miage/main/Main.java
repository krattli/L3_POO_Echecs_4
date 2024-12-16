package fr.pantheonsorbonne.miage.main;

import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.engine.local.PartieLocal;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;

public class Main {
    public static void main(String[] args) {

        PlayerBot j1 = new PlayerBot("Gary Kasparov");
        PlayerBot j2 = new PlayerBot("Hikaru Nakamura");
        PlayerBot j3 = new PlayerBot("Alireza Firouzja");
        PlayerBot j4 = new PlayerBot("Magnus Carlsen");

        PartieLocal game = new PartieLocal(new Player[]{j1, j2, j3, j4});
        game.initPlateau();

        Echiquier plateau = game.getPlateau();

        plateau.printPlateau();

        long t1 = System.currentTimeMillis();
        game.play();
        long t2 = System.currentTimeMillis();
        System.out.println("Took " + (t2 - t1) + " milliseconds");
    }
}

