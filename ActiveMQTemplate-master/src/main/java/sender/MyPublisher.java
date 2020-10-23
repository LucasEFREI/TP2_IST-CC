package sender;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;

public class MyPublisher {
    public static void main(String[] args) {
        try {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
            TopicConnectionFactory factory = (TopicConnectionFactory) applicationContext.getBean("connectionFactory");

            Topic topic = (Topic) applicationContext.getBean("topic");

            TopicConnection connection = factory.createTopicConnection();

            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            connection.start();

            TopicPublisher publisher = session.createPublisher(topic);

            TextMessage m = session.createTextMessage("un test encore");

            // Send the message
            publisher.send(m);


            // Close the session

            session.close();

            // Close the connection
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}