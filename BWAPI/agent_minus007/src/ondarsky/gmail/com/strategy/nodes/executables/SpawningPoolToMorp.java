package ondarsky.gmail.com.strategy.nodes.executables;

import bwapi.Game;
import bwapi.UnitType;

public class SpawningPoolToMorp extends MorpStructure {

	public static int ID;

	@Override
	public int getId() {
		return ID;
	}

	@Override
	protected UnitType getUnitType() {
		return UnitType.Zerg_Spawning_Pool;
	}

	@Override
	public void initialize(Game game, int id) {
		ID = id;
	}
}
