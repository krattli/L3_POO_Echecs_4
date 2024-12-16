package fr.pantheonsorbonne.miage.echiquierRelatedStuff;

import fr.pantheonsorbonne.miage.enums.AffichagePieces;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

public class PrintEchiquier {
    private static final String RESET = "\u001B[0m";
    private static final String BLACK_BG = "\u001B[40m";
    private static final String WHITE_BG = "\u001B[47m";
    private static final String[] PLAYER_COLORS = {
            "\u001B[31m",
            "\u001B[32m",
            "\u001B[33m",
            "\u001B[34m"
    };
    private static final char MENACE_CHAR = '█';

    private PrintEchiquier() {}

    public static void printEchiquier(Echiquier echiquier) {
        Piece[][] board = echiquier.getPlateau();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                String bgColor = (i + j) % 2 == 0 ? BLACK_BG : WHITE_BG;
                Piece piece = board[i][j];

                if (!Case.isValidCoord(i,j)){
                    System.out.print( "   " + RESET);
                }
                else if (piece == null) {
                    System.out.print(bgColor + "   " + RESET);
                } else {
                    String nomComplet = piece.getClass().toString();
                    String nomClasse = nomComplet.substring(nomComplet.lastIndexOf('.') + 1);
                    String pieceColor = PLAYER_COLORS[piece.getOwner().getColor().ordinal()];
                    System.out.print(bgColor + pieceColor + " " + AffichagePieces.getByAlias(nomClasse).getSymbol() + " " + RESET);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printMenaces(Echiquier plateau, Player player) {
        int playerOrder = player.getColor().ordinal();
        boolean[][] casesMenacees = plateau.getCasesMenacees()[playerOrder];

        for (int i = 0; i < casesMenacees.length; i++) {
            for (int j = 0; j < casesMenacees[i].length; j++) {
                String bgColor = (i + j) % 2 == 0 ? BLACK_BG : WHITE_BG;

                if (!Case.isValidCoord(i,j)){
                    System.out.print( "   " + RESET);
                }
                else if (casesMenacees[i][j]) {
                    System.out.print(bgColor + PLAYER_COLORS[0] + " " + MENACE_CHAR + " " + RESET);
                } else {
                    System.out.print(bgColor + "   " + RESET);
                }
            }
            System.out.println();
        }
    }
}
