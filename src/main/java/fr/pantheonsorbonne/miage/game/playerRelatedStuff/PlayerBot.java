package fr.pantheonsorbonne.miage.game.playerRelatedStuff;

import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.pieces.simple.Roi;

import java.util.ArrayList;

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
            //handlePat();
            return null;
        }
        return getRandomMove(allMoves);
    }

    private void handlePat() {
        this.getEchiquier().printPlateau();
        this.getEchiquier().printCasesMenacees();
        System.out.println("No moves available for color: " + this.getColor());
    }
}
