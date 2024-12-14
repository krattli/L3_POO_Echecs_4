package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.pieces.simple.Pion;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EchiquierTest {
    static PlayerBot j1;
    static PlayerBot j2;
    static PlayerBot j3;
    static PlayerBot j4;
    static Echiquier plateau;

    @BeforeAll
    static void setUp(){
        j1 = new PlayerBot("Joueur1");
        j2 = new PlayerBot("Joueur2");
        j3 = new PlayerBot("Joueur3");
        j4 = new PlayerBot("Joueur4");

        plateau = new Echiquier(new Player[]{j1, j2, j3, j4});
    }

    @Test
    void setAPieceOnBoard() throws WrongCaseFormatException {
        Case c = new Case("A1");
        Pion p = new Pion(j1, c);
        assertEquals(plateau.getPieceAt(c), p);
    }

    @Test
    void initAllPieces() throws WrongCaseFormatException {
        plateau.initAllPieces();
    }

    @Test
    void setpieceToPositionNull() throws WrongCaseFormatException {
        Case c = new Case("A1");
        Pion p = new Pion(j1, c);
        plateau.setPieceToPosition(p, null);
        assertTrue(plateau.getPieceAt(c) == null && p.getPosition() == null);
    }
}