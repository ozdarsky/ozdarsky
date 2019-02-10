package ondarsky.gmail.com.strategy.intf;

import java.util.Optional;

import bwapi.TilePosition;
import bwapi.Unit;
import ondarsky.gmail.com.utils.StepResults;

public abstract class ExecutableOrder extends Node {

    public abstract boolean isApplicable(Unit unit, Optional<TilePosition> position);

    public abstract StepResults execute(Unit unit, Optional<Unit> target, Optional<TilePosition> position);
}
