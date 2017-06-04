package packages.bean;

import lombok.Data;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import packages.dao.ItemDao;
import packages.entity.Item;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jms.*;
import java.io.Serializable;

@Data
@Named
@SessionScoped
public class DeliveryBean implements Serializable {

    private String deliveryResponse;

    @EJB
    ItemDao itemDao;

    public String handleOrder(int id_item) {
        String status;
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                    ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("amqmessage");
            MessageConsumer consumer = session.createConsumer(destination);
            connection.start();
            TextMessage message = (TextMessage) consumer.receive();
            if (message.getIntProperty("ID_order") == id_item) {
                Item item = itemDao.getItemById(id_item);
                item.setClosed(true);
                itemDao.edit(item);
                status = "Доставка оформлена";
            }
            else {
                status = "Ошибка оформления";
            }
            deliveryResponse = String.format("Основная информация: %s\nСтатус: %s", message.getText(), status);
            connection.close();
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
        return "delivery";
    }
}
