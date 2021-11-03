package com.sample.jms.activemq;

import java.util.concurrent.Callable;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer implements Callable<Boolean> {

    public Object init = new Object();
    protected String queueName = "";
    protected int numMsgs = 1;
    protected boolean isTopic = false;
    protected int sleepTime = 0;

    Logger log = null;

    public Consumer(String destName, int numMsgs, boolean topic, int sleepTime) {
        log = LoggerFactory.getLogger(this.getClass());
        this.isTopic = topic;
        this.numMsgs = numMsgs;
        this.queueName = destName;
        this.sleepTime = sleepTime;
    }

    @Override
    public Boolean call() throws Exception {
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;

        try {
            ActiveMQConnectionFactory amq = new ActiveMQConnectionFactory(AMQTemplateTest.BROKERURL);
            connection = amq.createConnection();

            connection.setExceptionListener(Throwable::printStackTrace);
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = null;

            if (isTopic) {
                destination = session.createTopic(queueName);
            } else {
                destination = session.createQueue(queueName);
            }

            consumer = session.createConsumer(destination);

            log.info("Consumer entering wait loop for messages");
            for (int i = 0; i < numMsgs; i++) {
                Message message = consumer.receive(3 * 1000);

                if (message == null) {
                    log.warn("I got starved and did not receive a message " + "for queue {} despite its queue size being {}", queueName, AMQTemplateTest.getQueueSize(queueName));
                    i--;
                } else if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText().substring(0, 10);
                    log.debug("Received message {} for {}", i, queueName);
                    log.debug("Received message {} ", text);
                } else {
                    log.warn("Received message of unsupported type. Expecting TextMessage. " + message);
                }
                Thread.sleep(sleepTime);
            }
            return true;
        } catch (InterruptedException | JMSException e) {
            log.error("Error in Consumer: " + e.getMessage());
            return false;
        } finally {
            try {
                if (consumer != null) {
                    consumer.close();
                }
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
                log.info("Consumer for dest {} finished.", queueName);
            } catch (JMSException ex) {
                log.error("Error closing down JMS objects: " + ex.getMessage());
            }
        }
    }
}
