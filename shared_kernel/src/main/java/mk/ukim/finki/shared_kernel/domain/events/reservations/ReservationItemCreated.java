package mk.ukim.finki.shared_kernel.domain.events.reservations;


import lombok.Getter;
import mk.ukim.finki.shared_kernel.domain.config.TopicHolder;
import mk.ukim.finki.shared_kernel.domain.events.DomainEvent;

@Getter
public class ReservationItemCreated extends DomainEvent {

    private String productId;
    private int quantity;

    public ReservationItemCreated(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
    }

    public ReservationItemCreated(String productId, int quantity) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
        this.productId = productId;
        this.quantity = quantity;
    }
}
