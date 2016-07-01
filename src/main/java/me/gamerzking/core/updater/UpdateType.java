package me.gamerzking.core.updater;

import me.gamerzking.core.utils.UtilTime;

/**
 * Created by GamerzKing on 4/18/2016.
 */
public enum UpdateType {

    MINUTE(60000),
    HALF_MINUTE(30000),
    FIVE_SECOND(5000),
    THREE_SECOND(3000),
    SECOND(1000),
    HALF_SECOND(500),
    QUARTER_SECOND(250),
    SEVENTH_SECOND(142),
    TICK(50);

    private long currentTime;
    private long milliseconds;

    /**
     * @param milliseconds How many milliseconds the time sequence is updated.
     */

    UpdateType(long milliseconds) {

        this.milliseconds = milliseconds;
    }

    /**
     * @return Whether time has elapsed between the current time, and the milliseconds provided in the constructor.
     */

    public boolean elapsed() {

        if (UtilTime.elapsed(currentTime, milliseconds)) {

            currentTime = System.currentTimeMillis();
            return true;
        }

        return false;
    }
}
