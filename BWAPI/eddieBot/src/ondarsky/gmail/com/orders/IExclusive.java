package ondarsky.gmail.com.orders;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

import bwapi.Unit;

public interface IExclusive {

    static final Lock lock = new ReentrantLock();

    /**
     * Executes the body exclusively for one Unit
     */
    default boolean doStuff(Function<Unit, Boolean> doStuff, Unit unit) {
        if (!lock.tryLock()) {
            return false;
        }
        return doStuff.apply(unit);
    }

    default void doUnlock() {
        lock.unlock();
    };
}
