package nl.fontys.atosgame.kafkaproducer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

@Controller
public class Config {

    @Bean
    public Function<Integer, Integer> testFunc() {
        return (input) -> {
            return input * 2;
        };
    }
}
