package ondarsky.gmail.com.orders;

import java.util.EnumSet;
import java.util.function.Function;
import java.util.function.Predicate;

import bwapi.Unit;

public abstract class Prikaz {

    private final Predicate<Unit> podminka;
    private final Function<Unit, Boolean> rozkaz;

    protected Integer branka;
    protected Integer originalniVelikostBranky;

    private EnumSet<EVlastnostPrikazu> options = EnumSet.noneOf(EVlastnostPrikazu.class);

    public enum EVlastnostPrikazu {
        Exklusivni,
        Prioritni
    }

    /**
     * Constructors
     */
    protected Prikaz(Predicate<Unit> condition, Function<Unit, Boolean> doStuff) {
        this(condition, doStuff, 1);
    }

    protected Prikaz(Predicate<Unit> condition, Function<Unit, Boolean> doStuff, int limit) {
        this.podminka = condition;
        this.rozkaz = doStuff;
        this.branka = originalniVelikostBranky = limit;
    }

    /**
     * Checks whether this order can be executed
     */
    public boolean checkCondition(Unit unit) {
        return podminka.test(unit);
    }

    /**
     * Vykona prikaz bez kecu (bez overeni podminky)
     */
    public Boolean executeFor(Unit unit) {
        return rozkaz.apply(unit);
    }

    /**
     * Vykona rozkaz, ale prvne si overi, jestli je platny
     *
     * @param jednotka, ktera ma rozkaz vykonat
     * @return <code>true</code> pokud je zaroven splnena podminka a rozkaz se vykonal uspesne,
     *         <code>false</code> pokud rozkaz nema byt vykonavan, nebo ho nelze splnit (napr. Dron nemuze postavit Extraktor na mineral fieldu)
     */
    public Boolean executeIfValid(Unit unit) {
        if (podminka.test(unit)) {
            return rozkaz.apply(unit);
        }
        return null;
    }

    /**
     * vraci defaultni prioritu
     */
    public double getPrioritu() {
        return 10.0;
    }

    public Prikaz addOption(EVlastnostPrikazu opt) {
        options.add(opt);
        return this;
    }

    public EnumSet<EVlastnostPrikazu> getOptions() {
        return options;
    }
}
