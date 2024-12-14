package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PionTest {
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
    void firstMoves() throws WrongCaseFormatException {
        Pion p1 = new Pion(j1,"F2");
        Pion p2 = new Pion(j2,"M7");
        Pion p3 = new Pion(j3,"H13");
        Pion p4 = new Pion(j4,"B9");

        ArrayList<Coup> cps1 = p1.getAllPossibleMoves();
        ArrayList<Coup> cps2 = p2.getAllPossibleMoves();
        ArrayList<Coup> cps3 = p3.getAllPossibleMoves();
        ArrayList<Coup> cps4 = p4.getAllPossibleMoves();

        boolean cond1 = cps1.getFirst().toString().hashCode() == "PF2-F3".hashCode();
        boolean cond2 = cps2.getFirst().toString().hashCode() == "PM7-L7".hashCode();
        boolean cond3 = cps3.getFirst().toString().hashCode() == "PH13-H12".hashCode();
        boolean cond4 = cps4.getFirst().toString().hashCode() == "PB9-C9".hashCode();

        boolean deplacementSimple = cond1 && cond2 && cond3 && cond4;

        boolean cond1D = cps1.get(1).toString().hashCode() == "PF2-F4".hashCode();
        boolean cond2D = cps2.get(1).toString().hashCode() == "PM7-K7".hashCode();
        boolean cond3D = cps3.get(1).toString().hashCode() == "PH13-H11".hashCode();
        boolean cond4D = cps4.get(1).toString().hashCode() == "PB9-D9".hashCode();

        boolean deplacementDouble = cond1D && cond2D && cond3D && cond4D;

        assertTrue(deplacementSimple && deplacementDouble);
    }

    @Test
    void notFirstMove() throws WrongCaseFormatException {
        Pion p1 = new Pion(j1,"F3");
        Pion p2 = new Pion(j2,"L8");
        Pion p3 = new Pion(j3,"H12");
        Pion p4 = new Pion(j4,"C9");

        ArrayList<Coup> c1 = p1.getAllPossibleMoves();
        ArrayList<Coup> c2 = p2.getAllPossibleMoves();
        ArrayList<Coup> c3 = p3.getAllPossibleMoves();
        ArrayList<Coup> c4 = p4.getAllPossibleMoves();

        boolean test = c1.size() == c2.size() && c1.size() == c3.size() && c1.size() == c4.size() && c1.size() == 1;

        assertTrue(test);
    }

    @Test
    void prisesEnnemies() throws WrongCaseFormatException {
        Pion p1 = new Pion(j1,"G6");
        Pion p2 = new Pion(j2,"I8");

        Cavalier ennemi = new Cavalier(j3,"H7");

        Coup cp1 = p1.getAllPossibleMoves().get(1);
        Coup cp2 = p2.getAllPossibleMoves().get(1);

        boolean cond1 = cp1.toString().contains("PG6xH7");
        boolean cond2 = cp2.toString().contains("PI8xH7");

        assertTrue(cond1 && cond2);
    }

    @Test
    void avancementBloquado() throws WrongCaseFormatException {
        Pion p1 = new Pion(j1,"H7");
        Pion p2 = new Pion(j2,"H10");
        Pion p3 = new Pion(j3,"H8");
        Pion p4 = new Pion(j4,"G10");

        ArrayList<Coup> c1 = p1.getAllPossibleMoves();
        ArrayList<Coup> c2 = p2.getAllPossibleMoves();
        ArrayList<Coup> c3 = p3.getAllPossibleMoves();
        ArrayList<Coup> c4 = p4.getAllPossibleMoves();

        boolean test = c1.size() + c2.size() + c3.size() + c4.size() == 0;

        assertTrue(test);
    }
    @Test
    void isPromuToQueen() throws WrongCaseFormatException {
        Pion p1 = new Pion(j1,"H13");

        ArrayList<Coup> c = p1.getAllPossibleMoves();

        assertTrue(c.toString().contains("PH13-H14D"));
    }
}