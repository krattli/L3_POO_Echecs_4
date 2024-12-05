package fr.pantheonsorbonne.miage.enums;

public enum AffichagePieces {
    WHITE_KING("♔","RoiB"),
    WHITE_QUEEN("♕","DameB"),
    WHITE_ROOK("♖","TourB"),
    WHITE_BISHOP( "♗","FouB"),
    WHITE_KNIGHT("♘","CavalierB"),
    WHITE_PAWN( "♙","PionB"),

    BLACK_KING("♚","RoiN"),
    BLACK_QUEEN("♛","DameN"),
    BLACK_ROOK( "♜","TourN"),
    BLACK_BISHOP( "♝","FouN"),
    BLACK_KNIGHT( "♞","CavalierN"),
    BLACK_PAWN("♟","PionN");

    private final String symbol;
    private final String className;

    AffichagePieces(String symbol, String className) {
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

    public String getSymbol() {
        return symbol;
    }
}