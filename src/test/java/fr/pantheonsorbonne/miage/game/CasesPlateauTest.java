package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Colonne;
import fr.pantheonsorbonne.miage.enums.Ligne;
import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CasesPlateauTest {

    @Test
    void Constructeur1CoordAlgebriques() {
        Case c1 = new Case(Colonne.A, Ligne.ONE);
        assertEquals("A1", c1.toString());
    }

    @Test
    void Constructeur1CoordPlateau() {
        Case c1 = new Case(Colonne.A, Ligne.ONE);
        int[] expectedCoordinates = {13, 0};
        assertArrayEquals(expectedCoordinates, c1.getCoordInt());
    }

    @Test
    void Constructeur2CoordAlgebriques() {
        Case c1 = new Case(Colonne.A, 1);
        assertEquals("A1", c1.toString());
    }

    @Test
    void Constructeur2CoordPlateau() {
        Case c1 = new Case(Colonne.A, 1);
        int[] expectedCoordinates = {13, 0};
        assertArrayEquals(expectedCoordinates, c1.getCoordInt());
    }

    @Test
    void Constructeur3CoordAlgebriques() {
        Case c1 = new Case(13, 0);
        assertEquals("A1", c1.toString());
    }

    @Test
    void Constructeur3CoordPlateau() {
        Case c1 = new Case(13, 0);
        int[] expectedCoordinates = {13, 0};
        assertArrayEquals(expectedCoordinates, c1.getCoordInt());
    }

    @Test
    void Constructeur4CoordAlgebriques() throws WrongCaseFormatException {
        Case c1 = new Case("A1");
        assertEquals("A1", c1.toString());
    }

    @Test
    void Constructeur4CoordPlateau() throws WrongCaseFormatException {
        Case c1 = new Case("N14");
        int[] expectedCoordinates = {0, 13};
        assertArrayEquals(expectedCoordinates, c1.getCoordInt());
    }

    @Test
    void areValidCases() throws WrongCaseFormatException {

        //Dans les coins (faux)
        Case c1 = new Case("C3");
        Case c2 = new Case("L12");
        Case c3 = new Case("C12");
        Case c4 = new Case("L3");

        //Adjacent aux coins (vrai)
        Case c5 = new Case("C4");
        Case c6 = new Case("K4");
        Case c7 = new Case("L11");
        Case c8 = new Case("C4");

        // En plein milieu du plateau (vrai)
        Case c9 = new Case("C4");
        Case c10 = new Case("C4");
        Case c11 = new Case("C4");
        Case c12 = new Case("C4");

        Case[] mauvaisesCases = {c1, c2, c3, c4};
        Case[] bonnesCases = {c5, c6, c7, c8, c9, c10, c11, c12};

        boolean testCases = true;
        for (Case c : mauvaisesCases) {
            if(c.isValid() == true){
                testCases = false;
            }
        }
        for (Case c : bonnesCases) {
            if(c.isValid() == false){
                testCases = false;
            }
        }
        assertTrue(testCases);
    }
}