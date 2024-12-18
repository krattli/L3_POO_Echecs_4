package fr.pantheonsorbonne.miage.engine.local;

import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.game.pieces.simple.Dame;
import fr.pantheonsorbonne.miage.game.typeCoup.Prise;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PartieLocal {
    private static final int POINTS_SUICIDE = 10;
    private static final int POINTS_ECHEC_ET_MAT = 12;

    private final Scanner scanner = new Scanner(System.in);
    private final Player[] players;
    private final int[] scoreBoard;
    private final Echiquier plateau;
    private final List<Coup> historiqueDesCoups;
    private int tour;
    private boolean showEachCoup = false;

    public PartieLocal(Player[] joueurs) {
        this.players = joueurs;
        this.plateau = new Echiquier(players);
        this.tour = 0;
        this.scoreBoard = new int[players.length];
        this.historiqueDesCoups = new ArrayList<>();
    }

    public PartieLocal(Player[] joueurs, boolean showEachCoup) {
        this(joueurs);
        this.showEachCoup = showEachCoup;
    }

    public void initPlateau() {
        revivePlayers();
        plateau.initBoard();
        plateau.computeMenaces();
    }

    private void revivePlayers () {
        for (Player p : players) {
            p.revive();
        }
    }

    public Echiquier getPlateau() {
        return plateau;
    }

    public int[] getUpdatedScoreBoard() {
        return Arrays.stream(players).mapToInt(Player::getPoints).toArray();
    }

    public String scoreBoardToString() {
        return String.join(" ", Arrays.stream(getUpdatedScoreBoard())
                .mapToObj(String::valueOf)
                .toArray(String[]::new));
    }

    public Player play() {
        boolean playing = true;
        while (playing) {
            Player currentPlayer = players[tour % players.length];
            if (currentPlayer.isAlive()) {
                jouerTour(currentPlayer);
            } if (tour > 100000) {
                handleInfiniteLoop();
            }
            if (endConditionMet()) {
                playing = false;
            }
            tour++;
        }
        return this.getHighestScore();
    }

    private void jouerTour(Player playerActif) {
        if (!playerActif.isAlive() || playerActif.getAllPieces().isEmpty()) {
            handlePat(playerActif);
            return;
        }
        Coup coup = playerActif.getNextCoup();
        if (coup == null) {
            handlePat(playerActif);
            return;
        }
        jouerUnCoup(coup);
        addPointsForCoup(coup);
        if (showEachCoup) {
            System.out.println(playerActif.getColor() + " a joué le coup  : " + coup);
            plateau.printPlateau();
            scanner.nextLine();
        }
        historiqueDesCoups.add(coup);
        plateau.computeMenaces();

        Player matedPlayer = plateau.isSomeOneMatted();
        if (matedPlayer != null) {
            handleEchecEtMat(playerActif, matedPlayer);
        }
    }

    private void handlePat(Player player) {
        player.addPoints(POINTS_SUICIDE);
        player.kill();
    }

    private void handleEchecEtMat(Player winner, Player loser) {
        loser.kill();
        winner.addPoints(POINTS_ECHEC_ET_MAT);
        plateau.computeMenaces();
    }

    public void jouerUnCoup(Coup coup) {
        if (coup != null) {
            plateau.jouerCoup(coup);
        }
    }

    public void addPointsForCoup(Coup coup) {
        if (coup == null) {
            Arrays.stream(players).forEach(p -> p.addPoints(POINTS_SUICIDE));
        } else if (coup instanceof Prise) {
            Prise prise = (Prise) coup;
            prise.getPiece().getOwner().addPoints(prise.getPiecePrise().getValuePiece());
        }
    }

    private boolean endConditionMet() {
        long alivePlayersCount = Arrays.stream(players).filter(Player::isAlive).count();
        int totalPiecesOnBoard = Arrays.stream(players).mapToInt(p -> p.getAllPieces().size()).sum();
        return alivePlayersCount <= 1 || alivePlayersCount == totalPiecesOnBoard;
    }

    public Player getHighestScore() {
        int[] updatedScoreBoard = getUpdatedScoreBoard();
        int max = updatedScoreBoard[0];
        int index = 0;
        for (int i = 0; i < updatedScoreBoard.length; i++) {
            if (updatedScoreBoard[i] > max) {
                max = updatedScoreBoard[i];
                index = i;
            }
        }
        return players[index];
    }

    public void printWinners() {
        int[] updatedScoreBoard = getUpdatedScoreBoard();
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].getNom() + " a " + updatedScoreBoard[i] + " points");
        }
    }

    public void jouerAPartirDuCoup(int index) {
        Echiquier simulation = initializeSimulation();
        rejouerCoupsDepuis(index, simulation);
    }

    private void handleInfiniteLoop() {
        plateau.printPlateau();
        plateau.printCasesMenacees();
        System.out.println("Tour " + tour);
        for (Player p : players) {
            ArrayList<Piece> a = p.getAllPieces();
            for (Piece piece : a) {
                System.out.println(piece.getAllPossibleMoves());
                if (piece.getClass() == Dame.class) {
                    System.out.println("Dame : " + piece.getOwner().getAllPieces()  + "   " + piece.getPosition());
                }
            }
        }
        System.out.println("Score : " + scoreBoardToString());
        System.out.println("---------------------------------------");
        System.exit(22);
    }

    private Echiquier initializeSimulation() {
        PlayerBot j1 = new PlayerBot("SimBot1");
        PlayerBot j2 = new PlayerBot("SimBot2");
        PlayerBot j3 = new PlayerBot("SimBot3");
        PlayerBot j4 = new PlayerBot("SimBot4");

        Echiquier simulation = new Echiquier(new Player[]{j1, j2, j3, j4});
        simulation.initBoard();
        simulation.computeMenaces();
        return simulation;
    }

    private void rejouerCoupsDepuis(int index, Echiquier simulation) {
        for (int i = 0; i < index; i++) {
            Coup coup = historiqueDesCoups.get(i);
            simulation.jouerCoup(coup);
            simulation.computeMenaces();
        }
        for (int i = index; i < historiqueDesCoups.size(); i++) {
            simulation.printPlateau();
            simulation.printCasesMenacees();
            Coup coup = historiqueDesCoups.get(i);
            simulation.jouerCoup(coup);
            simulation.computeMenaces();
            System.out.println(coup + " ");
            new Scanner(System.in).nextLine();
        }
    }
}
