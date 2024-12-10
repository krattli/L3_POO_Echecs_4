package fr.pantheonsorbonne.miage.game;

public class Coup {
    private Case depart;
    private Case arrivee;
    private Piece piece;
    private Piece pieceMangee;

    public Coup(Case arrivee, Piece piece) {
        this.depart = piece.getPosition();
        this.arrivee = arrivee;
        this.piece = piece;
        //pieceMangee = null;
    }

    public Coup(String coup){

    }

    public String toString(){
        String prise = this.pieceMangee!=null ? "x" : "-";
        return this.piece.getClass().getSimpleName().substring(0,1)+this.depart.toString()+prise+this.arrivee.toString();
    }
}
