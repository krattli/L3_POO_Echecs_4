package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        echiquier.emptyPlateau();
    }

    @Test
    void getAllPossibleMoves() throws WrongCaseFormatException {
        Tour t = new Tour(j1,"F8");
        ArrayList<Coup> moves = t.getAllPossibleMoves();
        for (Coup coup : moves) {
            if (coup != null) {
                System.out.println(coup.toString());
            }
            else {
                System.out.println("null");
            }
        }
    }
}