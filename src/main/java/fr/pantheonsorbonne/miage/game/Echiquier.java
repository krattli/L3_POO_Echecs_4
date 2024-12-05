package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.Color;
import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.game.Pieces.pieces_simple.Pion;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;
import fr.pantheonsorbonne.miage.playerRelatedStuff.PlayerBot;

public class Echiquier {
    private static final int TAILLE = 14;
    private static Piece[][] plateau = new Piece[TAILLE][TAILLE];
    private static Player[] players;

    private Echiquier() {}

    public static void addPlayers(Player joueur1, Player joueur2, Player joueur3, Player joueur4) throws WrongCaseFormatException {
        joueur1.setColor(Color.RED);
        joueur2.setColor(Color.GREEN);
        joueur3.setColor(Color.YELLOW);
        joueur4.setColor(Color.BLUE);
        Echiquier.players = new Player[]{joueur1, joueur2, joueur3, joueur4};
    }

    public static void initAllPieces () throws WrongCaseFormatException {
        if (players.length == 4) {
            plateau = new Piece[TAILLE][TAILLE];
            EchiquierInitializer.initialiser();
        }
    }
    
    public static Player[] getPlayers() {
        return players;
    }
    public static Piece[][] getPlateau() {
        return plateau;
    }

    public static void setPieceToPosition(Piece piece, Case position){

        if(piece.getPosition() != null) {
            int[] coordDepart = piece.getPosition().getCoordInt();
            plateau[coordDepart[0]][coordDepart[1]] = null;
        }

        if(position == null){
            piece.setPosition(null);
        }
        else {
            int[] coordDestination = position.getCoordInt();
            plateau[coordDestination[0]][coordDestination[1]] = piece;
            piece.setPosition(position);
        }
    }

    //Peut etre que cette méthode est completement innutile, on verra
    public static Piece[][] rotateCopyToRightBy90(int nombreRotation){
        Piece[][] rotatedPlateau = plateau;
        for (int rotation=0; rotation<nombreRotation; rotation++){
            for (int i=0; i<TAILLE; i++){
                for (int j=0; j<TAILLE; j++){
                    if(i!=j){
                        Piece tempVar = rotatedPlateau[i][j];
                        rotatedPlateau[i][j] = rotatedPlateau[j][i];
                        rotatedPlateau[j][i] = tempVar;
                    }
                }
            }
            for (int i=0; i<TAILLE; i++){
                for (int j=0; j<TAILLE; j++){
                    if(i!=j){
                        Piece tempVar = rotatedPlateau[i][j];
                        plateau[i][j] = plateau[i][TAILLE - i - 1];
                        plateau[i][TAILLE - j - 1] = tempVar;
                    }
                }
            }
        }
        return rotatedPlateau;
    }

    public static void jouerCoup(Coup coup) {
        // Implémenter la logique pour jouer un coup
    }

    public static void emptyCell(Case position) {
        //verifier si les lignes sont bien les bonnes
        plateau[position.getColonne().ordinal()][position.getLigne().ordinal()] = null;
    }

    public static Piece getPieceAt(Case position) {
        //verifier si les lignes sont bien les bonnes
        int[] coord = position.getCoordInt();
        return plateau[coord[0]][coord[1]];
    }
}