package fr.pantheonsorbonne.miage.game.pieces.super_pieces;

import fr.pantheonsorbonne.miage.game.pieces.SuperPiece;
import fr.pantheonsorbonne.miage.game.playersAI.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import java.util.ArrayList;

public class SuperPion extends SuperPiece {
    private static final int VALUE_PIECE = 2;

    public SuperPion(Player owner, Case position) {
        super(owner, position);
    }
    public SuperPion(Player owner, String position) {
        super(owner,position);
    }

    @Override
    public int getValuePiece() {
        return VALUE_PIECE;
    }

    @Override
    public boolean isOccupiedByThisPiece(Case position) {
        return false;
    }

    @Override
    public ArrayList<Case> getCasesOccupiedByThisPiece() {
        return null;
    }

    @Override
    public ArrayList<Coup> getAllPossibleMoves() {
        return null;
    }

    @Override
    public int[][] getDirections() {return new int[][]{};}
}