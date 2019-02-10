package ondarsky.gmail.com.utils;

import java.util.Optional;

import bwapi.TilePosition;
import bwapi.Unit;

public class StepResults {

	private Optional<Unit> key;
	private Optional<TilePosition> val;

	private StepResults(Optional<Unit> key, Optional<TilePosition> val) {
		this.key = key;
		this.val = val;
	}

	public static StepResults of(Optional<Unit> key, Optional<TilePosition> val) {
		return new StepResults(key, val);
	}

	public static StepResults of(Unit key, TilePosition val) {
		return of(Optional.ofNullable(key), Optional.ofNullable(val));
	}

	public static StepResults empty() {
		return of(Optional.empty(), Optional.empty());
	}

	public Optional<Unit> getUnitOpt() {
		return key;
	}

	public Optional<TilePosition> getPositionOpt() {
		return val;
	}

	public boolean isPresent() {
		return key.isPresent() || val.isPresent();
	}
}
