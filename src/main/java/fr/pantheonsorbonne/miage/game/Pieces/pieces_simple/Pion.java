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
    private int[] direction;
    private int[][] diagonales;

    public Pion(Player owner, Case position) {
        super(owner, position);
        switch (this.getOwner().getColor()) {
            case RED:
                direction = new int[]{0, 1};
                diagonales = new int[][]{{-1, 1}, {1, 1}};
                break;
            case GREEN:
                direction = new int[]{-1, 0};
                diagonales = new int[][]{{-1, -1}, {-1, 1}};
                break;
            case YELLOW:
                direction = new int[]{0, -1};
                diagonales = new int[][]{{-1, -1}, {1, -1}};
                break;
            case BLUE:
                direction = new int[]{1, 1};
                diagonales = new int[][]{{1, -1}, {1, 1}};
                break;
        }
    }

    @Override
    public ArrayList<Coup> getAllPossibleMoves() {
        ArrayList<Coup> coups = new ArrayList<>();

        Coup CoupCaseAvant = this.getCoupCaseAvant();
        if (CoupCaseAvant != null) {
            coups.add(CoupCaseAvant);
        }

        Coup CoupDouble = this.getCoupCaseAvant();
        if (CoupCaseAvant != null) {
            coups.add(CoupDouble);
        }

        Coup[] coupsDiagonaux = this.getCoupsDiagonaux();
        for (Coup coup : coupsDiagonaux) {
            if (coup != null) {
                coups.add(coup);
            }
        }

        return coups;
    }

    private Coup getCoupCaseAvant() {
        int[] coordsActuelles = this.getPosition().getCoordInt();
        Case caseAvant = new Case(coordsActuelles[0] + this.direction[0], coordsActuelles[1] + this.direction[1]);
        Coup coup = null;
        if (caseAvant.isValid() && Echiquier.getPieceAt(caseAvant) == null) {
            coup = new Coup(this, caseAvant);
        }
        return coup;
    }

    private Coup getCoupDouble() {
        int[] coordsActuelles = this.getPosition().getCoordInt();
        Coup coupDouble = null;
        return coupDouble;
    }

    private Coup[] getCoupsDiagonaux() {
        Coup[] coups = new Coup[2];
        for (int i = 0; i < 2; i++) {
            int dx = this.diagonales[i][0];
            int dy = this.diagonales[i][1];
            Case captureCase = this.position.getValidTranslatedCase(dx, dy);
            if (captureCase != null && Echiquier.getPieceAt(captureCase) != null) {
                Piece targetPiece = Echiquier.getPieceAt(captureCase);
                if (!targetPiece.getOwner().equals(this.getOwner())) {
                    coups[i] = new Coup(this,captureCase);
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
