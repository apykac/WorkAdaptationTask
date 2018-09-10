package ru.vsk.exchange;

import org.osgi.util.tracker.ServiceTracker;

public class StaticData {
    private static ServiceTracker<Object, Object> tracker;

    public static void putTracker(ServiceTracker<Object, Object> tracker) {
        StaticData.tracker = tracker;
    }

    public static ServiceTracker<Object, Object> getTracker() {
        return StaticData.tracker;
    }
}
