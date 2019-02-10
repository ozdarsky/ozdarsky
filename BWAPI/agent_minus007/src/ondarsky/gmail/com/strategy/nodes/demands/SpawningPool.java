package ondarsky.gmail.com.strategy.nodes.demands;

import bwapi.Game;
import bwapi.Unit;
import bwapi.UnitType;
import ondarsky.gmail.com.strategy.intf.Demand;

public class SpawningPool extends Demand {

	public static int ID;

	@Override
	public int getId() {
		return ID;
	}

	@Override
	public int getTraversalCost() {
		return 0;
	}

	@Override
	public void initialize(Game game, int id) {
		setPriority(10);
		ID = id;
	}

	@Override
	public boolean isApplicableForUnit(Unit unit) {
		return unit.getType().equals(UnitType.Zerg_Drone);
	}

}
