package ondarsky.gmail.com.strategy.nodes.demands;

import java.util.Objects;

import bwapi.Game;
import bwapi.Unit;
import bwapi.UnitType;
import ondarsky.gmail.com.strategy.intf.Demand;

public class Gas extends Demand {

	public static int ID;
	private Game game;

    @Override
    public void initialize(Game game, int id) {
        ID = id;
        this.game = game;
    }

	@Override
	public int getTraversalCost() {
		return (int) game.self().getUnits().stream()
				.filter(unit -> unit.getType().equals(UnitType.Zerg_Drone))
				.map(Unit::getOrderTarget)
				.filter(Objects::nonNull)
				.map(Unit::getType)
				.filter(UnitType.Resource_Vespene_Geyser::equals)
				.count();
	}

	@Override
	public int getId() {
		return ID;
	}

	@Override
	public boolean isApplicableForUnit(Unit unit) {
		return unit.getType().isWorker() && game.self().allUnitCount(UnitType.Zerg_Extractor) > 0;
	}
}
