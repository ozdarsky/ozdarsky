package ondarsky.gmail.com.strategy.nodes.executables;

import bwapi.Game;
import bwapi.UnitType;

public class DroneToMorp extends MorpUnit {

	public static int ID;

	@Override
	protected UnitType getUnitType() {
		return UnitType.Zerg_Drone;
	}

	@Override
	public void initialize(Game game, int id) {
		super.initialize(game, id);
		ID = id;
	}

	@Override
	public int getId() {
		return ID;
	}

}
