package fr.pantheonsorbonne.miage.game.Pieces.super_pieces;

import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.game.Pieces.PieceSimple;
import fr.pantheonsorbonne.miage.game.Pieces.SuperPiece;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import java.util.ArrayList;

public class SuperCavalier extends SuperPiece {

    public SuperCavalier(Player owner, Case position) {
        super(owner, position);
    }

    @Override
    public boolean isOccupiedByThisPiece(Case position) {
        return false;
    }

    @Override
    public ArrayList<Coup> getAllPossibleMoves() {
        return null;
    }

    @Override
    public Boolean isTheMoveLegal() {
        return false;
    }
}