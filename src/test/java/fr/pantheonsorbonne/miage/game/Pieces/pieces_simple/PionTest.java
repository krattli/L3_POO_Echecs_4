package fr.pantheonsorbonne.miage.game.Pieces.pieces_simple;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PionTest {

    @BeforeAll
    static void setUpgeneral() {
        PlayerBot j1 = new PlayerBot("Joueur1");
        PlayerBot j2 = new PlayerBot("Joueur2");
        PlayerBot j3 = new PlayerBot("Joueur3");
        PlayerBot j4 = new PlayerBot("Joueur4");

        Echiquier.addPlayers(j1, j2, j3, j4);
    }

    @BeforeEach
    void reset() {
        Echiquier.resetPlateau();
    }

    @Test
    void testAvancerSimple() {
    }

    @Test
    void isTheMoveLegal() {
    }

    @Test
    void isPromuToQueen() {
    }

    @Test
    void promouvoirPionToQueen() {
    }
}