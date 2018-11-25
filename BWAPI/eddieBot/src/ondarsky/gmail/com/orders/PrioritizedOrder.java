package ondarsky.gmail.com.orders;

public interface PrioritizedOrder {


	double priority = 1;
	
	/**
	 * method used to change priority of the order in time if it still haven't been executed
	 */
	public void decay();
	
	/**
	 * Raise this order's priority by the given amount
	 * @param amount decimal value representing by how much should be this order's priority raised.
	 * Can be negative amount in which case this method behaves exactly as {@linkplain PrioritizedOrder#decreasePriority(double * -1)}.
	 */
	public void raisePriority(double amount);
	
	/**
	 * Decrease this order's priority by the given amount
	 * @param amount decimal value representing by how much should be this order's priority decreased.
	 * Can be negative amount in which case this method behaves exactly as {@linkplain PrioritizedOrder#increasePriority(double * -1)}.
	 */
	public void decreasePriority(double amount);
	
	/**
	 * Returns current priority level
	 */
	public double getPriority();
	
}
