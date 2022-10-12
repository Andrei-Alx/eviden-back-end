package nl.fontys.atosgame.kafkaproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private StreamBridge streamBridge;

    @PostMapping("/test/{integer}")
    public void test(@PathVariable("integer") Integer integer) {
        streamBridge.send("testFunc-in-0", integer);
    }
}
