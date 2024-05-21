package mk.ukim.finki.reservation_management.domain.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import mk.ukim.finki.reservation_management.domain.valueobjects.PaymentId;
import mk.ukim.finki.shared_kernel.domain.base.AbstractEntity;
import mk.ukim.finki.shared_kernel.domain.base.DomainObjectId;
import mk.ukim.finki.shared_kernel.domain.financial.Money;

@Entity
@Table(name = "reservation_item")
@Getter
public class ReservationItem extends AbstractEntity<ReservationItemId> {

    private Money reservationPrice;

    @Column(name = "status", nullable = false)
    private int status;

    @AttributeOverride(name = "id", column = @Column(name = "payment_id", nullable = false))
    private PaymentId paymentId;

    private ReservationItem() {
        super(DomainObjectId.randomId(ReservationItemId.class));
    }

    public ReservationItem(@NonNull PaymentId productId, @NonNull Money reservationPrice, int status) {
        super(DomainObjectId.randomId(ReservationItemId.class));
        this.paymentId = productId;
        this.reservationPrice = reservationPrice;
        this.status = status;
    }



    public Money subtotal() {
        return reservationPrice.multiply(status);
    }

}
