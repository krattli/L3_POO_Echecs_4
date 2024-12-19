package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.exception.WrongCoupFormatException;
import fr.pantheonsorbonne.miage.game.pieces.simple.*;
import fr.pantheonsorbonne.miage.game.pieces.super_pieces.SuperPion;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;
import fr.pantheonsorbonne.miage.utils.CoupTranslator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CoupTest {

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
    void stringToCoup() throws WrongCaseFormatException, WrongCoupFormatException {
        new SuperPion(j1,"D1");
        String superDep = "SPD1xxxD2";
        Coup.stringToCoup(j1, superDep);
    }

    @Test
    void grandTestCoup() throws WrongCaseFormatException, WrongCoupFormatException {
        Random rand = new Random();
        int nbSimulations = rand.nextInt(100);
        int nbPieces = 10;
        int boundPlateau = 14;
        for (int i = 0; i < nbSimulations; i++) {
            ArrayList<Coup> list = new ArrayList<>();
            for (int j = 0; j < nbPieces; j++) {
                Case c = new Case(rand.nextInt(boundPlateau), rand.nextInt(boundPlateau));
                int pieceType = rand.nextInt(5);
                int playerOrdinal = rand.nextInt(4);
                switch (pieceType) {
                    case 0:
                        Pion p = new Pion(echiquier.getPlayers()[playerOrdinal],c);
                        break;
                    case 1:
                        Dame d = new Dame(echiquier.getPlayers()[playerOrdinal],c);
                        break;
                    case 2:
                        Tour t = new Tour(echiquier.getPlayers()[playerOrdinal],c);
                        break;
                    case 3:
                        Fou f = new Fou(echiquier.getPlayers()[playerOrdinal],c);
                        break;
                    case 4:
                        Cavalier cav = new Cavalier(echiquier.getPlayers()[playerOrdinal],c);
                        break;
                }
            }
            for (int j = 0; j < 4; j++) {
                list.addAll(echiquier.getPlayers()[j].getAllPossibleMoves());
            }
            for (Coup c : list) {
                String s = c.toString();
                Coup test = Coup.stringToCoup(c.getPiece().getOwner(),s);
                assert (c.toString().equals(test.toString()));
                System.out.println(test +"   "+ s);
            }
        }
    }
}