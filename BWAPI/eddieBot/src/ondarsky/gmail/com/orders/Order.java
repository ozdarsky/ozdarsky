package ondarsky.gmail.com.orders;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class Order<T> {
	
	private final Predicate<T> condition;
	private final Function<T, Boolean> doStuff;
	
	/**
	 * Constructor
	 */
	public Order(Predicate<T> condition, Function<T, Boolean> doStuff) {
		this.condition = condition;
		this.doStuff = doStuff;
	}
	
	/**
	 * Checks whether this order can be executed
	 */
	public boolean checkCondition(T unit) {
		return condition.test(unit);
	}
	
	/**
	 * Executes the order for the given unit.
	 * No condition is checked
	 */
	@SuppressWarnings("unchecked")
	public <R> R executeFor(T unit) {
		return (R) doStuff.apply(unit);
	}
	
	/**
	 * Executes the order for the given unit, but only if the unit satisfies this order's condition
	 * @param unit that should execute the order
	 * @return <code>true</code> if both the condition has been satisfied and the order was executed successfully,
	 * <code>false</code> if the unit does not satisfy this order's condition or the result of the execution was false (e.g. Worker can't build at the location)
	 */
	@SuppressWarnings("unchecked")
	public <R> R executeIfValid(T unit) {
		if (condition.test(unit)) {
			return (R) doStuff.apply(unit);
		}
		return null;
	}
	
	/**
	 * Returns default priority of non-prioritized Orders
	 */
	public double getPriority() {
		return 10.0;
	}
}
