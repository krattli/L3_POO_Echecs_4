package fr.pantheonsorbonne.miage.utils;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.exception.WrongCoupFormatException;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.game.pieces.simple.Pion;
import java.util.regex.*;
import fr.pantheonsorbonne.miage.game.typeCoup.*;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

public class CoupTranslator {

    private static final String REGULAR_EXPRESSION_FOR_COUP = "([A-Z])([A-Z]\\d+)([x\\-])([A-Z]\\d+)([A-Z]?)";
    private static final Pattern PATTERNS = Pattern.compile(REGULAR_EXPRESSION_FOR_COUP);

    //Temporaire, attends d'être supp
    private static final int START_INDEX_CASE_COUP_NORMAL = 1;
    private static final int END_INDEX_CASE_SUPER_DEP = 2;
    private static final int MINIMUM_SUPER_STRING_LENGTH = 6;


    public static Coup stringToCoup(Player joueur, String input) throws WrongCaseFormatException, WrongCoupFormatException {
        Matcher matcher = PATTERNS.matcher(input);
        if (input.startsWith("S")) {
            return handleSuperDeplacement(joueur, input);
        }
        else if (input.contains("O")) {
            return handleRoque(joueur, input);
        } else {
            return handleOtherTypesOfCoups(joueur, matcher);
        }
    }

    private static Coup handleOtherTypesOfCoups(Player joueur, Matcher input) throws WrongCaseFormatException, WrongCoupFormatException {
        if (input.matches()) {
            String pieceStr = input.group(1);
            String caseDepartStr = input.group(2);
            String typeCoupStr = input.group(3);
            String caseArriveeStr = input.group(4);
            String promotionStr = input.group(5);

            Case caseDepart = parseCase(caseDepartStr);
            Case caseArrivee = parseCase(caseArriveeStr);
            Piece piece = validatePieceAtCase(joueur, caseDepart, pieceStr);

            if (promotionStr.equals("D")) {
                if(((Pion) piece).isPromoted()) {
                    return new Promotion(piece, caseArrivee);
                }
                throw new WrongCoupFormatException("Le Pion à la position "+piece.getPosition().toString() +
                        " n'a pas apparement le droit d'effectuer une promotion \n " +
                        "Liste des coups disponibles du joueur de couleur " +joueur.getColor() +" : \n "
                        + joueur.getAllPossibleMoves() + "\n" +
                        "String Coup fournis : " + input);
            }
            else if (typeCoupStr.equals("-")) {
                return new Deplacement(piece,caseArrivee);
            }
            else if (typeCoupStr.equals("x")) {
                Piece piecePrise = validatePieceAtCase(joueur, caseArrivee, pieceStr, false, false);
                if (piecePrise.getOwner() == joueur) {
                    throw new WrongCoupFormatException("La pièce prise appartiens au joueur qui joue");
                }
                return new Prise(piece,caseArrivee);
            }
            else if (typeCoupStr.equals("o")) {
                // o = fusion, on a pas encore implémenté
                return handleFusion(joueur, input);
            }
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

    private static Fusion handleFusion(Player joueur, Matcher input) throws WrongCaseFormatException, WrongCoupFormatException {
        return null;
    }

    //Pas du touuut au point
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

    private static Case parseCase(String input) throws WrongCaseFormatException {
        try {
            return new Case(input);
        }
        catch (Exception e) {
            throw new WrongCaseFormatException("Invalid case format: " + input);
        }
    }

    private static Piece validatePieceAtCase(Player player, Case startCase, String expectedPieceType, boolean ennablePlayerValidation, boolean enablePieceTypeVerification) throws WrongCoupFormatException {
        Piece piece = player.getEchiquier().getPieceAt(startCase);
        if (piece == null) {
            throw new WrongCoupFormatException("No piece found at: " + startCase);
        }

        if (enablePieceTypeVerification) {
            String pieceType = piece.getClass().getSimpleName().substring(0, 1);
            if (!pieceType.equals(expectedPieceType)) {
                throw new WrongCoupFormatException("Mismatched piece type. Expected: " + expectedPieceType + ", but found: " + pieceType);
            }
        }

        if (ennablePlayerValidation) {
            if (piece.getOwner() != player) {
                throw new WrongCoupFormatException("La pièce est bien trouvée mais elle n'appartiens pas au joueur jouant le coup \n" +
                        "Joueur " + player.getColor() + " a comme coup disponibles : \n" +
                        player.getAllPossibleMoves() + "\n" +
                        "Piece trouvée à la case : " +startCase + " est à " + piece.getOwner().getColor());
            }
        }

        return piece;
    }

    private static Piece validatePieceAtCase(Player player, Case startCase, String expectedPieceType) throws WrongCoupFormatException {
        return validatePieceAtCase( player,  startCase, expectedPieceType, true, true);
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
