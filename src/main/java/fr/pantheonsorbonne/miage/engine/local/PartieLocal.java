package fr.pantheonsorbonne.miage.engine.local;

import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

import java.util.ArrayList;

public class PartieLocal {
    Player[] players;
    int[] scoreBoard;
    Echiquier plateau;
    ArrayList<Coup> historiqueDesCoups;

    public PartieLocal(Player[] joueurs) {
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
            Player playerPlaying = players[tour % players.length];
            plateau.computeMenaces();
            Coup c = playerPlaying.getNextCoup();
            if (c == null) {
                plateau.printPlateau();
                for (Player p : players) {
                    if (p != playerPlaying) {
                        plateau.printCasesMenacees(p);
                        System.out.println("====================================");
                    }
                }
                System.exit(0);
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
