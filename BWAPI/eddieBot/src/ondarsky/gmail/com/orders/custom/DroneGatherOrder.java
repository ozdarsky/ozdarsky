package ondarsky.gmail.com.orders.custom;

import bwapi.Game;
import ondarsky.gmail.com.orders.Order;

public class DroneGatherOrder extends Order {

    public DroneGatherOrder(Game game) {
        super(
                drone -> drone.isIdle(),
                drone -> {
                    game.neutral().getUnits().stream() // cannot be done parallel, because we need stable ordering of
                                                       // results
                            .filter(environmentalUnit -> environmentalUnit.getType().isMineralField())
                            .sorted((a, b) -> Integer.compare(drone.getDistance(a), drone.getDistance(b))).findFirst()
                            .ifPresent(field -> drone.gather(field));
                    return true;
                });
    }
}
