package me.gamerzking.core.countdown;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by GamerzKing on 7/24/2016.
 */
public abstract class BaseCountdown implements Runnable {

    private int startTime;
    private int currentTime;

    private ScheduledExecutorService executorService;
    private ScheduledFuture<?> future;

    public BaseCountdown(ScheduledExecutorService executorService, int startTime) {

        this.startTime = startTime;
        this.currentTime = startTime;

        this.executorService = executorService;
    }

    /**
     * Called when the countdown is finished.
     */

    public abstract void onEnd();

    /**
     * Called when the countdown is counting down.
     *
     * @param time The current time of the countdown.
     */

    public abstract void onInterval(int time);

    @Override
    public void run() {

        if(currentTime > 0) {

            onInterval(currentTime);
            currentTime--;

        } else {

            stopCountdown();
        }
    }

    /**
     * Starts the countdown by scheduling the executor service.
     *
     * @return False is the service is already running; otherwise, true.
     */

    public boolean startCountdown() {

        if(isRunning())
            return false;

        // Schedule a future task that runs at a fixed rate of 1 second.
        future = executorService.scheduleAtFixedRate(this, 1L, 1L, TimeUnit.SECONDS);

        return true;
    }

    /**
     * Stops the countdown by cancelling the future tasks.
     *
     * @return False is the service is already cancelled; otherwise, true.
     */

    public boolean stopCountdown() {

        if(!isRunning())
            return false;

        future.cancel(true);
        future = null;

        onEnd();
        return true;
    }

    /**
     * Restarts the countdown.
     */

    public void restartCountdown() {

        stopCountdown();
        currentTime = startTime;
        startCountdown();
    }

    /**
     * @return True is the future service isn't null; otherwise, false.
     */

    public boolean isRunning() {

        return future != null;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    public ScheduledFuture<?> getFuture() {
        return future;
    }
}