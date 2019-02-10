package ondarsky.gmail.com.strategy.intf;

import bwapi.Game;
public abstract class Node {

	/**
	 * Vrati cenu na zpracovani tohoto uzlu. Podili se na vysledne priorite prikazu
	 */
    public abstract int getTraversalCost();

    /**
     * Initialize node
     * @param i
     */
    public abstract void initialize(Game game, int i);

	public abstract int getId();

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
