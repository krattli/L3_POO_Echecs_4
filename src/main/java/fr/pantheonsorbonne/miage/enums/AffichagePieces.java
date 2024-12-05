package fr.pantheonsorbonne.miage.enums;

public enum AffichagePieces {
    WHITE_KING(1, "♔","RoiB"),
    WHITE_QUEEN(2, "♕","DameB"),
    WHITE_ROOK(3, "♖","TourB"),
    WHITE_BISHOP(4, "♗","FouB"),
    WHITE_KNIGHT(5, "♘","CavalierB"),
    WHITE_PAWN(6, "♙","PionB"),

    BLACK_KING(11, "♚","RoiN"),
    BLACK_QUEEN(12, "♛","DameN"),
    BLACK_ROOK(13, "♜","TourN"),
    BLACK_BISHOP(14, "♝","FouN"),
    BLACK_KNIGHT(15, "♞","CavalierN"),
    BLACK_PAWN(16, "♟","PionN");

    private final int id;
    private final String symbol;
    private final String className;

    AffichagePieces(int id, String symbol, String className) {
        this.id = id;
        this.symbol = symbol;
        this.className = className;
    }

    public static AffichagePieces getByAlias(String alias) {
        for (AffichagePieces piece : values()) {
            if (piece.getClassName().equals(alias)) {
                return piece;
            }
        }
        throw new IllegalArgumentException("Aucune pièce trouvée pour l'alias : " + alias);
    }

    public String getClassName() {
        return className;
    }

    public int getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getColor() {
        return id / 10; // 0 pour blanc, 1 pour noir
    }

    public int getPieceValue() {
        return id % 10; // 1=roi, 2=reine, 3=tour, etc.
    }

    @Override
    public String toString() {
        return symbol;
    }

    public static void main(String[] args){
        System.out.println(AffichagePieces.WHITE_KING.getId());
    }
}