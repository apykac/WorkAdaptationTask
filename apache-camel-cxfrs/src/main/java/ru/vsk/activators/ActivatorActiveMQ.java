package ru.vsk.activators;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;
import ru.vsk.exchange.StaticData;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

public class ActivatorActiveMQ implements BundleActivator {
    private ConnectionFactory connectionFactory =
            new ActiveMQConnectionFactory("tcp://localhost:61616?jms.userName=karaf&jms.password=karaf");


    @Override
    public void start(BundleContext bundleContext) throws Exception {
        JndiContext jndiContext = new JndiContext();
        CamelContext camelContext = new DefaultCamelContext(jndiContext);
        camelContext.addComponent("activemq", jmsComponentAutoAcknowledge(connectionFactory));
        StaticData.putCamelContext(camelContext);
        StaticData.getCamelContext().start();
        System.out.println("REST BUNDLE START SUCCESS");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        StaticData.getCamelContext().stop();
        System.out.println("REST BUNDLE STOP SUCCESS");
    }

    private JmsComponent jmsComponentAutoAcknowledge(ConnectionFactory connectionFactory) {
        JmsConfiguration template = new JmsConfiguration(connectionFactory);
        template.setAcknowledgementMode(Session.AUTO_ACKNOWLEDGE);
        return new JmsComponent(template);
    }
}
