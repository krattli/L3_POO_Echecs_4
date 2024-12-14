package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CavalierTest {
    static PlayerBot j1 = new PlayerBot("Joueur1");
    static PlayerBot j2 = new PlayerBot("Joueur2");
    static PlayerBot j3 = new PlayerBot("Joueur3");
    static PlayerBot j4 = new PlayerBot("Joueur4");

    @BeforeAll
    static void setUpgeneral() {
        Echiquier.addPlayers(j1, j2, j3, j4);
    }

    @BeforeEach
    void reset() {
        Echiquier.resetPlateau();
    }

    @Test
    void getAllMovesCenterBoard() throws WrongCaseFormatException {
        Cavalier c = new Cavalier(j1,"G8");
        ArrayList<Coup> moves = c.getAllPossibleMoves();

        boolean c1 = moves.toString().contains("I9");
        boolean c2 = moves.toString().contains("I7");
        boolean c3 = moves.toString().contains("H6");
        boolean c4 = moves.toString().contains("F6");
        boolean c5 = moves.toString().contains("E7");
        boolean c6 = moves.toString().contains("E9");
        boolean c7 = moves.toString().contains("F10");
        boolean c8 = moves.toString().contains("H10");

       assertTrue( c1 && c2 && c3 && c4 && c5 && c6 && c7 && c8 );
    }
}