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
import ondarsky.gmail.com.orders.Order;
import ondarsky.gmail.com.orders.PrioritizedOrder;
import ondarsky.gmail.com.orders.custom.DroneGatherOrder;

public class EddieBot extends DefaultBWListener {
    private Mirror mirror = new Mirror();
    private Game game;
    private Player self;

    private int nextReportTime = 0;

    private Map<UnitType, List<Order<Unit>>> orders;

    public void initialSetup(Game game) {
        orders.computeIfAbsent(UnitType.Zerg_Drone, k -> new LinkedList<>()).add(new DroneGatherOrder(game));
        orders.computeIfAbsent(UnitType.Zerg_Hatchery, k -> new LinkedList<>()).add(new DroneGatherOrder(game));
        orders.computeIfAbsent(UnitType.Zerg_Lair, k -> new LinkedList<>()).add(new DroneGatherOrder(game));
        orders.computeIfAbsent(UnitType.Zerg_Hive, k -> new LinkedList<>()).add(new DroneGatherOrder(game));
    }

    public void run() {
        mirror.getModule().setEventListener(new EddieBot());
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
        initialSetup(game);

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
        self.getUnits().parallelStream().collect(Collectors.groupingBy(Unit::getType)).forEach((unitType, unitList) -> {
            // find first applicable order and execute it
            for (Unit unit : unitList) {
                orders.get(unitType).stream().map(Order.class::cast).filter(order -> order.checkCondition(unit))
                        .findFirst().ifPresent(order -> {
                            @SuppressWarnings("unchecked")
                            Boolean result = (Boolean) order.executeFor(unit);
                            if (Boolean.TRUE.equals(result) && order instanceof PrioritizedOrder) {
                                ((PrioritizedOrder) order).decay();
                            }
                        });
            }
        });

        // for all my units
        if (nextReportTime == 0 || nextReportTime > game.elapsedTime()) {
            nextReportTime = game.elapsedTime() * 30;
            StringBuilder report = new StringBuilder("My units:\n");
            self.getUnits().stream().collect(Collectors.groupingBy(Unit::getType)).forEach(
                    (k, v) -> report.append(self.allUnitCount(k)).append("x ").append(k.toString()).append("\n"));
            game.drawTextScreen(10, 25, report.toString());

        }

        // reorder unit's orders based on their execution
        for (List<Order<Unit>> allOfThem : orders.values()) {
            Collections.sort(allOfThem, (o1, o2) -> Double.compare(o1.getPriority(), o2.getPriority()));
        }
    }

    /**
     * Runs the bot
     */
    public static void main(String[] args) {
        new EddieBot().run();
    }
}