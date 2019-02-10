package ondarsky.gmail.com.strategy.nodes.demands;

import bwapi.Game;
import bwapi.Player;
import bwapi.Unit;
import bwapi.UnitType;
import ondarsky.gmail.com.strategy.intf.Demand;

public class Overlord extends Demand {

	private static final int COST = 16;
	public static int ID;

	private int popBeingProduced = 0;

	private Player self;

	@Override
	public int getId() {
		return ID;
	}

	@Override
	public int getTraversalCost() {
		return COST;
	}

	@Override
	public void initialize(Game game, int id) {
		setPriority(COST);
		self = game.self();
		ID = id;
	}

	@Override
	public boolean isApplicableForUnit(Unit unit) {
		return unit.getType().equals(UnitType.Zerg_Larva);
	}

	@Override
	public void decayPriority(int amount) {
		setPriority(getPriority() + amount);
	}

	@Override
	public void starvePriority() {
		int popLeft = (self.supplyTotal() - self.supplyUsed());

		setPriority(popBeingProduced > popLeft
				? getPriority()
				: popLeft - 3);
	}

}
