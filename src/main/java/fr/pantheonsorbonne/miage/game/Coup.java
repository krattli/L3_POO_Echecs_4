package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.exception.WrongCoupFormatException;
import fr.pantheonsorbonne.miage.game.pieces.simple.Pion;
import fr.pantheonsorbonne.miage.game.typeCoup.*;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

public abstract class Coup {
    private final Case caseDepart;
    private final Case caseArrivee;
    private final Piece piece;

    private static final int START_INDEX_POSITION_PIECE_NORMAL = 0;
    private static final int END_INDEX_POSITION_PIECE_NORMAL = 1;

    private static final int START_INDEX_CASE_COUP_NORMAL = 2;
    private static final int END_INDEX_CASE_COUP_NORMAL = 4;

    private static final int START_INDEX_PIECE_TYPE_SUPER = 1;
    private static final int START_INDEX_CASE_SUPER_DEP = 3;
    private static final int END_INDEX_CASE_SUPER_DEP = 2;

    private static final int MINIMUM_SUPER_STRING_LENGTH = 6;

    public Coup(Piece piece, Case endCase) {
        this.caseDepart = piece.getPosition();
        this.caseArrivee = endCase;
        this.piece = piece;
    }

    public Coup(Piece piece, String endCase) {
        this(piece, new Case(endCase));
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

    public static Coup stringToCoup(Player joueur, String input) throws WrongCaseFormatException, WrongCoupFormatException {
        if (input.startsWith("S")) {
            return handleSuperDeplacement(joueur, input);
        } else {
            return handleOtherTypesOfCoups(joueur, input);
        }
    }

    public abstract String toString();

    private static Coup handleOtherTypesOfCoups(Player joueur, String input) throws WrongCaseFormatException, WrongCoupFormatException {
        if (input.contains("O")) {
            return handleRoque(joueur, input);
        }
        else if (input.contains("o")) {
            return handleFusion(joueur, input);
        }
        else if (input.contains("x")) {
            return handlePrise(joueur, input);
        }
        else if (input.contains("-")) {
            return handleDeplacement(joueur, input);
        }
        return null;
    }

    private static Roque handleRoque(Player joueur, String input) throws  WrongCoupFormatException {
        for (Roque r : joueur.getRoques()) {
            if (r != null && r.toString().equals(input)) {
                return r;
            }
        }
        throw new WrongCoupFormatException("Pas de roque trouvé pour ce joueur: " + joueur.getNom() +"\n" +
                                            "Coups disponibles pour ce joueur: " + joueur.getAllPossibleMoves());
    }

    private static Fusion handleFusion(Player joueur, String input) throws WrongCaseFormatException, WrongCoupFormatException {
        return null;
    }

    private static Promotion handlePromotion(Player joueur, String input) throws WrongCaseFormatException, WrongCoupFormatException {
        Case startCase = parseCase(input, START_INDEX_CASE_COUP_NORMAL, END_INDEX_CASE_COUP_NORMAL);
        Case endCase = parseCase(input, input.length() - 2, input.length());
        Pion pion = (Pion) validatePieceAtCase(joueur, startCase, input.substring(START_INDEX_POSITION_PIECE_NORMAL, END_INDEX_POSITION_PIECE_NORMAL));
        if(pion.isPromoted()) {
            return new Promotion(pion, endCase);
        }
        throw new WrongCoupFormatException("Le Pion à la position "+pion.getPosition().toString() +
                                            " n'a pas apparement le droit d'effectuer une promotion \n " +
                                            "Liste des coups disponibles du joueur de couleur " +joueur.getColor() +" : \n "
                                            + joueur.getAllPossibleMoves() + "\n" +
                                            "String Coup fournis : " + input);
    }

    private static Prise handlePrise(Player joueur, String input) throws WrongCaseFormatException, WrongCoupFormatException {
        Case startCase = parseCase(input, START_INDEX_CASE_COUP_NORMAL, END_INDEX_CASE_COUP_NORMAL);
        Piece piece = validatePieceAtCase(joueur, startCase, input.substring(START_INDEX_POSITION_PIECE_NORMAL, END_INDEX_POSITION_PIECE_NORMAL));
        Piece piecePrise = validatePieceAtCase(joueur, piece.getPosition(), input.substring(START_INDEX_PIECE_TYPE_SUPER, START_INDEX_CASE_SUPER_DEP));
        return null;
    }

    public static Deplacement handleDeplacement(Player joueur, String input) throws WrongCaseFormatException, WrongCoupFormatException {
        Case startCase = parseCase(input, START_INDEX_CASE_COUP_NORMAL, END_INDEX_CASE_COUP_NORMAL);
        Case endCase = parseCase(input, input.length() - 2, input.length());
        Piece piece = validatePieceAtCase(joueur, startCase, input.substring(START_INDEX_POSITION_PIECE_NORMAL, END_INDEX_POSITION_PIECE_NORMAL));
        return new Deplacement(piece, endCase);
    }


    private static SuperDeplacement handleSuperDeplacement(Player player, String input) throws WrongCaseFormatException, WrongCoupFormatException {
        Case startCase = parseCase(input, START_INDEX_CASE_COUP_NORMAL, START_INDEX_CASE_COUP_NORMAL + 2);
        Case endCase = parseCase(input, input.length() - END_INDEX_CASE_SUPER_DEP, input.length());

        Piece piece = validatePieceAtCase(player, startCase, input.substring(1, 2));

        return createSuperDeplacement(input, piece, endCase);
    }

    private static Case parseCase(String input, int start, int end) throws WrongCaseFormatException {
        try {
            return new Case(input.substring(start, end));
        } catch (Exception e) {
            throw new WrongCaseFormatException("Invalid case format: " + input.substring(start, end));
        }
    }

    private static Piece validatePieceAtCase(Player player, Case startCase, String expectedPieceType) throws WrongCoupFormatException {
        Piece piece = player.getEchiquier().getPieceAt(startCase);
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
