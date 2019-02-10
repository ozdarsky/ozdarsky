package ondarsky.gmail.com.orders.custom;

import bwapi.Game;
import bwapi.Player;
import bwapi.Unit;
import bwapi.UnitType;
import ondarsky.gmail.com.orders.IPriortniPrikaz;
import ondarsky.gmail.com.orders.IVylucny;
import ondarsky.gmail.com.orders.Prikaz;

public class ZergBuildDrone extends Prikaz implements IPriortniPrikaz, IVylucny {

    private double priority = 50;
    private Player player;

    public ZergBuildDrone(Game game, Player player) {
        // pokud je dost mineralu postav Drona
        super(
                unit -> unit.getType().producesLarva() && UnitType.Zerg_Drone.mineralPrice() <= player.minerals(),
                unit -> unit.train(UnitType.Zerg_Drone),
                3);
        this.player = player;
        this.addOption(EVlastnostPrikazu.Prioritni)
                .addOption(EVlastnostPrikazu.Exklusivni);
    }

    @Override
    public void vyhnij() {
        priority = priority / player.allUnitCount(UnitType.Zerg_Drone);
    }

    @Override
    public void propleskni() {
        zvysPrioritu(player.minerals() / 100);
    }

    @Override
    public void zvysPrioritu(double amount) {
        priority += amount;

    }

    @Override
    public void snizPrioritu(double amount) {
        priority -= amount;
    }

    @Override
    public double getPrioritu() {
        return this.priority;
    }

    @Override
    public synchronized Boolean provedVylucne(Unit unit) {
        synchronized (branka) {
            if (branka >= 0) {
                branka--;
                return executeFor(unit);
            } else {
                return false;
            }
        }
    }

    @Override
    public synchronized void odemkni() {
        branka = originalniVelikostBranky;
    }
}
