package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Colonne;
import fr.pantheonsorbonne.miage.enums.Ligne;
import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CasesPlateauTest {
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
    void validTranslation() throws WrongCaseFormatException {
        Case c = new Case("F8");

        Case c1 = c.getValidTranslatedCase(0, 0);
        Case c2 = c.getValidTranslatedCase(6, 5);
        Case c3 = c.getValidTranslatedCase(-7, -2);
        Case c4 = c.getValidTranslatedCase(8, -4);

        Case c5 = c.getValidTranslatedCase(40, -40);
        Case c6 = c.getValidTranslatedCase(7, 5);
        Case c7 = c.getValidTranslatedCase(-12, 5);
        Case c8 = c.getValidTranslatedCase(6, -5);

        Case[] bonnesCases = {c1,c2,c3,c4};
        Case[] mauvaisesCases = {c5,c6,c7,c8};

        boolean testCases = true;
        for (Case k : bonnesCases){
            if (k == null){
                testCases = false;
            }
        }
        for (Case k : mauvaisesCases){
            if (k != null){
                testCases = false;
            }
        }
        assertTrue(testCases);
    }
}