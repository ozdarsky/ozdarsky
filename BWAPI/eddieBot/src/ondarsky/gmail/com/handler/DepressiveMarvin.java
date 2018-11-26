package ondarsky.gmail.com.handler;

import java.util.Collections;
import java.util.LinkedList;
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
import ondarsky.gmail.com.orders.IExclusive;
import ondarsky.gmail.com.orders.IPrioritizedOrder;
import ondarsky.gmail.com.orders.Order;
import ondarsky.gmail.com.orders.custom.DroneGatherOrder;
import ondarsky.gmail.com.orders.custom.ZergBuildDrone;
import ondarsky.gmail.com.orders.custom.ZergBuildOverlord;

public class DepressiveMarvin extends DefaultBWListener {
    private Mirror mirror = new Mirror();
    private Game game;
    private Player self;

    private int nextReportTime = 0;

    private Map<UnitType, List<Order>> orders;

    public void initialSetup(Game game, Player player) {
        initDrones(game);
        initLarvas(game, player);

    }

    public void run() {
        mirror.getModule().setEventListener(new DepressiveMarvin());
        mirror.startGame();
    }

    @Override
    public void onUnitCreate(Unit unit) {
        System.out.println("New unit discovered " + unit.getType());
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
    public void onFrame() {
        if (game.getFPS() < 30 && game.getFrameCount() % 2 == 0) {
            return;
        }

        // iterate through my units
        self.getUnits().parallelStream()
                .collect(Collectors.groupingBy(Unit::getType))
                .forEach((unitType, unitList) -> {
                    // find first applicable order and execute it
                    for (Unit unit : unitList) {
                        orders.get(unitType).stream()
                                .filter(order -> order.checkCondition(unit))
                                .findFirst().ifPresent(order -> {
                                    Boolean result = order.executeFor(unit);
                                    if (Boolean.TRUE.equals(result) && order.getOptions().contains(
                                            Order.EDirectiveOption.Prioritized)) {
                                        ((IPrioritizedOrder) order).decay();
                                    }
                                });
                    }
                });

        // for all my units report them periodically
        if (nextReportTime == 0 || nextReportTime > game.elapsedTime()) {
            nextReportTime = game.elapsedTime() * 30;
            StringBuilder report = new StringBuilder("My units:\n");
            self.getUnits().stream().collect(Collectors.groupingBy(Unit::getType)).forEach(
                    (k, v) -> report.append(self.allUnitCount(k)).append("x ").append(k.toString()).append("\n"));
            game.drawTextScreen(10, 25, report.toString());

        }

        // reorder unit's orders based on their execution
        for (List<Order> allOfThem : orders.values()) {
            // order them by new priorities
            Collections.sort(allOfThem, (o1, o2) -> Double.compare(o1.getPriority(), o2.getPriority()));
            // also unlock any exclusive orders to be read for next execution
            allOfThem.stream()
                    .filter(order -> order.getOptions().contains(Order.EDirectiveOption.Exclusive))
                    .map(IExclusive.class::cast)
                    .forEach(IExclusive::unlock);
        }
    }

    /**
     * Do all stuff tied to Larvas
     */
    private void initLarvas(Game game, Player player) {
        List<Order> larvaOrders = orders.computeIfAbsent(UnitType.Zerg_Larva, k -> new LinkedList<>());
        Collections.addAll(larvaOrders,
                new ZergBuildDrone(game, player),
                new ZergBuildOverlord(game, player));
    }

    /**
     * Do all the stuff your Drones want you to do
     * 
     * @param game
     */
    private void initDrones(Game game) {
        List<Order> droneOrders = orders.computeIfAbsent(UnitType.Zerg_Drone, k -> new LinkedList<>());
        Collections.addAll(droneOrders,
                new DroneGatherOrder(game));
    }

    /**
     * Runs the bot
     */
    public static void main(String[] args) {
        new DepressiveMarvin().run();
    }
}