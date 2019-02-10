package ondarsky.gmail.com.strategy.nodes.executables;

import bwapi.UnitType;

public class SpawningPoolLocation extends FindBuildLocation {

	@Override
	public UnitType getBuilding() {
		return UnitType.Zerg_Spawning_Pool;
	}

}
