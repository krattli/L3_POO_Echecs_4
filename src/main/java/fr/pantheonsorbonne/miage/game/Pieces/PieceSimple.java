package fr.pantheonsorbonne.miage.game.Pieces;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import java.util.ArrayList;

public abstract class PieceSimple extends Piece {

    public PieceSimple(Player owner, Case position) {
        super(owner, position);
    }

    @Override
    public abstract ArrayList<Coup> getAllPossibleMoves();

    @Override
    public abstract boolean isTheMoveLegal(Coup coup);

}