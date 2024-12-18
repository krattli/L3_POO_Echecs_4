package fr.pantheonsorbonne.miage.playerRelatedStuff;

import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.pieces.simple.Roi;

import java.util.ArrayList;
import java.util.Random;

public class PlayerBot extends Player {
    private final Random random;

    public PlayerBot(String name) {
        super(name);
        this.random = new Random();
    }

    public Coup getNextCoup() {
        Roi myKing = this.getHisKing();
        if (myKing == null) {
            return null;
        }

        if (this.isChecked()) {
            return getRandomMove(myKing.getAllPossibleMoves());
        }

        ArrayList<Coup> allMoves = this.getAllPossibleMoves();
        if (allMoves.isEmpty()) {
            handleNoAvailableMoves();
            return null;
        }

        return getRandomMove(allMoves);
    }

    // Extracted helper method to randomly select a move from a list
    private Coup getRandomMove(ArrayList<Coup> moves) {
        if (moves == null || moves.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(moves.size());
        return moves.get(randomIndex);
    }

    // Extracted helper method to handle the case where there are no available moves
    private void handleNoAvailableMoves() {
        this.getEchiquier().printPlateau();
        this.getEchiquier().printCasesMenacees();
        System.out.println("No moves available for color: " + this.getColor());
    }
}
