package ondarsky.gmail.com.strategy.nodes.executables;

import java.util.EnumSet;
import java.util.Optional;

import bwapi.Game;
import bwapi.TilePosition;
import bwapi.Unit;
import bwapi.UnitType;
import ondarsky.gmail.com.Log;
import ondarsky.gmail.com.strategy.intf.ExecutableOrder;
import ondarsky.gmail.com.utils.StepResults;

public abstract class FindBuildLocation extends ExecutableOrder {

	public static int ID;
	private Game game;

	Log log = Log.getInstance();

	private enum DIRECTION {
		NORTH(0, 1),
		WEST(-1, 0),
		SOUTH(-1, 0),
		EAST(1, 0);

		private double probabilityLevel = 0.25;
		private final int xAxis;
		private final int yAxis;

		private DIRECTION(int xAxis, int yAxis) {
			this.xAxis = xAxis;
			this.yAxis = yAxis;
		}

		public double getLevel() {
			return probabilityLevel;
		}

		public int getX() {
			return xAxis;
		}

		public int getY() {
			return yAxis;
		}

		public static void reset() {
			for (DIRECTION d : values()) {
				d.probabilityLevel = 0.25;
			}
		}
	}


	@Override
	public boolean isApplicable(Unit unit, Optional<TilePosition> position) {
		return true;
	}

	@Override
	public StepResults execute(Unit unit, Optional<Unit> target, Optional<TilePosition> position) {

		TilePosition pos = position.orElseGet(() -> {
			DIRECTION.reset();
			return unit.getTilePosition();
		});
		// where?, what?, who?, isExplored?
		boolean found = false;
		found = game.canBuildHere(new TilePosition(pos.getX(), pos.getY()), getBuilding(), unit, true);

		log.error("location " + pos.toString() + " is " + (found ? "ok" : "not suitable"));

		int tryouts = 1000;	// just to prevent endless loop
		while(tryouts-- > 0 && !found) {
			double directionRoulette = Math.random();
			double threshold = 0.0;
			DIRECTION chosen = null;

			// iterate until most probable random direction is chosen
			for (DIRECTION direction : DIRECTION.values()) {
				threshold += direction.getLevel();
				chosen = direction;
				if (directionRoulette <= threshold) {
					break;
				}
			}
			// calculate new position in given direction
			pos = new TilePosition(pos.getX() + chosen.getX(), pos.getY() + chosen.getY());
			found = game.canBuildHere(pos, getBuilding(), unit, true);

			log.error("location " + pos.toString() + " is " + (found ? "ok" : "not suitable"));

			// adjust probabilities if not successfull
			if (!found) {
				adjust(chosen);
			}
		}
		log.error("done with " + tryouts + "attemps left");

		// return results
		return StepResults.of(
				target,
				Optional.ofNullable(pos));
	}

	/**
	 * adjust direction probabilities so that the total probability will be normalized to 1.0
	 */
	private void adjust(DIRECTION direction) {
		double diff = Math.min(direction.getLevel(), 0.03d);
		direction.probabilityLevel -= diff;

		EnumSet<DIRECTION> increased = EnumSet.allOf(DIRECTION.class);
		increased.remove(direction);
		increased.forEach(d -> d.probabilityLevel += (diff/3));
		log.error("NORTH " + (DIRECTION.NORTH.getLevel() * 100) + "%");
		log.error("WEST " + (DIRECTION.WEST.getLevel() * 100) + "%");
		log.error("SOUTH " + (DIRECTION.SOUTH.getLevel() * 100) + "%");
		log.error("EAST " + (DIRECTION.EAST.getLevel() * 100) + "%");
	}

	public abstract UnitType getBuilding();

	@Override
	public int getTraversalCost() {
		return 1;
	}

	@Override
	public void initialize(Game game, int id) {
		this.game = game;
		ID = id;
	}

	@Override
	public int getId() {
		return ID;
	}

}
