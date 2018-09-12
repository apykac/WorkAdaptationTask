package ru.vsk.exchange;

import com.google.gson.Gson;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;

import java.math.BigInteger;

public class ExchangerActiveMQ implements Exchanger {
    private CamelContext camelContext;
    private ProducerTemplate producer;
    private ConsumerTemplate consumer;
    private Gson gson;

    public ExchangerActiveMQ(CamelContext camelContext) {
        this.camelContext = camelContext;
        producer = this.camelContext.createProducerTemplate();
        consumer = this.camelContext.createConsumerTemplate();
        gson = new Gson();
    }

    @Override
    public String getFactorial(int i) {
        String jsonBI = gson.toJson(i);
        producer.sendBody("activemq:queue:testIncomingQueue", jsonBI);

        String jsonReceivedData = consumer.receiveBody("activemq:queue:testOutputQueue", String.class);
        BigInteger bigInteger = gson.fromJson(jsonReceivedData, BigInteger.class);

        return bigInteger.toString();
    }
}
