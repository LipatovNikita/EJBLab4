package packages.bean;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import packages.entity.Item;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.jms.*;

@Named
@RequestScoped
public class OrderBean {

    public String sendOrderInfo(Item item) {
        String orderInfo = String.format("Отправить: %s (ID%d)\nСо склада: %s\nПолучатель: %s",
                item.getName(), item.getId(), item.getWarehouse_address(), item.getCustomer().getAddress());
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                    ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("amqmessage");
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();
            message.setIntProperty("ID_order", Integer.valueOf(item.getId()));
            message.setText(orderInfo);
            producer.send(message);
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return "items";
    }
}
