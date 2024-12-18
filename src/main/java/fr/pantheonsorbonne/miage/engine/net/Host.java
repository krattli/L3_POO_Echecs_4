package fr.pantheonsorbonne.miage.engine.net;

import fr.pantheonsorbonne.miage.HostFacade;
import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.HostFacade;
import fr.pantheonsorbonne.miage.model.Game;

import java.util.Set;

public class Host {
    private static final int PLAYER_COUNT = 4;
    private final HostFacade hostFacade;
    private final Game echecs;

    public Host(HostFacade hostFacade, Set<String> players,
                fr.pantheonsorbonne.miage.model.Game echecs) {
        this.hostFacade = hostFacade;
        this.echecs = echecs;

    }

    public static void main(String[] args) {

        HostFacade hostFacade = Facade.getFacade();
        hostFacade.waitReady();

        hostFacade.createNewPlayer("Host");

        fr.pantheonsorbonne.miage.model.Game echecs = hostFacade.createNewGame("Echecs");

        hostFacade.waitForExtraPlayerCount(PLAYER_COUNT);

        Host host = new Host(hostFacade, echecs.getPlayers(), echecs);

    }

}
