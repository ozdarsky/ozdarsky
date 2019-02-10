package ondarsky.gmail.com.strategy.nodes.executables;

import java.util.Optional;

import bwapi.Game;
import bwapi.TilePosition;
import bwapi.Unit;
import ondarsky.gmail.com.strategy.intf.ExecutableOrder;
import ondarsky.gmail.com.utils.StepResults;

public class FindMineralField extends ExecutableOrder {

	public static int ID;

	private Game game;

	@Override
	public void initialize(Game game, int id) {
		this.game = game;
		ID = id;
	}

	@Override
	public int getId() {
		return ID;
	}

	@Override
	public boolean isApplicable(Unit unit, Optional<TilePosition> position) {
		return unit.getType().isWorker();
	}

	@Override
	public StepResults execute(Unit unit, Optional<Unit> target, Optional<TilePosition> position) {
		return StepResults.of(game.neutral().getUnits().stream()
                .filter(u -> u.getType().isMineralField())
                .sorted((a, b) -> Integer.compare(a.getDistance(unit), b.getDistance(unit)))
                .findFirst(),
            position);
	}

	@Override
	public int getTraversalCost() {
		return 2;
	}
}
