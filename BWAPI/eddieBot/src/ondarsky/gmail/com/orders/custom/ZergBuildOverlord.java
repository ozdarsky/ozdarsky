package ondarsky.gmail.com.orders.custom;

import bwapi.Game;
import bwapi.Player;
import bwapi.UnitType;
import ondarsky.gmail.com.orders.IPrioritizedOrder;
import ondarsky.gmail.com.orders.Order;

public class ZergBuildOverlord extends Order implements IPrioritizedOrder {

    private double priority = 20;
    private Player player;

    public ZergBuildOverlord(Game game, Player player) {
        // if there's enough minerals, train an Overlord
        super(
                unit -> unit.getType().producesLarva() && UnitType.Zerg_Overlord.mineralPrice() <= player.minerals(),
                unit -> unit.train(UnitType.Zerg_Overlord));
        this.player = player;
    }

    @Override
    public void decay() {
        priority = (player.supplyTotal() - player.supplyUsed()) / 2;
    }

    @Override
    public void bolster() {
        raisePriority((player.supplyTotal() - player.supplyUsed()) / 2);
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

}
