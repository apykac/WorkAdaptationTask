package ru.vsk.activators;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import ru.vsk.calculators.Calculator;
import ru.vsk.calculators.MainCalculator;

public class Activator implements BundleActivator {
    private ServiceRegistration serviceRegistration;

    public void start(BundleContext context) throws Exception {
        Calculator calculator = new MainCalculator();
        serviceRegistration = context.registerService(MainCalculator.class.getName(), calculator, null);
        System.out.println("TEST BUNDLE START SUCCESS");
    }

    public void stop(BundleContext context) throws Exception {
        serviceRegistration.unregister();
        System.out.println("TEST BUNDLE STOP SUCCESS");
    }
}
