package fr.pantheonsorbonne.miage.game;

public class Coup {
    private Case depart;
    private Case arrivee;
    private Piece piece;
    private Piece pieceMangee;
    private boolean isFusion;

    public Coup(Piece piece, Case arrivee) {
        this.depart = piece.getPosition();
        this.arrivee = arrivee;
        this.piece = piece;
    }

    public Coup(Piece piece, Case arrivee, Piece pieceMangee) {
        Coup coup = new Coup(piece, arrivee);
        this.pieceMangee = pieceMangee;
    }

    public Coup(String coup){
    }

    public String toString(){
        if (this == null) return "|--|";
        String prise = this.pieceMangee!=null ? "x" : "-";
        return this.piece.getClass().getSimpleName().substring(0,1)+this.depart.toString()+prise+this.arrivee.toString();
    }

    public Piece getPiecePrise() {
        return this.pieceMangee;
    }
}
