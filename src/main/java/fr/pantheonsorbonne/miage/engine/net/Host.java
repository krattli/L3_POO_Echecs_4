package fr.pantheonsorbonne.miage.engine.net;

import fr.pantheonsorbonne.miage.HostFacade;
import fr.pantheonsorbonne.miage.enums.Command;
import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.exception.WrongCaseFormatException;
import fr.pantheonsorbonne.miage.exception.WrongCoupFormatException;
import fr.pantheonsorbonne.miage.game.Coup;
import fr.pantheonsorbonne.miage.game.Echiquier;
import fr.pantheonsorbonne.miage.game.playersAI.Player;
import fr.pantheonsorbonne.miage.game.playersAI.PlayerBot;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

import java.util.Arrays;

public class Host {

    private static final int PLAYER_COUNT = 4;

    private static final Player simulatedPlayer1 = new PlayerBot("uselessBot");
    private static final Player simulatedPlayer2 = new PlayerBot("uselessBot");
    private static final Player simulatedPlayer3 = new PlayerBot("uselessBot");
    private static final Player simulatedPlayer4 = new PlayerBot("uselessBot");

    private static final Player[] players = new Player[]{simulatedPlayer1, simulatedPlayer2, simulatedPlayer3, simulatedPlayer4};

    private static final PlayerFacade playerFacade = Facade.getFacade();
    private static final HostFacade hostFacade = Facade.getFacade();
    private static final Game game = hostFacade.createNewGame("Echecs");

    public static void main(String[] args) throws WrongCoupFormatException, WrongCaseFormatException {

        hostFacade.waitReady();

        hostFacade.createNewPlayer("Host");

        System.out.println("facade host créée, game créée");

        hostFacade.waitForExtraPlayerCount(PLAYER_COUNT);

        playTheGame();
    }

    private static void playTheGame() throws WrongCoupFormatException, WrongCaseFormatException {

        Echiquier plateau = new Echiquier(new Player[]{simulatedPlayer1, simulatedPlayer2, simulatedPlayer3, simulatedPlayer4});

        playerFacade.sendGameCommandToAll(game, new GameCommand(Command.YOU_ARE.name(), getFirstCommand(game)));

        int tour = 0;
        Coup coup =null;

        while (!handleFinPartie(plateau)) {

            GameCommand command = playerFacade.receiveGameCommand(game);

            String[] commands = command.toString().split(":");
            String coupStr = commands[1];
            coup = Coup.stringToCoup(players[tour % 4], coupStr);

            plateau.jouerCoup(coup);
            plateau.computeMenaces();

            playerFacade.sendGameCommandToAll(game, new GameCommand(Command.COUP.name(), tour + ":" + coup));

            tour++;
        }
    }

    private static boolean handleFinPartie(Echiquier plateau) {
        long alivePlayersCount = Arrays.stream(plateau.getPlayers()).filter(Player::isAlive).count();
        int totalPiecesOnBoard = Arrays.stream(plateau.getPlayers()).mapToInt(p -> p.getAllPieces().size()).sum();
        return alivePlayersCount <= 1 || alivePlayersCount == totalPiecesOnBoard;
    }

    public static String getFirstCommand(Game game) {
        return String.join(":",game.getPlayers());
    }

}
