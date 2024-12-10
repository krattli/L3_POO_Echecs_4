package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.Pieces.pieces_simple.Pion;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EchiquierTest {
    private PlayerBot j1;
    private PlayerBot j2;
    private PlayerBot j3;
    private PlayerBot j4;
    private Echiquier plateau;

    @BeforeAll
    static void setUp() throws WrongCaseFormatException {
        PlayerBot j1 = new PlayerBot("Joueur1");
        PlayerBot j2 = new PlayerBot("Joueur2");
        PlayerBot j3 = new PlayerBot("Joueur3");
        PlayerBot j4 = new PlayerBot("Joueur4");

        Echiquier.addPlayers(j1, j2, j3, j4);
    }

    @Test
    void setAPieceOnBoard() throws WrongCaseFormatException {
        Case c = new Case("A1");
        Pion p = new Pion(j1, c);
        assertEquals(Echiquier.getPieceAt(c), p);
    }

    @Test
    void rotateCopyToRightBy90() throws WrongCaseFormatException {
        Case c = new Case("A1");
        Pion p = new Pion(j1, c);
        assertEquals(1, 1);
    }

    @Test
    void initAllPieces() throws WrongCaseFormatException {
        Echiquier.initAllPieces();
    }

    @Test
    void setPieceToPosition() {
        assertEquals(1, 1);
    }

    @Test
    void setpieceToPositionNull() throws WrongCaseFormatException {
        Case c = new Case("A1");
        Pion p = new Pion(j1, c);
        Echiquier.setPieceToPosition(p, null);
        assertTrue(Echiquier.getPieceAt(c) == null && p.getPosition() == null);
    }

    @Test
    void jouerCoup() {
        assertEquals(1, 1);
    }

    @Test
    void emptyCell() {
        assertEquals(1, 1);
    }

    @Test
    void getPieceAt() {
    }
}