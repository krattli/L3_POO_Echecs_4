package fr.pantheonsorbonne.miage.engine.net;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;
import fr.pantheonsorbonne.miage.game.playersAI.Player;
import fr.pantheonsorbonne.miage.game.playersAI.PlayerBot;
import fr.pantheonsorbonne.miage.PlayerFacade;

import java.util.Random;

public class Guest {
    private static final PlayerFacade playerFacade = Facade.getFacade();
    private static final String playerId = "Player-" + new Random().nextInt();
    private static Game echecs;

    private static final Player player = new PlayerBot(playerId);

    public static Player getPlayer() {
        return player;
    }

    public static void main(String[] args) {

        playerFacade.waitReady();
        playerFacade.createNewPlayer(playerId);
        echecs = playerFacade.autoJoinGame("Echecs");

        GameLoop : while (true) {
            GameCommand command = playerFacade.receiveGameCommand(echecs);


        }
    }
}

