package fr.pantheonsorbonne.miage.game.pieces.super_pieces;

import fr.pantheonsorbonne.miage.game.pieces.SuperPiece;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import java.util.ArrayList;

public class SuperTour extends SuperPiece {

    public SuperTour(Player owner, Case position) {
        super(owner, position);
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
}