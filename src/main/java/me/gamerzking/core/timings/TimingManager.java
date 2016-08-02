package me.gamerzking.core.timings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GamerzKing on 8/2/2016.
 */
public class TimingManager {

    private static final Object synchronizedLock = new Object();
    private static Map<String, Long> timingMap = new HashMap<>();

    /**
     * Starts timing the task specified.
     *
     * @param task The task you're timing.
     */

    public static void startTiming(String task) {

        synchronized (synchronizedLock) {

            timingMap.put(task, System.currentTimeMillis());
            System.out.println("]==[TIMING]==[ Started timing: (" + task + ")");
        }
    }

    /**
     * Stops timing the task specified.
     *
     * @param task The task you're no longer timing.
     */

    public static void stopTiming(String task) {

        synchronized (synchronizedLock) {

            if (timingMap.containsKey(task))
                System.out.println("]==[TIMING]==[ " + task + " took " + (System.currentTimeMillis() - timingMap.remove(task)) + "ms");
        }
    }
}