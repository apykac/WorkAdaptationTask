package ru.vsk.exchange;

import org.apache.camel.CamelContext;
import org.osgi.util.tracker.ServiceTracker;

public class StaticData {
    private static ServiceTracker<Object, Object> tracker;
    private static CamelContext camelContext;

    public static void putTracker(ServiceTracker<Object, Object> tracker) {
        StaticData.tracker = tracker;
    }

    public static ServiceTracker<Object, Object> getTracker() {
        return StaticData.tracker;
    }

    public static CamelContext getCamelContext() {
        return camelContext;
    }

    public static void putCamelContext(CamelContext camelContext) {
        StaticData.camelContext = camelContext;
    }
}
