package fr.pantheonsorbonne.miage.engine.local;

import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

import java.util.ArrayList;

public class PartieLocal {
    private Player[] players;
    private int[] scoreBoard;
    private final Echiquier plateau;
    private ArrayList<Coup> historiqueDesCoups;

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
    public int[] getScoreBoard() {return new int[] {
            players[0].getPoints(),
            players[1].getPoints(),
            players[2].getPoints(),
            players[3].getPoints(),};
    }
    public String scoreBoardToString() {
        StringBuilder stringScoreBoard = new StringBuilder();
        int[] individualScore = getScoreBoard();
        for (int s : individualScore) {
            stringScoreBoard.append(s + " ");
        }
        return stringScoreBoard.toString();
    }

    public void play(){

        boolean playing = true;
        Player[] players = this.players;
        int tour = 0;

        while (playing) {

            Player playerPlaying = players[tour % players.length];

            Coup c = playerPlaying.getNextCoup();

//            if (c == null) {
//                Player win = whoMated(playerPlaying);
//                win.addPoints(10);
//                System.out.println("echec et mat de  " +win.getColor() + " sur " + playerPlaying.getColor());
//                playerPlaying.suicide();
//                System.out.println(playerPlaying.getColor()+" "+playerPlaying.isAlive());
//                tour++;
//                continue;
//            }

            if (playerPlaying.isAlive()){

                jouerCoup(c);
                historiqueDesCoups.add(c);
                plateau.computeMenaces();

                Player matedPlayer = plateau.isSomeOneMatted();

                if (matedPlayer != null) {
                    matedPlayer.kill();
                    playerPlaying.addPoints(12);
                }

                System.out.println(playerPlaying.getColor() + "   " + tour + "   " + c + " Score : " +  this.scoreBoardToString());
            }

            if (this.endCondition()) {
                playing = false;
            }

            tour++;
        }
    }

    public void jouerCoup (Coup c){
        plateau.jouerCoup(c);
    }

    private boolean endCondition() {
        int alivePlayers = 0;
        int numberPiecesOnBoard = 0;
        for (Player player : players) {
            if (player.isAlive()) {
                alivePlayers++;
                numberPiecesOnBoard += player.getAllPieces().size();
            }
        }
        if (alivePlayers == numberPiecesOnBoard) {
            return true;
        }
        return alivePlayers == 1;
    }

    private static Player whoMated(Player player) {
        int[] position = player.getHisKing().getPosition().getCoordInt();
        Echiquier thisBoard = player.getEchiquier();
        boolean[][][] menaces = thisBoard.getCasesMenacees();
        for (int i = 0; i < menaces.length; i++) {
            if (menaces[i][position[0]][position[1]]) {
                return thisBoard.getPlayers()[i];
            }
        }
        return null;
    }

    public void printWinners(){
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].getNom() + " à " + scoreBoard[i] + " points");
        }
    }

}
