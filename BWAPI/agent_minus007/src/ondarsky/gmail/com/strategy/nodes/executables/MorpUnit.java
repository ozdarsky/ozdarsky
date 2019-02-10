package ondarsky.gmail.com.strategy.nodes.executables;

import java.util.Optional;

import bwapi.Game;
import bwapi.TilePosition;
import bwapi.Unit;
import bwapi.UnitType;
import ondarsky.gmail.com.strategy.intf.ExecutableOrder;
import ondarsky.gmail.com.utils.StepResults;

public abstract class MorpUnit extends ExecutableOrder {

	private Game game;
	private int priority = 1;

    @Override
    public boolean isApplicable(Unit unit, Optional<TilePosition> position) {
        return UnitType.Zerg_Larva.equals(unit.getType());
    }

    @Override
    public StepResults execute(Unit unit, Optional<Unit> target, Optional<TilePosition> position) {
    	Optional.of(getUnitType())
	    	.filter(this::checkUnitCost)
	    	.ifPresent(t -> unit.train(t));
        return StepResults.of(unit, null);
    }

    /**
     * Specifies what unit is this command step to build
     */
    protected abstract UnitType getUnitType();

    private boolean checkUnitCost(UnitType t) {
    	return t.mineralPrice() <= game.self().minerals()
    			&& t.gasPrice() <= game.self().gas();
    }

	@Override
    public void initialize(Game game, int id) {
    	this.game = game;
    }

	@Override
	public int getTraversalCost() {
		return priority;
	}
}
