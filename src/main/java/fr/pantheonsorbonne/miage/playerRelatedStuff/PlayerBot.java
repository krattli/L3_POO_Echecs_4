package fr.pantheonsorbonne.miage.playerRelatedStuff;

import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.game.pieces.simple.Roi;

import java.util.ArrayList;
import java.util.Random;

public class PlayerBot extends Player{

    static Random random = new Random();

    public PlayerBot(String name) {
        super(name);
    }

    public Coup getNextCoup(){
        Roi hisKing = this.getHisKing();
        if (hisKing == null) {
            return null;
        }
        ArrayList<Coup> allCoupsRoi = this.getHisKing().getAllPossibleMoves();
        if (this.isChecked()){
            if (!allCoupsRoi.isEmpty()){
                int index = random.nextInt(allCoupsRoi.size());
                return allCoupsRoi.get(index);
            }
            else{
                return null;
            }
        }
        ArrayList<Coup> coups = this.getAllPossibleMoves();
        if (coups.isEmpty()){
            this.getEchiquier().printPlateau();
            this.getEchiquier().printCasesMenacees();
            System.out.println("plus de coups dispo"+ this.getColor());
            System.exit(0);
        }
        int index = random.nextInt(coups.size());
        return coups.get(index);
    }
}
