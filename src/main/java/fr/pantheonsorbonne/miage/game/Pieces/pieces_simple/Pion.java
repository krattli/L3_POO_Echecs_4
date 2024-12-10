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

        Case positionActuelle = this.getPosition();
        int[] coordsActuelles = positionActuelle.getCoordInt();

        int deltaX = 0, deltaY = 0;
        switch (this.getOwner().getColor()) {
            case RED:    deltaY = 1;  break;
            case GREEN:  deltaX = -1;  break;
            case YELLOW: deltaY = -1; break;
            case BLUE:   deltaX = 1; break;
        }

        Case caseAvant = new Case(coordsActuelles[0] + deltaX, coordsActuelles[1] + deltaY);
        if (caseAvant.isValid() && Echiquier.getPieceAt(caseAvant) == null) {
            coups.add(new Coup(caseAvant, this));
        }

        int[][] diagonales = {
                {-1, 1}, {1, 1},
                {-1, -1}, {-1, 1},
                {-1, -1}, {1, -1},
                {1, -1}, {1, 1}
        };

        int colorIndex = this.getOwner().getColor().ordinal();

        for (int i = 0; i < 2; i++) {
            int dx = diagonales[colorIndex * 2 + i][0];
            int dy = diagonales[colorIndex * 2 + i][1];
            Case captureCase = positionActuelle.getValidTranslatedCase(dx, dy);
            if (captureCase != null && Echiquier.getPieceAt(captureCase) != null) {
                Piece targetPiece = Echiquier.getPieceAt(captureCase);
                if (!targetPiece.getOwner().equals(this.getOwner())) {
                    coups.add(new Coup(captureCase, this));
                }
            }
        }
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
                    //finir de coder ça lmao

        }
        return isPromu;
    }

    public void promouvoirPionToQueen() {

    }
}
