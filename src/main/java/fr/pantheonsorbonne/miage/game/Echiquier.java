package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Color;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

public class Echiquier {
    private Piece[][] plateau;
    private Player[] players;

    private static final int TAILLE = 14;

    public Echiquier(Player joueur1, Player joueur2, Player joueur3, Player joueur4) {
        joueur1.setColor(Color.RED);
        joueur2.setColor(Color.GREEN);
        joueur3.setColor(Color.YELLOW);
        joueur4.setColor(Color.BLUE);
        Player[] players = new Player[]{joueur1, joueur2, joueur3, joueur4};
        this.plateau = new Piece[TAILLE][TAILLE];
        this.players = players;
        EchiquierInitializer.initialiser(this);
    }
    
    public Player[] getPlayers() {
        return this.players;
    }
    public Piece[][] getPlateau() {
        return this.plateau;
    }

    public void jouerCoup(Coup coup) {
        // Implémenter la logique pour jouer un coup
    }

    public void movePiece(Piece piece, Case position) {
        plateau[position.getColonne().ordinal()][position.getLigne()-1] = piece;
        piece.setPosition(position);
        //update la pos de la pièce prise si la case est dékja occupée
    }

    public void emptyCell(Case position) {
        plateau[position.getColonne().ordinal()][position.getLigne()-1] = null;
    }

    public Piece getPieceAt(Case position) {
        return this.plateau[position.getColonne().ordinal()][position.getLigne()-1];
    }
}