package ondarsky.gmail.com.strategy.nodes.executables;

import java.util.Optional;

import bwapi.Game;
import bwapi.TilePosition;
import bwapi.Unit;
import bwapi.UnitType;
import ondarsky.gmail.com.strategy.intf.ExecutableOrder;
import ondarsky.gmail.com.utils.StepResults;

public abstract class MorpStructure extends ExecutableOrder {

	public static int ID;

	private Game game;
	private int priority = 50;

	@Override
	public int getId() {
		return ID;
	}

    @Override
    public boolean isApplicable(Unit unit, Optional<TilePosition> position) {
        return UnitType.Zerg_Drone.equals(unit.getType()) && position.isPresent();
    }

    @Override
    public StepResults execute(Unit unit, Optional<Unit> target, Optional<TilePosition> position) {
    	Optional.of(getUnitType())
	    	.filter(t -> t.mineralPrice() <= game.self().minerals()
	    			&& t.gasPrice() <= game.self().gas())
	    	.ifPresent(t -> unit.build(t, position.get()));
        return StepResults.of(target, position);
    }

    /**
     * Specifies what unit is this command step to build
     */
    protected abstract UnitType getUnitType();

	@Override
    public void initialize(Game game, int id) {
    	this.game = game;
    	ID = id;
    }

	@Override
	public int getTraversalCost() {
		return priority;
	}
}
