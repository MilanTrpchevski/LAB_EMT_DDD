package mk.ukim.finki.shared_kernel.domain.events.reservations;


import lombok.Getter;
import mk.ukim.finki.shared_kernel.domain.config.TopicHolder;
import mk.ukim.finki.shared_kernel.domain.events.DomainEvent;
@Getter
public class ReservationItemRemoved extends DomainEvent {

    private String productId;
    private int quantity;

    public ReservationItemRemoved(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
    }

    public ReservationItemRemoved(String topic, String productId, int quantity) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
        this.productId = productId;
        this.quantity = quantity;
    }
}
