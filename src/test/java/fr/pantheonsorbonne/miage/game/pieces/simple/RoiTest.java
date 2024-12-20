package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.typeCoup.Roque;
import fr.pantheonsorbonne.miage.game.playersAI.Player;
import fr.pantheonsorbonne.miage.game.playersAI.PlayerBot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

class RoiTest {
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
    void doesntGoToMenacedCases() {
        Tour t = new Tour(j2, "H9");
        echiquier.computeMenaces();
        Roi r = new Roi(j1,"F8");
        echiquier.computeMenaces();

        ArrayList<Coup> moves = r.getAllPossibleMoves();

        assert !moves.toString().contains("9");
    }

    @Test
    void doesntGoToMenacedcasesThrough() {
        Roi r = new Roi(j1,"F8");
        Dame d = new Dame(j2,"F11");

        echiquier.computeMenaces();
        ArrayList<Coup> moves = r.getAllPossibleMoves();

        System.out.println(moves.toString());

        assert !moves.toString().contains("F7");
    }

    @Test
    void testRoques() {
        Roi r = new Roi(j1,"H1");
        Tour tourPetitRoque = new Tour(j1, "K1");
        Tour tourGrandRoque = new Tour(j1, "D1");

        ArrayList<Coup> moves = r.getAllPossibleMoves();

        assert moves.toString().contains("O-O-O");
        assert moves.toString().contains("O-O");
    }

    @Test
    void isRoqueIfPieceInBetween() {
        Roi r = new Roi(j1,"H1");
        Tour tourPetitRoque = new Tour(j1, "K1");
        Tour tourGrandRoque = new Tour(j1, "D1");
        Pion separateur = new Pion(j1,"F1");

        ArrayList<Coup> moves = r.getAllPossibleMoves();

        assert !moves.toString().contains("O-O-O");
        assert moves.toString().contains("O-O");
    }

    @Test
    void isRoqueIfMenaceInBetween() {
        Roi r = new Roi(j1,"H1");
        Tour tourPetitRoque = new Tour(j1, "K1");
        Tour tourGrandRoque = new Tour(j1, "D1");

        Dame menacante = new Dame(j2,"F7");

        echiquier.computeMenaces();

        ArrayList<Coup> moves = r.getAllPossibleMoves();

        assert !moves.toString().contains("O-O-O");
        assert moves.toString().contains("O-O");
    }

    @Test
    void hasMoved() {
        Roi r = new Roi(j1,"H1");

        ArrayList<Coup> moves = r.getAllPossibleMoves();
        Random rand = new Random();
        Coup coup = moves.get(rand.nextInt(moves.size()));

        echiquier.jouerCoup(coup);

        assert !r.hasntMooved();
    }

    @Test
    void jouerGrandRoque () {
        Roi r = new Roi(j1,"H1");
        Tour tourPetitRoque = new Tour(j1, "K1");
        Tour tourGrandRoque = new Tour(j1, "D1");

        Roque roque = new Roque(r,true);

        echiquier.jouerCoup(roque);

        assert echiquier.getPieceAt("F1") == r;
        assert echiquier.getPieceAt("G1") == tourGrandRoque;
    }
}