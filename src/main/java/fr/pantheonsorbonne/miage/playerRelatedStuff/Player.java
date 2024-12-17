package fr.pantheonsorbonne.miage.playerRelatedStuff;

import java.util.ArrayList;

import fr.pantheonsorbonne.miage.enums.Color;
import fr.pantheonsorbonne.miage.game.Case;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.Piece;
import fr.pantheonsorbonne.miage.game.pieces.simple.Roi;

public abstract class Player{
    private final String nom;
    private int points;
    private Color color;
    private Boolean isAlive;
    private Echiquier echiquier;

    public Player (String nom) {
        this.nom = nom;
        this.points = 0;
        this.isAlive = true;
    }

    public String getNom(){
        return this.nom;
    }
    public int getPoints(){
        return this.points;
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
    public void kill(){
        this.isAlive = false;
    }

    public abstract Coup getNextCoup();

    public ArrayList<Coup> getAllPossibleMoves(){
        ArrayList<Coup> coups = new ArrayList<>();
        ArrayList<Piece> pieces = this.getAllPieces();
        for (Piece piece : pieces) {
            coups.addAll(piece.getAllPossibleMoves());
        }
        return coups;
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
            if (this.getColor().ordinal() != i){
                if (menaces[i][coords[1]][coords[0]]) {
                    return true;
                }
            }
        }
        return false;
    }

    public void suicide() {
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