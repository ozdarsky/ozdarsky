package ondarsky.gmail.com.orders.custom;

import bwapi.Game;
import bwapi.Player;
import bwapi.Unit;
import bwapi.UnitType;
import ondarsky.gmail.com.orders.IExclusive;
import ondarsky.gmail.com.orders.IPrioritizedOrder;
import ondarsky.gmail.com.orders.Order;

public class ZergBuildDrone extends Order implements IPrioritizedOrder, IExclusive {

    private double priority = 50;
    private Player player;

    public ZergBuildDrone(Game game, Player player) {
        // if there's enough minerals, train a Drone
        super(
                unit -> unit.getType().producesLarva() && UnitType.Zerg_Drone.mineralPrice() <= player.minerals(),
                unit -> unit.train(UnitType.Zerg_Drone),
                3);
        this.player = player;
        this.addOption(EDirectiveOption.Prioritized)
                .addOption(EDirectiveOption.Exclusive);
    }

    @Override
    public void decay() {
        priority = priority / player.allUnitCount(UnitType.Zerg_Drone);
    }

    @Override
    public void bolster() {
        raisePriority(player.minerals() / 100);
    }

    @Override
    public void raisePriority(double amount) {
        priority += amount;

    }

    @Override
    public void decreasePriority(double amount) {
        priority -= amount;
    }

    @Override
    public double getPriority() {
        return this.priority;
    }

    @Override
    public synchronized Boolean executeExclusively(Unit unit) {
        synchronized (gate) {
            if (gate >= 0) {
                gate--;
                return executeFor(unit);
            } else {
                return false;
            }
        }
    }

    @Override
    public synchronized void unlock() {
        gate = originalGateSize;
    }
}
