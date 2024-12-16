package fr.pantheonsorbonne.miage.echiquierRelatedStuff;

import fr.pantheonsorbonne.miage.game.Echiquier;
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
    void menaces() {
        plateau.initBoard();
        boolean[][][] menaces = EchiquierMenace.computeMenace(plateau);
        for (boolean[][] booleans : menaces) {
            for (int i = 0; i < menaces[0].length; i++) {
                for (int j = 0; j < menaces[1].length; j++) {
                    boolean menace = booleans[j][i];
                    if (menace) {
                        System.out.print(" 0 ");
                    } else {
                        System.out.print(" _ ");
                    }
                }
                System.out.println();
            }
            System.out.println("\n\n\n");
        }
    }
}