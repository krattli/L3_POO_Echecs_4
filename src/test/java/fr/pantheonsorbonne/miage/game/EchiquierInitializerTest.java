package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.Pieces.pieces_simple.Pion;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class EchiquierInitializerTest {
    private PlayerBot j1;
    private PlayerBot j2;
    private PlayerBot j3;
    private PlayerBot j4;
    private Echiquier plateau;

    @BeforeAll
    static void setUpgeneral() {
        PlayerBot j1 = new PlayerBot("Joueur1");
        PlayerBot j2 = new PlayerBot("Joueur2");
        PlayerBot j3 = new PlayerBot("Joueur3");
        PlayerBot j4 = new PlayerBot("Joueur4");

        Echiquier.addPlayers(j1, j2, j3, j4);
    }
    @BeforeEach
    void setUp() {
        Echiquier.initAllPieces();
    }

    @Test
    void VerifyIfPreviousPiecesAreKilled() throws WrongCaseFormatException {
        Case c = new Case("B3");
        Pion p = new Pion(j1,c);
        Echiquier.initAllPieces();
    }

    //Faire touts les autres tests
    //Et trouver pourquoi assertEquals ne fonctionne pas ici >:((((
}