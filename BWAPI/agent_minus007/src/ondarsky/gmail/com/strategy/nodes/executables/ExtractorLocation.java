package ondarsky.gmail.com.strategy.nodes.executables;

import java.util.Comparator;
import java.util.Optional;

import bwapi.Game;
import bwapi.TilePosition;
import bwapi.Unit;
import bwapi.UnitType;
import ondarsky.gmail.com.utils.StepResults;

public class ExtractorLocation extends FindBuildLocation {

	public static int ID;
	private Game game;

	TilePosition nextBuildLocation = null;

	@Override
	public UnitType getBuilding() {
		return UnitType.Zerg_Extractor;
	}

	@Override
	public int getId() {
		return ID;
	}

	@Override
	public boolean isApplicable(Unit unit, Optional<TilePosition> position) {
		nextBuildLocation = game.neutral().getUnits().stream()
				.filter(u -> u.getType().equals(UnitType.Resource_Vespene_Geyser))
				.filter(Unit::isVisible)
				.sorted(Comparator.comparingInt(unit::getDistance))
				.findFirst()
				.map(Unit::getTilePosition)
				.orElse(null);
		return nextBuildLocation != null;
	}

	@Override
	public StepResults execute(Unit unit, Optional<Unit> target, Optional<TilePosition> position) {		// return results
		return StepResults.of(
				target,
				Optional.ofNullable(nextBuildLocation));
	}

	@Override
	public void initialize(Game game, int id) {
		ID = id;
		this.game = game;
	}

}
