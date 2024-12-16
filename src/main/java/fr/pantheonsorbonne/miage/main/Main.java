package fr.pantheonsorbonne.miage.main;

import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.pieces.simple.Dame;
import fr.pantheonsorbonne.miage.game.pieces.simple.Pion;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        PlayerBot j1 = new PlayerBot("Gary Kasparov");
        PlayerBot j2 = new PlayerBot("Hikaru Nakamura");
        PlayerBot j3 = new PlayerBot("Alireza Firouzja");
        PlayerBot j4 = new PlayerBot("Magnus Carlsen");

        Echiquier plateau = new Echiquier(new Player[] {j1, j2,j3, j4});

        Dame roi = new Dame(j1, "G7");
        Pion p = new Pion(j2,"G4");
        plateau.computeMenaces();
        ArrayList<Coup> coups = roi.getAllPossibleMoves();
        plateau.printCasesMenacees(j1);
        System.out.println(coups.toString());

    }
}
