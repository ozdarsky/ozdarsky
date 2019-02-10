package ondarsky.gmail.com.strategy.nodes.executables;

import java.util.Optional;

import bwapi.Game;
import bwapi.TilePosition;
import bwapi.Unit;
import ondarsky.gmail.com.strategy.intf.ExecutableOrder;
import ondarsky.gmail.com.utils.StepResults;

public class Harvest extends ExecutableOrder {

	public static int ID;

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
		target.ifPresent(t -> unit.gather(t, false));
        return StepResults.of(target, position);
    }

	@Override
	public void initialize(Game game, int id) {
		ID = id;
	}

	@Override
	public int getTraversalCost() {
		return 1;
	}
}
