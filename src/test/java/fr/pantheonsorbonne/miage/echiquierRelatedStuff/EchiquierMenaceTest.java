package fr.pantheonsorbonne.miage.echiquierRelatedStuff;

import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.pieces.simple.Pion;
import fr.pantheonsorbonne.miage.game.pieces.simple.Roi;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class EchiquierMenaceTest {

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
    }

    @BeforeEach
    void setUpEach(){
        plateau = new Echiquier(new Player[]{j1, j2, j3, j4});
    }

    @Test
    void menacesPion() {
        Case c = new Case("H2");
        Pion p1 = new Pion(j1,c);
        int[] coords = c.getCoordInt();

        plateau.computeMenaces();

        boolean[][][] menaces = plateau.getCasesMenacees();

        assert menaces[0][coords[1] - 1][coords[0] + 1];
        assert !menaces[0][coords[1] - 1][coords[0]];
        assert menaces[0][coords[1] - 1][coords[0] - 1];
    }

    @Test
    void menacesRoi() {
        Roi r1 = new Roi(j1,"H6");
        plateau.computeMenaces();
        Roi r2 = new Roi(j2,"H8");
        plateau.computeMenaces();

        int[] coordTest = new Case("H7").getCoordInt();

        boolean[][][] menaces = plateau.getCasesMenacees();

        assert menaces[0][coordTest[1]][coordTest[0]];
        assert menaces[0][coordTest[1]][coordTest[0] + 1 ];
        assert menaces[0][coordTest[1]][coordTest[0] - 1 ];
        assert plateau.getPieceAt(new Case("H8")).getClass() == Roi.class;
        assert plateau.getPieceAt(new Case("H6")).getClass() == Roi.class;
    }
}