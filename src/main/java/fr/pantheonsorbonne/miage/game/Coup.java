package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.exception.WrongCoupFormatException;
import fr.pantheonsorbonne.miage.game.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.utils.CoupTranslator;

public abstract class Coup {
    private final Case caseDepart;
    private final Case caseArrivee;
    private final Piece piece;

    public Coup(Piece piece, Case endCase) {
        this.caseDepart = piece.getPosition();
        this.caseArrivee = endCase;
        this.piece = piece;
    }

    public Coup(Piece piece, String endCase) {
        this(piece, new Case(endCase));
    }

    public abstract String toString();

    public static Coup stringToCoup(Player joueur, String coup) throws WrongCoupFormatException, WrongCaseFormatException {
        return CoupTranslator.stringToCoup(joueur,coup);
    }

    public Case getCaseDepart() {
        return caseDepart;
    }

    public Case getCaseArrivee() {
        return caseArrivee;
    }

    public Piece getPiece() {
        return piece;
    }
}
