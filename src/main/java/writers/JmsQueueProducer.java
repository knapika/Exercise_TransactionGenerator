package writers;

import org.apache.activemq.ActiveMQConnectionFactory;


import javax.jms.*;
import java.net.URISyntaxException;

public class JmsQueueProducer {
   // private final Logger JmsQueueLogger = LogManager.getLogger(JmsQueueProducer.class);

    public void sendQueue(String brokerUrl, String queueName, String message) throws URISyntaxException, Exception{
        Connection connection = null;
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                    brokerUrl);
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(queueName);

            Message msg = session.createTextMessage(message);
            MessageProducer producer = session.createProducer(queue);
            producer.send(msg);
            //JmsQueueLogger.info("Message sent");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
