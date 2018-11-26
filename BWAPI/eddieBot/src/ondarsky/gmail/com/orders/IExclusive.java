package ondarsky.gmail.com.orders;

import bwapi.Unit;

public interface IExclusive {

    /**
     * Provides synchronization over the order among multiple units
     */

    public Boolean executeExclusively(Unit unit);

    /**
     * reset synchronization gate;
     */
    public void unlock();
}
