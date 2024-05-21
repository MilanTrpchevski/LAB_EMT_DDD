package mk.ukim.finki.reservation_management.domain.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Getter;
import mk.ukim.finki.reservation_management.domain.valueobjects.Payment;
import mk.ukim.finki.shared_kernel.domain.base.AbstractEntity;
import mk.ukim.finki.shared_kernel.domain.financial.Currency;
import mk.ukim.finki.shared_kernel.domain.financial.Money;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "reservations")
@Getter
public class Reservation extends AbstractEntity<ReservationId>{
    private Instant reservationOn;
    @Enumerated(EnumType.STRING)
    private ReservationState reservationState;

    @Column(name="order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ReservationItem> reservationItemList;
    private Reservation() {
        super(ReservationId.randomId(ReservationId.class));
    }

    public Reservation(Instant now, java.util.Currency currency){
        super(ReservationId.randomId(ReservationId.class));
    }
    public Reservation(Instant now, mk.ukim.finki.shared_kernel.domain.financial.Currency currency) {
        super(ReservationId.randomId(ReservationId.class));
        this.reservationOn = now;
        this.currency = currency;
    }

    public Money total() {
        return reservationItemList.stream().map(ReservationItem::subtotal).reduce(new Money(currency, 0), Money::add);
    }

    public ReservationItem addItem(@NonNull Payment payment, int qty) {
        Objects.requireNonNull(payment,"product must not be null");
        var item  = new ReservationItem(payment.getId(),payment.getPrice(),qty);
        reservationItemList.add(item);
        return item;
    }

    public void removeItem(@NonNull ReservationItemId orderItemId) {
        Objects.requireNonNull(orderItemId,"Order Item must not be null");
        reservationItemList.removeIf(v->v.getId().equals(orderItemId));
    }

}
