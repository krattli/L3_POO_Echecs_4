package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.game.typeCoup.Deplacement;
import fr.pantheonsorbonne.miage.game.typeCoup.Prise;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

import java.util.ArrayList;

public class Partie {
    Player[] players;
    int[] scoreBoard;
    Echiquier plateau;
    ArrayList<Coup> historiqueDesCoups;

    public Partie(Player[] joueurs) {
        this.players = joueurs;
        this.plateau = new Echiquier(players);
        scoreBoard = new int[players.length];
        historiqueDesCoups = new ArrayList<>();
    }

    public void initPlateau() {
        plateau.initBoard();
        plateau.computeMenaces();
    }

    public Echiquier getPlateau() {return plateau;}

    public void play(){

        boolean playing = true;
        Player[] players = this.players;
        int tour = 0;
        while (playing) {
            if (tour >100000) {
                playing = false;
            }
            //System.out.println("tour : "+tour);
            Player playerPlaying = players[tour % players.length];
            plateau.computeMenaces();
            Coup c = playerPlaying.getNextCoup();
            if (c == null) {
                playerPlaying.suicide();
                tour++;
                continue;
            }
            if (playerPlaying.isAlive()){
                jouerCoup(c);
                historiqueDesCoups.add(c);
                System.out.println(playerPlaying.getColor() + "   " + tour + "   " + c.toString());
            }
            else {
                System.out.println(playerPlaying.getNom() + " is dead :/");
            }
            tour++;
        }
    }
    public void jouerCoup (Coup c){
        plateau.jouerCoup(c);
    }

    public void printWinners(){
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].getNom() + " à " + scoreBoard[i] + " points");
        }
    }

}
