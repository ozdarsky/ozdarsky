package ondarsky.gmail.com.strategy.nodes.entries;

import bwapi.Game;
import bwapi.Unit;
import bwapi.UnitType;
import ondarsky.gmail.com.strategy.intf.EntryPoint;

public class IdleLarva extends EntryPoint {

	public static int ID;

	@Override
	public int getId() {
		return ID;
	}

    @Override
    public String toString() {
        return IdleLarva.class.getSimpleName();
    }

    @Override
    public boolean qualify(Unit unit) {
        return UnitType.Zerg_Larva.equals(unit.getType());
    }

	@Override
	public void initialize(Game game, int id) {
		ID = id;
	}

	@Override
	public int getTraversalCost() {
		return -3;
	}

}
