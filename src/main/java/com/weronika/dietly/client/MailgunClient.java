package com.weronika.dietly.client;

import com.weronika.dietly.entity.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("MailgunClient")
public class MailgunClient implements ExternalClient{

    @Value("${dietly.mailgun.url:http://mailgun/api}")
    private String mailGunApiUrl;

    private final RestTemplate restTemplate;

    public MailgunClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendMessage(Message message) {
        restTemplate.postForObject(mailGunApiUrl, message, Message.class);
    }
}
