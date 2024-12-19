package fr.pantheonsorbonne.miage.engine.net;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.enums.Command;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;
import fr.pantheonsorbonne.miage.game.playersAI.Player;
import fr.pantheonsorbonne.miage.game.playersAI.PlayerBot;
import fr.pantheonsorbonne.miage.PlayerFacade;

import java.util.Random;

public class Guest {
    private static final PlayerFacade playerFacade = Facade.getFacade();
    private static final String playerId = "Player-" + new Random().nextInt();

    private static final Player player = new PlayerBot(playerId);

    private static final Player simulatedPlayer1 = new PlayerBot("simulation");
    private static final Player simulatedPlayer2 = new PlayerBot("simulation");
    private static final Player simulatedPlayer3 = new PlayerBot("simulation");

    public static Player getPlayer() {
        return player;
    }

    public static void main(String[] args) {

        playerFacade.waitReady();
        playerFacade.createNewPlayer(playerId);
        Game gameEchecs = playerFacade.autoJoinGame("Echecs");


        GameCommand command = playerFacade.receiveGameCommand(gameEchecs);
        Command receivedCommand = Command.valueOf(command.name());
        if (!receivedCommand.equals(Command.YOU_ARE)) {
            throw new RuntimeException();
        }
        final char myMark = command.body().charAt(0);

        while (true) {
            // get a command from someone else
            GameCommand commandLoop = playerFacade.receiveGameCommand(gameEchecs);
            receivedCommand = Command.valueOf(commandLoop.name());
            switch (receivedCommand) {
                case Command.COUP:
                    // if we receive a new board, the game is not other and we should player one
                    // more round
                    handleNextCoup(playerFacade, gameEchecs, myMark, commandLoop);
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

    public static void handleNextCoup(PlayerFacade facade, Game currentGame, char myMark, GameCommand commandLoop) {

    }

    private static void handleGameOver(PlayerFacade facade, Game currentGame, GameCommand gameOverCommand) {

    }
}