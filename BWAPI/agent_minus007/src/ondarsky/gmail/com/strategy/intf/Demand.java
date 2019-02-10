package ondarsky.gmail.com.strategy.intf;

import bwapi.Game;
import bwapi.Unit;
import ondarsky.gmail.com.Constants;

public abstract class Demand extends Node {

    private Game game;

    private int priority;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getPriority() {
        return priority;
    }

    protected void setPriority(int priority) {
        this.priority = priority;
    }

    public void starvePriority() {
    	this.priority = Math.max(0, priority);
    }

    public void decayPriority(int amount) {
    	this.priority = (amount % Constants.DECAY_THRESHOLD);
	}

    public abstract boolean isApplicableForUnit(Unit unit);

	@Override
	public abstract int getId();

}
