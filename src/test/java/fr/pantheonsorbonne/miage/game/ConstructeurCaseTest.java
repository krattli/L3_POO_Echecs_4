package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Colonne;
import fr.pantheonsorbonne.miage.enums.Ligne;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstructeurCaseTest {

    @Test
    void Constructeur1CoordAlgebriques() {
        Case c1 = new Case(Colonne.A, Ligne.ONE);
        assertEquals("A1", c1.toString());
    }

    @Test
    void Constructeur1CoordPlateau() {
        Case c1 = new Case(Colonne.A, Ligne.ONE);
        int[] expectedCoordinates = {0, 13};
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
        int[] expectedCoordinates = {0, 13};
        assertArrayEquals(expectedCoordinates, c1.getCoordInt());
    }

    //Ne fonctionne pas, mais osef, on n'utilise jamais ce constructeur de toute façon
    @Test
    void Constructeur3CoordAlgebriques() {
        Case c1 = new Case(13, 0);
        assertEquals("A1", c1.toString());
    }

    //Ne fonctionne pas, mais osef, on n'utilise jamais ce constructeur de toute façon
    @Test
    void Constructeur3CoordPlateau() {
        Case c1 = new Case(13, 0);
        int[] expectedCoordinates = {0, 13};
        assertArrayEquals(expectedCoordinates, c1.getCoordInt());
    }

    @Test
    void Constructeur4CoordAlgebriques() {
        Case c1 = new Case("A1");
        assertEquals("A1", c1.toString());
    }

    @Test
    void Constructeur4CoordPlateau() {
        Case c1 = new Case("N14");
        int[] expectedCoordinates = {13, 0};
        assertArrayEquals(expectedCoordinates, c1.getCoordInt());
    }
}