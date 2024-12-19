package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.game.pieces.simple.Pion;
import fr.pantheonsorbonne.miage.game.playersAI.Player;
import fr.pantheonsorbonne.miage.game.playersAI.PlayerBot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EchiquierInitializerTest {
    static PlayerBot j1;
    static PlayerBot j2;
    static PlayerBot j3;
    static PlayerBot j4;
    static Echiquier echiquier;

    @BeforeAll
    static void setUpgeneral() {
        j1 = new PlayerBot("Joueur1");
        j2 = new PlayerBot("Joueur2");
        j3 = new PlayerBot("Joueur3");
        j4 = new PlayerBot("Joueur4");

        echiquier = new Echiquier(new Player[]{j1, j2, j3, j4});
    }
    @BeforeEach
    void setUp() {
        echiquier.initBoard();
    }

    @Test
    void VerifyIfPreviousPiecesAreKilled() {
        Case c = new Case("B3");
        Pion p = new Pion(j1,c);
        echiquier.initBoard();
        echiquier.printPlateau();
    }

    //Faire touts les autres tests
    //Et trouver pourquoi assertEquals ne fonctionne pas ici >:((((
}