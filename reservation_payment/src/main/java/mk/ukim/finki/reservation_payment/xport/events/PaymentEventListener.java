package mk.ukim.finki.reservation_payment.xport.events;


import lombok.AllArgsConstructor;
import mk.ukim.finki.reservation_payment.domain.models.PaymentId;
import mk.ukim.finki.reservation_payment.services.PaymentServices;
import mk.ukim.finki.shared_kernel.domain.config.TopicHolder;
import mk.ukim.finki.shared_kernel.domain.events.DomainEvent;
import mk.ukim.finki.shared_kernel.domain.events.reservations.ReservationItemCreated;
import mk.ukim.finki.shared_kernel.domain.events.reservations.ReservationItemRemoved;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentEventListener {

    private final PaymentServices paymentServices;

    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_CREATED, groupId = "productCatalog")
    public void consumeOrderItemCreatedEvent(String jsonMessage) {
        try {
            ReservationItemCreated event = DomainEvent.fromJson(jsonMessage, ReservationItemCreated.class);
            paymentServices.paymentItemCreated(PaymentId.of(event.getProductId()), event.getQuantity());
        } catch (Exception e){

        }

    }

    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_REMOVED, groupId = "productCatalog")
    public void consumeOrderItemRemovedEvent(String jsonMessage) {
        try {
            ReservationItemRemoved event = DomainEvent.fromJson(jsonMessage,ReservationItemRemoved.class);
            paymentServices.paymentItemRemoved(PaymentId.of(event.getProductId()), event.getQuantity());
        } catch (Exception e){

        }

    }
}
