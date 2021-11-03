package com.sample.jms.activemq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer implements Runnable {

    Logger log = null;
    protected int timeToLive = 0;
    protected String destName = "TEST.QUEUE";
    protected int numMsgs = 1;
    protected int msgSize = 0;
    protected int sleepTime = 0;

    public Producer(int numMsgs, String dest, int ttl, int msgSize, int sleepTime) {
        log = LoggerFactory.getLogger(this.getClass());
        this.destName = dest;
        this.timeToLive = ttl;
        this.numMsgs = numMsgs;
        this.msgSize = msgSize;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {

        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;

        try {
            ActiveMQConnectionFactory amq = new ActiveMQConnectionFactory(AMQTemplateTest.BROKERURL);

            connection = amq.createConnection();
            connection.setExceptionListener(Throwable::printStackTrace);

            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(destName);
            producer = session.createProducer(destination);

            if (timeToLive > 0) {
                producer.setTimeToLive(timeToLive);
            }

            int size = msgSize * 1024;
            StringBuilder stringBuilder = new StringBuilder(msgSize);
            stringBuilder.setLength(msgSize);

            for (int i = 0; i < (size / 10); i++) {
                stringBuilder.append("XXXXXXXXXX");
            }

//            String text = stringBuilder.toString();
            TextMessage message = session.createTextMessage(stringBuilder.toString());

            for (int i = 0; i < numMsgs; i++) {
                log.debug("Sent message : " + i);
                producer.send(message);

                if ((1 % 1000) == 0) {
                    log.info("sent " + i + " messages");
                }

                Thread.sleep(sleepTime);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return;
        } finally {
            try {
                if (producer != null) {
                    producer.close();
                }
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                log.error("Problem closing down JMS objects: " + e.getMessage());
            }
        }
        log.info("Closing producer for " + destName);

    }

}
