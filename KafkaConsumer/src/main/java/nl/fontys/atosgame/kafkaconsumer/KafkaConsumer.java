package nl.fontys.atosgame.kafkaconsumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class KafkaConsumer {

    /**
     * function to consume a string message and output it to the console
     * input topic: test3
     * output topic: -
     */
    @Bean
    public Consumer<String> consume()
    {
        return (message) -> System.out.println("message = " + message);
    }
}
