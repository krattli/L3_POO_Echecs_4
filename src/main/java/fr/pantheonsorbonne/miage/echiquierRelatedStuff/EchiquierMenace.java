package fr.pantheonsorbonne.miage.echiquierRelatedStuff;

import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.pieces.simple.Pion;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

import java.util.ArrayList;


public class EchiquierMenace {
    private static final int[] stats = Echiquier.getInfosAboutThat();
    private EchiquierMenace() {}

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
        for (Coup coup : coups) {
            Case c = coup.getArrivee();
            int[] coords = c.getCoordInt();
            if (coup.getPiece().getClass() == Pion.class){
                Pion p = (Pion) coup.getPiece();
                computeMenacesPion(menaces, p);
            }
            else {
                menaces[coords[1]][coords[0]] = true;
            }
        }
        return menaces;
    }

    private static void computeMenacesPion(boolean[][] menaces, Pion p) {
        int[][] directions = p.getDirections();
        Case position = p.getPosition();
        int[] coords = position.getCoordInt();
        for (int i = 1; i < directions.length; i++) {
            int x = directions[i][0];
            int y = directions[i][1];
            if (position.getValidTranslatedCase(x,y) != null) {
                menaces[coords[1] - y][coords[0] + x] = true;
            }
        }
    }
}
