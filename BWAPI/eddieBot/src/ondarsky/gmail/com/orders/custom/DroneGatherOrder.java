package ondarsky.gmail.com.orders.custom;

import bwapi.Game;
import ondarsky.gmail.com.orders.Prikaz;

public class DroneGatherOrder extends Prikaz {

    public DroneGatherOrder(Game game) {
        super(
                drone -> drone.isIdle(),
                drone -> {
                    game.neutral().getUnits().stream()
                            .filter(environmentalUnit -> environmentalUnit.getType().isMineralField())
                            .sorted((a, b) -> Integer.compare(drone.getDistance(a), drone.getDistance(b))).findFirst()
                            .ifPresent(field -> drone.gather(field));
                    return true;
                });
    }
}
