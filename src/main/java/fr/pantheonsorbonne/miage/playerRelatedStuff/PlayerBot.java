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
            int i = 0;
            if (allCoupsRoi.isEmpty()){
                ArrayList<Coup> allCoups = this.getHisKing().getAllPossibleMoves();
                Roi r = this.getHisKing();
                return null;
            }
            else{
                int index = random.nextInt(allCoupsRoi.size());
                return allCoupsRoi.get(index);
            }
        }
        ArrayList<Coup> coups = this.getAllPossibleMoves();
        int index = random.nextInt(coups.size());
        if (index < 0) {
            System.out.println(index + " l'index est plus bas que terre");
            System.out.println(this.getColor() + "  " + this.isAlive());
            System.out.println(coups);
            System.exit(0);
        }
        return coups.get(index);
    }
}
