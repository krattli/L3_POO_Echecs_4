package fr.pantheonsorbonne.miage.echiquierRelatedStuff;

import fr.pantheonsorbonne.miage.enums.AffichagePieces;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

public class PrintEchiquier {
    private PrintEchiquier() {}

    public static void printEchiquier(Echiquier echiquier) {
        Piece[][] plateau = echiquier.getPlateau();
        for (Piece[] pieces : plateau) {
            for (Piece piece : pieces) {
                printPiece(piece);
            }
            System.out.println("|");
        }
    }

    public static void printMenaces(Echiquier plateau, Player player) {
        int playerOrder = player.getColor().ordinal();
        boolean[][] casesMenacees = plateau.getCasesMenacees()[playerOrder];
        for (boolean[] row : casesMenacees) {
            for (boolean b : row) {
                if (b) {
                    System.out.print(" * ");
                }
                else {
                    System.out.print(" _ ");
                }
            }
            System.out.println();
        }
    }

    private static void printPiece(Piece piece) {
        if (piece != null) {
            String CouleurAffichage;
            if (piece.getOwner().getColor().ordinal() % 2 == 0){
                CouleurAffichage = "B";
            }
            else {
                CouleurAffichage = "N";
            }
            String nomComplet = piece.getClass().toString();
            String nomClasse = nomComplet.substring(nomComplet.lastIndexOf('.') + 1) + CouleurAffichage;
            System.out.print("| "+ AffichagePieces.getByAlias(nomClasse).getSymbol()+" ");
        }
        else {
            System.out.print("|___");
        }
    }
}
