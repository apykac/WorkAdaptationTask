package ru.vsk.services;

import java.math.BigInteger;

public class MainCalculator implements Calculator {
    public String factorial(int number) {
        BigInteger result = new BigInteger("1");
        for (int i = 2; i <= number; i++) {
            result = result.multiply(new BigInteger("" + i));
        }
        return result.toString();
    }
}
