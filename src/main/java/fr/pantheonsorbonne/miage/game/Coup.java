package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.exception.WrongCoupFormatException;
import fr.pantheonsorbonne.miage.game.typeCoup.SuperDeplacement;

public abstract class Coup {
    private final Case caseDepart;
    private final Case caseArrivee;
    private final Piece piece;

    private static final int START_CASE_IDX = 2;
    private static final int END_CASE_IDX_OFFSET = 2;
    private static final int MINIMUM_SUPER_STRING_LENGTH = 6;

    public Coup(Piece piece, Case endCase) {
        this.caseDepart = piece.getPosition();
        this.caseArrivee = endCase;
        this.piece = piece;
    }

    public Coup(Piece piece, String endCase) {
        this(piece, new Case(endCase));
    }

    public static Coup stringToCoup(Echiquier board, String input) throws WrongCaseFormatException, WrongCoupFormatException {
        if (input.startsWith("S")) {
            return handleSuperDeplacement(board, input);
        } else {
            new Case(input.substring(1, 3)); // Validating case; unused here but retained for original intent
            return null;
        }
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

    public abstract String toString();

    private static SuperDeplacement handleSuperDeplacement(Echiquier board, String input) throws WrongCaseFormatException, WrongCoupFormatException {
        Case startCase = parseCase(input, START_CASE_IDX, START_CASE_IDX + 2);
        Case endCase = parseCase(input, input.length() - END_CASE_IDX_OFFSET, input.length());

        Piece piece = validatePieceAtCase(board, startCase, input.substring(1, 2));

        return createSuperDeplacement(input, piece, endCase);
    }

    private static Case parseCase(String input, int start, int end) throws WrongCaseFormatException {
        try {
            return new Case(input.substring(start, end));
        } catch (Exception e) {
            throw new WrongCaseFormatException("Invalid case format: " + input.substring(start, end));
        }
    }

    private static Piece validatePieceAtCase(Echiquier board, Case startCase, String expectedPieceType) throws WrongCoupFormatException {
        Piece piece = board.getPieceAt(startCase);
        if (piece == null) {
            throw new WrongCoupFormatException("No piece found at: " + startCase);
        }

        String pieceType = piece.getClass().getSimpleName().substring(0, 1);
        if (!pieceType.equals(expectedPieceType)) {
            throw new WrongCoupFormatException("Mismatched piece type. Expected: " + expectedPieceType + ", but found: " + pieceType);
        }

        return piece;
    }

    private static SuperDeplacement createSuperDeplacement(String input, Piece piece, Case endCase) throws WrongCoupFormatException {
        SuperDeplacement superMove = new SuperDeplacement(piece, endCase);

        if (input.contains("x")) {
            int expectedCaptures = input.length() - MINIMUM_SUPER_STRING_LENGTH;
            int actualCaptures = superMove.getAllPiecesMangees().size();

            if (expectedCaptures != actualCaptures) {
                throw new WrongCoupFormatException("Mismatched capture count. Expected: " + expectedCaptures + ", found: " + actualCaptures);
            }
        }
        return superMove;
    }
}
