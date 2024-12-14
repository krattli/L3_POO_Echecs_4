package fr.pantheonsorbonne.miage.game.pieces.simple;

import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.pieces.PieceSimple;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import java.util.ArrayList;

public class Roi extends PieceSimple {
    public Roi(Player owner, Case position) {
        super(owner, position);
    }
    public Roi(Player owner, String position) throws WrongCaseFormatException {
        super(owner, position);
    }

    protected int[][] getDirections() {return new int[][] {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};}

    public ArrayList<Coup> getAllPossibleMoves() {
        ArrayList<Coup> coupsWithoutEchecCheck =  this.computeLinesOfMoves(this.getDirections(),1);
        return getCheckCheckedMoves(coupsWithoutEchecCheck);
    }

    private ArrayList<Coup> getCheckCheckedMoves(ArrayList<Coup> coupsWithoutEchecCheck) {
        ArrayList<Coup> checkMoves = new ArrayList<>();
        return checkMoves;
    }

    private int[][] getDirectionDiagonales() {return new int[][] {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};}
    private int[][] getDirectionsLignesDroites() {return new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};}
    private int[][] getDirectionsCavalier() {return new int[][] {{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1},{-2,1},{-1,2}};}
}
