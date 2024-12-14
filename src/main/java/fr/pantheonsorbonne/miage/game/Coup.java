package fr.pantheonsorbonne.miage.game;

public abstract class Coup {
    private Case depart;
    private Case arrivee;
    private Piece piece;

    public Coup(Piece piece, Case arrivee) {
        this.depart = piece.getPosition();
        this.arrivee = arrivee;
        this.piece = piece;
    }

    public Coup(String coup){
    }

    public Case getDepart() {return depart;}
    public Case getArrivee() {return arrivee;}
    public Piece getPiece() {return piece;}

    public abstract String toString();
}
