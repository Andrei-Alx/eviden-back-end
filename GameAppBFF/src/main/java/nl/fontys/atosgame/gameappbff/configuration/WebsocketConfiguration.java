package nl.fontys.atosgame.gameappbff.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Register Websocket message broker: STOMP
 * @author Eli
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
            .addEndpoint("/websocket/gameappbff")
            // TODO: Not very secure, but for now it will do
            .setAllowedOrigins("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Prefix for endpoint that client will listen to
        registry.enableSimpleBroker("/socket/gameapp");

        // Prefix for endpoint that client will send to
        registry.setApplicationDestinationPrefixes("/socket/gameapp");
    }
}
