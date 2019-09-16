package util;

/**
 * Util helper class that hold time constants
 * and jvm time.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */
public class Time {

    /**
     * Amount nanoseconds in 1 second.
     */
    public static final long SECOND = 1000000000L;

    /**
     * Get current jvm time in nanoseconds.
     */
    public static long get(){
        return System.nanoTime();
    }
}
