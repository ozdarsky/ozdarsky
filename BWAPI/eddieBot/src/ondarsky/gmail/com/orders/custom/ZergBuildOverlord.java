package ondarsky.gmail.com.orders.custom;

import bwapi.Game;
import bwapi.Player;
import bwapi.Unit;
import bwapi.UnitType;
import ondarsky.gmail.com.orders.IPriortniPrikaz;
import ondarsky.gmail.com.orders.IVylucny;
import ondarsky.gmail.com.orders.Prikaz;

public class ZergBuildOverlord extends Prikaz implements IPriortniPrikaz, IVylucny {

    private double priority = 20;
    private Player player;

    public ZergBuildOverlord(Game game, Player player) {
        // pokud mam dost mineralu, postavim Overlorda
        super(
                unit -> unit.getType().producesLarva() && UnitType.Zerg_Overlord.mineralPrice() <= player.minerals(),
                unit -> unit.train(UnitType.Zerg_Overlord),
                2);
        this.player = player;
        this.addOption(EVlastnostPrikazu.Prioritni)
                .addOption(EVlastnostPrikazu.Exklusivni);
    }

    @Override
    public void vyhnij() {
        priority = (player.supplyTotal() - player.supplyUsed()) / 2;
    }

    @Override
    public void propleskni() {
        zvysPrioritu((player.supplyTotal() - player.supplyUsed()) / 2);
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
