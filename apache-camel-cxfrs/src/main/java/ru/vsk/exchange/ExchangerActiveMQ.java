package ru.vsk.exchange;

import com.google.gson.Gson;
import org.apache.activemq.Closeable;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import java.util.logging.Logger;

public class ExchangerActiveMQ implements Exchanger, Closeable {
    private ConnectionFactory connectionFactory;
    private Gson gson;
    private String jmsComponentName;

    private CamelContext camelContext;
    private ProducerTemplate producer;
    private ConsumerTemplate consumer;

    private static final Logger log = Logger.getLogger(ExchangerActiveMQ.class.getName());

    public ExchangerActiveMQ(ConnectionFactory connectionFactory, String jmsComponentName) throws Exception {
        this.connectionFactory = connectionFactory;
        this.jmsComponentName = jmsComponentName;
        gson = new Gson();
    }

    @Override
    public String getFactorial(int i) {
        try {
            init();
        } catch (Exception e) {
            log.info("Error in ExchangerActiveMQ.init(): " + e.getMessage());
        }
        String jsonBI = gson.toJson(i);
        producer.sendBody(jmsComponentName + ":queue:testIncomingQueue", jsonBI);

        String jsonReceivedData = consumer.receiveBody(jmsComponentName + ":queue:testOutputQueue", String.class);
        return gson.fromJson(jsonReceivedData, String.class);

    }

    private void init() throws Exception {
        if (camelContext != null) return;
        JndiContext jndiContext = new JndiContext();
        camelContext = new DefaultCamelContext(jndiContext);
        camelContext.addComponent(jmsComponentName, JmsComponent.jmsComponent(this.connectionFactory));
        camelContext.start();
        producer = this.camelContext.createProducerTemplate();
        consumer = this.camelContext.createConsumerTemplate();
    }

    @Override
    public void close() throws JMSException {
        if (camelContext != null) {
            try {
                camelContext.stop();
            } catch (Exception e) {
                log.info("Error in ExchangerActiveMQ.init(): " + e.getMessage());
            }
        }
    }
}
