package fr.pantheonsorbonne.miage.engine.local;

import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.typeCoup.Prise;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;

import java.util.ArrayList;
import java.util.Scanner;

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

        this.plateau.setPartieLocal(this);
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

            if (playerPlaying.isAlive()){
                jouerTour(playerPlaying);
            }

            if (this.endCondition()) {
                playing = false;
            }
            tour++;
        }
    }

    private void jouerTour(Player playerPlaying) {

        Coup c = playerPlaying.getNextCoup();

        jouerCoup(c);
        addPoints(c);
        historiqueDesCoups.add(c);
        plateau.computeMenaces();

        Player matedPlayer = plateau.isSomeOneMatted();

        if (matedPlayer != null) {
            matedPlayer.kill();
            playerPlaying.addPoints(12);
        }

        System.out.println(playerPlaying.getColor() + "   " + "   " + c + " Score : " +  this.scoreBoardToString());

    }

    public void jouerCoup (Coup c){
        plateau.jouerCoup(c);
    }

    public void addPoints(Coup c){
        if (c.getClass() != Prise.class) {return;}
        Player joueur = c.getPiece().getOwner();
        int points = ((Prise) c).getPiecePrise().getValuePiece();
        joueur.addPoints(points);
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

    public void jouerAPartirDuCoup(int index) {
        PlayerBot j1 = new PlayerBot("a");
        PlayerBot j2 = new PlayerBot("a");
        PlayerBot j3 = new PlayerBot("a");
        PlayerBot j4 = new PlayerBot("a");

        Echiquier simulation = new Echiquier(new Player[]{j1, j2, j3, j4});
        simulation.initBoard();
        simulation.computeMenaces();
        ArrayList<Coup> historique = this.historiqueDesCoups;
        int i;
        for (i = 0; i < index; i++) {

            Coup c = historique.get(i);
            simulation.jouerCoup(c);
            simulation.computeMenaces();
        }
        while ( i < historique.size()) {
            simulation.printPlateau();
            simulation.printCasesMenacees();
            Coup c = historique.get(i);
            simulation.jouerCoup(c);
            simulation.computeMenaces();
            System.out.println( c + " ");
            new Scanner(System.in).nextLine();
            i++;
        }
    }

}
