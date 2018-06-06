package writers;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.*;
import java.net.URISyntaxException;

public class JmsTopicProducer {
    //private final Logger JmsTopicLogger = LogManager.getLogger(JmsTopicProducer.class);

    public void sendTopic(String brokerUrl, String topicName, String message) throws URISyntaxException, Exception {

        Connection connection = null;
        try {
            ActiveMQConnectionFactory  connectionFactory = new ActiveMQConnectionFactory(
                    brokerUrl);

            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(topicName);

            connection.start();

            Message msg = session.createTextMessage(message);
            MessageProducer producer = session.createProducer(topic);
            producer.send(msg);
            //JmsTopicLogger.info("Topic published!");
            Thread.sleep(1000);
            session.close();
            //JmsTopicLogger.info("Session closed!");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
