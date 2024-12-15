package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Colonne;
import fr.pantheonsorbonne.miage.enums.Ligne;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstructeurCaseTest {

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