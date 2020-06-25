package com.weronika.dietly.client;

import com.weronika.dietly.entity.Message;

public interface ExternalClient {
    void sendMessage(Message message);
}
