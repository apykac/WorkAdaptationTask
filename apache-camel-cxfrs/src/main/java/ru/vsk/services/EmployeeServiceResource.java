package ru.vsk.services;

import ru.vsk.exchange.Exchanger;

public class EmployeeServiceResource implements EmployeeService {
    private Exchanger exchanger;

    public EmployeeServiceResource(Exchanger exchanger) {
        this.exchanger = exchanger;
    }

    public String getString(String name) {
        return "Welcome your string: " + name;
    }

    public String methods(Integer id) {
        return "you write in this: " + id + System.lineSeparator() +
                "Factorial of this number is: " + exchanger.getFactorial(id);
    }

}