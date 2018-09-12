package ru.vsk.exchenge;

import org.apache.camel.CamelContext;

public class StaticData {
    private static CamelContext camelContext;

    public static CamelContext getCamelContext() {
        return camelContext;
    }

    public static void putCamelContext(CamelContext camelContext) {
        StaticData.camelContext = camelContext;
    }
}
