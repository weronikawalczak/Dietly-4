package com.weronika.dietly.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class RabbitMQConnector {
    private ConnectionFactory connectionFactory = new ConnectionFactory();
    private Channel channel;

    public void setDeliverCallback(DeliverCallback deliverCallback) {
        try {
            channel.basicConsume("Dietly", true, deliverCallback, consumerTag -> { });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() throws IOException, TimeoutException {
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare("Dietly", true, false, false, null);
    }
}
