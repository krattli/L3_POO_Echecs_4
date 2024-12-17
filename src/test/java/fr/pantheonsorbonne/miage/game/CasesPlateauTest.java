package fr.pantheonsorbonne.miage.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CasesPlateauTest {
    @Test
    void areValidCases() {

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
            if (c.isValid()) {
                testCases = false;
            }
        }
        for (Case c : bonnesCases) {
            if (!c.isValid()) {
                testCases = false;
            }
        }
        assertTrue(testCases);
    }

    @Test
    void validTranslation() {
        Case c = new Case("F8");

        assert null != c.getValidTranslatedCase(0, 0);
        assert null != c.getValidTranslatedCase(5, 6);
        assert null != c.getValidTranslatedCase(-2, -7);
        assert null != c.getValidTranslatedCase(8, -4);

        assert null == c.getValidTranslatedCase(40, -40);
        assert null == c.getValidTranslatedCase(7, 5);
        assert null == c.getValidTranslatedCase(-12, 5);
        assert null == c.getValidTranslatedCase(6, -5);

    }

    @Test
    void Equals() {
        Case c1 = new Case("F8");
        Case c2 = new Case("F8");

        assert !(c1 == c2);
        assert c1.equals(c2);
    }
}