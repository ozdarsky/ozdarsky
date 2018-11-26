package ondarsky.gmail.com.orders;

import java.util.EnumSet;
import java.util.function.Function;
import java.util.function.Predicate;

import bwapi.Unit;

public abstract class Order {

    private final Predicate<Unit> condition;
    private final Function<Unit, Boolean> doStuff;

    protected Integer gate;
    protected Integer originalGateSize;

    private EnumSet<EDirectiveOption> options = EnumSet.noneOf(EDirectiveOption.class);

    public enum EDirectiveOption {
        Exclusive,
        Prioritized
    }

    /**
     * Constructors
     */
    protected Order(Predicate<Unit> condition, Function<Unit, Boolean> doStuff) {
        this(condition, doStuff, 1);
    }

    protected Order(Predicate<Unit> condition, Function<Unit, Boolean> doStuff, int limit) {
        this.condition = condition;
        this.doStuff = doStuff;
        this.gate = originalGateSize = limit;
    }

    /**
     * Checks whether this order can be executed
     */
    public boolean checkCondition(Unit unit) {
        return condition.test(unit);
    }

    /**
     * Executes the order for the given unit.
     * No condition is checked
     */
    public Boolean executeFor(Unit unit) {
        return doStuff.apply(unit);
    }

    /**
     * Executes the order for the given unit, but only if the unit satisfies this
     * order's condition
     *
     * @param unit that should execute the order
     * @return <code>true</code> if both the condition has been satisfied and the
     *         order was executed successfully,
     *         <code>false</code> if the unit does not satisfy this order's
     *         condition or the result of the execution was false (e.g. Worker can't
     *         build at the location)
     */
    public Boolean executeIfValid(Unit unit) {
        if (condition.test(unit)) {
            return doStuff.apply(unit);
        }
        return null;
    }

    /**
     * Returns default priority of non-prioritized Orders
     */
    public double getPriority() {
        return 10.0;
    }

    public Order addOption(EDirectiveOption opt) {
        options.add(opt);
        return this;
    }

    public EnumSet<EDirectiveOption> getOptions() {
        return options;
    }
}
