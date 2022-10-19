package nl.fontys.atosgame.kafkaconsumer;

import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumer {

    @KafkaListener(topics = "NewTopic", groupId = "group_id")

    // Method
    public void
    consume(String message)
    {
        // Print statement
        System.out.println("message = " + message);
    }
}
