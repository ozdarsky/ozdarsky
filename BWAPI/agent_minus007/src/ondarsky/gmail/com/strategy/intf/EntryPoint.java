package ondarsky.gmail.com.strategy.intf;

import bwapi.Game;
import bwapi.Unit;

public abstract class EntryPoint extends Node {

    public abstract boolean qualify(Unit unit);

	@Override
	public abstract void initialize(Game game, int id);

	@Override
	public abstract int getId();
}
