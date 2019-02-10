package ondarsky.gmail.com.strategy.nodes.demands;

import bwapi.Game;
import bwapi.Player;
import bwapi.Unit;
import bwapi.UnitType;
import ondarsky.gmail.com.strategy.intf.Demand;

public class Drone extends Demand {

	public static int ID;
	private Player self;

	@Override
	public int getId() {
		return ID;
	}

	@Override
	public int getTraversalCost() {
		return 0;
	}

	@Override
	public void initialize(Game game, int id) {
		setPriority(0);
		ID = id;
		self = game.self();
	}

	@Override
	public boolean isApplicableForUnit(Unit unit) {
		return unit.getType().equals(UnitType.Zerg_Larva);
	}

	@Override
	public void decayPriority(int amount) {
		super.decayPriority((int) self.getUnits().stream()
				.filter(unit -> unit.getType().equals(UnitType.Zerg_Drone))
				.filter(Unit::isIdle)
				.count());
	}

}
