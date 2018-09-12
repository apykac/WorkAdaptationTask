package ru.vsk;

import com.google.gson.Gson;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import ru.vsk.calculators.Calculator;
import ru.vsk.calculators.MainCalculator;
import ru.vsk.exchenge.StaticData;

public class Main extends Thread {
    private boolean isAlive;

    public void stopThread() {
        isAlive = false;
    }

    @Override
    public void run() {
        isAlive = true;
        Gson gson = new Gson();
        Calculator calculator = new MainCalculator();
        ProducerTemplate producer = StaticData.getCamelContext().createProducerTemplate();
        ConsumerTemplate consumer = StaticData.getCamelContext().createConsumerTemplate();
        int i = 0;
        while (isAlive) {
            String receivedData = consumer.receiveBody("activemq:queue:testIncomingQueue", String.class);
            int number = gson.fromJson(receivedData, Integer.class);

            String dataForSend = gson.toJson(calculator.factorial(number));
            producer.sendBody("activemq:queue:testOutputQueue", dataForSend);
        }
    }
}
