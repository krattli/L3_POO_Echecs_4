package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class RoiTest {
    static PlayerBot j1 = new PlayerBot("Joueur1");
    static PlayerBot j2 = new PlayerBot("Joueur2");
    static PlayerBot j3 = new PlayerBot("Joueur3");
    static PlayerBot j4 = new PlayerBot("Joueur4");
    static Echiquier echiquier;

    @BeforeAll
    static void setUpgeneral() {
        echiquier = new Echiquier(new Player[] {j1, j2, j3, j4});
    }

    @BeforeEach
    void reset() {
        echiquier.emptyPlateau();
    }

    @Test
    void doesntGoToMenacedCases() {
        Tour t = new Tour(j2, "H9");
        echiquier.computeMenaces();
        Roi r = new Roi(j1,"F8");
        echiquier.computeMenaces();

        ArrayList<Coup> moves = r.getAllPossibleMoves();

        assert !moves.toString().contains("9");
    }

    @Test
    void testRoques() {
        Roi r = new Roi(j1,"H1");
        Tour tourPetitRoque = new Tour(j1, "K1");
        Tour tourGrandRoque = new Tour(j1, "D1");

        ArrayList<Coup> moves = r.getAllPossibleMoves();

        System.out.println(moves.toString());

    }
}