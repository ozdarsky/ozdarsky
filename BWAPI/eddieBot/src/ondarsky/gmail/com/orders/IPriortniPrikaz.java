package ondarsky.gmail.com.orders;

public interface IPriortniPrikaz {

    /**
     * Automaticke snizeni priority pro situace, kdy se prikaz prave provedl
     */
    public void vyhnij();

    /**
     * Automaticke zvyseni priority urcene do situaci, kde se prikaz porad jeste
     * neprovedl
     */
    public void propleskni();

    /**
     * Raise this order's priority by the given amount
     *
     * @param amount desetinne cislo o kolik se ma zvysit. Pokud je zaporne, tak je
     *               vysledek stejny jako u
     *               {@linkplain PrioritizedOrder#decreasePriority(double * -1)}.
     */
    public void zvysPrioritu(double amount);

    /**
     * Snizi prioritu
     *
     * @param amount desetinne cislo o kolik se ma snizit. Pokud je zaporne, tak je
     *               vysledek stejny jako u
     *               {@linkplain PrioritizedOrder#increasePriority(double * -1)}.
     */
    public void snizPrioritu(double amount);

    /**
     * Vrati aktualni prioritu
     */
    public double getPrioritu();

}
