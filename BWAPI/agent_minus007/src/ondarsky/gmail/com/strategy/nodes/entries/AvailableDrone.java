package ondarsky.gmail.com.strategy.nodes.entries;

import bwapi.Game;
import bwapi.Unit;
import ondarsky.gmail.com.strategy.intf.EntryPoint;
import ondarsky.gmail.com.utils.DroneUtils;

public class AvailableDrone extends EntryPoint {

	public static int ID;

	@Override
	public int getId() {
		return ID;
	}

    @Override
    public boolean qualify(Unit unit) {
        return unit.getType().isWorker() && DroneUtils.isIdleOrHarvesting(unit);
    }

    @Override
    public String toString() {
        return AvailableDrone.class.getSimpleName();
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
