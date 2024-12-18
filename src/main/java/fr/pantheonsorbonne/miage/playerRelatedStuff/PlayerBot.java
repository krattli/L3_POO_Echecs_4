package fr.pantheonsorbonne.miage.playerRelatedStuff;

import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.pieces.simple.Roi;

import java.util.ArrayList;
import java.util.Random;

public class PlayerBot extends Player {

    public PlayerBot(String name) {
        super(name);
    }

    @Override
    public Coup getNextCoupKingChecked() {
        Roi myKing = this.getHisKing();
        return getRandomMove(myKing.getAllPossibleMoves());
    }

    @Override
    public Coup getNextCoupNormal() {
        ArrayList<Coup> allMoves = this.getAllPossibleMoves();
        if (allMoves.isEmpty()) {
            //handleNoAvailableMoves();
            return null;
        }

        return getRandomMove(allMoves);
    }

    private void handleNoAvailableMoves() {
        this.getEchiquier().printPlateau();
        this.getEchiquier().printCasesMenacees();
        System.out.println("No moves available for color: " + this.getColor());
    }
}
