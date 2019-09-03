package com.forezp.test1;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
//https://www.liangzl.com/get-article-detail-12212.html
public class Test1 {





    @Test
    public void testConnection()
    {

        try {
            ConnectionFactory factory = new ConnectionFactory();

            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("/");
            factory.setHost("114.67.80.70");
            factory.setPort(5672);

            Connection conn = factory.newConnection();

            Channel channel = conn.createChannel();

            String exchangeName = "exchangename2";
            String queueName = "myqueuename";
            String routingKey = "myroutingkeky";
            channel.exchangeDeclare(exchangeName, "direct", true);
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, exchangeName, routingKey);


            byte[] messageBodyBytes = "Hello, world!".getBytes();
          //  channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);
            channel.basicPublish(exchangeName, routingKey,
                    new AMQP.BasicProperties.Builder()
                            .contentType("text/plain")
                            .deliveryMode(2)
                            .priority(1)
                            .userId("bob")
                            .build(),
                    messageBodyBytes);

           // AMQP.Queue.DeclareOk response = channel.queueDeclarePassive("myqueuename");
// returns the number of messages in Ready state in the queue
          //  System.out.println(response.getMessageCount());;
// returns the number of consumers the queue has
          //  System.out.println(response.getConsumerCount());



            //channel.queueDelete("myqueuename");


            Thread.sleep(99000);

            channel.close();

            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
