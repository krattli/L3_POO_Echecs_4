package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.playerRelatedStuff.Player;

public class PartieEchecs {
    Player[] players;
    Echiquier plateau;

    public PartieEchecs(Player[] joueurs) {
        this.players = joueurs;
        this.plateau = new Echiquier(players);
    }

}
