package fr.pantheonsorbonne.miage.playerRelatedStuff;

import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Piece;

import java.util.ArrayList;
import java.util.Random;

public class PlayerBot extends Player{

    static Random random = new Random();

    public PlayerBot(String name) {
        super(name);
    }

    public Coup getNextCoup(){
        ArrayList<Coup> coups = this.getAllPossibleMoves();

        return null;
    }

}
