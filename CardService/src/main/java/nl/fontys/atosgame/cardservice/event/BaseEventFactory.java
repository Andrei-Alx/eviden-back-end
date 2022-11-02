package nl.fontys.atosgame.cardservice.event;

/**
 * Factory class for creating BaseEvent objects
 * @author Eli
 */
public class BaseEventFactory {
    public static BaseEvent create(String type, String service, EventData data) {
        BaseEvent event = new BaseEvent();
        event.setTimestamp(java.time.LocalDateTime.now());
        event.setId(java.util.UUID.randomUUID());
        event.setType(type);
        event.setService(service);
        event.setData(data);
        return event;
    }
}
