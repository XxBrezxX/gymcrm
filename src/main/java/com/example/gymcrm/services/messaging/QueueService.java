package com.example.gymcrm.services.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.sqs.SqsClient;

@Service
public class QueueService {

    @Autowired
    private SqsClient sqsClient;

    @Value("${aws.sqs.queue.url}")
    private String queueUrl;

    public void sendMessage(String message) {
        sqsClient.sendMessage(sendMessageRequest -> sendMessageRequest.queueUrl(queueUrl)
                .messageBody(message));
    }

}