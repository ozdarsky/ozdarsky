package ondarsky.gmail.com.strategy.nodes.entries;

import bwapi.Game;
import bwapi.Unit;
import ondarsky.gmail.com.strategy.intf.EntryPoint;

public class IdleDrone extends EntryPoint {

	public static int ID;

	@Override
	public int getId() {
		return ID;
	}

    @Override
    public boolean qualify(Unit unit) {
        return unit.getType().isWorker() && unit.isIdle();
    }

    @Override
    public String toString() {
        return IdleDrone.class.getSimpleName();
    }

	@Override
	public void initialize(Game game, int id) {
		ID = id;
	}

	@Override
	public int getTraversalCost() {
		return -1;
	}
}
