package ondarsky.gmail.com.handler;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import bwapi.DefaultBWListener;
import bwapi.Game;
import bwapi.Mirror;
import bwapi.Player;
import bwapi.Unit;
import bwapi.UnitType;
import bwta.BWTA;
import ondarsky.gmail.com.orders.IPriortniPrikaz;
import ondarsky.gmail.com.orders.IVylucny;
import ondarsky.gmail.com.orders.Prikaz;
import ondarsky.gmail.com.orders.custom.DroneGatherOrder;
import ondarsky.gmail.com.orders.custom.ZergBuildDrone;
import ondarsky.gmail.com.orders.custom.ZergBuildOverlord;

public class KrapolaToasterBot extends DefaultBWListener {
    private Mirror mirror = new Mirror();
    private Game game;
    private Player self;

    private int nextReportTime = 0;

    private Map<UnitType, List<Prikaz>> prikazy = new HashMap<>();

    public void initialSetup(Game game, Player player) {
        initDrones(game);
        initLarvas(game, player);

    }

    public void run() {
        mirror.getModule().setEventListener(this);
        System.out.println("START Crapola Inc.");
        mirror.startGame();
    }

    @Override
    public void onUnitCreate(Unit unit) {
        System.out.println("Objevena nova jednotka " + unit.getType());
    }

    @Override
    public void onStart() {
        game = mirror.getGame();
        self = game.self();
        initialSetup(game, self);

        // Use BWTA to analyze map
        // This may take a few minutes if the map is processed first time!
        System.out.println("Analyzing map...");
        BWTA.readMap();
        BWTA.analyze();
        System.out.println("Map data ready");

        game.drawTextScreen(10, 10, "Playing as " + self.getName() + " - " + self.getRace());

        BWTA.getBaseLocations().stream()
                .forEach(i -> System.out.println(String.format("Base at %d:%d", i.getX(), i.getY())));
    }

    @Override
    public void onUnitComplete(Unit arg0) {
        // TODO Auto-generated method stub
        super.onUnitComplete(arg0);
    }

    @Override
    public void onEnd(boolean arg0) {
        // TODO Auto-generated method stub
        super.onEnd(arg0);
    }

    @Override
    public void onUnitDiscover(Unit arg0) {
        // TODO Auto-generated method stub
        super.onUnitDiscover(arg0);
    }

    @Override
    public void onFrame() {
        if (game.getFPS() < 30 && game.getFrameCount() % 2 == 0) {
            return;
        }

        // projdi vsechny moje chcipaky
        for (Unit unit : self.getUnits()) {
            prikazy.get(unit.getType()).stream()
                    .peek(p -> System.out.println(p.getClass().getSimpleName()))
                    .filter(order -> order.checkCondition(unit))
                    .peek(p -> System.out.println(p.getClass().getSimpleName()))
                    .findFirst().ifPresent(order -> {
                        Boolean result = order.executeFor(unit);
                        if (Boolean.TRUE.equals(result) && order.getOptions().contains(
                                Prikaz.EVlastnostPrikazu.Prioritni)) {
                            ((IPriortniPrikaz) order).vyhnij();
                        }
                    });
        }
        // Vzdycky jednou za cas je napraskej
        if (nextReportTime == 0 || nextReportTime > game.elapsedTime()) {
            nextReportTime = game.elapsedTime() * 30;
            StringBuilder report = new StringBuilder("My units:\n");
            self.getUnits().stream().collect(Collectors.groupingBy(Unit::getType)).forEach(
                    (k, v) -> report.append(self.allUnitCount(k)).append("x ").append(k.toString()).append("\n"));
            game.drawTextScreen(10, 25, report.toString());
        }
        // Prepocti priority
        for (List<Prikaz> allOfThem : prikazy.values()) {
            Collections.sort(allOfThem, (o1, o2) -> Double.compare(o1.getPrioritu(), o2.getPrioritu()));
            // Pokud byly nektere prikazy vylucne, tak je ted odemkni
            allOfThem.stream()
                    .filter(order -> order.getOptions().contains(Prikaz.EVlastnostPrikazu.Exklusivni))
                    .map(IVylucny.class::cast)
                    .forEach(IVylucny::odemkni);
        }
    }

    /**
     * Vosefuj vsechny larvy
     */
    private void initLarvas(Game game, Player player) {
        prikazy.putIfAbsent(
                UnitType.Zerg_Larva,
                Arrays.asList(
                        new ZergBuildDrone(game, player),
                        new ZergBuildOverlord(game, player)));
        System.out.println("LARVAs - pripraveny");
        prikazy.entrySet().forEach(System.out::println);
    }

    /**
     * Vosefuj Drony
     */
    private void initDrones(Game game) {
        prikazy.putIfAbsent(
                UnitType.Zerg_Drone,
                Arrays.asList(
                        new DroneGatherOrder(game)));
        System.out.println("DRONEs - pripraveny");
        prikazy.entrySet().forEach(System.out::println);
    }

    /**
     * Runs the bot
     */
    public static void main(String[] args) {
        new KrapolaToasterBot().run();
    }
}