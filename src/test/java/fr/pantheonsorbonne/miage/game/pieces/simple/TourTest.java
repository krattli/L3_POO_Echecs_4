package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.playersAI.Player;
import fr.pantheonsorbonne.miage.game.playersAI.PlayerBot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

class TourTest {
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
        echiquier.clearPlateau();
    }

    @Test
    void getAllPossibleMoves() {
        Tour t = new Tour(j1,"F8");
        ArrayList<Coup> moves = t.getAllPossibleMoves();

        assert moves.toString().contains("TF8-F1");
    }

    @Test
    void hasMoved() {
        Tour tour = new Tour(j1,"H1");

        ArrayList<Coup> moves = tour.getAllPossibleMoves();
        Random rand = new Random();
        Coup coup = moves.get(rand.nextInt(moves.size()));

        echiquier.jouerCoup(coup);

        assert !tour.hasntMooved();
    }
}