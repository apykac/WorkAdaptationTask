package ru.vsk.services;

import ru.vsk.exchange.Exchanger;
import ru.vsk.exchange.ExchangerActiveMQ;

public class EmployeeServiceResource implements EmployeeService {
    private Exchanger exchanger = new ExchangerActiveMQ();

    public EmployeeServiceResource() throws Exception {
    }

    public String getString(String name) {
        return "Welcome your string: " + name;
    }

    public String methods(Integer id) {
        return "you write in this: " + id + System.lineSeparator() +
                "Factorial of this number is: " + exchanger.getFactorial(id);
    }

}