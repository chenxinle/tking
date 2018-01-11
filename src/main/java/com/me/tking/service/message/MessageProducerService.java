package com.me.tking.service.message;

public interface MessageProducerService {
    void sendMessage(final String message, String destinationName, String messageType);
}
