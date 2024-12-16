package fr.pantheonsorbonne.miage.enums;

public enum AffichagePieces {

    KING("♚","Roi"),
    QUEEN("♛","Dame"),
    ROOK( "♜","Tour"),
    BISHOP( "♝","Fou"),
    KNIGHT( "♞","Cavalier"),
    PAWN("♟","Pion");

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