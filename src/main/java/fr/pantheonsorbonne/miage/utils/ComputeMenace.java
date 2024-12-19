package fr.pantheonsorbonne.miage.utils;

import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.game.pieces.simple.Pion;
import fr.pantheonsorbonne.miage.game.pieces.simple.Roi;
import fr.pantheonsorbonne.miage.game.typeCoup.Roque;
import fr.pantheonsorbonne.miage.game.playerRelatedStuff.Player;

import java.util.ArrayList;


public class ComputeMenace {
    private static final int[] stats = Echiquier.getBoardInfo();
    private ComputeMenace() {}

    public static boolean[][][] computeAllMenaces(Echiquier echiquier) {
        boolean[][][] menaces = new boolean[stats[1]][stats[0]][stats[0]];
        Player[] players = echiquier.getPlayers();
        for (int i = 0; i < players.length; i++) {
            menaces[i] = allPlayerMenaces(players[i]);
         }
        return menaces;
    }

    private static boolean[][] allPlayerMenaces(Player player) {
        boolean[][] menaces = new boolean[stats[0]][stats[0]];
        ArrayList<Coup> coups = player.getAllPossibleMoves();
        ArrayList<Piece> pieces = player.getAllPieces();
        for (Piece piece : pieces) {
            if (piece instanceof Pion) {
                computeMenacesPiece(menaces, piece);
            }
            else if (piece instanceof Roi) {
                computeMenacesPiece(menaces, piece);
            }
        }
        for (Coup coup : coups) {
            if (coup.getClass() == Roque.class) {continue;}
            Case c = coup.getCaseArrivee();
            int[] coords = c.getCoordInt();
            if (coup.getPiece().getClass() != Pion.class && coup.getPiece().getClass() != Roi.class) {
                menaces[coords[1]][coords[0]] = true;
            }
        }
        return menaces;
    }

    private static void computeMenacesPiece(boolean[][] menaces, Piece p) {
        int[][] directions = p.getDirections();
        Case position = p.getPosition();
        int[] coords = position.getCoordInt();
        for ( int i = p instanceof Pion ? 1 : 0; i < directions.length; i++) {
            int x = directions[i][0];
            int y = directions[i][1];
            if (position.getValidTranslatedCase(x,y) != null) {
                menaces[coords[1] - y][coords[0] + x] = true;
            }
        }
    }
}
