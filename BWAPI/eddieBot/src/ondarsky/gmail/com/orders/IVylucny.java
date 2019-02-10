package ondarsky.gmail.com.orders;

import bwapi.Unit;

public interface IVylucny {

    /**
     * provede prikaz vylucne pro jednu jednotku
     */

    public Boolean provedVylucne(Unit unit);

    /**
     * resetuje synchronizacni branu;
     */
    public void odemkni();
}
