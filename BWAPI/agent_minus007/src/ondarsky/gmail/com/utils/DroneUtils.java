package ondarsky.gmail.com.utils;

import bwapi.Order;
import bwapi.Unit;

public class DroneUtils {

	public static boolean isNotCarrying(Unit unit) {
		return !unit.isCarryingMinerals() && !unit.isCarryingGas();
	}

	public static boolean isNotBuilding(Unit unit) {
		return !unit.isConstructing();
	}

	public static boolean isIdleOrHarvesting(Unit unit) {
		return (unit.isIdle() || isNotCarrying(unit)) && isNotBuilding(unit);
	}

	public static boolean isWaitingForGas(Unit unit) {
		return Order.WaitForGas.equals(unit.getOrder());
	}
}
