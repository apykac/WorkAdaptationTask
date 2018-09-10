package ru.vsk.exchange;

import org.osgi.util.tracker.ServiceTracker;
import ru.vsk.calculators.Calculator;
import ru.vsk.calculators.MainCalculator;

import java.math.BigInteger;

public class Exchanger {
    private final ServiceTracker<Object, Object> serviceTracker;

    public Exchanger(ServiceTracker<Object, Object> serviceTracker) {
        this.serviceTracker = serviceTracker;
    }

    public String getFactorial(int i) {
        Calculator calculator = (MainCalculator) serviceTracker.getService();
        if (calculator != null) {
            BigInteger bigInteger = calculator.factorial(i);
            return bigInteger.toString();
        } else {
            return "EMPTY";
        }
    }
}
