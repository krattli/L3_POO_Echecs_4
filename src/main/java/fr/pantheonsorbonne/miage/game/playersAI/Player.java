package fr.pantheonsorbonne.miage.game.playersAI;

import java.util.ArrayList;
import java.util.Random;

import fr.pantheonsorbonne.miage.enums.Color;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.game.pieces.simple.Roi;
import fr.pantheonsorbonne.miage.game.typeCoup.Roque;

public abstract class Player{
    private final String nom;
    private int points;
    private Color color;
    private Boolean isAlive;
    private Echiquier echiquier;
    private final Random random;

    public Player (String nom) {
        this.nom = nom;
        this.points = 0;
        this.isAlive = true;
        this.random = new Random();
    }

    public String getNom(){
        return this.nom;
    }
    public int getPoints(){
        return this.points;
    }
    public void resetPoints(){
        this.points = 0;
    }
    public void setColor (Color color){
        this.color = color;
    }
    public Color getColor(){
        return this.color;
    }
    public void addPoints(int points){
        this.points+=points;
    }
    public Echiquier getEchiquier() {
        return echiquier;
    }
    public void setEchiquier(Echiquier echiquier) {
        this.echiquier = echiquier;
    }
    public Boolean isAlive(){
        return this.isAlive;
    }
    public void revive(){
        this.isAlive = true;
    }
    public int getOrderInGame() {return this.getColor().ordinal();}

    public Coup getNextCoup() {
        Roi myKing = this.getHisKing();
        if (myKing == null) {
            return null;
        }

        if (this.isChecked()) {
            return this.getNextCoupKingChecked();
        }

        return this.getNextCoupNormal();
    }

    public abstract Coup getNextCoupKingChecked();

    public abstract Coup getNextCoupNormal();

    protected Coup getRandomMove(ArrayList<Coup> moves) {
        if (moves == null || moves.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(moves.size());
        return moves.get(randomIndex);
    }

    public ArrayList<Coup> getAllPossibleMoves(){
        ArrayList<Coup> coups = new ArrayList<>();
        ArrayList<Piece> pieces = this.getAllPieces();
        for (Piece piece : pieces) {
            coups.addAll(piece.getAllPossibleMoves());
        }
        return coups;
    }

    public Roque[] getRoques() {
        return Roque.getRoques(this);
    }

    public ArrayList<Piece> getAllPieces(){
        ArrayList<Piece> pieces = new ArrayList<>();
        Piece[][] plateau = echiquier.getPlateau();
        for (Piece[] row : plateau ) {
            for (Piece piece : row) {
                if (piece != null && piece.getOwner() == this) {
                    pieces.add(piece);
                }

            }
        }
        return pieces;
    }

    public boolean isChecked() {
        Roi r = this.getHisKing();
        int[] coords = r.getPosition().getCoordInt();
        boolean[][][] menaces = this.getEchiquier().getCasesMenacees();
        for (int i = 0 ; i < menaces.length ; i++) {
            if (this.getOrderInGame() != i){
                if (menaces[i][coords[1]][coords[0]]) {
                    return true;
                }
            }
        }
        return false;
    }

    public void killPlayer() {
        this.isAlive = false;
        ArrayList<Piece> pieces = this.getAllPieces();
        for (Piece piece : pieces) {
            piece.kill();
        }
    }

    public Roi getHisKing() {
        Piece[][] plateau = echiquier.getPlateau();
        for (Piece[] row : plateau ) {
            for (Piece piece : row) {
                if (piece != null && piece.getClass() == Roi.class && piece.getOwner() == this) {
                    return (Roi) piece;
                }

            }
        }
        return null;
    }
}