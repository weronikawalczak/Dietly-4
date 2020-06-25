package com.weronika.dietly.client;

import com.weronika.dietly.entity.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MailChimpClient implements ExternalClient{

    @Value("${dietly.mailchimp.url:http://mailchimp/api}")
    private String mailChimpApiUrl;

    private final RestTemplate restTemplate;

    public MailChimpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendMessage(Message message) {
        restTemplate.postForObject(mailChimpApiUrl, message, Message.class);
    }
}
