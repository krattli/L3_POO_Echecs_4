package fr.pantheonsorbonne.miage.utils;

import fr.pantheonsorbonne.miage.enums.AffichagePieces;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.game.playerRelatedStuff.Player;

public class PrintEchiquier {
    private static final String RESET = "\u001B[0m";
    private static final String BLACK_BG = "\u001B[40m";
    private static final String WHITE_BG = "\u001B[0m";
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
                Piece piece = board[j][i];

                if (!Case.isValidCoord(i,j)){
                    System.out.print( "   " + RESET);
                }
                else if (piece == null) {
                    System.out.print(bgColor + "   " + RESET);
                } else {
                    String nomComplet = piece.getClass().toString();
                    String nomClasse = nomComplet.substring(nomComplet.lastIndexOf('.') + 1);
                    String pieceColor = PLAYER_COLORS[piece.getOwner().getOrderInGame()];
                    System.out.print(bgColor + pieceColor + " " + AffichagePieces.getByAlias(nomClasse).getSymbol() + " " + RESET);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printMenaces(Echiquier plateau, Player excludedPlayer) {
        Player[] players = plateau.getPlayers();
        boolean[][][] allMenaces = plateau.getCasesMenacees();
        int boardSize = allMenaces[0].length; // Taille du plateau

        for (int i = 0; i < boardSize; i++) {
            for (Player currentPlayer : players) {
                if (currentPlayer == excludedPlayer) continue;

                for (int j = 0; j < boardSize; j++) {
                    String backgroundColor = (i + j) % 2 == 0 ? BLACK_BG : WHITE_BG;

                    if (!Case.isValidCoord(i, j)) {
                        System.out.print("   " + RESET);
                    }
                    else if (allMenaces[currentPlayer.getOrderInGame()][i][j]) {
                        System.out.print(backgroundColor + PLAYER_COLORS[currentPlayer.getOrderInGame()]
                                + " " + MENACE_CHAR + " " + RESET);
                    } else {
                        System.out.print(backgroundColor + "   " + RESET);
                    }
                }
                // Ajoute un espace entre chaque plateau
                System.out.print("    ");
            }
            // Nouvelle ligne après avoir affiché tous les plateaux de cette rangée
            System.out.println();
        }
    }
}
