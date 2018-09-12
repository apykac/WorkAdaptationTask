package ru.vsk.activators;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import ru.vsk.Main;
import ru.vsk.exchenge.StaticData;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

public class ActivatorActiveMQ implements BundleActivator {
    private ConnectionFactory connectionFactory =
            new ActiveMQConnectionFactory("tcp://localhost:61616?jms.userName=karaf&jms.password=karaf");
    private Main mainThread = new Main();


    @Override
    public void start(BundleContext bundleContext) throws Exception {
        JndiContext jndiContext = new JndiContext();
        CamelContext camelContext = new DefaultCamelContext(jndiContext);
        camelContext.addComponent("activemq", jmsComponentAutoAcknowledge(connectionFactory));
        StaticData.putCamelContext(camelContext);
        StaticData.getCamelContext().start();
        mainThread.start();
        System.out.println("CALC BUNDLE START SUCCESS");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        mainThread.stopThread();
        StaticData.getCamelContext().stop();
        System.out.println("CALC BUNDLE STOP SUCCESS");
    }

    private JmsComponent jmsComponentAutoAcknowledge(ConnectionFactory connectionFactory) {
        JmsConfiguration template = new JmsConfiguration(connectionFactory);
        template.setAcknowledgementMode(Session.AUTO_ACKNOWLEDGE);
        return new JmsComponent(template);
    }
}
