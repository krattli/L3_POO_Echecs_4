package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Color;
import fr.pantheonsorbonne.miage.game.pieces.simple.Dame;
import fr.pantheonsorbonne.miage.game.pieces.simple.FirstMovePiece;
import fr.pantheonsorbonne.miage.game.pieces.simple.Roi;
import fr.pantheonsorbonne.miage.game.pieces.simple.Tour;
import fr.pantheonsorbonne.miage.game.typeCoup.Deplacement;
import fr.pantheonsorbonne.miage.game.typeCoup.Prise;
import fr.pantheonsorbonne.miage.game.typeCoup.Promotion;
import fr.pantheonsorbonne.miage.game.typeCoup.Roque;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.echiquierRelatedStuff.EchiquierInitializer;
import fr.pantheonsorbonne.miage.echiquierRelatedStuff.ComputeMenace;
import fr.pantheonsorbonne.miage.echiquierRelatedStuff.PrintEchiquier;

public class Echiquier {
    private static final int TAILLE = 14;
    private static final int NB_JOUEURS = 4;

    private final Piece[][] plateau = new Piece[TAILLE][TAILLE];
    private final Player[] players;
    private boolean[][][] casesMenacees;

    public Echiquier(Player[] players) {
        if (players.length != NB_JOUEURS) {
            System.out.println("Le jeu se joue à " + NB_JOUEURS);
            this.players = new Player[NB_JOUEURS];
            System.exit(0);
        } else {
            this.players = players;
            for (int i = 0; i < NB_JOUEURS; i++) {
                players[i].setColor(Color.values()[i]);
                players[i].setEchiquier(this);
            }
        }
        casesMenacees = new boolean[NB_JOUEURS][TAILLE][TAILLE];
    }

    public void initBoard() {
        EchiquierInitializer.initialiser(this);
    }

    public void clearPlateau() {
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                plateau[i][j] = null;
            }
        }
    }

    public Player[] getPlayers() {
        return this.players;
    }

    public Piece[][] getPlateau() {
        return this.plateau;
    }

    public static int[] getBoardInfo() {
        return new int[]{TAILLE, NB_JOUEURS};
    }

    public boolean[][][] getCasesMenacees() {
        return this.casesMenacees;
    }

    public void printPlateau() {
        PrintEchiquier.printEchiquier(this);
    }

    public void printCasesMenacees() {
        PrintEchiquier.printMenaces(this, null);
    }

    public void computeMenaces() {
        this.casesMenacees = ComputeMenace.computeAllMenaces(this);
    }

    public void setPieceToPosition(Piece piece, Case position) {
        if (piece.getPosition() != null) {
            int[] coordDepart = piece.getPosition().getCoordInt();
            plateau[coordDepart[0]][coordDepart[1]] = null;
        }
        if (position == null) {
            piece.setPosition(null);
        } else {
            int[] coordDestination = position.getCoordInt();
            plateau[coordDestination[0]][coordDestination[1]] = piece;
            piece.setPosition(position);
        }
    }

    public void jouerCoup(Coup coup) {
        if (coup instanceof Deplacement) {
            handleDeplacement((Deplacement) coup);
        } else if (coup instanceof Prise) {
            handlePrise((Prise) coup);
        } else if (coup instanceof Promotion) {
            handlePromotion((Promotion) coup);
        } else if (coup instanceof Roque) {
            jouerRoque((Roque) coup);
        } else {
            handleUnknownMove(coup);
        }

        Piece pieceJoueuse = coup.getPiece();
        if (pieceJoueuse instanceof FirstMovePiece && ((FirstMovePiece) pieceJoueuse).hasntMooved()) {
            ((FirstMovePiece) pieceJoueuse).hasMooved();
        }
    }

    private void handleDeplacement(Deplacement coup) {
        setPieceToPosition(coup.getPiece(), coup.getArrivee());
    }

    private void handlePrise(Prise coup) {
        if (coup.getPiecePrise() instanceof Roi) {
            coup.getPiece().getOwner().kill();
        }
        getPieceAt(coup.getArrivee()).kill();
        setPieceToPosition(coup.getPiece(), coup.getArrivee());
    }

    private void handlePromotion(Promotion coup) {
        coup.getPiece().kill();
        new Dame(coup.getPiece().getOwner(), coup.getArrivee()).setValuePiecePromotion();
    }

    private void handleUnknownMove(Coup coup) {
        System.out.println("Type de coup inconnu : " + coup.getClass());
        System.exit(101);
    }

    private void jouerRoque(Roque roque) {
        Roi roi = roque.getPiece();
        Tour tour = roque.getTourARoquer();

        int[][] translations = getRoqueTranslations(roque);
        int[] translationRoi = translations[0];
        int[] translationTour = translations[1];

        setPieceToPosition(roi, roi.getPosition().getValidTranslatedCase(translationRoi[0], translationRoi[1]));
        setPieceToPosition(tour, tour.getPosition().getValidTranslatedCase(translationTour[0], translationTour[1]));
    }

    private int[][] getRoqueTranslations(Roque roque) {
        int[] sens = roque.getSensDuRoqueRoi();
        if (roque.isGrandRoque()) {
            return new int[][]{
                    {sens[0] * 2, sens[1] * 2},
                    {-sens[0] * 3, -sens[1] * 3}
            };
        } else {
            return new int[][]{
                    {sens[0] * 2, -sens[1] * 2},
                    {-sens[0] * 2, sens[1] * 2}
            };
        }
    }

    public Player isSomeOneMatted() {
        for (Player player : players) {
            if (player.isAlive()) {
                Roi hisKing = player.getHisKing();
                if (isKingMatted(hisKing)) {
                    return player;
                }
            }
        }
        return null;
    }

    private boolean isKingMatted(Roi king) {
        if (king == null) {
            return true;
        }
        return isMenaced(king) && king.getAllPossibleMoves().isEmpty();
    }

    public boolean isMenaced(Piece piece) {
        int[] coords = piece.getPosition().getCoordInt();
        for (int i = 0; i < casesMenacees.length; i++) {
            if (piece.getOwner().getOrderInGame() != i && casesMenacees[i][coords[1]][coords[0]]) {
                return true;
            }
        }
        return false;
    }

    public Piece getPieceAt(Case position) {
        int[] coord = position.getCoordInt();
        return plateau[coord[0]][coord[1]];
    }

    public Piece getPieceAt(String position) {
        return getPieceAt(new Case(position));
    }
}