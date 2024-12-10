package fr.pantheonsorbonne.miage.game.Pieces.pieces_simple;

import fr.pantheonsorbonne.miage.enums.Colonne;
import fr.pantheonsorbonne.miage.enums.Ligne;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;

import java.util.ArrayList;

public class Pion extends Piece {
    public Pion(Player owner, Case position) {
        super(owner, position);
    }

    @Override
    public ArrayList<Coup> getAllPossibleMoves() {
        ArrayList<Coup> coups = new ArrayList<>();
        /*
        Case positionActuelle = this.getPosition();
        int[] coords = positionActuelle.getCoordInt();

        // Directions principales pour avancer
        int deltaX = 0, deltaY = 0;
        switch (this.getOwner().getColor()) {
            case RED:    deltaY = 1;  break;  // Bas
            case GREEN:  deltaX = 1;  break;  // Droite
            case YELLOW: deltaY = -1; break;  // Haut
            case BLUE:   deltaX = -1; break;  // Gauche
        }

        // Mouvement simple vers l'avant
        Case caseAvant = new Case(coords[0] + deltaX, coords[1] + deltaY);
        if (isTheMoveLegal(caseAvant) && Echiquier.getPieceAt(caseAvant) == null) {
            coups.add(new Coup(caseAvant, this));
        }

        // Captures diagonales (gauche et droite)
        // Les directions sont définies en fonction de la couleur du joueur
        int[][] diagonales = {
                {1, 1}, {1, -1},   // RED: Bas-droite, Bas-gauche
                {1, 1}, {-1, 1},   // GREEN: Droite-haut, Droite-bas
                {-1, -1}, {1, -1}, // YELLOW: Haut-gauche, Haut-droite
                {-1, 1}, {-1, -1}  // BLUE: Gauche-haut, Gauche-bas
        };

        // Récupérer l'index de la couleur
        int colorIndex = this.getOwner().getColor().ordinal(); // RED=0, GREEN=1, YELLOW=2, BLUE=3

        // Vérifier les cases de capture diagonale
        for (int i = 0; i < 2; i++) {
            int dx = diagonales[colorIndex * 2 + i][0];
            int dy = diagonales[colorIndex * 2 + i][1];
            Case captureCase = new Case(coords[0] + dx, coords[1] + dy);
            if (isTheMoveLegal(captureCase)) {
                Piece targetPiece = Echiquier.getPieceAt(captureCase);
                if (targetPiece != null && !targetPiece.getOwner().equals(this.getOwner())) {
                    coups.add(new Coup(captureCase, this));
                }
            }
        }
        */
        return coups;
    }

    public boolean isTheMoveLegal(Coup coup) {
        return false;
    }

    public boolean isPromuToQueen() {
        boolean isPromu = false;
        switch (this.getOwner().getColor()) {
            case RED:
                if (this.getPosition().getLigne() == Ligne.FOURTEEN) {
                    isPromu = true;
                }
                break;
            case YELLOW:
                if (this.getPosition().getColonne() == Colonne.A) {
                    isPromu = true;
                }
                break;
                case GREEN:

        }
        return isPromu;
    }

    public void promouvoirPionToQueen() {

    }
}
