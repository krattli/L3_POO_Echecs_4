package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.AffichagePieces;
import fr.pantheonsorbonne.miage.enums.Color;
import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

public class Echiquier {
    private static final int TAILLE = 14;
    private static final int NB_JOUEURS = 4;
    private Piece[][] plateau = new Piece[TAILLE][TAILLE];
    private Player[] players;

    public Echiquier(Player [] players) {
        if (players.length != NB_JOUEURS) {
            System.out.println("Le jeu se joue à " + NB_JOUEURS);
        }
        else {
            this.players = new Player[NB_JOUEURS];
            plateau = new Piece[TAILLE][TAILLE];
            this.addPlayers(players);
        }
    }

    //On verra si ça reste à la fin, peut être metre dans la classe partie jsp
    private void addPlayers(Player[] joueurs){
        for ( int i = 0; i < NB_JOUEURS; i++){
            joueurs[i].setColor(Color.values()[i]);
            joueurs[i].setEchiquier(this);
            this.players[i] = joueurs[i];
        }
    }

    public void initAllPieces (){
        if (this.players.length == 4) {
            //Il faut que 4 soit une constante
            resetPlateau();
            EchiquierInitializer.initialiser(this);
        }
    }

    //Maintenant que c'est plus statique, je suis perplexe sur l'utilité de cette méthode... afuera !
    public void resetPlateau(){
        this.plateau = new Piece[TAILLE][TAILLE];
    }
    
    public Player[] getPlayers() {return this.players;}
    public Piece[][] getPlateau() {return this.plateau;}

    public void printPlateau() {
        for (Piece[] pieces : plateau) {
            for (Piece piece : pieces) {
                //if (plateau[i][j] != null) {System.out.print(plateau[i][j].getOwner().getColor().name());}
                printPiece(piece);
            }
            System.out.println("|");
        }
    }

    public static void printPiece(Piece piece) {
        if (piece != null) {
            String CouleurAffichage;
            if (piece.getOwner().getColor().ordinal() % 2 == 0){
                CouleurAffichage = "B";
            }
            else {
                CouleurAffichage = "N";
            }
            String nomComplet = piece.getClass().toString();
            String nomClasse = nomComplet.substring(nomComplet.lastIndexOf('.') + 1) + CouleurAffichage;
            System.out.print("| "+AffichagePieces.getByAlias(nomClasse).getSymbol()+" ");
        }
        else {
            System.out.print("|___");
        }
    }

    public void setPieceToPosition(Piece piece, Case position){
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

    public Piece[][] rotateCopyToRightBy90(int nombreRotation){
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

    public void emptyCell(Case position) {
        //verifier si les lignes sont bien les bonnes
        plateau[position.getColonne().ordinal()][position.getLigne().ordinal()] = null;
    }

    public Piece getPieceAt(Case position) {
        //verifier si les lignes sont bien les bonnes
        int[] coord = position.getCoordInt();
        return plateau[coord[0]][coord[1]];
    }

}