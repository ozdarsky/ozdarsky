package ondarsky.gmail.com.strategy.nodes.executables;

import java.util.Optional;

import bwapi.Game;
import bwapi.TilePosition;
import bwapi.Unit;
import ondarsky.gmail.com.strategy.intf.ExecutableOrder;
import ondarsky.gmail.com.utils.StepResults;

public class FindGasField extends ExecutableOrder {

	public static int ID;

	private Game game;

	@Override
	public int getId() {
		return ID;
	}

	@Override
	public void initialize(Game game, int id) {
		this.game = game;
		ID = id;
	}

	@Override
	public boolean isApplicable(Unit unit, Optional<TilePosition> position) {
		return unit.getType().isWorker();
	}

	@Override
	public StepResults execute(Unit u, Optional<Unit> target, Optional<TilePosition> position) {
		return StepResults.of(game.self().getUnits().stream()
                .filter(unit -> unit.getType().isRefinery())
                .sorted((a, b) -> Integer.compare(a.getDistance(u), b.getDistance(u)))
                .findFirst(),
            position);
	}

	@Override
	public int getTraversalCost() {
		return 2;
	}



}
