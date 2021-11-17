import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;

public class RabbitMq {

    public static void main(String[] args) throws IOException, InterruptedException, ConsumerCancelledException, ShutdownSignalException {
        // TODO Auto-generated method stub
        try {

            SAXReader reader = new SAXReader();
            Document config = reader.read("Config.xml");
            Element root = config.getRootElement();
            List<Element> tasks = root.elements();
            ArrayList<TaskInfo> taskInfos = new ArrayList<TaskInfo>();
            for (Element task : tasks) {
                TaskInfo info = new TaskInfo();
                info.setQueueName(task.elementText("queuename"));
                info.setTaskId(task.elementText("id"));
                info.setAutoDeleteQueue(task.elementText("autodeletequeue").equals("false") ? false : true);
                taskInfos.add(info);
            }
            System.out.println(taskInfos);

            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("192.168.1.17");
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("root");
            connectionFactory.setPassword("root");
            Connection conn = connectionFactory.newConnection();
            Channel channel = conn.createChannel();
            String queuename = "dc.mobile.hall.login";
            channel.queueBind(queuename, "ctbi.topic", "");

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(queuename, true, consumer);

            while (true) {
                Delivery delivery = consumer.nextDelivery();
                String mString = new String(delivery.getBody());
                System.out.println("receive message[" + mString + "] from " + queuename);
                ;
            }

        } catch (Exception e) {
            System.out.println("连接mq失败:" + e.getClass() + e.getMessage());
            // TODO: handle exception
        }
    }

}
