package fr.pantheonsorbonne.miage.engine.net;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.engine.local.PartieLocal;
import fr.pantheonsorbonne.miage.enums.Command;
import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.exception.WrongCoupFormatException;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;
import fr.pantheonsorbonne.miage.game.playersAI.Player;
import fr.pantheonsorbonne.miage.game.playersAI.PlayerBot;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.utils.CoupTranslator;

import java.util.Arrays;
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

    public static void main(String[] args) throws WrongCoupFormatException, WrongCaseFormatException {

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
        int myPosition = getMyPosition(command.body(), Integer.parseInt(playerId));

        Echiquier plateau = getCorrespondingEchiquier(player, fillerPlayers, myPosition);

        while (true) {
            // get a command from someone else
            GameCommand commandLoop = playerFacade.receiveGameCommand(gameEchecs);
            receivedCommand = Command.valueOf(commandLoop.name());
            String[] commandes = commandLoop.body().split(":");
            int tour = Integer.parseInt(commandes[0]);
            String coup = commandes[1];
            handlePassivePlaying(coup, tour);
            if (tour == player.getOrderInGame()) {
                switch (receivedCommand) {
                    case Command.COUP:
                        handleNextCoup(gameEchecs, player, coup, tour);
                        break;
                    case Command.GAME_OVER:
                        handleGameOver(playerFacade, gameEchecs, commandLoop);
                        System.exit(0);
                    default:
                        break;
                }
            }
        }
    }

    private static void handleNextCoup(Game game, Player player, String coup, int tour) throws WrongCoupFormatException, WrongCaseFormatException {
        Coup nextCoup = player.getNextCoup();
        playerFacade.sendGameCommandToAll(game, new GameCommand(Command.COUP.name() , (tour+1) + ":"+ nextCoup.toString()));
        JouerCoup(player.getEchiquier(), nextCoup);
    }

    private static void handlePassivePlaying(String coup, int tour) throws WrongCoupFormatException, WrongCaseFormatException {
        Echiquier plateau = player.getEchiquier();
        Player playerPlaying = plateau.getPlayers()[tour%4];
        Coup previousCoup = CoupTranslator.stringToCoup(playerPlaying,coup);
        JouerCoup(plateau, previousCoup);
    }

    private static void JouerCoup(Echiquier plateau, Coup coup) {
        plateau.jouerCoup(coup);
        plateau.computeMenaces();
        PartieLocal.addPointsForCoup(coup);
    }

    private static void handleGameOver(PlayerFacade facade, Game currentGame, GameCommand gameOverCommand) {

    }

    private static int getMyPosition(String firstCommand, int myId) {
        String[] playersIdsStr =  firstCommand.split(":");
        int[] playersIds = new int[playersIdsStr.length];
        for (int i = 0; i < playersIds.length; i++) {
            playersIds[i] = Integer.parseInt(playersIdsStr[i]);
        }
        Arrays.sort(playersIds);
        return Arrays.binarySearch(playersIds, myId);
    }

    private static Echiquier getCorrespondingEchiquier(Player thisPlayer, Player[] fillerPlayers, int myPosition) {
        switch (myPosition){
            case 0 -> new Echiquier(new Player[] {thisPlayer, fillerPlayers[0], fillerPlayers[1], fillerPlayers[2]});
            case 1 -> new Echiquier(new Player[] {fillerPlayers[0], thisPlayer, fillerPlayers[1], fillerPlayers[2]});
            case 2 -> new Echiquier(new Player[] {fillerPlayers[0], fillerPlayers[1], thisPlayer, fillerPlayers[2]});
            case 3 -> new Echiquier(new Player[] {fillerPlayers[0], fillerPlayers[1], fillerPlayers[2], thisPlayer});
        }
        return null;
    }
}