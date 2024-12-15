package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Color;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.utils.EchiquierMenace;
import fr.pantheonsorbonne.miage.utils.PrintEchiquier;

public class Echiquier {
    private static final int TAILLE = 14;
    static final int NB_JOUEURS = 4;
    private Piece[][] plateau = new Piece[TAILLE][TAILLE];
    private Player[] players;
    private boolean[][][] casesMenacees = new boolean[NB_JOUEURS][TAILLE][TAILLE];

    public Echiquier(Player [] players) {
        if (players.length != NB_JOUEURS) {
            System.out.println("Le jeu se joue à " + NB_JOUEURS);
        }
        else {
            this.players = players;
            plateau = new Piece[TAILLE][TAILLE];
            for (int i = 0; i < NB_JOUEURS; i++) {
                players[i].setColor(Color.values()[i]);
                players[i].setEchiquier(this);
            }
        }
    }

    public void initBoard(){
        EchiquierInitializer.initialiser(this);
    }

    public void emptyPlateau(){
        this.plateau = new Piece[TAILLE][TAILLE];
    }
    
    public Player[] getPlayers() {return this.players;}
    public Piece[][] getPlateau() {return this.plateau;}
    public static int[] getInfosAboutThat() {return new int[] {TAILLE, NB_JOUEURS};}
    public boolean[][][] getCasesMenacees() {return this.casesMenacees;}

    public void printPlateau() {
        PrintEchiquier.printEchiquier(this);
    }
    public void printCasesMenacees(Player p) {
        PrintEchiquier.printMenaces(this, p);
    }

    public void computeMenaces() {
        this.casesMenacees = EchiquierMenace.computeMenace(this);
    }

    public void setPieceToPosition(Piece piece, Case position){
        if(piece.getPosition() != null) {
            int[] coordDepart = piece.getPosition().getCoordInt();
            plateau[coordDepart[0]][coordDepart[1]] = null;
        }
        if(position == null){
            piece.setPosition(null);
        }
        else {
            int[] coordDestination = position.getCoordInt();
            plateau[coordDestination[0]][coordDestination[1]] = piece;
            piece.setPosition(position);
        }
    }

    public Piece[][] rotateCopyToRightBy90(int nombreRotation){
        Piece[][] rotatedPlateau = plateau;
        for (int rotation=0; rotation<nombreRotation; rotation++){
            for (int i=0; i<TAILLE; i++){
                for (int j=0; j<TAILLE; j++){
                    if(i!=j){
                        Piece tempVar = rotatedPlateau[i][j];
                        rotatedPlateau[i][j] = rotatedPlateau[j][i];
                        rotatedPlateau[j][i] = tempVar;
                    }
                }
            }
            for (int i=0; i<TAILLE; i++){
                for (int j=0; j<TAILLE; j++){
                    if(i!=j){
                        Piece tempVar = rotatedPlateau[i][j];
                        plateau[i][j] = plateau[i][TAILLE - i - 1];
                        plateau[i][TAILLE - j - 1] = tempVar;
                    }
                }
            }
        }
        return rotatedPlateau;
    }

    public static void jouerCoup(Coup coup) {
        // Implémenter la logique pour jouer un coup
    }

    public void emptyCell(Case position) {
        plateau[position.getColonne().ordinal()][position.getLigne().ordinal()] = null;
    }

    public Piece getPieceAt(Case position) {
        int[] coord = position.getCoordInt();
        return plateau[coord[0]][coord[1]];
    }
    public Piece getPieceAt(String position) {
        try {
            return this.getPieceAt(new Case(position));
        }
        catch (Exception ignored) {}
        return null;
    }

}