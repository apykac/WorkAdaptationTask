package ru.vsk.activators;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
//import ru.vsk.calculators.MainCalculator;
//import ru.vsk.exchange.StaticData;

public class Activator implements BundleActivator {
    private ServiceTracker<Object, Object> tracker;

    public void start(BundleContext bundleContext) throws Exception {
        /*tracker = new ServiceTracker<Object, Object>(bundleContext, MainCalculator.class.getName(), null);
        tracker.open();
        StaticData.putTracker(tracker);*/
        System.out.println("REST START SUCCESS");
    }

    public ServiceTracker<Object, Object> getTracker() {
        return tracker;
    }

    public void stop(BundleContext bundleContext) throws Exception {
        tracker.close();
        System.out.println("REST STOP SUCCESS");
    }
}
