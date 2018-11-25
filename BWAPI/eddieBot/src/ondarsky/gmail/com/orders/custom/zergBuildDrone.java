package ondarsky.gmail.com.orders.custom;


import bwapi.Game;
import bwapi.Player;
import bwapi.Unit;
import bwapi.UnitType;
import ondarsky.gmail.com.orders.Order;
import ondarsky.gmail.com.orders.PrioritizedOrder;

public class zergBuildDrone extends Order<Unit> implements PrioritizedOrder {

	private double priority = 50;
	private Player player;
	
	public zergBuildDrone(Game game, Player player) {
    	//if there's enough minerals, train an SCV
		super(
			unit -> unit.getType().producesLarva() 
				&& UnitType.Zerg_Drone.mineralPrice() <= player.minerals(), 
			unit -> unit.train(UnitType.Zerg_Drone));
		this.player = player;
	}

	@Override
	public void decay() {
		priority = priority / player.allUnitCount(UnitType.Zerg_Drone);

	}

	@Override
	public void raisePriority(double amount) {
		priority += amount;

	}

	@Override
	public void decreasePriority(double amount) {
		priority -= amount;
	}
}
