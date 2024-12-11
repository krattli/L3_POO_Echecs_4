package fr.pantheonsorbonne.miage.game.Pieces.pieces_simple;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DameTest {
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
    void getAllPossibleMoves() throws WrongCaseFormatException {
        Dame d = new Dame(j1,"F8");
        ArrayList<Coup> moves = d.getAllPossibleMoves();
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