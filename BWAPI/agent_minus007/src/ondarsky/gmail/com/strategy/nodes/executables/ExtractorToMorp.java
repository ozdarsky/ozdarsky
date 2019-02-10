package ondarsky.gmail.com.strategy.nodes.executables;

import bwapi.Game;
import bwapi.UnitType;

public class ExtractorToMorp extends MorpStructure {
	public static int ID;

	@Override
	public int getId() {
		return ID;
	}

	@Override
	protected UnitType getUnitType() {
		return UnitType.Zerg_Extractor;
	}

	@Override
	public void initialize(Game game, int id) {
		super.initialize(game, id);
		ID = id;
	}

}
