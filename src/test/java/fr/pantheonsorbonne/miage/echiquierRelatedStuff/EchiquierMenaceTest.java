package fr.pantheonsorbonne.miage.echiquierRelatedStuff;

import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.pieces.simple.Pion;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

        plateau = new Echiquier(new Player[]{j1, j2, j3, j4});
    }

    @Test
    void menacesPion() {
        Case c = new Case("H2");
        Pion p1 = new Pion(j1,c);
        int[] coords = c.getCoordInt();

        plateau.computeMenaces();
        plateau.printCasesMenacees(j1);

        boolean[][][] menaces = plateau.getCasesMenacees();

        assert menaces[0][coords[1] - 1][coords[0] + 1];
        assert !menaces[0][coords[1] - 1][coords[0]];
        assert menaces[0][coords[1] - 1][coords[0] - 1];
    }
}