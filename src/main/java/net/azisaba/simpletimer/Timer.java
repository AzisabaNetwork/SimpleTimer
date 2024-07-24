package net.azisaba.simpletimer;

public final class Timer {
    private long startTime;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public String getElapsedTimeInString() {
        // HH:mm:ss.SSS
        long elapsedTime = getElapsedTime();
        long hours = elapsedTime / 3600000;
        long minutes = (elapsedTime % 3600000) / 60000;
        long seconds = (elapsedTime % 60000) / 1000;
        long millis = elapsedTime % 1000;
        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, millis);
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - startTime;
    }
}
