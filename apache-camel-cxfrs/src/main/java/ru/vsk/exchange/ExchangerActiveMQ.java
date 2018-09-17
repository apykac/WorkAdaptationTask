package ru.vsk.exchange;

import com.google.gson.Gson;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

public class ExchangerActiveMQ implements Exchanger {
    private CamelContext camelContext;
    private ProducerTemplate producer;
    private ConsumerTemplate consumer;
    private Gson gson;

    public ExchangerActiveMQ() throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://localhost:61616?" +
                        "jms.userName=karaf&" +
                        "jms.password=karaf");
        JndiContext jndiContext = new JndiContext();
        camelContext = new DefaultCamelContext(jndiContext);
        camelContext.addComponent("activemq", JmsComponent.jmsComponent(connectionFactory));
        gson = new Gson();

        camelContext.start();
        producer = this.camelContext.createProducerTemplate();
        consumer = this.camelContext.createConsumerTemplate();
    }

    @Override
    public String getFactorial(int i) {
        String jsonBI = gson.toJson(i);
        producer.sendBody("activemq:queue:testIncomingQueue", jsonBI);

        String jsonReceivedData = consumer.receiveBody("activemq:queue:testOutputQueue", String.class);
        return gson.fromJson(jsonReceivedData, String.class);
    }
}
