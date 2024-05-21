package mk.ukim.finki.reservation_management.domain.valueobjects;

import jakarta.persistence.Embeddable;
import mk.ukim.finki.shared_kernel.domain.base.DomainObjectId;

@Embeddable
public class PaymentId extends DomainObjectId {
    private PaymentId() {
        super(PaymentId.randomId(PaymentId.class).getId());
    }
    public PaymentId(String uuid) {
        super(uuid);
    }
}
