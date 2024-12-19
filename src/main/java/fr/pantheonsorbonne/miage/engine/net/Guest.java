package fr.pantheonsorbonne.miage.engine.net;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.enums.Command;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;
import fr.pantheonsorbonne.miage.game.playersAI.Player;
import fr.pantheonsorbonne.miage.game.playersAI.PlayerBot;
import fr.pantheonsorbonne.miage.PlayerFacade;

import java.util.Random;

public class Guest {
    private static final PlayerFacade playerFacade = Facade.getFacade();
    private static final String playerId = String.valueOf(new Random().nextInt());

    private static final Player player = new PlayerBot(playerId);

    private static final Player simulatedPlayer1 = new PlayerBot("simulation");
    private static final Player simulatedPlayer2 = new PlayerBot("simulation");
    private static final Player simulatedPlayer3 = new PlayerBot("simulation");
    private static final Player[] fillerPlayers = new Player[]{simulatedPlayer1, simulatedPlayer2, simulatedPlayer3};

    public static Player getPlayer() {
        return player;
    }

    public static void main(String[] args) {

        playerFacade.waitReady();
        playerFacade.createNewPlayer(playerId);
        Game gameEchecs = playerFacade.autoJoinGame("Echecs");
        System.out.println(playerId);

        GameCommand command = playerFacade.receiveGameCommand(gameEchecs);
        Command receivedCommand = Command.valueOf(command.name());
        if (!receivedCommand.equals(Command.YOU_ARE)) {
            throw new RuntimeException();
        }
        System.out.println(command.body() + "  thats me !");
        int myPosition = getMyPosition(command.body());

        boolean firstThingToDo = true;

        while (true) {
            // get a command from someone else
            GameCommand commandLoop = playerFacade.receiveGameCommand(gameEchecs);
            receivedCommand = Command.valueOf(commandLoop.name());
            switch (receivedCommand) {
                case Command.COUP:
                    // if we receive a new board, the game is not other and we should player one
                    // more round
                    handleNextCoup(playerFacade, gameEchecs, commandLoop);
                    break;
                case Command.GAME_OVER:
                    // if the game is other, stop playing and show the results
                    handleGameOver(playerFacade, gameEchecs, commandLoop);
                    System.exit(0);
                default:
                    break;
            }

        }
    }

    public static void handleNextCoup(PlayerFacade facade, Game currentGame, GameCommand commandLoop) {

    }

    private static void handleGameOver(PlayerFacade facade, Game currentGame, GameCommand gameOverCommand) {

    }

    private static int getMyPosition(String firstCommand) {
        return 0;
    }
}