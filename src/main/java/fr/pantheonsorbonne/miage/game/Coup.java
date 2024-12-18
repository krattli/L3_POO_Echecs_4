package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.game.typeCoup.SuperDeplacement;

public abstract class Coup {
    private Case depart;
    private Case arrivee;
    private Piece piece;

    public Coup(Piece piece, Case arrivee) {
        this.depart = piece.getPosition();
        this.arrivee = arrivee;
        this.piece = piece;
    }

    public Coup(Piece piece, String arrivee) {
        this(piece, new Case(arrivee));
    }

    public static Coup stringToCoup(String s) {
        Coup c;
        if(s.startsWith("S")) {
            c = handleSuperDeplacement(s);
        }
        else {
            Case Depart = new Case(s.substring(1, 3));
        }
        return null;
    }

    public Case getDepart() {return depart;}
    public Case getArrivee() {return arrivee;}
    public Piece getPiece() {return piece;}

    public abstract String toString();

    private static SuperDeplacement handleSuperDeplacement(String s) {
        SuperDeplacement superDeplacement = null;
        Case Depart = new Case(s.substring(1, 3));
        return superDeplacement;
    }
}
