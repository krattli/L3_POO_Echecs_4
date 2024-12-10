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
        ArrayList<Piece> allPieces=  this.getAllPieces();
        if (!allPieces.isEmpty()) {
            ArrayList<Coup> allCoups;
            do {
                int randIndexPieces = random.nextInt(allPieces.size());
                Piece randPiece = allPieces.get(randIndexPieces);
                allCoups = randPiece.getAllPossibleMoves();
                if (!allCoups.isEmpty()) {
                    int randIndexCoups = random.nextInt(allCoups.size());
                    return allCoups.get(randIndexCoups);
                }
            } while (!allCoups.isEmpty());

        }
        return null;
    }

    public ArrayList<Piece> getAllPlayerPieces(){
        ArrayList<Piece> allPlayerPieces= new ArrayList<>();
        //Implémenter
        return allPlayerPieces;
    }
}
